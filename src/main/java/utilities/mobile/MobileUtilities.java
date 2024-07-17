package utilities.mobile;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import constants.Direction;
import drivers.mobile.AppiumDriverManager;
import utilities.common.Log;

public class MobileUtilities extends AppiumDriverManager{
	private static int STD_WAIT=10;

	public static void zoom(WebElement element) {
		try {
			Log.info("Inside MobileUtilities::zoom Utility method");
			Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(
							finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(),
							centerOfElement.getX() + 100, centerOfElement.getY() - 100))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			Sequence sequence2 = new Sequence(finger2, 1)
					.addAction(
							finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger2, Duration.ofMillis(200)))
					.addAction(finger2.createPointerMove(Duration.ofMillis(200), PointerInput.Origin.viewport(),
							centerOfElement.getX() - 100, centerOfElement.getY() + 100))
					.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			getDriver().perform(Arrays.asList(sequence, sequence2));
		} catch (Exception e) {
			Log.error("Exception in zoom method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in zoom method - " + e.getMessage());
		}
	}

	public static void scroll(Direction direction) {
		try {
			Log.info("Inside MobileUtilities::scroll Utility method");
			Dimension size = getDriver().manage().window().getSize();
			int startX, startY, endX, endY;
			switch (direction) {
			case DOWN:
				startX = (int) (size.getWidth() / 2);
				startY = (int) (size.getHeight() * 0.80);
				endX = startX;
				endY = (int) (size.getHeight() * 0.20);
				PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
				Sequence sequence1 = new Sequence(finger1, 1)
						.addAction(finger1
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
						.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger1, Duration.ofMillis(200))).addAction(finger1
								.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
						.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				getDriver().perform(Collections.singletonList(sequence1));
				break;
			case UP:
				size = getDriver().manage().window().getSize();
				startX = (int) (size.getWidth() / 2);
				startY = (int) (size.getHeight() * 0.20);
				endX = startX;
				endY = (int) (size.getHeight() * 0.80);
				PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
				Sequence sequence2 = new Sequence(finger2, 1)
						.addAction(finger2
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
						.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger2, Duration.ofMillis(200))).addAction(finger2
								.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
						.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				getDriver().perform(Collections.singletonList(sequence2));
				break;
			case RIGHT:
				size = getDriver().manage().window().getSize();
				startX = (int) (size.getWidth() * 0.9);
				startY = (int) (size.getWidth() / 2);
				endX = (int) (size.getWidth() * 0.1);
				endY = startY;
				PointerInput finger3 = new PointerInput(PointerInput.Kind.TOUCH, "finger3");
				Sequence sequence3 = new Sequence(finger3, 1)
						.addAction(finger3
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
						.addAction(finger3.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger3, Duration.ofMillis(200))).addAction(finger3
								.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
						.addAction(finger3.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				getDriver().perform(Collections.singletonList(sequence3));
				break;
			case LEFT:
				size = getDriver().manage().window().getSize();
				startX = (int) (size.getWidth() * 0.1);
				startY = (int) (size.getWidth() / 2);
				endX = (int) (size.getWidth() * 0.9);
				endY = startY;
				PointerInput finger4 = new PointerInput(PointerInput.Kind.TOUCH, "finger4");
				Sequence sequence4 = new Sequence(finger4, 1)
						.addAction(finger4
								.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
						.addAction(finger4.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
						.addAction(new Pause(finger4, Duration.ofMillis(200))).addAction(finger4
								.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
						.addAction(finger4.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
				getDriver().perform(Collections.singletonList(sequence4));
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in scroll method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in scroll method - " + e.getMessage());
		}
	}

	public static void longPress(WebElement element) {
		try {
			Log.info("Inside MobileUtilities::longPress Utility method");
			Point location = element.getLocation();
			Dimension size = element.getSize();
			Point centerOfElement = getCenterOfElement(location, size);
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(
							finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofSeconds(2)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			getDriver().perform(Collections.singletonList(sequence));
		} catch (Exception e) {
			Log.error("Exception in longPress method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in longPress method - " + e.getMessage());
		}
	}

	public static void doubleTap(WebElement element) {
		try {
			Log.info("Inside MobileUtilities::doubleTap Utility method");
			Point location = element.getLocation();
			Dimension size = element.getSize();
			Point centerOfElement = getCenterOfElement(location, size);
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(
							finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(100)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			getDriver().perform(Collections.singletonList(sequence));
		} catch (Exception e) {
			Log.error("Exception in doubleTap method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in doubleTap method - " + e.getMessage());
		}
	}

	public static void tap(WebElement element) {
		try {
			Log.info("Inside MobileUtilities::tap Utility method");
			Point location = element.getLocation();
			Dimension size = element.getSize();
			Point centerOfElement = getCenterOfElement(location, size);
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(
							finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(200)))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			getDriver().perform(Collections.singletonList(sequence));
		} catch (Exception e) {
			Log.error("Exception in tap method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in tap method - " + e.getMessage());
		}
	}

	public static Point getCenterOfElement(Point location, Dimension size) {
		Log.info("Inside MobileUtilities::getCenterOfElement Utility method");
		return new Point(location.getX() + size.getWidth() / 2, location.getY() + size.getHeight() / 2);
	}

	public static Point getPointOnCircle(int step, int totalSteps, Point origin, double radius) {
		Log.info("Inside MobileUtilities::getPointOnCircle Utility method");
		double theta = 2 * Math.PI * ((double) step / totalSteps);
		int x = (int) Math.floor(Math.cos(theta) * radius);
		int y = (int) Math.floor(Math.sin(theta) * radius);
		return new Point(origin.x + x, origin.y + y);
	}

	public static void dragAndDrop(WebElement source, WebElement target) {
		try {
			Log.info("Inside MobileUtilities::dragAndDrop Utility method");
			Point sourceElementCenter = getCenterOfElement(source.getLocation(), source.getSize());
			Point targetElementCenter = getCenterOfElement(target.getLocation(), target.getSize());
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(),
							sourceElementCenter))
					.addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
					.addAction(new Pause(finger1, Duration.ofMillis(500)))
					.addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(),
							targetElementCenter))
					.addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
			getDriver().perform(Arrays.asList(sequence));
		} catch (Exception e) {
			Log.error("Exception in dragAndDrop method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in dragAndDrop method - " + e.getMessage());
		}
	}

	public static void javaScriptLongClick(WebElement ele) {
		try {
			Log.info("Inside MobileUtilities::javaScriptLongClick Utility method");
			getDriver().executeScript("mobile: longClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", "2000"));
		} catch (Exception e) {
			Log.error("Exception in javaScriptLongClickAction method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in javaScriptLongClickAction method - " + e.getMessage());
		}
	}

	public static void javaScriptDoubleClick(WebElement ele) {
		try {
			Log.info("Inside MobileUtilities::javaScriptDoubleClick Utility method");
			getDriver().executeScript("mobile: doubleClickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId()));
		} catch (Exception e) {
			Log.error("Exception in javaScriptDoubleClickAction method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in javaScriptDoubleClickAction method - " + e.getMessage());
		}
	}

	public static void javaScriptClick(WebElement ele) {
		try {
			Log.info("Inside MobileUtilities::javaScriptClick Utility method");
			getDriver().executeScript("mobile: clickGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId()));
		} catch (Exception e) {
			Log.error("Exception in javaScriptClickAction method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in javaScriptClickAction method - " + e.getMessage());
		}
	}

	public static void javaScriptScrollToEnd(Direction direction) {
		try {
			Log.info("Inside MobileUtilities::javaScriptScrollToEnd Utility method");
			Boolean canScrollMore;
			switch(direction)
			{
			case DOWN:
				do {
					canScrollMore = (Boolean) getDriver().executeScript("mobile: scrollGesture", ImmutableMap.of("left", 400,
							"top", 1200, "width", 400, "height", 400, "direction", "down", "percent", 50.0, "speed", 500));
				} while (canScrollMore);
				break;
			case UP:
				do {
					canScrollMore = (Boolean) getDriver().executeScript("mobile: scrollGesture", ImmutableMap.of("left", 400,
							"top", 1200, "width", 400, "height", 400, "direction", "up", "percent", 50.0, "speed", 500));
				} while (canScrollMore);
				break;
			case LEFT:
				do {
					canScrollMore = (Boolean) getDriver().executeScript("mobile: scrollGesture", ImmutableMap.of("left", 400,
							"top", 1200, "width", 400, "height", 400, "direction", "left", "percent", 50.0, "speed", 500));
				} while (canScrollMore);
				break;
			case RIGHT:
				do {
					canScrollMore = (Boolean) getDriver().executeScript("mobile: scrollGesture", ImmutableMap.of("left", 400,
							"top", 1200, "width", 400, "height", 400, "direction", "right", "percent", 50.0, "speed", 500));
				} while (canScrollMore);
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in javaScriptScrollToEndAction method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in javaScriptScrollToEndAction method - " + e.getMessage());
		}
	}

	public static void javaScriptSwipe(WebElement element, Direction direction) {
		try {
			Log.info("Inside MobileUtilities::javaScriptSwipe Utility method");
			switch(direction)
			{
			case DOWN:
				getDriver().executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
						((RemoteWebElement) element).getId(), "direction", "down", "percent", 0.75));
				break;
			case UP:
				getDriver().executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
						((RemoteWebElement) element).getId(), "direction", "up", "percent", 0.75));
				break;
			case LEFT:
				getDriver().executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
						((RemoteWebElement) element).getId(), "direction", "left", "percent", 0.75));
				break;
			case RIGHT:
				getDriver().executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
						((RemoteWebElement) element).getId(), "direction", "right", "percent", 0.75));
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in javaScriptSwipeAction method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in javaScriptSwipeAction method - " + e.getMessage());
		}
	}
	
	public static void javaScriptSendKeys(WebElement element, String key)
	{
		try {
			Log.info("Inside MobileUtilities::javaScriptSendKeys Utility method");
			getDriver().executeScript("mobile: keys",ImmutableMap.of("element", ((RemoteWebElement) element).getId(), 
					"keys",key.toCharArray()));
		} catch (Exception e) {
			Log.error("Exception in javaScriptSendKeys method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in javaScriptSendKeys method - " + e.getMessage());
		}
	}	

	public static boolean isElementVisible(WebElement element) {
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::isElementVisible Utility method");
			String val = element.getAttribute("visible");
			if(val.trim().toLowerCase().equals("true"))
				status=true;			
		} catch (Exception e) {
			Log.error("Exception in isElementVisible method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in isElementVisible method - " + e.getMessage());
		}
		return status;
	}	
	
	public static boolean isDisplayed(WebElement element)
	{
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::isDisplayed Utility method");
			if(element.isDisplayed())
				status=true;			
		} catch (Exception e) {
			Log.error("Exception in isDisplayed method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in isDisplayed method - " + e.getMessage());
		}
		return status;		
	}
	
	public static boolean isDisabled(WebElement element)
	{
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::isDisabled Utility method");
			if(!element.isEnabled())
				status=true;			
		} catch (Exception e) {
			Log.error("Exception in isDisabled method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in isDisabled method - " + e.getMessage());
		}
		return status;		
	}
	
	public static boolean isEnabled(WebElement element)
	{
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::isEnabled Utility method");
			if(element.isEnabled())
				status=true;			
		} catch (Exception e) {
			Log.error("Exception in isEnabled method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in isEnabled method - " + e.getMessage());
		}
		return status;		
	}
	
	public static boolean isPresent(WebElement element, int seconds)
	{
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::isPresent Utility method");
			waitForVisible(element,seconds);
			if(isEnabled(element) || isDisplayed(element))
				status=true;
			else if(isElementVisible(element))
				status = true;					
		} catch (Exception e) {
			Log.error("Exception in isPresent method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in isPresent method - " + e.getMessage());
		}
		return status;		
	}
	
	public static boolean waitForVisible(WebElement element, int seconds) {
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::waitForVisible Utility method");
			WebDriverWait wait=new WebDriverWait(getDriver(),Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.visibilityOf(element));	
			if(isEnabled(element) || isDisplayed(element))
				status=true;
			else if(isElementVisible(element))
				status = true;			
		} catch (Exception e) {
			Log.error("Exception in waitForVisible method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in waitForVisible method - " + e.getMessage());
		}
		return status;
	}
	
	public static boolean waitForClickable(WebElement element, int seconds) {
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::waitForClickable Utility method");
			WebDriverWait wait=new WebDriverWait(getDriver(),Duration.ofSeconds(seconds));
			wait.until(ExpectedConditions.elementToBeClickable(element));	
			if(isEnabled(element) || isDisplayed(element))
				status=true;
			else if(isElementVisible(element))
				status = true;			
		} catch (Exception e) {
			Log.error("Exception in waitForClickable method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in waitForClickable method - " + e.getMessage());
		}
		return status;
	}
	
	public static boolean click(WebElement element)
	{
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::click Utility method");
			waitForClickable(element,STD_WAIT);
			element.click();
		} catch (Exception e) {
			Log.error("Exception in click method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in click method - " + e.getMessage());
		}
		return status;		
	}
	
	public static boolean sendKeys(WebElement element, String text)
	{
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::sendKeys Utility method");
			waitForVisible(element,STD_WAIT);
			element.clear();
			element.sendKeys(text);
		} catch (Exception e) {
			Log.error("Exception in click method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in click method - " + e.getMessage());
		}
		return status;		
	}
	
	public static void hideKeyboard()
	{
		try {
			Log.info("Inside MobileUtilities::hideKeyboard Utility method");
			getDriver().executeScript("mobile: hideKeyboard");
		} catch (Exception e) {
			Log.error("Exception in hideKeyboard method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in hideKeyboard method - " + e.getMessage());
		}
	}
	
	public static void hoverOver(WebElement element)
	{
		try {
			Log.info("Inside MobileUtilities::hoverOver Utility method");
			Point location = element.getLocation();
			Dimension size = element.getSize();
			Point centerOfElement = getCenterOfElement(location, size);
			PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
			Sequence sequence = new Sequence(finger1, 1)
					.addAction(
							finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement));
			getDriver().perform(Collections.singletonList(sequence));
		} catch (Exception e) {
			Log.error("Exception in hoverOver method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in hoverOver method - " + e.getMessage());
		}
	}
	
	public static void volumeToggle(Direction direction, int counter)
	{
		try {
			Log.info("Inside MobileUtilities::volumeToggle Utility method");
			switch(direction)
			{
			case DOWN:
				for(int i=0;i<counter;i++)
					getDriver().executeScript("mobile: pressButton",ImmutableMap.of("name","volumedown"));
				break;
			case UP:
				for(int i=0;i<counter;i++)
					getDriver().executeScript("mobile: pressButton",ImmutableMap.of("name","volumeup"));
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in volumeToggle method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in volumeToggle method - " + e.getMessage());
		}
	}
	
	public static void navigateBack()
	{
		try {
			Log.info("Inside MobileUtilities::navigateBack Utility method");
			getDriver().navigate().back();
		} catch (Exception e) {
			Log.error("Exception in navigateBack method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in navigateBack method - " + e.getMessage());
		}
	}
		
	public static boolean containsText(WebElement element, String text)
	{
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::containsText Utility method");
			waitForVisible(element,STD_WAIT);
			if(element.getText().toLowerCase().trim().contains(text.toLowerCase().trim()))
				status=true;			
		} catch (Exception e) {
			Log.error("Exception in containsText method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in containsText method - " + e.getMessage());
		}
		return status;
	}
	
	public static boolean equalsText(WebElement element, String text)
	{
		boolean status = false;
		try {
			Log.info("Inside MobileUtilities::equalsText Utility method");
			waitForVisible(element,STD_WAIT);
			if(element.getText().toLowerCase().trim().equals(text.toLowerCase().trim()))
				status=true;			
		} catch (Exception e) {
			Log.error("Exception in equalsText method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in equalsText method - " + e.getMessage());
		}
		return status;
	}
	
	public static void pickerWheelStep(WebElement element, String order, double offset)
	{
		try {		
			Log.info("Inside MobileUtilities::pickerWheelStep Utility method");
			switch(order.toLowerCase().trim())
			{
			case "next":
				getDriver().executeScript("mobile: selectPickerWheelValue", ImmutableMap.of("elementId ", ((RemoteWebElement) element).getId(),
						"order", "next", "offset", offset));
				break;
			case "previous":
				getDriver().executeScript("mobile: selectPickerWheelValue", ImmutableMap.of("elementId ", ((RemoteWebElement) element).getId(),
						"order", "previous", "offset", offset));
				break;
			}
		} catch (Exception e) {
			Log.error("Exception in pickerWheelStep method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in pickerWheelStep method - " + e.getMessage());
		}
	}
	
	public static void javaScriptScrollToElement(WebElement element)
	{
		try {
			Log.info("Inside MobileUtilities::javaScriptScrollToElement Utility method");
			getDriver().executeScript("mobile: scrollToElement", ImmutableMap.of("elementId ", ((RemoteWebElement) element).getId()));
		} catch (Exception e) {
			Log.error("Exception in javaScriptScrollToElement method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in javaScriptScrollToElement method - " + e.getMessage());
		}
	}
	
	public static void javaScriptMoveToBackground(int seconds)
	{
		try {
			Log.info("Inside MobileUtilities::javaScriptMoveToBackground Utility method");
			getDriver().executeScript("mobile: backgroundApp", ImmutableMap.of("seconds ", seconds));
		} catch (Exception e) {
			Log.error("Exception in javaScriptMoveToBackground method - " + e.getMessage());
			Assert.assertTrue(false, "Exception in javaScriptMoveToBackground method - " + e.getMessage());
		}
	}	
}