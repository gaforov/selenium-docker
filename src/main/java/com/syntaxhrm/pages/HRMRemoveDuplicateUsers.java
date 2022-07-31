package com.syntaxhrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public class HRMRemoveDuplicateUsers {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(id = "txtUsername")
    private WebElement username;
    @FindBy(id = "txtPassword")
    private WebElement password;
    @FindBy(id = "btnLogin")
    private WebElement loginBtn;
    @FindBy(id = "menu_pim_viewPimModule")
    private WebElement PIMmenuLink;
    @FindBy(id = "menu_pim_viewEmployeeList")
    private WebElement employeeListLink;
    @FindBy(id = "//table[@id='resultTable']/tbody/tr/td[2]")
    private List<WebElement> empID;
    @FindBy(id = "//table[@id='resultTable']/tbody/tr/td[3]")
    private List<WebElement> firstAndMiddleName;
    @FindBy(id = "//table[@id='resultTable']/tbody/tr/td[4]")
    private List<WebElement> lastName;
    @FindBy(id = "//table[@id='resultTable']/tbody/tr/td[5]")
    private List<WebElement> jobTitle;
    @FindBy(id = "//table[@id='resultTable']/tbody/tr/td[6]")
    private List<WebElement> employmentStatus;
//    @FindBy(id = "menu_pim_viewPimModule")
//    private WebElement PIMmenuLink;
//    @FindBy(id = "menu_pim_viewPimModule")
//    private WebElement PIMmenuLink;


    public HRMRemoveDuplicateUsers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void goToHomePage() {
        this.driver.get("http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login");
    }

    public void loginAsAdmin(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);
        loginBtn.click();
    }

    public void goToEmployeeList() {
        PIMmenuLink.click();
        employeeListLink.click();
    }

    public void grabEmployeeDetails() {
        Map<String, List<String>> mapList = new HashMap<>();
        List<String> employeeInfo = new ArrayList<>();
        for (int i = 0; i < empID.size(); i++) {
            employeeInfo.add(firstAndMiddleName.get(i).getText());
            employeeInfo.add(lastName.get(i).getText());
            employeeInfo.add(jobTitle.get(i).getText());
            employeeInfo.add(employmentStatus.get(i).getText());

            mapList.put(empID.get(i).getText(), employeeInfo);
        }
        mapList.forEach((k,v) -> System.out.println(k + " " + v));

    }
}
