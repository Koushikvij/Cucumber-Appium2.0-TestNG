package mobile.appium.runner;
 
import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import utilities.common.Log;

import org.junit.runner.RunWith;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import java.io.File;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import drivers.DriverManager;
import drivers.mobile.AppiumDriverManager;

@RunWith(Cucumber.class)
@CucumberOptions(
		tags = "", 
		features = {"src/test/resources/features/mobile"},
		glue = {"mobile/appium/stepDefinitions"},
		plugin= {"pretty","html:target/cucumber.html",
         "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
         "rerun:target/failedrerun.txt"
        },
		monochrome=true,
		publish = true)


public class testRunner extends AbstractTestNGCucumberTests
{
	@BeforeSuite()
	public void clearLogFile()
	{
		String logFilePath = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "Logs" + File.separator+ "test_automation.log";
		File f=new File(logFilePath);
        if(f.exists())
        	f.delete();
        
        String log4jConfPath = System.getProperty("user.dir") + File.separator + "src" + File.separator+ "test" + File.separator + "java"
                + File.separator + "config" + File.separator + "common" + File.separator+ "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);		
	}

    @BeforeClass(alwaysRun = true)
    @Parameters({"AUTType", "capabilityLocator"})
    public void initSession(String autType, @Optional String capabilityLocator)
    {
        try
        {
            Log.info("Current Application Under Test Type -> " + autType);
            if(!autType.isBlank() || !autType.isEmpty())
            	DriverManager.autType=autType;
            Log.info("Capability Locator=" + capabilityLocator);
            if(!capabilityLocator.isBlank() || !capabilityLocator.isEmpty())            	
            	AppiumDriverManager.capabilityLocator.set(capabilityLocator);
			DriverManager.setUpAppiumDriver();
        }
        catch(Exception e)
        {
        	Assert.assertTrue(false,"Exception found in Method - initSession : " + e.getMessage());
        }
    }
    
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}