package mobile.appium.locators;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class CartPageLocators {

	@AndroidFindBy(id="com.androidsample.generalstore:id/productName") 
	public List<WebElement> productName;
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice") 
	public List<WebElement> productPrice;
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl") 
	public WebElement totalAmount;
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton") 
	public WebElement termsButton;
	@AndroidFindBy(id="com.androidsample.generalstore:id/alertTitle") 
	public WebElement alertTitle;
	@AndroidFindBy(xpath="//android.widget.Button[@text='CLOSE']") 
	public WebElement alertCloseBtn;
	@AndroidFindBy(xpath="//android.widget.CheckBox[1]") 
	public WebElement checkBox;
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed") 
	public WebElement ProceedBtn;
}