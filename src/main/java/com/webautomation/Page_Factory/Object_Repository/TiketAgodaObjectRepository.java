package com.webautomation.Page_Factory.Object_Repository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class TiketAgodaObjectRepository {

    public By flightCards = By.cssSelector("div[data-testid='web-refresh-flights-card']");
    // public By PilihMaskapai = By.xpath("//ul[@aria-label='Maskapai']//li[@data-testid='flight-filter-item-airline']//p");
    // public By PilihMaskapai = By.xpath("//li[@data-testid='flight-filter-item-airline']//p");
    public By PilihMaskapai = By.cssSelector("ul[aria-label='Maskapai'] li[data-testid='flight-filter-item-airline']");


    public By Logo = By.cssSelector("img[data-testid='airline-logo'], p");
    public By CheapestCard = By.xpath("//div[@data-testid='web-refresh-flights-card']//svg[contains(@mask,'1a7dba98.svg')]");
    public By termurahPrice = By.xpath("//div[@data-testid='flights-quick-sort-item-Price']//p[contains(text(),'Rp')]");
    public By sortTermurahButton = By.xpath("//button[@data-testid='flights-quick-sort-item-Price-button' or .//p[text()='Termurah']]");
    public By airAsiaCheckbox = By.xpath("//label[contains(.,'AirAsia')]/preceding-sibling::input");

    public TiketAgodaObjectRepository(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public By arrowDetailByAirline(String namaMaskapai) {
        return By.xpath("//p[text()='" + namaMaskapai + "']/ancestor::div[@data-testid='web-refresh-flights-card']//svg");
    }

    public By checkboxMaskapai(String namaMaskapai) {
        return By.xpath("//li[@data-testid='flight-filter-item-airline']" +
                        "[.//p[text()='" + namaMaskapai + "']]");
    }

}
