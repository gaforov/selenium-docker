package com.flighttickets.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class _05_FlightItineraryConfirmationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//font[contains(text(), 'Confirmation')]")
    private WebElement flightConfirmationText;

    @FindBy(xpath = "//font[contains(text(), 'USD')]")
    private List<WebElement> prices;

    @FindBy(linkText = "SIGN-OFF")
    private WebElement signOffLink;

    public _05_FlightItineraryConfirmationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    /**
     * Price accepts int argument
     * @param price
     */
    public String getPrice(int price) {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationText));
        System.out.println(this.flightConfirmationText.getText());
        System.out.println(this.prices.get(price).getText());
        String ticketPrice = this.prices.get(price).getText();
        this.signOffLink.click();
        return ticketPrice;
    }
}
