package com.webautomation.Page_Factory.Page;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webautomation.AbstractComponents.AbstractComponents;
import com.webautomation.Page_Factory.Object_Repository.IsiKontakAgodaObjectRepository;

public class IsiKontakAgodaPage extends AbstractComponents{
    public IsiKontakAgodaObjectRepository isiKontakAgodaObjectRepository;
    public WebDriver driver;

    public IsiKontakAgodaPage(WebDriver driver){
        super(driver);
        this.isiKontakAgodaObjectRepository = new IsiKontakAgodaObjectRepository(driver);
        this.driver = driver;
    }

    public void IsiKontak(String namaDepan, String namaBelakang, String email, String noTelp) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Isi Nama Depan
        WebElement inputNamaDepan = wait.until(ExpectedConditions.visibilityOfElementLocated(
            isiKontakAgodaObjectRepository.inputNamaDepan
        ));
        inputNamaDepan.clear();
        inputNamaDepan.sendKeys(namaDepan);
        System.out.println("Nama depan diisi: " + namaDepan);

        // Isi Nama Belakang
        WebElement inputNamaBelakang = wait.until(ExpectedConditions.visibilityOfElementLocated(
            isiKontakAgodaObjectRepository.inputNamaBelakang
        ));
        inputNamaBelakang.clear();
        inputNamaBelakang.sendKeys(namaBelakang);
        System.out.println("Nama belakang diisi: " + namaBelakang);

        // Isi Email
        WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(
            isiKontakAgodaObjectRepository.inputEmail
        ));
        inputEmail.clear();
        inputEmail.sendKeys(email);
        System.out.println("Email diisi: " + email);

        // Isi Nomor Telepon
        WebElement inputNoTelp = wait.until(ExpectedConditions.visibilityOfElementLocated(
            isiKontakAgodaObjectRepository.inputNoTelp
        ));
        inputNoTelp.clear();
        inputNoTelp.sendKeys(noTelp);
        System.out.println("Nomor telepon diisi: " + noTelp);
    }

}