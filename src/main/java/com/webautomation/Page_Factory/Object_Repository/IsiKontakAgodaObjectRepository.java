package com.webautomation.Page_Factory.Object_Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class IsiKontakAgodaObjectRepository {

    public By inputNamaDepan = By.xpath("//input[@id='contact.contactFirstName']");
    public By inputNamaBelakang = By.xpath("//input[@id='contact.contactLastName']");
    public By inputEmail = By.xpath("//input[@id='contact.contactEmail']");
    public By inputNoTelp = By.xpath("//input[@id='contact.contactPhoneNumber']");
    
    public IsiKontakAgodaObjectRepository(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
