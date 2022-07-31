package com.syntaxhrm.tests;

import com.syntaxhrm.pages.HRMRemoveDuplicateUsers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Using this automation, once complete, I will delete duplicate employees from the
 * EmployeeList of HRM database.
 */
public class SyntaxHRMTests {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void startDriver() {
        WebDriver driver = WebDriverManager.chromedriver().create();
        this.driver = driver;
    }

    @Test
    public void testPrintEmployeeList() {
        var duplicateUsers = new HRMRemoveDuplicateUsers(driver);
        duplicateUsers.goToHomePage();
        duplicateUsers.loginAsAdmin("Admin", "Hum@nhrm123");
        duplicateUsers.goToEmployeeList();
        duplicateUsers.grabEmployeeDetails();

    }

    @AfterTest
    public void quitDriver() {
        this.driver.quit();
    }

}
