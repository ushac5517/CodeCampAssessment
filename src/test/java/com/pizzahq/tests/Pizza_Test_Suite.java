package com.pizzahq.tests;

import com.pizzahq.ui.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        homePage.content_Button_Click();
        homePage.content_SubmitButton_Click();

        // Act
        var errorMessages =driver.findElements(By.className("form-error"));
        new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOf(errorMessages.get(0)));

        //Assert
        Assertions.assertEquals("Forename is required", errorMessages.get(0).getText());
        Assertions.assertEquals("Email is required", errorMessages.get(1).getText());
        Assertions.assertEquals("Message is required", errorMessages.get(2).getText());

    }

    @Test
    public void Pizza_Mandatory_ValidInput_ErrorMessage_Test() {

        // Arrange
        var homePage = new HomePage(driver);
        homePage.content_Button_Click();
        homePage.content_SubmitButton_Click();

        // Act
        driver.findElement(By.id("forename")).sendKeys("Dan");
        driver.findElement(By.id("email")).sendKeys("dan@abc.com");
        driver.findElement(By.id("message")).sendKeys("Nice Pizza");

        // Assert
        Assertions.assertTrue( driver.findElements(By.id("forename-err")).size() == 0);
        Assertions.assertTrue( driver.findElements(By.id("forename-err")).size() == 0);
        Assertions.assertTrue( driver.findElements(By.id("forename-err")).size() == 0);

    }

    @Test
    public void NewSideDish_Test(){
        var homePage = new HomePage(driver);
        homePage.menu_Button_Click();
        homePage.menu_Page_SidesButton_Click();


        var flashcards = driver.findElements(By.className("v-card__text"));
        for( var flashcard : flashcards ) {
            var newFlag = flashcard.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div/div[2]/div[2]/div/div[2]/div/div[10]/div/div[1]/div/span")).getAttribute("value");
            System.out.println(newFlag=="New");
            if(newFlag == "NEW"){
                Assertions.assertEquals("Koren Sticky Wings", flashcard.findElement(By.className("name")).getText());
            }

        }
    }

    @AfterEach
    public void CleanUp() {
        driver.quit();
    }

}
