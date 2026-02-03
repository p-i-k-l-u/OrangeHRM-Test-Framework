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
//public class HomePageTest extends BaseClass {
//	
//	 private LoginPage loginPage;
//	 private HomePage homePage;
//	 
//	 @BeforeMethod
//	 public void setupPages() {
//		 loginPage = new LoginPage(getDriver());
//		 homePage = new HomePage(getDriver());
//	 }
//	 
//	 @Test
//	 public void verifyOrnageHRMLogo() {
//		 loginPage.login("admin", "admin123");
//		 Assert.assertTrue(homePage.verifyOrangeHRMlogo(),"Logo Is Not Visible");
//	 }
//	
//	
//
//}


//                        :: NEW CODE :: 

package com.orangeHRM.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangeHRM.base.BaseClass;
import com.orangeHRM.pages.HomePage;
import com.orangeHRM.pages.LoginPage;

public class HomePageTest extends BaseClass {

    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(getDriver());
        homePage = new HomePage(getDriver());
    }

    @Test
    public void verifyOrangeHRMLogo() {
        // Login first
        boolean loginStatus = loginPage.login("admin", "admin123");
        Assert.assertTrue(loginStatus, "Login failed. Cannot verify logo.");

        // Verify Logo visibility
        Assert.assertTrue(homePage.verifyOrangeHRMlogo(), "OrangeHRM logo is not visible.");
    }
}







