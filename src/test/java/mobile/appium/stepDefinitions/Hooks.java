package mobile.appium.stepDefinitions;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import drivers.DriverManager;
import drivers.mobile.AppiumDriverManager;
import io.cucumber.java.*;

public class Hooks
{
    private static final Logger LOG = LogManager.getLogger(mobile.appium.stepDefinitions.Hooks.class);


    @Before
    public void testStart(Scenario scenario) {
        LOG.info("*****************************************************************************************");
        LOG.info("	Scenario: "+scenario.getName());
        LOG.info("*****************************************************************************************");
    }

    @After
    public static void tearDown(Scenario scenario) {
 
        //validate if scenario has failed
        if(scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) AppiumDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName()); 
        }   
         
        DriverManager.mobiletearDown();
    }
    
    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException
    {
        final byte[] screenshot = ((TakesScreenshot) AppiumDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName()); 
    }
}