package com.pizzahq.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    public void content_Button_Click(){
        driver.findElement(By.cssSelector("[aria-label=contact]")).click();
    }

    public void content_SubmitButton_Click(){
        driver.findElement(By.cssSelector("[aria-label=submit]")).click();
    }

    public void menu_Button_Click() {
        driver.findElement(By.cssSelector("[aria-label=menu]")).click();
    }
    public void menu_Page_SidesButton_Click() {
        driver.findElement(By.xpath("//*[@id=\"app\"]/div/main/div/div/div[2]/div[1]/div[2]/div/div[3]")).click();
    }


}
