package com.webautomation.Page_Factory.Page;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.webautomation.AbstractComponents.AbstractComponents;
import com.webautomation.Page_Factory.Object_Repository.HomeAgodaObjectRepository;

public class HomeAgodaPage extends AbstractComponents{

    private HomeAgodaObjectRepository homeAgodaObjectRepository;
    private WebDriver driver;

    public HomeAgodaPage(WebDriver driver){
        super(driver);
        this.homeAgodaObjectRepository = new HomeAgodaObjectRepository(driver);
        this.driver = driver;
    }

    public void GotoPesawat(){
        WebElement pesawatLink = driver.findElement(homeAgodaObjectRepository.Pesawat);
        pesawatLink.click();
    }

}
