package mobile.appium.actions;

import org.openqa.selenium.support.PageFactory;

import drivers.mobile.AppiumDriverManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import mobile.appium.locators.FormPageLocators;
import utilities.mobile.MobileUtilities;

public class FormPageActions {

	FormPageLocators formLocator = null;

	public FormPageActions() {
		this.formLocator = new FormPageLocators();
		PageFactory.initElements(new AppiumFieldDecorator(AppiumDriverManager.getAndroidDriver()), formLocator);
	}

	public void fillBaseFormPage(String name, String countryName, String gender) {
		MobileUtilities.hideKeyboard();
		MobileUtilities.sendKeys(formLocator.nameField, name);
		if(gender.toUpperCase().trim().equals("MALE"))
			MobileUtilities.click(formLocator.MaleRadioBtn);
		else if(gender.toUpperCase().trim().equals("FEMALE"))
			MobileUtilities.click(formLocator.FemaleRadioBtn);
		setCountry(countryName);
		MobileUtilities.click(formLocator.LetsShopButton);
	}

	public void setCountry(String countryName) {

		MobileUtilities.click(formLocator.CountrySpinner);
		AppiumDriverManager.getAndroidDriver().findElement(AppiumBy.androidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + countryName + "\"));"));
		AppiumDriverManager.getAndroidDriver().findElement(AppiumBy.xpath("//*[@text='" + countryName + "']")).click();
	}
}