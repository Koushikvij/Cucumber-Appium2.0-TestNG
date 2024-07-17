package mobile.appium.stepDefinitions.addToCartTest;

import drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import mobile.appium.actions.*;

public class AddToCartDefinitions {

	private static ThreadLocal<CartPageActions> objCart = new ThreadLocal<>();
	private static ThreadLocal<FormPageActions> objForm = new ThreadLocal<>();
	private static ThreadLocal<ProductDetailPageActions> objPDP = new ThreadLocal<>();

	@Before
	public void initializeVariables() {
		objCart.set(new CartPageActions());
		objForm.set(new FormPageActions());
		objPDP.set(new ProductDetailPageActions());
	}

	@Given("The device is connected and the app is launched")
	public void the_device_is_connected_and_the_app_is_launched() {
		try {
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("User enters username as {string}, country as {string} and gender as {string} to login")
	public void user_enters_username_as_country_as_and_gender_as_to_login(String string, String string2,
			String string3) {
		try {
			objForm.get().fillBaseFormPage(string, string2, string3);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@When("Add products with name {string} to cart")
	public void add_products_with_name_to_cart(String string) {
		try {
			objPDP.get().clickAddtoCartOfItemByID(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Then("User should be able to validate the count in cart page")
	public void user_should_be_able_to_validate_the_count_in_cart_page() {
		try {
			objCart.get().verifyCart();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDownVariables() {
		objForm.remove();
		objPDP.remove();
		objCart.remove();
	}
}