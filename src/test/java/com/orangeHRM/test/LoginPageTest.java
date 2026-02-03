//package com.orangeHRM.test;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.orangeHRM.base.BaseClass;
//import com.orangeHRM.pages.HomePage;
//import com.orangeHRM.pages.LoginPage;
//
//public class LoginPageTest extends BaseClass {
//
//	private LoginPage loginPage;
//	private HomePage homePage;
//
//	@BeforeMethod
//	public void setupPages() {
//
//		loginPage = new LoginPage(getDriver());
//		homePage = new HomePage(getDriver());
//
//	}
//
//	@Test
//	public void verifyValidLoginTest() {
//		loginPage.login("admin", "admin123");
//		Assert.assertTrue(homePage.isAdminTabVisible(), "Admin tab should be Visible after successfully Login");
//		homePage.logout();
//		staticWait(2);
//	}
//
////	@Test
////	public void invalidLogintest() {
////		loginPage.login("admin", "admin");
////		String expectedErrorMessage = "Invalid credentials";
////		Assert.assertTrue(loginPage.verifyErrorMessage(expectedErrorMessage), "Test Failed: Invalid Error Message");
////		;
////	}
//
//}



//  ----------------------------   NEW CODE  ------------------------------
//package com.orangeHRM.test;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import com.orangeHRM.base.BaseClass;
//import com.orangeHRM.pages.HomePage;
//import com.orangeHRM.pages.LoginPage;
//
//import java.time.Duration;
//
//public class LoginPageTest extends BaseClass {
//
//    private LoginPage loginPage;
//    private HomePage homePage;
//
//    @BeforeMethod
//    public void setupPages() {
//        loginPage = new LoginPage(getDriver());
//        homePage = new HomePage(getDriver());
//    }
//
//    @Test
//    public void verifyValidLoginTest() {
//        loginPage.login("admin", "admin123");
//
//        // Wait until Admin tab is visible (using HomePage method)
//        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(5));
//        wait.until(driver -> homePage.isAdminTabVisible());
//
//        Assert.assertTrue(homePage.isAdminTabVisible(), 
//            "Admin tab should be visible after successful login");
//
//        homePage.logout();
//
//        // Optional small static wait
//        staticWait(2);
//    }
//
//    // Optional static wait method
//    private void staticWait(int seconds) {
//        try {
//            Thread.sleep(seconds * 1000L);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//}
//


//                                         :: NEW CODE ::

package com.orangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.pages.HomePage;
import com.orangeHRM.pages.LoginPage;

public class LoginPageTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }

    @Test
    public void verifyValidLoginTest() {
        // Login
        boolean loginStatus = loginPage.login("admin", "admin123");
        Assert.assertTrue(loginStatus, "Login failed.");

        // Verify Admin tab visibility
        Assert.assertTrue(homePage.isAdminTabVisible(),
                "Admin tab should be visible after successful login.");

        // Logout
        homePage.logout();
    }
}


