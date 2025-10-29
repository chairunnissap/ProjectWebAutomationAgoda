package com.webautomation.Page_Factory.Page;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.webautomation.AbstractComponents.AbstractComponents;
import com.webautomation.Page_Factory.Object_Repository.VerifikasiTiketAgodaObjectRepository;


public class VerifikasiTiketAgodaPage extends AbstractComponents {
    private VerifikasiTiketAgodaObjectRepository verifikasiTiketAgodaObjectRepository;
    private WebDriver driver;
    public String expectedRute;
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    public VerifikasiTiketAgodaPage(WebDriver driver){
        super(driver);
        this.verifikasiTiketAgodaObjectRepository = new VerifikasiTiketAgodaObjectRepository(driver);
        this.driver = driver;
    }

    public boolean verifikasiPenerbanganPergi() {
        WebElement keberangkatan = driver.findElement(By.xpath("(//span[contains(text(), 'CGK')])[1]"));
        WebElement tujuan = driver.findElement(By.xpath("(//span[contains(text(), 'SIN')])[1]"));
        String teksKeberangkatan = keberangkatan.getText();
        String teksTujuan = tujuan.getText();
        boolean valid = teksKeberangkatan.contains("CGK") && teksTujuan.contains("SIN");
        if (valid) {
            System.out.println("Verifikasi Pergi: " + teksKeberangkatan + " → " + teksTujuan);
        } else {
            System.out.println("Verifikasi gagal: " + teksKeberangkatan + " → " + teksTujuan);
        }
        return valid;
    }

    
    public boolean verifikasiPenerbanganPulang() {
        WebElement keberangkatan = driver.findElement(By.xpath("(//span[contains(text(), 'SIN')])[last()]"));
        WebElement tujuan = driver.findElement(By.xpath("(//span[contains(text(), 'CGK')])[last()]"));
        String teksKeberangkatan = keberangkatan.getText();
        String teksTujuan = tujuan.getText();
        boolean valid = teksKeberangkatan.contains("SIN") && teksTujuan.contains("CGK");
        if (valid) {
            System.out.println("Verifikasi Pulang: " + teksKeberangkatan + " → " + teksTujuan);
        } else {
            System.out.println("Verifikasi gagal: " + teksKeberangkatan + " → " + teksTujuan);
        }
        return valid;
    }

    public boolean verifikasiAdaMaskapaiAirAsia() {
        List<WebElement> semuaMaskapai = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath("//span[contains(text(),'AirAsia') or contains(text(),'Citilink') or contains(text(),'Lion') or contains(text(),'Garuda')]")
            )
        );
        boolean ditemukanAirAsia = semuaMaskapai.stream()
            .anyMatch(el -> el.getText().toLowerCase().contains("airasia"));

        if (ditemukanAirAsia) {
            System.out.println("Maskapai AirAsia ditemukan di daftar penerbangan");
        } else {
            System.out.println("Tidak ada maskapai AirAsia di daftar penerbangan!");
        }
        return ditemukanAirAsia;
    }

    public double verifikasiHarga() {
        WebElement elemenHarga = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//dd[@data-component='mob-flight-price-total-desc']//span")
            )
        );
        String hargaText = elemenHarga.getText().trim();
        System.out.println("Harga ditemukan di halaman: " + hargaText);
        String hargaClean = hargaText.replaceAll("[^0-9]", "");
        double harga = Double.parseDouble(hargaClean);
        double batas = 5_000_000;
        boolean valid = harga < batas;
        if (valid) {
            System.out.println("Harga valid: Rp " + hargaText + " (kurang dari Rp 5.000.000)");
        } else {
            System.out.println("Harga melebihi batas 5 juta! Harga ditemukan: Rp " + hargaText);
        }
        return harga;
    }

}
