package mobile.appium.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions(
		tags = "", 
		features = {"@target/failedrerun.txt"}, 
		glue = {"mobile/appium/stepDefinitions"},
		monochrome=true,
		publish=true,
        plugin = {"pretty","html:target/cucumber.html",
        		 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class failedRunner extends AbstractTestNGCucumberTests {


    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}