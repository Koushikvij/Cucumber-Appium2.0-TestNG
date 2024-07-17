package mobile.appium.locators;

import java.util.List;

import org.openqa.selenium.WebElement;

import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailPageLocators {

	@AndroidFindBy(id="com.androidsample.generalstore:id/productName") 
	public List<WebElement> productName;
	@AndroidFindBy(id="com.androidsample.generalstore:id/productAddCart") 
	public List<WebElement> addtoCartBtn;
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart") 
	public WebElement appBarAddtoCartBtn;
}
