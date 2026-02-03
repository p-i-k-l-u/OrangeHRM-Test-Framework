//package com.orangeHRM.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import com.orangeHRM.actiondriver.ActionDriver;
//import com.orangeHRM.base.BaseClass;
//
//public class LoginPage {
//
//	private ActionDriver actionDriver;
//
//	// Define locators using By Class
//
//	private By userNameField = By.name("username");
//	private By passwordField = By.cssSelector("input[type='password']");
//	private By loginButton = By.xpath("//*[@id=\"app\"]/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
//	private By errorMessage = By.xpath("//p[text()='invalid credentials']");
//
//	/*
//	 * // Initialize the Action Driver object by passing weDriver instance public
//	 * LoginPage(WebDriver driver) { this.actionDriver = new ActionDriver(driver); }
//	 */
//	
//	// Before 
//
////	public LoginPage(WebDriver driver) {
////		this.actionDriver = BaseClass.getActionDriver();
////	}
//
//	
//	// after
//	public LoginPage(WebDriver driver) {
//	    this.actionDriver = new ActionDriver(driver);
//	}
//
//	
//	
//	// Method to perform Login
//	public void login(String username, String password) {
//		actionDriver.enterText(userNameField, username);
//		actionDriver.enterText(passwordField, password);
//		actionDriver.click(loginButton);
//
//	}
//
//	// Method to check if error message is displayed
//
//	public boolean isErrorMessageDisplayed() {
//		return actionDriver.isDisplayed(errorMessage);
//
//	}
//
//	// Method to get the text from error message
//
//	public String getErrorMessageText() {
//		return actionDriver.getText(errorMessage);
//	}
//
//	// Method to verify if error is correct or not
//
//	public boolean verifyErrorMessage(String expectedError) {
//		return actionDriver.compareText(errorMessage, expectedError);
//	}
//
//}



// ------------------------ NEW CODE ----------------------------
package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM.actiondriver.ActionDriver;

public class LoginPage {

    private WebDriver driver;
    private ActionDriver actionDriver;

    private By userNameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By adminTab = By.xpath("//span[normalize-space()='Admin']");
    private By errorMsg = By.xpath("//p[contains(@class,'alert-content-text')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.actionDriver = new ActionDriver(driver);
    }

    public boolean login(String username, String password) {

        actionDriver.enterText(userNameField, username);
        actionDriver.enterText(passwordField, password);
        actionDriver.click(loginButton);

        actionDriver.waitForPageToLoad(10);

        // If login fails
        if (driver.findElements(errorMsg).size() > 0) {
            System.out.println("❌ Login Failed");
            return false;
        }

        // If login succeeds
        actionDriver.waitForVisibility(adminTab, 20);
        System.out.println("✅ Login Successful");
        return true;
    }
}
