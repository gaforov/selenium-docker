package com.flighttickets.tests;

import com.flighttickets.pages.*;
import com.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BookFlightTests extends BaseTest {
    private String noOfPassengers;
    private String expectedPrice;

    @BeforeTest
    @Parameters({"noOfPassengers", "expectedPrice"})
    public void setupParameters(String noOfPassengers, String expectedPrice) {
        this.noOfPassengers = noOfPassengers;
        this.expectedPrice = expectedPrice;
    }

    @Test
    public void registrationPageTest() {
        _01_RegistrationPage registrationPage = new _01_RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("John", "Doe");
        registrationPage.enterUserCredentials("johndoe","jd1234");
        registrationPage.submit();
    }

    @Test(dependsOnMethods = "registrationPageTest")
    public void registrationConfirmationPage() {
        _02_RegistrationConfirmationPage registrationConfirmationPage = new _02_RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPage")
    public void flightDetailsPage() {
        _03_FlightDetailsPage flightDetailsPage = new _03_FlightDetailsPage(driver);
        flightDetailsPage.selectNumberOfPassengers(noOfPassengers);
        flightDetailsPage.goToDeparturePage();
    }

    @Test(dependsOnMethods = "flightDetailsPage")
    public void departurePage() {
        _04_DepartureAndBillingPage departureAndBillingPage = new _04_DepartureAndBillingPage(driver);
        departureAndBillingPage.submitDepartureFlightPage();
        departureAndBillingPage.goToItineraryConfirmationPage();
    }

    @Test(dependsOnMethods = "departurePage")
    public void flightConfirmationPage() {
        _05_FlightItineraryConfirmationPage itineraryConfirmationPage = new _05_FlightItineraryConfirmationPage(driver);
        String actualPrice = itineraryConfirmationPage.getPrice(1);// price starts from index 0, we need total price which is index of 1.
        Assert.assertEquals(actualPrice, expectedPrice, "Price mismatch");
    }

}
