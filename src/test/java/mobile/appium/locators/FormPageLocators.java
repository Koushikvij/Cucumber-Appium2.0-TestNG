package mobile.appium.locators;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class FormPageLocators {
 
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField") 
	public WebElement nameField;
	@AndroidFindBy(xpath = "//*[@text='Female']") 
	public WebElement FemaleRadioBtn;
	@AndroidFindBy(xpath = "//*[@text='Male']") 
	public WebElement MaleRadioBtn;
	@AndroidFindBy(id="android:id/text1") 
	public WebElement CountrySpinner;
	@AndroidFindBy(xpath="//*[@text='India']") 
	public WebElement India;
	@AndroidFindBy(xpath="//*[@text='Argentina']")
	public WebElement Argentina;
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop") 
	public WebElement LetsShopButton;
	@AndroidFindBy(xpath="//android.widget.Toast[1]") 
	public WebElement ToastMessage;
}