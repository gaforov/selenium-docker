package com.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext iTestContext) throws MalformedURLException {
        String host = "localhost"; //port will be 4444, full path to Sel Grid: localhost:4444
        MutableCapabilities dc;

        if (System.getProperty("BROWSER") != null &&
                System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
            dc = new FirefoxOptions();
        } else {
            dc = new ChromeOptions();
        }

        if (System.getProperty("HUB_HOST") != null) {
            host = System.getProperty("HUB_HOST");
        }

        String testName = iTestContext.getCurrentXmlTest().getName();

        String fullURL = "http://" + host + ":4444/wd/hub"; // "/wd/hub" is optional, still works without adding it.
        dc.setCapability("name", testName);

        // Turn this on to run on remotely (Selenium Grid/Docker), when you run local WebDriver comment this out. Use one at a time.
        this.driver = new RemoteWebDriver(new URL(fullURL), dc);

        // No need for local WebDriver (when we run on Selenium Grid comment this out).
//        WebDriver driver = WebDriverManager.chromedriver().create();
//        this.driver = driver;
    }

    @AfterTest
    public void tearDown() {
        this.driver.quit();
    }
}
