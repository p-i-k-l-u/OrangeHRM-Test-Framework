package com.orangeHRM.actiondriver;

import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.orangeHRM.base.BaseClass;

public class ActionDriver {

	private WebDriver driver;
	private WebDriverWait wait;
	public static final Logger logger = BaseClass.logger;

	public ActionDriver(WebDriver driver) {
		this.driver = driver;
		int explicitwait = Integer.parseInt(BaseClass.getProp().getProperty("explicitwait").trim());
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitwait));
		logger.info("Web Driver Instance is Created");
	}

	// Method to Click an Element
	public void click(By by) {
		try {
			waitForElementToBeClickable(by);
			driver.findElement(by).click();
			logger.info("Clicked an Element..");
		} catch (Exception e) {
			System.out.println("Unable to click Element" + e.getMessage());
			logger.error("Unable to click Element..");
		}
	}

	// Method to enter Text into an input field

	public void enterText(By by, String value) {
		try {
			waitForElemetnToBeVisible(by);
//			driver.findElement(by).clear();
//			driver.findElement(by).sendKeys(value);
			WebElement element = driver.findElement(by);
			element.clear();
			element.sendKeys(value);
			logger.info("Entered text:" + value);
		} catch (Exception e) {
			logger.error("Unable to enter the value:" + e.getMessage());
		}
	}

	// wait for element to be clickable
	private void waitForElementToBeClickable(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			System.out.println("Element is Not Clickable" + e.getMessage());
		}
	}

	// Method to get text from an input field
	public String getText(By by) {
		try {
			waitForElemetnToBeVisible(by);
			return driver.findElement(by).getText();
		} catch (Exception e) {
			logger.error("Unable to Get text" + e.getMessage());
			return "";
		}
	}

// Method to compare Two text -- changed the Return Type

	public boolean compareText(By by, String expectedText) {
		try {
			waitForElemetnToBeVisible(by);
			String actualText = driver.findElement(by).getText();
			if (expectedText.equals(actualText)) {
				logger.info("Text are Matching " + actualText + "equals" + expectedText);
				return true;
			} else {
				logger.error("Text are not  Matching " + actualText + "not equals" + expectedText);
				return false;
			}
		} catch (Exception e) {
			logger.error("Unable to compare Text:" + e.getMessage());
		}
		return false;

	}

// Method to check if an element is displayed
//	public boolean isDisplayed(By by) {
//		try {
//			waitForElemetnToBeVisible(by);
//			boolean isDisplayed = driver.findElement(by).isDisplayed();
//
//			if (isDisplayed) {
//				System.out.println("Element is Visible");
//				return isDisplayed;
//			} else {
//				return isDisplayed;
//			}
//		} catch (Exception e) {
//			System.out.println("Element is Not Displayed " + e.getMessage());
//			return false;
//		}
//	}
	// Simplified Method
	public boolean isDisplayed(By by) {
		try {
			waitForElemetnToBeVisible(by);
			return driver.findElement(by).isDisplayed();
		} catch (Exception e) {
			logger.error("Element is not displayed:" + e.getMessage());
			return false;
		}
	}

	// Wait for the page to load
	public void waitForPageLoad(int timeOutInSec) {
		try {
			wait.withTimeout(Duration.ofSeconds(timeOutInSec)).until(WebDriver -> ((JavascriptExecutor) WebDriver)
					.executeScript("return document.readyState").equals("complete"));
			logger.info("Page loaded successfully.");
		} catch (Exception e) {
			logger.error("Page did not load within " + timeOutInSec + " seconds. Exception: " + e.getMessage());
		}
	}

	// Scroll to an Element
	public void scrollToElement(By by) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(by);
			js.executeScript("arguments[0], scrollIntoView(true);", element);
		} catch (Exception e) {
			logger.error("Unable to locate the Elemetn" + e.getMessage());
		}

	}

// Wait for Element to be Visible 
	private void waitForElemetnToBeVisible(By by) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
			logger.error("Element is Not Visible" + e.getMessage());
		}
	}

}
