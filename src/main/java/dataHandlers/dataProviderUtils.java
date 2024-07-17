package dataHandlers;

import org.testng.annotations.DataProvider;
import constants.BrowserType;
import constants.EnvironmentType;

public class dataProviderUtils
{
    public BrowserType getBrowser()  {
        String browserName = ConfigProperties.getPropertyValue("browser");

        switch (browserName) {
            case "chrome":
                return BrowserType.CHROME;
            case "firefox":
                return BrowserType.FIREFOX;
            case "edge":
                return BrowserType.EDGE;
            case "safari":
                return BrowserType.SAFARI;
            default:
                throw new RuntimeException("Browser name key value in configuration file is not matched: " + browserName);
        }
    }

    public EnvironmentType getEnvironment() {
        String environmentName = ConfigProperties.getPropertyValue("environment");

        switch (environmentName) {
            case "local":
                return EnvironmentType.LOCAL;
            case "remote":
                return EnvironmentType.REMOTE;
            default:
                throw new RuntimeException("Environment type key value in configuration file is not matched: " + environmentName);
        }
    }
    
    @DataProvider(name = "HopeMember_Plans")
    public static Object[][] getData()
    {
        return new Object[][]
        {
                {"HOPE Premium 5000", true, false, false, "Credit Card"},
//                {"HOPE Premium 5000", true, true, false, "Credit Card"},
//                {"HOPE Premium 5000", true, true, true, "Credit Card"},
//                {"HOPE Premium 5000", true, false, false, "Debit Card"},
//                {"HOPE Premium 5000", true, true, false, "Debit Card"},
//                {"HOPE Premium 5000", true, true, true, "Debit Card"},
//                {"HOPE Premium 5000", true, false, false, "Bank ACH"},
//                {"HOPE Premium 5000", true, true, false, "Bank ACH"},
//                {"HOPE Premium 5000", true, true, true, "Bank ACH"},
//                {"HOPE Premium 7500", true, false, false, "Credit Card"},
//                {"HOPE Premium 7500", true, true, false, "Credit Card"},
//                {"HOPE Premium 7500", true, true, true, "Credit Card"},
//                {"HOPE Premium 7500", true, false, false, "Debit Card"},
//                {"HOPE Premium 7500", true, true, false, "Debit Card"},
//                {"HOPE Premium 7500", true, true, true, "Debit Card"},
//                {"HOPE Premium 7500", true, false, false, "Bank ACH"},
//                {"HOPE Premium 7500", true, true, false, "Bank ACH"},
//                {"HOPE Premium 7500", true, true, true, "Bank ACH"},
//                {"HOPE Premium 10000", true, false, false, "Credit Card"},
//                {"HOPE Premium 10000", true, true, false, "Credit Card"},
//                {"HOPE Premium 10000", true, true, true, "Credit Card"},
//                {"HOPE Premium 10000", true, false, false, "Debit Card"},
//                {"HOPE Premium 10000", true, true, false, "Debit Card"},
//                {"HOPE Premium 10000", true, true, true, "Debit Card"},
//                {"HOPE Premium 10000", true, false, false, "Bank ACH"},
//                {"HOPE Premium 10000", true, true, false, "Bank ACH"},
//                {"HOPE Premium 10000", true, true, true, "Bank ACH"},
//                {"HOPE Plus", true, false, false, "Credit Card"},
//                {"HOPE Plus", true, true, false, "Credit Card"},
//                {"HOPE Plus", true, true, true, "Credit Card"},
//                {"HOPE Plus", true, false, false, "Debit Card"},
//                {"HOPE Plus", true, true, false, "Debit Card"},
//                {"HOPE Plus", true, true, true, "Debit Card"},
//                {"HOPE Plus", true, false, false, "Bank ACH"},
//                {"HOPE Plus", true, true, false, "Bank ACH"},
//                {"HOPE Plus", true, true, true, "Bank ACH"}
        };
    }
    @DataProvider(name = "JoppaMember_Plans")
    public static Object[][] getDatajoppa()
    {
        return new Object[][]
                {
//                        {"Ambassador Program option","Ambassador","Core", true, false, false, "Credit Card"},
//                        {"Ambassador Program option","Sample Ambassador","Core", true, false, false, "Credit Card"},
//                        {"Ambassador Program option","Ambassador Advantage","$10k MRA", true, false, false, "Credit Card"},
//                          {"Ambassador Program option","Ambassador Advantage","$7500 MRA", true, false, false, "Credit Card"},
//                        {"Ambassador Program option","Ambassador Advantage","$5k MRA", true, false, false, "Credit Card"},
                        {"Ambassador Program option","Ambassador Plus","$25k Sharing Max", true, false, false, "Credit Card"},
//                        {"Ambassador Program option","Ambassador Plus","$50k Sharing Max", true, false, false, "Credit Card"},
//                        {"Ambassador Program option","Ambassador Plus","$75k Sharing Max", true, false, false, "Credit Card"},
//                        {"Genesis Program option", "Basic",1,true, false, false, "Credit Card"},
//                        {"Genesis Program option", "Preferred",2,true, false, false, "Credit Card"},
//                        {"Genesis Program option", "Premium",3,true, false, false, "Credit Card"},
//                        {"Genesis Program option", "Sample Premium",3,true, false, false, "Credit Card"},
//                        {"Genesis Program option", "Platinum",3,true, false, false, "Credit Card"},
//                        {"Genesis Program option", "Sample Platinum",4,true, false, false, "Credit Card"}
                };
    }
}
