package com.pizzahq.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public void click_Content_Button(){
        driver.findElement(By.cssSelector("[aria-label=contact]")).click();
    }

    public void click_Content_SubmitButton(){
        driver.findElement(By.cssSelector("[aria-label=submit]")).click();
    }

    public void click_Menu_Button() {
        driver.findElement(By.cssSelector("[aria-label=menu]")).click();
    }
    public void click_Menu_Page_SidesButton() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div/div[2]/div[1]/div[2]/div/div[3]")).click();
    }


}
