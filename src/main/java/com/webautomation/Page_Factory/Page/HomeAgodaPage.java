package com.webautomation.Page_Factory.Page;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.By;
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
        waitForVisibilityElement(homeAgodaObjectRepository.Pesawat);
        WebElement pesawatLink = driver.findElement(homeAgodaObjectRepository.Pesawat);
        pesawatLink.click();
    }

    public void GotoPulangPergi(){
        waitForVisibilityElement(homeAgodaObjectRepository.PulangPergi);
        WebElement PPLink = driver.findElement(homeAgodaObjectRepository.PulangPergi);
        PPLink.click();
    }

    public void InputBerangkat(String berangkat){
        waitForVisibilityElement(homeAgodaObjectRepository.InputBerangkat2);
        homeAgodaObjectRepository.InputBerangkat.sendKeys(berangkat);
        waitForVisibilityElement(homeAgodaObjectRepository.SuggestionBerangkat);
        WebElement suggestionBerangkat = driver.findElement(homeAgodaObjectRepository.SuggestionBerangkat);
        suggestionBerangkat.click();
    }

    public void InputDestinasi(String destinasi){
        waitForVisibilityElement(homeAgodaObjectRepository.InputDestinasi2);
        homeAgodaObjectRepository.InputDestinasi.sendKeys(destinasi);
        waitForVisibilityElement(homeAgodaObjectRepository.SuggestionDestinasi);
        WebElement suggestionDestinasi = driver.findElement(homeAgodaObjectRepository.SuggestionDestinasi);
        suggestionDestinasi.click();
    }

    public void TglBrgkt(){
        WebElement tglBrgkt = driver.findElement(homeAgodaObjectRepository.TglBrgkt);
        tglBrgkt.click();
        waitForVisibilityElement(homeAgodaObjectRepository.TglBrgkt);
    }

    public void TglPulang(){
        WebElement tglPulang = driver.findElement(homeAgodaObjectRepository.TglPulang);
        tglPulang.click();
        waitForVisibilityElement(homeAgodaObjectRepository.TglPulang);
    }

    public static String getTomorrowDate() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return tomorrow.format(formatter);
    }

    public static String getDatePlus(int days) {
        LocalDate today = LocalDate.now();
        LocalDate result = today.plusDays(days);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return result.format(formatter);
    }

    public void PilihTanggalBerangkat(String tanggalbrgkt) {
        TglBrgkt();
        By elemenTanggalBrgkt = By.cssSelector("[data-selenium-date='" + tanggalbrgkt + "']");
        waitForVisibilityElement(elemenTanggalBrgkt);
        driver.findElement(elemenTanggalBrgkt).click();
    }

    public void PilihTanggalPulang(String tanggalpulang) {
        TglPulang();
        By elemenTanggalPulang = By.cssSelector("[data-selenium-date='" + tanggalpulang + "']");
        waitForVisibilityElement(elemenTanggalPulang);
        driver.findElement(elemenTanggalPulang).click();
    }

    public void KlikPenumpang() {
        waitForVisibilityElement(homeAgodaObjectRepository.Penumpang);
        driver.findElement(homeAgodaObjectRepository.Penumpang).click();
    }

    public void TambahPenumpangDewasa() {
        KlikPenumpang();
        driver.findElement(homeAgodaObjectRepository.BtnTambahDewasa).click();
    }

    public void TambahPenumpangAnak() {
        KlikPenumpang();
        for (int i = 0; i < 3; i++) {
            try {
                waitForVisibilityElement(homeAgodaObjectRepository.BtnTambahAnak);
                driver.findElement(homeAgodaObjectRepository.BtnTambahAnak).click();
                return;
            } catch (Exception e) {
                driver.findElement(homeAgodaObjectRepository.Penumpang).click();
            }
        }
        throw new RuntimeException("Gagal menemukan tombol tambah anak setelah 3 percobaan");
    }

    public void KlikCariPenerbangan() {
        waitForVisibilityElement(homeAgodaObjectRepository.BtnCariPenerbangan);
        driver.findElement(homeAgodaObjectRepository.BtnCariPenerbangan).click();
    }
}
