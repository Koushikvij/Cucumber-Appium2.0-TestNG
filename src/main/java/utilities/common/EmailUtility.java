package utilities.common;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FromStringTerm;
import javax.mail.search.RecipientStringTerm;
import javax.mail.search.SubjectTerm;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This utility can be used to send & retrieve mail over SMTP/IMAP/POP3 server
 * It can be used for gmail, Outlook, yahoo,etc..
 *
 * @author kunal kaviraj
 *
 */
public class EmailUtility
{
    private Folder folder;


    @Test
    public void CheckEmail()
    {
        EmailUtility emailUtility = new EmailUtility(EmailFolder.INBOX);
        int c = emailUtility.getNumberOfMessages();
        c = emailUtility.getNumberOfUnreadMessages();
    }

    public enum EmailFolder
    {
        INBOX("INBOX"), SPAM("SPAM");

        private String text;

        private EmailFolder(String text)
        {
            this.text = text;
        }

        public String getText()
        {
            return text;
        }
    }

    /**
     * Connects to email server with credentials provided to read from a given
     * folder of the email application
     *
     * @param emailFolder Folder in email application to interact with
     */
    public EmailUtility(EmailFolder emailFolder)
    {
        try
        {
            Properties props = System.getProperties();
            try
            {
                String filePath = System.getProperty("user.dir") + "/src/test/resources/config/email.properties";
                props.load(new FileInputStream(filePath));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                System.exit(-1);
            }

            Session session = Session.getInstance(props);
            Store store = session.getStore("imaps");
            store.connect(
                    props.getProperty("email.server"), props.getProperty("email.username"),
                    props.getProperty("email.password")
            );
            folder = store.getFolder(emailFolder.getText());
            folder.open(Folder.READ_WRITE);
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    // ************* EMAIL ACTIONS *******************

    /**
     *
     * @param message
     */
    public void openEmail(Message message)
    {
        try
        {
            message.getContent();
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
    }

    /**
     *
     * @return
     */
    public int getNumberOfMessages()
    {
        try
        {
            return folder.getMessageCount();
        }
        catch (MessagingException e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return 0;
    }

    /**
     *
     * @return
     */
    public int getNumberOfUnreadMessages()
    {
        try
        {
            return folder.getUnreadMessageCount();
        }
        catch (MessagingException e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return 0;
    }

    /**
     * Gets a message by its position in the folder. The earliest message is indexed
     * at 1.
     */
    public Message getMessageByIndex(int index)
    {
        try
        {
            return folder.getMessage(index);
        }
        catch (MessagingException e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     *
     * @return
     */
    public Message getLatestMessage()
    {
        return getMessageByIndex(getNumberOfMessages());
    }

    /**
     * Gets all messages within the folder
     */
    public Message[] getAllMessages()
    {
        try
        {
            return folder.getMessages();
        }
        catch (MessagingException e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * @param maxToGet maximum number of messages to get, starting from the latest.
     *                 For example, enter 100 to get the last 100 messages received.
     */
    public Message[] getMessages(int maxToGet)
    {
        try
        {
            Map<String, Integer> indices = getStartAndEndIndices(maxToGet);
            return folder.getMessages(indices.get("startIndex"), indices.get("endIndex"));
        }
        catch (MessagingException e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * Searches for messages with a specific subject
     *
     * @param subject     Subject to search messages for
     * @param unreadOnly  Indicate whether to only return matched messages that are
     *                    unread
     * @param maxToSearch maximum number of messages to search, starting from the
     *                    latest. For example, enter 100 to search through the last
     *                    100 messages.
     */
    public Message[] getMessagesBySubject(String subject, boolean unreadOnly, int maxToSearch)
    {
        try
        {
            Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);

            Message messages[] = folder.search( new SubjectTerm(subject), folder.getMessages(indices.get("startIndex"),
                                 indices.get("endIndex")));

            if(unreadOnly)
            {
                List<Message> unreadMessages = new ArrayList<>();
                for (Message message : messages)
                {
                    if(isMessageUnread(message))
                    {
                        unreadMessages.add(message);
                    }
                }
                messages = unreadMessages.toArray(new Message[] {});
            }
            return messages;
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     *
     * @param senderEmail
     * @param unreadOnly
     * @param maxToSearch
     * @return
     */

    public Message[] getMessagesBySender(String senderEmail, boolean unreadOnly, int maxToSearch)
    {
        try
        {
            Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);

            Message[] messages = folder.search( new FromStringTerm(senderEmail),
                    folder.getMessages(indices.get("startIndex"), indices.get("endIndex")));

            if(unreadOnly)
            {
                List<Message> unreadMessages = new ArrayList<>();
                for (Message message : messages)
                {
                    if(isMessageUnread(message))
                    {
                        unreadMessages.add(message);
                    }
                }
                messages = unreadMessages.toArray(new Message[] {});
            }
            return messages;
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * Get messages by Subject and Sender bases on Read/Unread flag
     * @param subject
     * @param senderEmail
     * @param unreadOnly
     * @param maxToSearch
     * @return Message[]
     */
    public Message[] getMessagesBySubjectAndSender(String subject, String senderEmail, boolean unreadOnly, int maxToSearch)
    {
        Message[] messages = null;
        try
        {
            Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);
            messages = folder.search(new FromStringTerm(senderEmail),folder.getMessages(indices.get("startIndex"), indices.get("endIndex")));

            if(unreadOnly)
            {
                List<Message> unreadMessages = new ArrayList<Message>();
                for (Message message : messages)
                {
                    if(isMessageUnread(message))
                    {
                        unreadMessages.add(message);
                    }
                }
                messages = unreadMessages.toArray(new Message[] {});
            }

            List<Message> messagesWithSubject = new ArrayList<>();
            for (Message message : messages)
            {
                String messageSubject = message.getSubject();
                if(messageSubject.contains(subject))
                {
                    messagesWithSubject.add(message);
                }
            }
            messages = messagesWithSubject.toArray(new Message[] {});
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return messages;
    }

    /**
     * Gets all the messages by given Subject & recieverEmail
     * @param subject
     * @param recieverEmail
     * @param unreadOnly
     * @param maxToSearch
     * @return Message[]
     */
    public Message[] getMessagesBySubjectAndReciever(String subject, String recieverEmail, boolean unreadOnly, int maxToSearch, Boolean... deleteAfterRead)
    {
        Message[] messages = null;
        try
        {
            Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);
            messages = folder.search(new RecipientStringTerm(Message.RecipientType.TO,recieverEmail),folder.getMessages(indices.get("startIndex"), indices.get("endIndex")));

            if(unreadOnly)
            {
                List<Message> unreadMessages = new ArrayList<>();
                for (Message message : messages)
                {
                    if(isMessageUnread(message))
                    {
                        unreadMessages.add(message);
                    }
                }
                messages = unreadMessages.toArray(new Message[] {});
            }

            List<Message> messagesWithSubject = new ArrayList<>();
            for (Message message : messages)
            {
                String messageSubject = message.getSubject();
                if(messageSubject.contains(subject))
                {
                    messagesWithSubject.add(message);
                    message.setFlag(Flags.Flag.SEEN,true);
                }
            }
            messages = messagesWithSubject.toArray(new Message[] {});

        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return messages;
    }

    /**
     *
     * @param Subject
     * @param recieverEmail
     */
    public void deleteMessagesBySubjectAndReciever(String Subject, String recieverEmail)
    {
        try
        {
            Message[] msg = getMessagesBySubjectAndReciever(Subject,recieverEmail,false,1000);

            for (int i = 0; i < msg.length; i++)
            {
                Message message = msg[i];
                String subject = message.getSubject();
                if(subject.contains(Subject))
                {
                    message.setFlag(Flags.Flag.DELETED, true);
                    System.out.println("Marked DELETE for message: " + subject);
                }
            }
            folder.expunge();
        }
        catch (NoSuchProviderException ex)
        {
            Log.error(ExceptionUtils.getStackTrace(ex));
        }
        catch (MessagingException ex)
        {
            Log.error("Could not connect to the message store.");
            ex.printStackTrace();
        }
    }

    /**
     *
     * @param messages
     * @param textToVerify
     */
    public void verifyTextInMessage(Message[] messages, String textToVerify)
    {
        boolean textVerified = false;
        for (Message message : messages)
        {
            textVerified = isTextInMessage(message, textToVerify);
            if(textVerified)
            {
                openEmail(message);
                break;
            }
        }
        Assert.assertTrue(textVerified, "Message does not contain text '" + textToVerify + "'");
    }

    /**
     * Returns HTML of the email's content
     */
    public String getMessageContent(Message message)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(message.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null)
            {
                builder.append(line);
            }
            return builder.toString();
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     *
     * @param message
     * @return
     */
    public String getMessageText(Message message)
    {
        try
        {
            String result = "";
            if(message.isMimeType("text/plain"))
            {
                result = message.getContent().toString();
            }
            else if(message.isMimeType("text/html"))
            {
                String html = (String) message.getContent();
//                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            }
            else if(message.isMimeType("multipart/*"))
            {
                MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
                result = getTextFromMimeMultipart(mimeMultipart);
            }
            return result;
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     *
     * @param mimeMultipart
     * @return
     */
    private String getTextFromMimeMultipart(MimeMultipart mimeMultipart)
    {
        try
        {
            String result = "";
            int count = mimeMultipart.getCount();
            for (int i = 0; i < count; i++)
            {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                if(bodyPart.isMimeType("text/plain"))
                {
                    result = result + "\n" + bodyPart.getContent();
                    break; // without break same text appears twice in my tests
                }
                else if(bodyPart.isMimeType("text/html"))
                {
                    String html = (String) bodyPart.getContent();
//                    result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
                }
                else if(bodyPart.getContent() instanceof MimeMultipart)
                {
                    result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
                }
            }
            return result;
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }

    /**
     * Returns all urls from an email message with the linkText specified
     */
    public List<String> getUrlsFromMessage(Message message, boolean isUrlInText, String regexPattern)
    {
        List<String> allMatches = new ArrayList<String>();
        String content;

        if(isUrlInText)
            content = getMessageText(message);
        else
            content = getMessageContent(message);

        Matcher matcher = Pattern.compile(regexPattern,Pattern.MULTILINE).matcher(content);

        while (matcher.find())
        {
            allMatches.add(matcher.group(0));
        }
        return allMatches;
    }

    /**
     *
     * @param max
     * @return
     */
    private Map<String, Integer> getStartAndEndIndices(int max)
    {
        int endIndex = getNumberOfMessages();
        int startIndex = endIndex - max;

        // In event that maxToGet is greater than number of messages that exist
        if(startIndex < 1)
            startIndex = 1;

        Map<String, Integer> indices = new HashMap<String, Integer>();
        indices.put("startIndex", startIndex);
        indices.put("endIndex", endIndex);
        return indices;
    }

    // ************* BOOLEAN METHODS *******************

    /**
     * Searches an email message for a specific string
     */
    public boolean isTextInMessage(Message message, String text)
    {
        try
        {
            String content = getMessageText(message);
            // Some Strings within the email have whitespace and some have break coding.
            // Need to be the same.
            content = content.replace("&nbsp;", " ");
            return content.contains(text);
        }
        catch (Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    /**
     *
     * @param subject
     * @param unreadOnly
     * @return
     */
    public boolean isMessageInFolder(String subject, boolean unreadOnly)
    {
        int messagesFound = getMessagesBySubject(subject, unreadOnly, getNumberOfMessages()).length;
        return messagesFound > 0;
    }

    /**
     *
     * @param message
     * @return
     * @throws Exception
     */
    public boolean isMessageUnread(Message message) throws Exception
    {
        return !message.isSet(Flags.Flag.SEEN);
    }

}