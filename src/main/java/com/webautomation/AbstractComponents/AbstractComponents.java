package com.webautomation.AbstractComponents;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
    WebDriver driver;

    public AbstractComponents(WebDriver driver){
        this.driver = driver;
    }

    public void waitForVisibilityElement(By findBy){
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
         wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void refreshForVisibilityElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.refreshed(
        ExpectedConditions.visibilityOfElementLocated(locator)
    ));
}
}
