package drivers.mobile;

import java.io.File;

import org.testng.Assert;

import dataHandlers.ConfigProperties;
import utilities.common.Log;

public class AppiumPropertiesManager extends AppiumDriverManager {

	static void loadStaticVariableValues() {
		try {
			Log.info("Loading all static variables...");
			setExecutionDetails();
			setLabDetails();
			setDeviceProperties();
		} catch (Exception e) {
			Assert.assertTrue(false, "Exception in loadStaticVariableValues method - " + e.getMessage());
		}
	}

	private static void setExecutionDetails() {
		setplatformType();
		setdeviceType();
		setdeviceLocation();
		setresponsiveApp();
		setnativeApp();
		sethybridApp();
		setplatformType();
		setbrowserName();
	}

	private static void setLabDetails() {
		labURL(deviceLocation.get().toLowerCase().trim());
		labUserName(deviceLocation.get().toLowerCase().trim());
		labAccessKey(deviceLocation.get().toLowerCase().trim());
		labNetWorkCapture(deviceLocation.get().toLowerCase().trim());
		labPrivateDeviceOnly(deviceLocation.get().toLowerCase().trim());
	}

	private static void setDeviceProperties() {
		if (platformType.get().toLowerCase().trim().equals("android")) {
			setplatformName();
			setautomationName();
			setapp();
			setappPackage();
			setappActivity();
			setlocale();
			setlanguage();
			setnoReset();
			setnewCommandTimeout();
			setautoGrantPermissions();
			setprintPageSourceOnFindFailure();
			setdownloadFilepath();
		} else if (platformType.get().toLowerCase().trim().equals("ios")) {
			if (deviceType.get().toLowerCase().trim().equals("iphone")) {
				setplatformName();
				setautomationName();
				setapp();
				setlocale();
				setlanguage();
				setnoReset();
				setnewCommandTimeout();
				setautoGrantPermissions();
				setprintPageSourceOnFindFailure();
				setdownloadFilepath();
				setbundleID();
			} else if (deviceType.get().toLowerCase().trim().equals("ipad")) {
				setplatformName();
				setautomationName();
				setapp();
				setlocale();
				setlanguage();
				setnoReset();
				setnewCommandTimeout();
				setautoGrantPermissions();
				setprintPageSourceOnFindFailure();
				setdownloadFilepath();
				setbundleID();
			}
		}
	}

	private static void labURL(String labType) {
		String laburl = ConfigProperties.getProperty(propFilePath, labType + ".url");
		if (laburl==null || laburl.trim().isBlank() || laburl.trim().isEmpty())
			labURL.set("");
		else
			labURL.set(laburl);
		Log.debug("Loaded laburl as " + labURL.get());
	}

	private static void labUserName(String labType) {
		String labusername = ConfigProperties.getProperty(propFilePath, labType + ".username");
		if (labusername==null || labusername.trim().isBlank() || labusername.trim().isEmpty())
			labUserName.set("");
		else
			labUserName.set(labusername);
		Log.debug("Loaded labUserName as " + labUserName.get());
	}

	private static void labAccessKey(String labType) {
		String labaccessKey = ConfigProperties.getProperty(propFilePath, labType + ".accessKey");
		if (labaccessKey==null || labaccessKey.trim().isBlank() || labaccessKey.trim().isEmpty())
			labAccessKey.set("");
		else
			labAccessKey.set(labaccessKey);
		Log.debug("Loaded labAccessKey as " + labAccessKey.get());
	}

	private static void labPrivateDeviceOnly(String labType) {
		String strprivateDeviceOnly = ConfigProperties.getProperty(propFilePath, labType + ".privateDeviceOnly");
		if (strprivateDeviceOnly!=null && strprivateDeviceOnly.toLowerCase().trim().equals("true"))
			privateDeviceOnly.set(true);
		else
			privateDeviceOnly.set(false);
		Log.debug("Loaded privateDeviceOnly as " + privateDeviceOnly.get());
	}

	private static void labNetWorkCapture(String labType) {
		String strnetworkCapture = ConfigProperties.getProperty(propFilePath, labType + ".networkCapture");
		if (strnetworkCapture!=null && strnetworkCapture.toLowerCase().trim().equals("true"))
			networkCapture.set(true);
		else
			networkCapture.set(false);
		Log.debug("Loaded networkCapture as " + networkCapture.get());
	}

	private static void setplatformType() {
		String strplatformType = ConfigProperties.getProperty(propFilePath, "platformType");
		if (strplatformType==null || strplatformType.trim().isBlank() || strplatformType.trim().isEmpty())
			platformType.set("");
		else
			platformType.set(strplatformType);
		Log.debug("Loaded platformType as " + platformType.get());
	}

	private static void setbrowserName() {
		String strbrowserName = ConfigProperties.getProperty(propFilePath, platformType.get().toLowerCase().trim() + "browserName");
		if (strbrowserName==null || strbrowserName.trim().isBlank() || strbrowserName.trim().isEmpty())
			browserName.set("");
		else
			browserName.set(strbrowserName);
		Log.debug("Loaded browserName as " + browserName.get());
	}

	private static void setdeviceType() {
		String strdeviceType = ConfigProperties.getProperty(propFilePath, "deviceType");
		if (strdeviceType==null || strdeviceType.trim().isBlank() || strdeviceType.trim().isEmpty())
			deviceType.set("");
		else
			deviceType.set(strdeviceType);
		Log.debug("Loaded deviceType as " + deviceType.get());
	}

	private static void setdeviceLocation() {
		String strdeviceLocation = ConfigProperties.getProperty(propFilePath, "deviceLocation");
		if (strdeviceLocation==null || strdeviceLocation.trim().isBlank() || strdeviceLocation.trim().isEmpty())
			deviceLocation.set("");
		else
			deviceLocation.set(strdeviceLocation);
		Log.debug("Loaded deviceLocation as " + deviceLocation.get());
	}

	private static void setresponsiveApp() {
		String strresponsiveApp = ConfigProperties.getProperty(propFilePath, "applicationtype.responsive");
		if (strresponsiveApp!=null && strresponsiveApp.toLowerCase().trim().equals("true"))
			responsiveApp.set(true);
		else
			responsiveApp.set(false);
		Log.debug("Loaded responsiveApp as " + responsiveApp.get());
	}

	private static void setnativeApp() {
		String strnativeApp = ConfigProperties.getProperty(propFilePath, "applicationtype.native");
		if (strnativeApp!=null && strnativeApp.toLowerCase().trim().equals("true"))
			nativeApp.set(true);
		else
			nativeApp.set(false);
		Log.debug("Loaded nativeApp as " + nativeApp.get());
	}

	private static void sethybridApp() {
		String strhybridApp = ConfigProperties.getProperty(propFilePath, "applicationtype.hybrid");
		if (strhybridApp!=null && strhybridApp.toLowerCase().trim().equals("true"))
			hybridApp.set(true);
		else
			hybridApp.set(false);
		Log.debug("Loaded hybridApp as " + hybridApp.get());
	}

	private static void setplatformName() {
		String strplatformName = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".platformName").trim();
		if (strplatformName==null || strplatformName.trim().isBlank() || strplatformName.trim().isEmpty())
			platformName.set("");
		else
			platformName.set(strplatformName);
		Log.debug("Loaded platformName as " + platformName.get());
	}

	private static void setautomationName() {
		String strautomationName = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".automationName").trim();
		if (strautomationName==null || strautomationName.trim().isBlank() || strautomationName.trim().isEmpty())
			automationName.set("");
		else
			automationName.set(strautomationName);
		Log.debug("Loaded automationName as " + automationName.get());
	}

	private static void setapp() {
		String strapp = ConfigProperties.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".app")
				.trim();
		String strappLocation = System.getProperty("user.dir") + File.separator + "src" + File.separator+ "test" + File.separator + "resources"
		                + File.separator + "apps" + File.separator;
		if (strapp==null || strapp.trim().isBlank() || strapp.trim().isEmpty())
			app.set("");
		else
			app.set(strappLocation+strapp);
		Log.debug("Loaded app as " + app.get());
	}

	private static void setappPackage() {
		String strappPackage = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".appPackage").trim();
		if (strappPackage==null || strappPackage.trim().isBlank() || strappPackage.trim().isEmpty())
			appPackage.set("");
		else
			appPackage.set(strappPackage);
		Log.debug("Loaded appPackage as " + appPackage.get());
	}

	private static void setappActivity() {
		String strappActivity = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".appActivity").trim();
		if (strappActivity==null || strappActivity.trim().isBlank() || strappActivity.trim().isEmpty())
			appActivity.set("");
		else
			appActivity.set(strappActivity);
		Log.debug("Loaded appActivity as " + appActivity.get());
	}

	private static void setlocale() {
		String strlocale = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".locale").trim();
		if (strlocale==null || strlocale.trim().isBlank() || strlocale.trim().isEmpty())
			locale.set("");
		else
			locale.set(strlocale);
		Log.debug("Loaded locale as " + locale.get());
	}

	private static void setlanguage() {
		String strlanguage = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".language").trim();
		if (strlanguage==null || strlanguage.trim().isBlank() || strlanguage.trim().isEmpty())
			language.set("");
		else
			language.set(strlanguage);
		Log.debug("Loaded language as " + language.get());
	}

	private static void setnoReset() {
		String strnoReset = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".noReset").trim();
		if (strnoReset!=null && strnoReset.toLowerCase().trim().equals("true"))
			noReset.set(true);
		else
			noReset.set(false);
		Log.debug("Loaded noReset as " + noReset.get());
	}

	private static void setnewCommandTimeout() {
		String strnewCommandTimeout = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".newCommandTimeout").trim();
		if (strnewCommandTimeout==null || strnewCommandTimeout.trim().isBlank() || strnewCommandTimeout.trim().isEmpty())
			newCommandTimeout.set("0");
		else
			newCommandTimeout.set(strnewCommandTimeout);
		Log.debug("Loaded newCommandTimeout as " + newCommandTimeout.get());
	}

	private static void setautoGrantPermissions() {
		String strautoGrantPermissions = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".autoGrantPermissions").trim();
		if (strautoGrantPermissions!=null && strautoGrantPermissions.toLowerCase().trim().equals("true"))
			autoGrantPermissions.set(true);
		else
			autoGrantPermissions.set(false);
		Log.debug("Loaded autoGrantPermissions as " + autoGrantPermissions.get());
	}

	private static void setprintPageSourceOnFindFailure() {
		String strprintPageSourceOnFindFailure = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".printPageSourceOnFindFailure")
				.trim();
		if (strprintPageSourceOnFindFailure!=null && strprintPageSourceOnFindFailure.toLowerCase().trim().equals("true"))
			printPageSourceOnFindFailure.set(true);
		else
			printPageSourceOnFindFailure.set(false);
		Log.debug("Loaded printPageSourceOnFindFailure as " + printPageSourceOnFindFailure.get());
	}

	private static void setdownloadFilepath() {
		String strdownloadFilepath = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".downloadFilepath").trim();
		if (strdownloadFilepath==null || strdownloadFilepath.trim().isBlank() || strdownloadFilepath.trim().isEmpty())
			downloadFilepath.set("");
		else
			downloadFilepath.set(strdownloadFilepath);
		Log.debug("Loaded downloadFilepath as " + downloadFilepath.get());
	}

	private static void setbundleID() {
		String strbundleID = ConfigProperties
				.getProperty(propFilePath, platformType.get().toLowerCase().trim() + ".bundleID").trim();
		if (strbundleID==null || strbundleID.trim().isBlank() || strbundleID.trim().isEmpty())
			bundleID.set("");
		else
			bundleID.set(strbundleID);
		Log.debug("Loaded bundleID as " + bundleID.get());
	}
}
