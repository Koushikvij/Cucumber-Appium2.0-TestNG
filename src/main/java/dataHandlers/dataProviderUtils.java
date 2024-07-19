package dataHandlers;

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
}
