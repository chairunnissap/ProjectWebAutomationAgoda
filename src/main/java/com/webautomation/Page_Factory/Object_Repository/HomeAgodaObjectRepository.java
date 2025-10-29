package com.webautomation.Page_Factory.Object_Repository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeAgodaObjectRepository {
    @FindBy(css = "input[data-selenium='flight-origin-search-input']")
    public WebElement InputBerangkat;

    @FindBy(id = "flight-destination-search-input")
    public WebElement InputDestinasi;

    public By Pesawat = By.xpath("//li[@data-selenium='agodaFlightsTab']");
    public By PulangPergi = By.xpath("//button[@data-element-object-id='round-trip']");
    public By InputBerangkat2 = By.xpath("//input[@data-selenium='flight-origin-search-input']");
    public By InputDestinasi2 = By.cssSelector("input[data-selenium='flight-destination-search-input']");
    public By SuggestionBerangkat = By.xpath("//li[@data-element-object-id='HLP']");
    public By SuggestionDestinasi = By.cssSelector("li[data-element-object-id='SIN']");
    public By TglBrgkt = By.id("flight-departure");
    public By TglPulang = By.cssSelector("[data-component='flight-search-returnDate']");
    public By Penumpang = By.cssSelector("[data-component='flight-search-occupancy']");
    public By BtnTambahDewasa = By.cssSelector("[data-element-name='flight-occupancy-adult-increase']");
    public By BtnTambahAnak = By.cssSelector("[data-element-name='flight-occupancy-children-increase']");
    public By BtnCariPenerbangan = By.cssSelector("[data-element-name='flight-search']");

    public HomeAgodaObjectRepository(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
