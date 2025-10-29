package com.webautomation.Page_Factory.Object_Repository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class VerifikasiTiketAgodaObjectRepository {
    public By keberangkatanverifPenerbanganPergi = By.xpath("(//span[contains(text(), 'CGK')])[1]");
    public By tujuanverifPenerbanganPergi = By.xpath("(//span[contains(text(), 'SIN')])[1]");
    public By keberangkatanverifPenerbanganPulang = By.xpath("(//span[contains(text(), 'SIN')])[last()]");
    public By tujuanverifPenerbanganPulang = By.xpath("(//span[contains(text(), 'CGK')])[last()]");

    public By semuaMaskapai = By.xpath("//div[contains(@data-component, 'pacFlightDetail')]//span[" +
                        "contains(text(),'AirAsia') or contains(text(),'Citilink') or " +
                        "contains(text(),'Lion') or contains(text(),'Garuda') or " +
                        "contains(text(),'Aviation') or contains(text(),'Sriwijaya') or " +
                        "contains(text(),'Super Air Jet') or contains(text(),'Batik') or " +
                        "contains(text(),'Pelita')]");

    public By elemenHarga = By.xpath("//dd[@data-component='mob-flight-price-total-desc']//span");
    
    public VerifikasiTiketAgodaObjectRepository(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
