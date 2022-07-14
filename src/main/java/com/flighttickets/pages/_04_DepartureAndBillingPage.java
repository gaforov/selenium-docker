package com.flighttickets.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class _04_DepartureAndBillingPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "reserveFlights")
    private WebElement departureContinueBtn;

    @FindBy(id = "buyFlights")
    private WebElement billingAddressPageContinueBtn;

    public _04_DepartureAndBillingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void submitDepartureFlightPage() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.departureContinueBtn));
        this.departureContinueBtn.click();
    }

    public void goToItineraryConfirmationPage() {
        this.wait.until(ExpectedConditions.elementToBeClickable(this.billingAddressPageContinueBtn));
        this.billingAddressPageContinueBtn.click();
    }
}
