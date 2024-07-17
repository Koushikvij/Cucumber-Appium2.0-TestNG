package drivers;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import drivers.mobile.AppiumDriverManager;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverManager {
	public final static int TIMEOUT = 0;
	//public static String screenshotBasePath = System.getProperty("user.dir") + File.separator + "screenshots";
	public static String downloadFilepath = System.getProperty("user.dir") + File.separator + "DownloadPath";
	//public static String folderPath;
	public static File outputFile;
	public static String autType="";
    public static String environment = "";
    public static String client = "";
    public static String browser = "";
    public static String version = "";
    public static String mode = "";
    public static String testOS = "";
    public static String buildNumber = "";
    public static String projectName = "";
    public static String baseurl = "";
    public static long implicitwaitduration = 0;
    public static String timeunit = "";

    public static void setUpAppiumDriver() {
		try {
			AppiumDriverManager.setUpDriver();
		} catch (Exception e) {
		}
	}
    	
	public static void mobiletearDown() {
		try {
			AppiumDriverManager.tearDown();
		} catch (Exception e) {
		}
	}
}