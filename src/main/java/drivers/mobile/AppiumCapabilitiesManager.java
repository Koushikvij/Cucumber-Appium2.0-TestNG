package drivers.mobile;

import java.time.Duration;

import org.openqa.selenium.MutableCapabilities;
import org.testng.Assert;

import dataHandlers.XMLHandler;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import utilities.common.Log;

public class AppiumCapabilitiesManager extends AppiumDriverManager {

	static void setExecutionModeAndCapabilities() {
		switch (deviceLocation.get().toUpperCase()) {
		case "LOCAL":
			setLocalCapabilities();
			break;
		case "SIMULATOR":
		case "EMULATOR":
			setSimulatorCapabilities();
			break;
		case "SAUCELABS":
			setSauceLabCapabilities();
			break;
		case "DEVICELAB":
			setDeviceLabCapabilities();
			break;
		default:
			setLocalCapabilities();
			break;
		}
	}

	private static void setLocalCapabilities() {
		try {
			UiAutomator2Options androidOptions=new UiAutomator2Options();
			XCUITestOptions iosOptions=new XCUITestOptions();
			switch (platformType.get().toUpperCase().trim()) {
			case "ANDROID":
				deviceName.set(readAndroidCapabilities(capabilityLocator.get(), "deviceName"));
				udid.set(readAndroidCapabilities(capabilityLocator.get(), "udid"));
				platformVersion.set(readAndroidCapabilities(capabilityLocator.get(), "platformVersion"));
				ip.set(readAndroidCapabilities(capabilityLocator.get(), "ip"));
				port.set(readAndroidCapabilities(capabilityLocator.get(), "port"));
				systemIP.set(readAndroidCapabilities(capabilityLocator.get(), "systemIP"));
				systemPort.set(readAndroidCapabilities(capabilityLocator.get(), "systemPort"));
				validateProperties("deviceName", deviceName.get());
				androidOptions.setDeviceName(deviceName.get());
				validateProperties("udid", udid.get());
				androidOptions.setUdid(udid.get());
				validateProperties("platformName", platformName.get());
				androidOptions.setPlatformName(platformName.get());
				validateProperties("platformVersion", platformVersion.get());
				androidOptions.setPlatformVersion(platformVersion.get());
				validateProperties("automationName", automationName.get());
				androidOptions.setAutomationName(automationName.get());
				validateProperties("noReset", noReset.get().toString());
				androidOptions.setNoReset(noReset.get());
				validateProperties("printPageSourceOnFindFailure", printPageSourceOnFindFailure.get().toString());
				androidOptions.setPrintPageSourceOnFindFailure(printPageSourceOnFindFailure.get());
				validateProperties("autoGrantPermissions", autoGrantPermissions.get().toString());
				androidOptions.setAutoGrantPermissions(autoGrantPermissions.get());
				validateProperties("newCommandTimeout", newCommandTimeout.get());
				androidOptions.setNewCommandTimeout(Duration.ofSeconds(Integer.parseInt(newCommandTimeout.get())));
				validateProperties("locale", locale.get());
				androidOptions.setLocale(locale.get());
				validateProperties("language", language.get());
				androidOptions.setLanguage(language.get());
				if (systemPort.get().length()>0)
					androidOptions.setSystemPort(Integer.parseInt(systemPort.get()));
				if (responsiveApp.get()) {
					validateProperties("browserName", browserName.get());
					androidOptions.withBrowserName(browserName.get());
				} else if (nativeApp.get()) {
					validateProperties("app", app.get());
					androidOptions.setApp(app.get());
					if (appPackage.get().length()>0) {
						validateProperties("appPackage", appPackage.get());
						androidOptions.setAppPackage(appPackage.get());
						validateProperties("appActivity", appActivity.get());
						androidOptions.setAppActivity(appActivity.get());
					}
				} else if (hybridApp.get()) {
					validateProperties("browserName", browserName.get());
					androidOptions.withBrowserName(browserName.get());
					validateProperties("app", app.get());
					androidOptions.setApp(app.get());
					if (appPackage.get().length()>0) {
						validateProperties("appPackage", appPackage.get());
						androidOptions.setAppPackage(appPackage.get());
						validateProperties("appActivity", appActivity.get());
						androidOptions.setAppActivity(appActivity.get());
					}
				}
				uiAutomator2Options.set(androidOptions);
				break;
				
			case "IOS":
				deviceName.set(readIOSCapabilities(capabilityLocator.get(), "deviceName"));
				udid.set(readIOSCapabilities(capabilityLocator.get(), "udid"));
				platformVersion.set(readIOSCapabilities(capabilityLocator.get(), "platformVersion"));
				ip.set(readIOSCapabilities(capabilityLocator.get(), "ip"));
				port.set(readIOSCapabilities(capabilityLocator.get(), "port"));
				systemIP.set(readIOSCapabilities(capabilityLocator.get(), "systemIP"));
				systemPort.set(readIOSCapabilities(capabilityLocator.get(), "systemPort"));
				validateProperties("deviceName", deviceName.get());
				iosOptions.setDeviceName(deviceName.get());
				validateProperties("udid", udid.get());
				iosOptions.setUdid(udid.get());
				validateProperties("platformName", platformName.get());
				iosOptions.setPlatformName(platformName.get());
				validateProperties("platformVersion", platformVersion.get());
				iosOptions.setPlatformVersion(platformVersion.get());
				validateProperties("automationName", automationName.get());
				iosOptions.setAutomationName(automationName.get());
				validateProperties("noReset", noReset.get().toString());
				iosOptions.setNoReset(noReset.get());
				validateProperties("printPageSourceOnFindFailure", printPageSourceOnFindFailure.get().toString());
				iosOptions.setPrintPageSourceOnFindFailure(printPageSourceOnFindFailure.get());
				validateProperties("newCommandTimeout", newCommandTimeout.get());
				iosOptions.setNewCommandTimeout(Duration.ofSeconds(Integer.parseInt(newCommandTimeout.get())));
				validateProperties("locale", locale.get());
				iosOptions.setLocale(locale.get());
				validateProperties("language", language.get());
				iosOptions.setLanguage(language.get());
				if (!systemPort.get().isBlank() || !systemPort.get().isEmpty())
					iosOptions.setWdaLocalPort(Integer.parseInt(systemPort.get()));
				if (responsiveApp.get()) {
					validateProperties("browserName", browserName.get());
					iosOptions.withBrowserName(browserName.get());
				} else if (nativeApp.get()) {
					validateProperties("app", app.get());
					iosOptions.setApp(app.get());
					if (bundleID.get()!=null || !bundleID.get().isBlank() || !bundleID.get().isEmpty()) {
						validateProperties("bundleID", bundleID.get());
						iosOptions.setBundleId(bundleID.get());
					}
				} else if (hybridApp.get()) {
					validateProperties("browserName", browserName.get());
					iosOptions.withBrowserName(browserName.get());
					validateProperties("app", app.get());
					iosOptions.setApp(app.get());
					if (bundleID.get()!=null || !bundleID.get().isBlank() || !bundleID.get().isEmpty()) {
						validateProperties("bundleID", bundleID.get());
						iosOptions.setBundleId(bundleID.get());
					}
				}
				xcuitestOptions.set(iosOptions);
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in setLocalCapabilities method - " + e.getMessage());
			Assert.assertTrue(false, "Exception occurred in setLocalCapabilities Method - " + e.getMessage());
		}
	}
	
	private static void setSimulatorCapabilities() {
		try {
			UiAutomator2Options androidOptions=new UiAutomator2Options();
			XCUITestOptions iosOptions=new XCUITestOptions();
			switch (platformType.get().toUpperCase().trim()) {
			case "ANDROID":
				deviceName.set(readAndroidCapabilities(capabilityLocator.get(), "deviceName"));
				validateProperties("deviceName", deviceName.get());
				androidOptions.setDeviceName(deviceName.get());
				validateProperties("platformName", "Android");
				androidOptions.setPlatformName(platformName.get());
				validateProperties("automationName", automationName.get());
				androidOptions.setAutomationName(automationName.get());
				if (responsiveApp.get()) {
					validateProperties("browserName", browserName.get());
					androidOptions.withBrowserName(browserName.get());
				} else if (nativeApp.get()) {
					validateProperties("app", app.get());
					androidOptions.setApp(app.get());
					if (appPackage.get().length()>0) {
						validateProperties("appPackage", appPackage.get());
						androidOptions.setAppPackage(appPackage.get());
						validateProperties("appActivity", appActivity.get());
						androidOptions.setAppActivity(appActivity.get());
					}
				} else if (hybridApp.get()) {
					validateProperties("browserName", browserName.get());
					androidOptions.withBrowserName(browserName.get());
					validateProperties("app", app.get());
					androidOptions.setApp(app.get());
					if (appPackage.get().length()>0) {
						validateProperties("appPackage", appPackage.get());
						androidOptions.setAppPackage(appPackage.get());
						validateProperties("appActivity", appActivity.get());
						androidOptions.setAppActivity(appActivity.get());
					}
				}
				uiAutomator2Options.set(androidOptions);
				System.out.println(uiAutomator2Options.get().toString());
				break;
				
			case "IOS":
				deviceName.set(readIOSCapabilities(capabilityLocator.get(), "deviceName"));
				validateProperties("deviceName", deviceName.get());
				iosOptions.setDeviceName(deviceName.get());
				validateProperties("platformName", platformName.get());
				iosOptions.setPlatformName(platformName.get());
				validateProperties("automationName", automationName.get());
				iosOptions.setAutomationName(automationName.get());
				if (responsiveApp.get()) {
					validateProperties("browserName", browserName.get());
					iosOptions.withBrowserName(browserName.get());
				} else if (nativeApp.get()) {
					validateProperties("app", app.get());
					iosOptions.setApp(app.get());
					if (bundleID.get()!=null || !bundleID.get().isBlank() || !bundleID.get().isEmpty()) {
						validateProperties("bundleID", bundleID.get());
						iosOptions.setBundleId(bundleID.get());
					}
				} else if (hybridApp.get()) {
					validateProperties("browserName", browserName.get());
					iosOptions.withBrowserName(browserName.get());
					validateProperties("app", app.get());
					iosOptions.setApp(app.get());
					if (bundleID.get()!=null || !bundleID.get().isBlank() || !bundleID.get().isEmpty()) {
						validateProperties("bundleID", bundleID.get());
						iosOptions.setBundleId(bundleID.get());
					}
				}
				xcuitestOptions.set(iosOptions);
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in setSimulatorCapabilities method - " + e.getMessage());
			Assert.assertTrue(false, "Exception occurred in setSimulatorCapabilities Method - " + e.getMessage());
		}
	}

	private static void setSauceLabCapabilities() {
		UiAutomator2Options androidOptions=new UiAutomator2Options();
		XCUITestOptions iosOptions=new XCUITestOptions();
		try {
			MutableCapabilities sauceOptions;
			setLocalCapabilities();
			validateProperties("Sauce Labs URL", labURL.get().toString());			
			switch(deviceType.get().toUpperCase().trim())
			{
			case "ANDROID":
				androidOptions = uiAutomator2Options.get();
				sauceOptions = new MutableCapabilities();
				sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
				sauceOptions.setCapability("sessionCreationTimeout", 300000);
				sauceOptions.setCapability("sessionCreationRetry", 2);
				validateProperties("networkCapture", networkCapture.get().toString());
				sauceOptions.setCapability("networkCapture", networkCapture.get());
				validateProperties("username", labUserName.get().toString());
				sauceOptions.setCapability("username", labUserName.get());
				validateProperties("accessKey", labAccessKey.get().toString());
				sauceOptions.setCapability("accessKey", labAccessKey.get());
				validateProperties("privateDeviceOnly", privateDeviceOnly.get().toString());
				sauceOptions.setCapability("privateDeviceOnly", privateDeviceOnly.get());				
				androidOptions.setCapability("sauce:options", sauceOptions);
				uiAutomator2Options.set(androidOptions);
				break;
			case "IPHONE":
				iosOptions = xcuitestOptions.get();
				sauceOptions = new MutableCapabilities();
				sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
				sauceOptions.setCapability("phoneOnly", true);
				sauceOptions.setCapability("sessionCreationTimeout", 300000);
				sauceOptions.setCapability("sessionCreationRetry", 2);
				validateProperties("networkCapture", networkCapture.get().toString());
				sauceOptions.setCapability("networkCapture", networkCapture.get());
				validateProperties("username", labUserName.get().toString());
				sauceOptions.setCapability("username", labUserName.get());
				validateProperties("accessKey", labAccessKey.get().toString());
				sauceOptions.setCapability("accessKey", labAccessKey.get());
				validateProperties("privateDeviceOnly", privateDeviceOnly.get().toString());
				sauceOptions.setCapability("privateDeviceOnly", privateDeviceOnly.get());				
				iosOptions.setCapability("sauce:options", sauceOptions);
				xcuitestOptions.set(iosOptions);
				break;
			case "IPAD":
				iosOptions = xcuitestOptions.get();
				sauceOptions = new MutableCapabilities();
				sauceOptions.setCapability("tabletOnly", true);
				sauceOptions.setCapability("deviceOrientation", "PORTRAIT");
				sauceOptions.setCapability("sessionCreationTimeout", 300000);
				sauceOptions.setCapability("sessionCreationRetry", 2);
				validateProperties("networkCapture", networkCapture.get().toString());
				sauceOptions.setCapability("networkCapture", networkCapture.get());
				validateProperties("username", labUserName.get().toString());
				sauceOptions.setCapability("username", labUserName.get());
				validateProperties("accessKey", labAccessKey.get().toString());
				sauceOptions.setCapability("accessKey", labAccessKey.get());
				validateProperties("privateDeviceOnly", privateDeviceOnly.get().toString());
				sauceOptions.setCapability("privateDeviceOnly", privateDeviceOnly.get());				
				iosOptions.setCapability("sauce:options", sauceOptions);
				xcuitestOptions.set(iosOptions);
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in setSauceLabCapabilities method - " + e.getMessage());
			Assert.assertTrue(false, "Exception occurred in setSauceLabCapabilities Method - " + e.getMessage());
		}
	}

	private static void setDeviceLabCapabilities() {
		try {
			setLocalCapabilities();
			validateProperties("Device Labs URL", labURL.get().toString());	
		} catch (Exception e) {
			Log.error("Exception in setDeviceLabCapabilities method - " + e.getMessage());
			Assert.assertTrue(false, "Exception occurred in setDeviceLabCapabilities Method - " + e.getMessage());
		}
	}

	private static String readAndroidCapabilities(String root, String tag) {
		return XMLHandler.readXMLValues(androidCapabilitiesFilePath, root, tag);
	}

	private static String readIOSCapabilities(String root, String tag) {
		return XMLHandler.readXMLValues(iOSCapabilitiesFilePath, root, tag);
	}

	private static void validateProperties(String propertyName, String value) {
		try {
			if (value.length()<=0) {
				Log.debug("Validation of " + propertyName + " failed. Value is either null, empty or uninitialized");
			}
			else
				Log.debug("Validation of " + propertyName + " successful. Value = "+ value);
		} catch (Exception e) {
			Log.error("Exception in validateProperties method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in validateProperties method - " + e.getMessage());
		}
	}
}
