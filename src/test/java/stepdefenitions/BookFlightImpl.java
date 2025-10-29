package stepdefenitions;
import java.util.List;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.webautomation.Page_Factory.Page.HomeAgodaPage;
import com.webautomation.Page_Factory.Page.IsiKontakAgodaPage;
import com.webautomation.Page_Factory.Page.TiketAgodaPage;
import com.webautomation.Page_Factory.Page.VerifikasiTiketAgodaPage;
import hook.Hooks;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;


public class BookFlightImpl {
    WebDriver driver;
    HomeAgodaPage homeAgodaPage;
    TiketAgodaPage tiketAgodaPage;

    public BookFlightImpl(Hooks hooks) {
        this.driver = Hooks.GetDriver();
    }

    @When("User can access web Agoda")
    public void User_can_access_web_Agoda(){
        System.out.println("Dapat Akses");
    }

    @Then("User click pesawat button")
    public void User_click_pesawat_button(){
        HomeAgodaPage homeAgodaPage = new HomeAgodaPage(driver);
        homeAgodaPage.GotoPesawat();
    }

    @When("User click PulangPergi button")
    public void User_click_PulangPergi_button(){
        HomeAgodaPage homeAgodaPage = new HomeAgodaPage(driver);
        homeAgodaPage.GotoPulangPergi();
    }

    @Then("User input dan pilih bandara keberangkatan {string} dan destinasi {string}")
    public void User_input_dan_pilih_bandara_keberangkatan_dan_destinasi(String bandaraberangkat, String bandaradestinasi){
        HomeAgodaPage homeAgodaPage = new HomeAgodaPage(driver);
        homeAgodaPage.InputBerangkat(bandaraberangkat);
        homeAgodaPage.InputDestinasi(bandaradestinasi);
    }

    @When("User click tgl brgkt dan tgl pulang")
    public void User_click_tgl_brgkt_dan_tgl_pulang(){
        HomeAgodaPage homeAgodaPage = new HomeAgodaPage(driver);
        homeAgodaPage.TglBrgkt();
        String tglBerangkat = HomeAgodaPage.getTomorrowDate(); // H+1
        homeAgodaPage.PilihTanggalBerangkat(tglBerangkat);

        homeAgodaPage.TglPulang();
        String tglPulang = HomeAgodaPage.getDatePlus(3);  // H+3
        homeAgodaPage.PilihTanggalPulang(tglPulang);
    }

    @Then("User click tombol penumpang dan cari penerbangan")
    public void User_click_tombol_penumpang_dan_cari_penerbangan() {
        HomeAgodaPage homeAgodaPage = new HomeAgodaPage(driver);
        homeAgodaPage.KlikPenumpang();
        homeAgodaPage.TambahPenumpangDewasa();
        // homeAgodaPage.TambahPenumpangAnak();
        homeAgodaPage.KlikCariPenerbangan();
    }

    @When("User filter maskapai {string}")
    public void User_filter_maskapai(String namaMaskapai) throws InterruptedException, TimeoutException {
        TiketAgodaPage tiketAgodaPage = new TiketAgodaPage(driver);
        tiketAgodaPage.pilihMaskapai(namaMaskapai);
        tiketAgodaPage.klikSortTermurah();
        Thread.sleep(4000);
        tiketAgodaPage.clickDetailButtonByIndex(0);
        Thread.sleep(4000);
    }

    @Then("User pilih maskapai terpilih")
    public void User_pilih_maskapai_terpilih(){
        TiketAgodaPage tiketAgodaPage = new TiketAgodaPage(driver);
        tiketAgodaPage.clickPilihButtonLanjutan();
    }

    @When("User verifikasi detail tiket")
    public void User_verifikasi_detail_tiket() throws InterruptedException{
        VerifikasiTiketAgodaPage verifikasitiketAgodaPage = new VerifikasiTiketAgodaPage(driver);
        Assert.assertTrue(verifikasitiketAgodaPage.verifikasiPenerbanganPergi(), "Penerbangan pergi tidak sesuai!");
        Assert.assertTrue(verifikasitiketAgodaPage.verifikasiPenerbanganPulang(), "Penerbangan pulang tidak sesuai!");
        List<String> daftarMaskapai = verifikasitiketAgodaPage.verifikasiAdaMaskapai();
        Assert.assertTrue(!daftarMaskapai.isEmpty(), "Tidak ada maskapai yang tampil di halaman!");
        Assert.assertTrue(verifikasitiketAgodaPage.verifikasiHarga() < 10000000, "Harga tiket lebih dari Rp 10 juta!");
    }

    @Then("User isi informasi kontak")
    public void User_isi_informasi_kontak(){
        IsiKontakAgodaPage isiKontakAgodaPage = new IsiKontakAgodaPage(driver);
        isiKontakAgodaPage.IsiKontak("Chairun", "Puspitasari", "kueichaa@gmail.com", "87886095151");
    }
}
