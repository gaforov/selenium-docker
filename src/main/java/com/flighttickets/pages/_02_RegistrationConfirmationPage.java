package com.flighttickets.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class _02_RegistrationConfirmationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(partialLinkText = "sign-in")
    private WebElement singInLink;

    @FindBy(linkText = "Flights")
    private WebElement flightsLink;

    public _02_RegistrationConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void goToFlightDetailsPage() {
        this.wait.until(ExpectedConditions.visibilityOf(this.singInLink));
        this.flightsLink.click();
    }

}
