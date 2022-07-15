package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setupDriver() {
        WebDriver driver = WebDriverManager.chromedriver().create();
        this.driver = driver;
    }

    @AfterTest
    public void tearDown() {
        this.driver.quit();
    }
}
