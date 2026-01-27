package com.orangeHRM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.orangeHRM.actiondriver.ActionDriver;
import com.orangeHRM.base.BaseClass;

public class HomePage {

	private ActionDriver actionDriver;

	// Define locators using By Class

	private By adminTab = By.xpath("//span[text()='Admin']");
	private By userButton = By.className("oxd-userdropdown-name");
	private By logoutButton = By.xpath("//a[text()='Logout']");
	private By orangeHRMlogo = By.xpath("//div[@class='oxd-brand-banner']//img");

	/*
	 * // Initialize the Action Driver object by passing weDriver instance public
	 * HomePage(WebDriver driver) { this.actionDriver = new ActionDriver(driver); }
	 */

	public HomePage(WebDriver driver) {
		this.actionDriver = BaseClass.getActionDriver();
	}

	// Method to verify if admin tab is visible

	public boolean isAdminTabVisible() {
		return actionDriver.isDisplayed(adminTab);
	}

	public boolean verifyOrangeHRMlogo() {
		return actionDriver.isDisplayed(orangeHRMlogo);
	}

	// Method to perform Logout Operations

	public void logout() {
		actionDriver.click(userButton);
		actionDriver.click(logoutButton);
	}

}
