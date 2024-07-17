package mobile.appium.actions;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import drivers.mobile.AppiumDriverManager;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.appium.locators.CartPageLocators;
import utilities.mobile.MobileUtilities;

public class CartPageActions {

	CartPageLocators cartLocator = null;

	public CartPageActions() {
		this.cartLocator = new CartPageLocators();
		PageFactory.initElements(new AppiumFieldDecorator(AppiumDriverManager.getAndroidDriver()), cartLocator);
	}

	public void verifyCart() {
		MobileUtilities.waitForVisible(cartLocator.productName.get(0), 20);

		int count = cartLocator.productName.size();
		int matchCount = 0;
		for (int i = 0; i < count; i++) {
			String itemName = cartLocator.productName.get(i).getText();
			if (itemName.equalsIgnoreCase("Jordan 6 Rings") || itemName.equalsIgnoreCase("Air Jordan 4 Retro"))
				matchCount++;
		}
		if (matchCount == 2)
			System.out.println("Both items matches successfully");
		else if (matchCount == 1)
			System.out.println("Only one item matches successfully");
		else
			System.out.println("Both items match Failed");

		int priceCount = cartLocator.productPrice.size();
		double sum = 0;
		for (int j = 0; j < priceCount; j++) {
			String priceAmt = cartLocator.productPrice.get(j).getText();
			sum = sum + Double.parseDouble(priceAmt.substring(1));
		}
		double totalAmt = Double.parseDouble(cartLocator.totalAmount.getText().substring(1));
		System.out.println("Sum = " + sum + "  totalAmt=" + totalAmt);
		Assert.assertEquals(totalAmt, sum);
	}
}