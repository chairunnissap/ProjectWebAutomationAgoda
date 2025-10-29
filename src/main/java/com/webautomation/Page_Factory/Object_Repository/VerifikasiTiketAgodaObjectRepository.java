package com.webautomation.Page_Factory.Object_Repository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class VerifikasiTiketAgodaObjectRepository {

    
    public VerifikasiTiketAgodaObjectRepository(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
