package stepdefenitions;
import org.openqa.selenium.WebDriver;
import com.webautomation.Page_Factory.Page.HomeAgodaPage;
import hook.Hooks;
import io.cucumber.java.en.When;

public class BookFlightImpl {
    WebDriver driver;
    HomeAgodaPage homeAgodaPage;

    public BookFlightImpl(Hooks hooks) {
        this.driver = Hooks.GetDriver();
    }

    @When("User can access web Agoda")
    public void User_can_access_web_Agoda(){
        HomeAgodaPage homeAgodaPage = new HomeAgodaPage(driver);
        homeAgodaPage.GotoPesawat();
    }
}
