package com.search.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    public static String searchKeyword;
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(name = "q")
    private WebElement searchField;

    @FindBy(id = "search_button_homepage")
    private WebElement searchBtn;

    @FindBy(xpath = "//a[@data-zci-link='videos']")
    private WebElement videosLink;
    @FindBy(xpath = "//div[contains(@class, 'tile--vid')]")
    private List<WebElement> allVideos;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void goTo() {
        this.driver.get("https://duckduckgo.com");
    }

    public void doSearch(String searchKeyword) {
        SearchPage.searchKeyword = searchKeyword;
        this.wait.until(ExpectedConditions.visibilityOf(this.searchField));
        searchField.sendKeys(searchKeyword);
        this.searchBtn.click();
    }

    public void goToVideos() {
        this.wait.until(ExpectedConditions.visibilityOf(this.videosLink));
        this.videosLink.click();
    }

    public int getResult() {
        By by = By.xpath("//div[contains(@class, 'tile--vid')]");
        this.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, 0));
        System.out.println("Total videos for search result of \"" + searchKeyword + "\" = " + this.allVideos.size());
        return this.allVideos.size();
    }
}
