package com.pizzahq.tests;

import com.pizzahq.ui.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Pizza_Test_Suite {
    private ChromeDriver driver;

    @BeforeEach
    public void SetUp() {
        driver = new ChromeDriver();
        driver.get("https://d2yh0qhobk4vxr.cloudfront.net/#/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void Pizza_Mandatory_Verify_ErrorMessagesText_Test() {

        // Arrange
        var homePage = new HomePage(driver);
        homePage.click_Content_Button();
        homePage.click_Content_SubmitButton();

        // Act
        var errorMessages =driver.findElements(By.className("form-error"));
        new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOf(errorMessages.get(0)));

        //Assert
        Assertions.assertEquals("Forename is required",  driver.findElement(By.id("forename-err")).getText());
        Assertions.assertEquals("Email is required",  driver.findElement(By.id("email-err")).getText());
        Assertions.assertEquals("Message is required",  driver.findElement(By.id("message-err")).getText());

    }

    @Test
    public void Pizza_Mandatory_ValidInput_ErrorMessage_Test() {

        // Arrange
        var homePage = new HomePage(driver);

        // Act
        homePage.click_Content_Button();
        homePage.click_Content_SubmitButton();

        driver.findElement(By.id("forename")).sendKeys("Dan");
        driver.findElement(By.id("email")).sendKeys("dan@abc.com");
        driver.findElement(By.id("message")).sendKeys("Nice Pizza");

        // Assert
        Assertions.assertTrue( driver.findElements(By.id("forename-err")).size() == 0);
        Assertions.assertTrue( driver.findElements(By.id("email-err")).size() == 0);
        Assertions.assertTrue( driver.findElements(By.id("message-err")).size() == 0);

    }

    @Test
    public void NewSideDish_Test(){

        // Arrange
        var homePage = new HomePage(driver);

        // Act
        homePage.click_Menu_Button();
        homePage.click_Menu_Page_SidesButton();

        var newClass = driver.findElement(By.className("new"));
        var newFlag = newClass.findElement(By.tagName("span")).getText();
        if(newFlag.equals("NEW")){

            // Assert
                Assertions.assertEquals("Korean Sticky Wings", newClass.findElement(By.className("name")).getText());
                Assertions.assertEquals("Korean Sticky Wings", newClass.findElement(By.tagName("img")).getAttribute("alt"));
        }

    }


    @AfterEach
    public void CleanUp() {
        driver.quit();
    }

}
