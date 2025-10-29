package com.webautomation.Page_Factory.Page;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.webautomation.AbstractComponents.AbstractComponents;
import com.webautomation.Page_Factory.Object_Repository.VerifikasiTiketAgodaObjectRepository;


public class VerifikasiTiketAgodaPage extends AbstractComponents {
    public VerifikasiTiketAgodaObjectRepository verifikasiTiketAgodaObjectRepository;
    public WebDriver driver;
    public String expectedRute;

    public VerifikasiTiketAgodaPage(WebDriver driver){
        super(driver);
        this.verifikasiTiketAgodaObjectRepository = new VerifikasiTiketAgodaObjectRepository(driver);
        this.driver = driver;
    }

    public boolean verifikasiPenerbanganPergi() {
        WebElement keberangkatan = driver.findElement(verifikasiTiketAgodaObjectRepository.keberangkatanverifPenerbanganPergi);
        WebElement tujuan = driver.findElement(verifikasiTiketAgodaObjectRepository.tujuanverifPenerbanganPergi);
        String teksKeberangkatan = keberangkatan.getText();
        String teksTujuan = tujuan.getText();
        boolean valid = teksKeberangkatan.contains("CGK") && teksTujuan.contains("SIN");
        if (valid) {
            System.out.println("Verifikasi Pergi: " + teksKeberangkatan + " → " + teksTujuan);
        } else {
            System.out.println("Verifikasi pergi gagal: " + teksKeberangkatan + " → " + teksTujuan);
        }
        return valid;
    }
    
    public boolean verifikasiPenerbanganPulang() {
        WebElement keberangkatan = driver.findElement(verifikasiTiketAgodaObjectRepository.keberangkatanverifPenerbanganPulang);
        WebElement tujuan = driver.findElement(verifikasiTiketAgodaObjectRepository.tujuanverifPenerbanganPulang);
        String teksKeberangkatan = keberangkatan.getText();
        String teksTujuan = tujuan.getText();
        boolean valid = teksKeberangkatan.contains("SIN") && teksTujuan.contains("CGK");
        if (valid) {
            System.out.println("Verifikasi Pulang: " + teksKeberangkatan + " → " + teksTujuan);
        } else {
            System.out.println("Verifikasi pulang gagal: " + teksKeberangkatan + " → " + teksTujuan);
        }
        return valid;
    }

    public List<String> verifikasiAdaMaskapai() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<WebElement> semuaMaskapai = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(verifikasiTiketAgodaObjectRepository.semuaMaskapai)
        );
        List<String> namaMaskapai = semuaMaskapai.stream()
            .map(el -> el.getText().trim())
            .filter(n -> !n.isEmpty())
            .distinct()
            .collect(Collectors.toList());

        System.out.println("Maskapai yang ditemukan di halaman:");
        namaMaskapai.forEach(System.out::println);
        boolean adaAirAsia = namaMaskapai.stream()
            .anyMatch(nama -> nama.toLowerCase().contains("airasia"));
        if (adaAirAsia) {
            System.out.println("Terdapat penerbangan AirAsia di daftar.");
        } else {
            System.out.println("Tidak ada penerbangan AirAsia di daftar.");
        }
        return namaMaskapai;
    }

    public double verifikasiHarga() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement elemenHarga = wait.until(
            ExpectedConditions.visibilityOfElementLocated(verifikasiTiketAgodaObjectRepository.elemenHarga)
        );
        String hargaText = elemenHarga.getText().trim();
        System.out.println("Harga ditemukan di halaman: " + hargaText);
        String hargaClean = hargaText.replaceAll("[^0-9]", "");
        double harga = Double.parseDouble(hargaClean);
        double batas = 10_000_000;
        boolean valid = harga < batas;
        if (valid) {
            System.out.println("Harga valid: Rp " + hargaText + " (kurang dari Rp 10.000.000)");
        } else {
            System.out.println("Harga melebihi batas 10 juta! Harga ditemukan: Rp " + hargaText);
        }
        return harga;
    }

}
