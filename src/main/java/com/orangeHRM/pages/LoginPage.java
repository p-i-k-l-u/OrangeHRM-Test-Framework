package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM.actiondriver.ActionDriver;
import com.orangeHRM.base.BaseClass;

public class LoginPage {

	private ActionDriver actionDriver;

	// Define locators using By Class

	private By userNameField = By.name("username");
	private By passwordField = By.cssSelector("input[type='password']");
	private By loginButton = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
	private By errorMessage = By.xpath("//p[text()='invalid credentials']");

	/*
	 * // Initialize the Action Driver object by passing weDriver instance public
	 * LoginPage(WebDriver driver) { this.actionDriver = new ActionDriver(driver); }
	 */

	public LoginPage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver();
	}

	// Method to perform Login
	public void login(String username, String password) {
		actionDriver.enterText(userNameField, username);
		actionDriver.enterText(passwordField, password);
		actionDriver.click(loginButton);

	}

	// Method to check if error message is displayed

	public boolean isErrorMessageDisplayed() {
		return actionDriver.isDisplayed(errorMessage);

	}

	// Method to get the text from error message

	public String getErrorMessageText() {
		return actionDriver.getText(errorMessage);
	}

	// Method to verify if error is correct or not

	public boolean verifyErrorMessage(String expectedError) {
		return actionDriver.compareText(errorMessage, expectedError);
	}

}
