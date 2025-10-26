package com.webautomation.Page_Factory.Object_Repository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomeAgodaObjectRepository {
    public By Pesawat = By.xpath("//h6[contains(@class,'Typographystyled__TypographyStyled') and text()='Pesawat']");

    public HomeAgodaObjectRepository(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
