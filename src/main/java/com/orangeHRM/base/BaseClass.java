package com.orangeHRM.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.orangeHRM.actiondriver.ActionDriver;
import com.orangeHRM.utilities.LoggerManager;

public class BaseClass {

	protected static Properties prop;
	protected static WebDriver driver;
	private static ActionDriver actionDriver;
	public static final Logger logger = LoggerManager.getLogger(BaseClass.class);

	@BeforeSuite
	public void loadConfig() throws IOException {
		// load the Configurations File

		prop = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");

		prop.load(fis);
		logger.info("config.properties file Loaded.. ");
	}

	@BeforeMethod
	public void setup() throws IOException {

		System.out.println("Setting Up webdriver for:" + this.getClass().getSimpleName());
		launchBrowser();
		configureBrowser();
		staticWait(2);

		logger.info("WebDriver Initialized and Browser maximized");
		logger.trace("This is Trace message");
		logger.error("This is Error Message");
		logger.debug("This is Debug Message");
		logger.fatal("This is Fatal Message");
		logger.warn("This is Warn Message");

		// Initialize the Action Driver only ones

		if (actionDriver == null) {
			actionDriver = new ActionDriver(driver);
//			System.out.println("Action Driver instance is Created.");
			logger.info("Action Driver Instance is Created..");

		}

	}

	/*
	 * Initialize the WebDriver based on defined in config Properties file
	 */

	private void launchBrowser() {

		String browser = prop.getProperty("browser");

//		if (browser.equalsIgnoreCase("chrome")) {
//			driver = new ChromeDriver();
//			logger.info("ChromeDriver Instance is Created.");
//		} else if (browser.equalsIgnoreCase("firefox")) {
//			driver = new FirefoxDriver();
//			logger.info("Firefox Instance is Created.");
//		} else if (browser.equalsIgnoreCase("edge")) {
//			driver = new EdgeDriver();
//			logger.info("Edge Instance is Created.");
//		} else {
//			throw new IllegalArgumentException("Browser Not Supported");
//		}
		
		if (browser.equalsIgnoreCase("chrome")) {
		    ChromeOptions options = new ChromeOptions();
		    options.addArguments("--headless=new");
		    options.addArguments("--no-sandbox");
		    options.addArguments("--disable-dev-shm-usage");
		    options.addArguments("--window-size=1920,1080");

		    driver = new ChromeDriver(options);
		    logger.info("ChromeDriver started in headless mode.");
		}
	}

	/*
	 * Configure browser settings such as implict wait , maximize the browser and
	 * navigate to the URL
	 */
	private void configureBrowser() {
		// Implicit Wait
//		int implicitWait = Integer.parseInt(prop.getProperty("impicitWait"));
		// Implicit Wait (FIXED)
		String waitValue = prop.getProperty("impicitWait").trim();
		int implicitWait = Integer.parseInt(waitValue);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
		// Maximize the Driver

		driver.manage().window().maximize();

		// Navigate to the URL

		try {
			driver.get(prop.getProperty("url"));
		} catch (Exception e) {
			System.out.println("Failed to Navigate to the URL" + e.getMessage());
		}
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			try {
				driver.quit();

			} catch (Exception e) {
				System.out.println("Unable to Find the Driver" + e.getMessage());
			}
		}
		logger.info("WebDriver instance is Closed");
		driver = null;
		actionDriver = null;
	}

	// Getter for prop
	public static Properties getProp() {
		return prop;
	}

	/*
	 * // Driver getter and Setter setup public WebDriver getDriver() { return
	 * driver;
	 * 
	 * }
	 */

	// getter Method For WebDriver

	public static WebDriver getDriver() {
		if (driver == null) {
			System.out.println("WebDriver is not Initialize");
			throw new IllegalStateException("WebDriver is Not Initilize");
		}
		return driver;
	}

	// getter Method For Action Driver

	public static ActionDriver getActionDriver() {
		if (actionDriver == null) {
			System.out.println("Actiondriver is not Initialize");
			throw new IllegalStateException("WebDriver is Not Initilize");
		}
		return actionDriver;
	}

	// Driver Setter Method
	public void setterDriver(WebDriver driver) {
		this.driver = driver;
	}

	// Static Wait for Pause
	public void staticWait(int seconds) {
		LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(seconds));
	}

}
