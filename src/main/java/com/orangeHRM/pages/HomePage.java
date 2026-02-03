//package com.orangeHRM.pages;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//
//import com.orangeHRM.actiondriver.ActionDriver;
//import com.orangeHRM.base.BaseClass;
//
//public class HomePage {
//
//	private ActionDriver actionDriver;
//
//	// Define locators using By Class
//
//	private By adminTab = By.xpath("//span[text()='Admin']");
//	private By userButton = By.className("oxd-userdropdown-name");
//	private By logoutButton = By.xpath("//a[text()='Logout']");
//	private By orangeHRMlogo = By.xpath("//div[@class='oxd-brand-banner']//img");
//
//	/*
//	 * // Initialize the Action Driver object by passing weDriver instance public
//	 * HomePage(WebDriver driver) { this.actionDriver = new ActionDriver(driver); }
//	 */
//
//	// Before 
////	public HomePage(WebDriver driver) {
////		this.actionDriver = BaseClass.getActionDriver();
////	}
//	
//	// after new 
//	public HomePage(WebDriver driver) {
//	    this.actionDriver = new ActionDriver(driver);
//	}
//
//
//	// Method to verify if admin tab is visible
//
//	public boolean isAdminTabVisible() {
//		return actionDriver.isDisplayed(adminTab);
//	}
//
//	public boolean verifyOrangeHRMlogo() {
//		return actionDriver.isDisplayed(orangeHRMlogo);
//	}
//
//	// Method to perform Logout Operations
//
//	public void logout() {
//		actionDriver.click(userButton);
//		actionDriver.click(logoutButton);
//	}
//
//}



//                   _-------- NEW CODE ------------

package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM.actiondriver.ActionDriver;

public class HomePage {

    private WebDriver driver;
    private ActionDriver actionDriver;

    private By adminTab = By.xpath("//span[text()='Admin']");
    private By userButton = By.className("oxd-userdropdown-name");
    private By logoutButton = By.xpath("//a[text()='Logout']");
    private By orangeHRMlogo = By.xpath("//div[@class='oxd-brand-banner']//img");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.actionDriver = new ActionDriver(driver);
    }

    public boolean isAdminTabVisible() {
        actionDriver.waitForVisibility(adminTab, 20);
        return actionDriver.isDisplayed(adminTab);
    }

    public boolean verifyOrangeHRMlogo() {
        actionDriver.waitForVisibility(orangeHRMlogo, 20);
        return actionDriver.isDisplayed(orangeHRMlogo);
    }

    public void logout() {
        actionDriver.click(userButton);
        actionDriver.click(logoutButton);
    }
}













