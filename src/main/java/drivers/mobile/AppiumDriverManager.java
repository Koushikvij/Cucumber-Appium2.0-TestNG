package drivers.mobile;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.net.URL;
import java.util.Set;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import utilities.common.Log;

public class AppiumDriverManager {
	protected static String propFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "java" + File.separator + "config" + File.separator + "mobile" + File.separator
			+ "appium" + File.separator + "appium.properties";
	protected static String androidCapabilitiesFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "java" + File.separator + "config" + File.separator + "mobile" + File.separator
			+ "appium" + File.separator + "AndroidDeviceCapabilities.xml";
	protected static String iOSCapabilitiesFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "test" + File.separator + "java" + File.separator + "config" + File.separator + "mobile" + File.separator
			+ "appium" + File.separator + "IOSDeviceCapabilities.xml";
	
	public static ThreadLocal<AndroidDriver> androidDriver = new ThreadLocal<>();
	public static ThreadLocal<IOSDriver> iosDriver = new ThreadLocal<>();
	public static ThreadLocal<AppiumDriver> appiumDriver = new ThreadLocal<>();
	public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
	protected static ThreadLocal<UiAutomator2Options> uiAutomator2Options = new ThreadLocal<UiAutomator2Options>();
	protected static ThreadLocal<XCUITestOptions> xcuitestOptions = new ThreadLocal<XCUITestOptions>();
	public static ThreadLocal<String> capabilityLocator = new ThreadLocal<>();

	protected static ThreadLocal<String> platformType = new ThreadLocal<>();
	protected static ThreadLocal<String> deviceType = new ThreadLocal<>();
	protected static ThreadLocal<String> deviceLocation = new ThreadLocal<>();
	protected static ThreadLocal<String> labURL = new ThreadLocal<>();
	protected static ThreadLocal<String> labUserName = new ThreadLocal<>();
	protected static ThreadLocal<String> labAccessKey = new ThreadLocal<>();
	protected static ThreadLocal<Boolean> privateDeviceOnly = new ThreadLocal<>();
	protected static ThreadLocal<Boolean> networkCapture = new ThreadLocal<>();
	
	protected static ThreadLocal<Boolean> responsiveApp = new ThreadLocal<>();
	protected static ThreadLocal<Boolean> nativeApp = new ThreadLocal<>();
	protected static ThreadLocal<Boolean> hybridApp = new ThreadLocal<>();
	protected static ThreadLocal<String> browserName = new ThreadLocal<>();
	protected static ThreadLocal<String> browserVersion = new ThreadLocal<>();
	
	protected static ThreadLocal<String> platformName = new ThreadLocal<>();
	protected static ThreadLocal<String> automationName = new ThreadLocal<>();
	protected static ThreadLocal<String> app = new ThreadLocal<>();
	protected static ThreadLocal<String> appPackage = new ThreadLocal<>();
	protected static ThreadLocal<String> appActivity = new ThreadLocal<>();
	protected static ThreadLocal<String> bundleID = new ThreadLocal<>();
	protected static ThreadLocal<String> locale = new ThreadLocal<>();
	protected static ThreadLocal<String> language = new ThreadLocal<>();
	protected static ThreadLocal<String> newCommandTimeout = new ThreadLocal<>();
	protected static ThreadLocal<Boolean> noReset = new ThreadLocal<>();
	protected static ThreadLocal<Boolean> autoGrantPermissions = new ThreadLocal<>();
	protected static ThreadLocal<Boolean> printPageSourceOnFindFailure = new ThreadLocal<>();
	protected static ThreadLocal<String> downloadFilepath = new ThreadLocal<>();
	protected static ThreadLocal<String> platformVersion = new ThreadLocal<>();
	protected static ThreadLocal<String> deviceName = new ThreadLocal<>();
	protected static ThreadLocal<String> udid = new ThreadLocal<>();
	protected static ThreadLocal<String> systemIP = new ThreadLocal<>();
	protected static ThreadLocal<String> systemPort = new ThreadLocal<>();
	protected static ThreadLocal<String> ip = new ThreadLocal<>();
	protected static ThreadLocal<String> port = new ThreadLocal<>();
	
	public static void setUpDriver() {
		try {
			AppiumPropertiesManager.loadStaticVariableValues();
			AppiumCapabilitiesManager.setExecutionModeAndCapabilities();
			createDriver();
			printSessionCapabilities();
		}
		catch (Exception e) {
			printSessionCapabilities();
			Log.error("Exception in setUpDriver method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in setUpDriver method - " + e.getMessage());
		}
	}
	
	private static void createDriver()
	{
		try
		{
			printSessionCapabilities();
			switch(platformName.get().toUpperCase().trim())
			{
			case "ANDROID":
				System.out.println(labURL.get());
				if (responsiveApp.get()) {
					WebDriver wd = new AndroidDriver(new URL(labURL.get()),uiAutomator2Options.get());
					setWebDriver(wd);
				} else if (nativeApp.get()) {
					AndroidDriver ad = new AndroidDriver(new URL(labURL.get()),uiAutomator2Options.get());
					setAndroidDriver(ad);
					setDriver(ad);
				} else if (hybridApp.get()) {
					WebDriver wd = new AndroidDriver(new URL(labURL.get()),uiAutomator2Options.get());
					setWebDriver(wd);
					AndroidDriver ad = new AndroidDriver(new URL(labURL.get()),uiAutomator2Options.get());
					setAndroidDriver(ad);
					setDriver(ad);
				}
				break;
			case "IOS":
				if (responsiveApp.get()) {
					WebDriver wd = new IOSDriver(new URL(labURL.get()),xcuitestOptions.get());
					setWebDriver(wd);
				} else if (nativeApp.get()) {
					IOSDriver ios = new IOSDriver(new URL(labURL.get()),xcuitestOptions.get());
					setIOSDriver(ios);
					setDriver(ios);
				} else if (hybridApp.get()) {
					WebDriver wd = new IOSDriver(new URL(labURL.get()),xcuitestOptions.get());
					setWebDriver(wd);
					IOSDriver ios = new IOSDriver(new URL(labURL.get()),xcuitestOptions.get());
					setIOSDriver(ios);
					setDriver(ios);
				}
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in createDriver method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in createDriver method - " + e.getMessage());
			e.printStackTrace();
		}
	}
		
	public static void setAndroidDriver(AndroidDriver driver) {
		try {
			androidDriver.set(driver);
		} catch (Exception e) {
			Log.error("Exception in setAndroidDriver method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in setAndroidDriver method - " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static AndroidDriver getAndroidDriver() {
		return androidDriver.get();
	}	
	
	public static void setDriver(AppiumDriver driver) {
		try {
			appiumDriver.set(driver);
		} catch (Exception e) {
			Log.error("Exception in setAppiumDriver method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in setAppiumDriver method - " + e.getMessage());
		}
	}
	
	public static AppiumDriver getDriver() {
		return appiumDriver.get();
	}	

	public static void setIOSDriver(IOSDriver driver) {
		try {
			iosDriver.set(driver);
		} catch (Exception e) {
			Log.error("Exception in setIOSDriver method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in setIOSDriver method - " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static IOSDriver getIOSDriver() {
		return iosDriver.get();
	}
	
	public static void setWebDriver(WebDriver driver) {
		try {
			webDriver.set(driver);
		} catch (Exception e) {
			Log.error("Exception in setWebDriver method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in setWebDriver method - " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static WebDriver getWebDriver() {
		return webDriver.get();
	}	
	
	public static void tearDown() {
		try {
			if (getWebDriver() != null) {
				getWebDriver().close();
				getWebDriver().quit();
			}
			if (getAndroidDriver() != null) {
				getAndroidDriver().close();
				getAndroidDriver().quit();
			}
			if (getIOSDriver() != null) {
				getIOSDriver().close();
				getIOSDriver().quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	private static void printSessionCapabilities()
	{
		String printText="**********SESSION CAPABILITIES**********";
		Set<String> deviceDetails;
		switch (platformType.get().toUpperCase().trim()) {
		case "ANDROID":
			deviceDetails = uiAutomator2Options.get().getCapabilityNames();
			for(String element:deviceDetails)
			{
				printText = printText + element + " = " + uiAutomator2Options.get().getCapability(element) + "|" ;
			}
			break;
		case "IOS":
			deviceDetails = xcuitestOptions.get().getCapabilityNames();
			for(String element:deviceDetails)
			{
				printText = printText + element + " = " + xcuitestOptions.get().getCapability(element) + "|" ;
			}
			break;
		}
		Log.info(printText);
	}
}
