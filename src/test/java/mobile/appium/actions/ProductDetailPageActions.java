package mobile.appium.actions;

import org.openqa.selenium.support.PageFactory;
import drivers.mobile.AppiumDriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.appium.locators.ProductDetailPageLocators;
import utilities.mobile.MobileUtilities;

public class ProductDetailPageActions {
	
	ProductDetailPageLocators pdpLocator=null;

	public ProductDetailPageActions()
	{ 
        this.pdpLocator = new ProductDetailPageLocators(); 
        PageFactory.initElements(new AppiumFieldDecorator(AppiumDriverManager.getAndroidDriver()),pdpLocator);
	}

	
	public void clickAddtoCartOfItemByID(String itemName)
	{
		//Searching for a product in the PDP and scrolling into view
		AppiumDriverManager.getAndroidDriver().findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
                + ".resourceId(\"com.androidsample.generalstore:id/rvProductList\")).scrollIntoView("
                + "new UiSelector().text(\""+itemName+"\"));"));
						
		int itemCount=pdpLocator.productName.size();
		int index=0;
		boolean blnFlag=false;
		for(index=0;index<itemCount;index++)
		{
			String itmName=pdpLocator.productName.get(index).getText();
			if(itmName.equalsIgnoreCase(itemName))
			{
				blnFlag=true;
				break;
			}
		}
		if(blnFlag==true)	
		{
			MobileUtilities.waitForVisible(pdpLocator.addtoCartBtn.get(index),20);
			pdpLocator.addtoCartBtn.get(index).click();
		}
		else
		{
				System.out.println("Item Not Found !");
		}
	}
}
