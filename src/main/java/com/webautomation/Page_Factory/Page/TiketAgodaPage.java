package com.webautomation.Page_Factory.Page;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.webautomation.AbstractComponents.AbstractComponents;
import com.webautomation.Page_Factory.Object_Repository.TiketAgodaObjectRepository;

public class TiketAgodaPage extends AbstractComponents{
    private TiketAgodaObjectRepository tiketAgodaObjectRepository;
    private WebDriver driver;

    public TiketAgodaPage(WebDriver driver){
        super(driver);
        this.tiketAgodaObjectRepository = new TiketAgodaObjectRepository(driver);
        this.driver = driver;
    }
    
    public void pilihMaskapai(String namaMaskapai) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            By maskapaiLocator = By.xpath("//p[normalize-space(text())='" + namaMaskapai + "']/ancestor::li");
            WebElement maskapaiElement = wait.until(ExpectedConditions.visibilityOfElementLocated(maskapaiLocator));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", maskapaiElement);
            wait.until(ExpectedConditions.elementToBeClickable(maskapaiElement));
            try {
                maskapaiElement.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", maskapaiElement);
            }
            System.out.println("Maskapai '" + namaMaskapai + "' berhasil dipilih.");
        } catch (Exception e) {
            System.out.println("Terjadi error saat memilih maskapai '" + namaMaskapai + "': " + e.getMessage());
        }
        Thread.sleep(4000);
    }

    public void klikSortTermurah() throws InterruptedException {
        By locator = tiketAgodaObjectRepository.sortTermurahButton;
        waitForVisibilityElement(locator);
        WebElement ele = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
        Thread.sleep(4000);
    }

    public void clickDetailButtonByIndex(int index) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            List<WebElement> flightCards = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    By.cssSelector("div[data-testid='flightCard-flight-detail']")
                )
            );
            System.out.println("Total flight card ditemukan: " + flightCards.size());
            if (flightCards.isEmpty()) {
                System.out.println("Tidak ada flight card yang ditemukan.");
                return;
            }
            if (index < 0 || index >= flightCards.size()) {
                System.out.println("Index " + index + " di luar jangkauan. Maksimum: " + (flightCards.size() - 1));
                return;
            }
            WebElement selectedCard = flightCards.get(index);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", selectedCard);
            Thread.sleep(1000);
            wait.until(ExpectedConditions.elementToBeClickable(selectedCard));
            try {
                selectedCard.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectedCard);
            }
            System.out.println("Berhasil klik flight card index ke-" + index);

        } catch (Exception e) {
            System.out.println("Gagal klik flight card: " + e.getMessage());
        }

        clickPilihButton();
    }

    public void klikButtonPilihPadaCard(int index) throws InterruptedException, TimeoutException {
        int maxRetry = 3;
        int attempt = 0;
        boolean success = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        
        while (attempt < maxRetry && !success) {
            try {
                attempt++;
                System.out.println("Mencoba klik tombol 'Pilih' pada card ke-" + index + " (Percobaan ke-" + attempt + ")");

                List<WebElement> flightCards = wait.until(ExpectedConditions
                        .visibilityOfAllElementsLocatedBy(By.cssSelector("[data-testid='drone-box']")));

                if (flightCards.isEmpty() || index >= flightCards.size()) {
                    throw new NoSuchElementException("Tidak ditemukan card ke-" + index);
                }

                WebElement card = flightCards.get(index);

                By pilihButton = By.cssSelector("button[data-element-name='flight-detail-select-button']");
                WebElement button = card.findElement(pilihButton);

                wait.until(ExpectedConditions.elementToBeClickable(button));

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
                Thread.sleep(500);

                button.click();
                System.out.println("Berhasil klik tombol 'Pilih' pada card ke-" + index);
                success = true;

            } catch (NoSuchElementException e) {
                System.out.println("Gagal menemukan tombol 'Pilih' pada percobaan ke-" + attempt);
                if (attempt < maxRetry) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ignored) {}
                } else {
                    throw new RuntimeException("Gagal klik tombol 'Pilih' setelah " + maxRetry + " percobaan.", e);
                }
            }
        }
    }

    public void clickPilihButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        try {
            WebElement pilihButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-component='flight-card-bookButton'][data-element-name='flight-detail-select-button']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", pilihButton);
            Thread.sleep(500);
            try {
                pilihButton.click();
            } catch (Exception e) {
                // Jika gagal klik biasa, pakai klik via JavaScript
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pilihButton);
            }
            System.out.println("Berhasil klik tombol 'Pilih'.");
        } catch (Exception e) {
            System.out.println("Terjadi error saat klik tombol 'Pilih': " + e.getMessage());
        }
    }

    public void clickPilihButtonLanjutan() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            WebElement pilihButtonLanjutan = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-testid='inline-flight-card-bookButton']")
            ));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", pilihButtonLanjutan);
            Thread.sleep(500);
            try {
                pilihButtonLanjutan.click();
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", pilihButtonLanjutan);
            }
            System.out.println("Berhasil klik tombol 'Pilih' lanjutan (inline-flight-card-bookButton).");
        } catch (Exception e) {
            System.out.println("Error saat klik tombol 'Pilih' lanjutan: " + e.getMessage());
        }
    }

    public void klikExpandDanPilih(int index) throws InterruptedException {
        Thread.sleep(4000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        int maxRetry = 2;

        for (int attempt = 1; attempt <= maxRetry; attempt++) {
            try {
                System.out.println("🔁 Percobaan ke-" + attempt + " untuk klik expand dan tombol 'Pilih'...");

                List<WebElement> cards = wait.until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.cssSelector("[data-testid='drone-box']")));

                if (cards.isEmpty()) {
                    throw new NoSuchElementException("Tidak ada card flight ditemukan!");
                }

                WebElement card = cards.get(index);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", card);

                System.out.println(cards);
                

                WebElement expandIcon = card.findElement(By.cssSelector("svg.a61f8-fill-product-primary"));

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", expandIcon);
                System.out.println("Klik ikon expand di card ke-" + index);

                By pilihBtn = By.cssSelector("button[data-element-name='flight-detail-select-button']");
                WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                        card.findElement(pilihBtn)));

                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
                button.click();

                System.out.println("Klik tombol 'Pilih' di card ke-" + index);
                return;

            } catch (Exception e) {
                System.out.println("Gagal pada percobaan ke-" + attempt + ": " + e.getMessage());
                if (attempt == maxRetry) {
                    throw new RuntimeException("Gagal setelah " + maxRetry + " percobaan: " + e.getMessage());
                }
                try {
                    Thread.sleep(2000); // delay sebelum retry
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void extractFlightsData() {
        waitForVisibilityElement(tiketAgodaObjectRepository.flightCards);
        List<WebElement> cards = driver.findElements(tiketAgodaObjectRepository.flightCards);
        System.out.println("Total flight cards: " + cards.size());
        for (WebElement card : cards) {
            try {
                String airline = card.findElement(By.cssSelector("img[data-testid='airline-logo'], p")).getAttribute("alt");
                String origin = card.findElement(By.cssSelector("p[data-testid='origin']")).getText();
                String destination = card.findElement(By.cssSelector("p[data-testid='destination']")).getText();
                String duration = card.findElement(By.cssSelector("span[data-testid='duration']")).getText();
                String finalPrice = card.findElement(By.cssSelector("span[data-testid='final-price']")).getText();
                System.out.println("Airline: " + airline);
                System.out.println("From: " + origin + " To: " + destination);
                System.out.println("Duration: " + duration);
                System.out.println("Price: " + finalPrice);
                System.out.println("-------------------------------");

            } catch (NoSuchElementException e) {
                System.out.println("Data incomplete in this flight card, skipping...");
            }
        }
    }

    public WebElement FilterNamaMaskapai(String namaMaskapai) {
        waitForVisibilityElement(tiketAgodaObjectRepository.flightCards);
        List<WebElement> nameMaskapai = driver.findElements(tiketAgodaObjectRepository.flightCards);
        return nameMaskapai.stream()
        .filter(item -> {
            String name = item.findElement(tiketAgodaObjectRepository.Logo).getAttribute("alt");
            System.out.println(name);
            return name.equalsIgnoreCase("Indonesia " + namaMaskapai);
        })
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Product name not found: " + namaMaskapai));
    }

    public void clickFlightDetail(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        List<WebElement> cards = driver.findElements(tiketAgodaObjectRepository.flightCards);
        if (index < 0 || index >= cards.size()) {
            throw new RuntimeException("Invalid flight card index: " + index);
        }
        WebElement button = cards.get(index).findElement(By.xpath("//div//span[@role='img']//svg[contains(@class,'a61f8-fill-product-primary')]\r\n" + //
                        ""));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", button);
        wait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public void TermurahPrice(){
        waitForVisibilityElement(tiketAgodaObjectRepository.termurahPrice);
        WebElement termurahButton = driver.findElement(tiketAgodaObjectRepository.termurahPrice);
        termurahButton.click();
        System.out.println("Berhasil klik tombol Termurah");
    }

    public void clickCheapestByAirline(String maskapai) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(tiketAgodaObjectRepository.flightCards));
        List<WebElement> cards = driver.findElements(tiketAgodaObjectRepository.flightCards);
        WebElement cheapestCard = null;
        int cheapestPrice = Integer.MAX_VALUE;
        for (int i = 0; i < cards.size(); i++) {
            try {
                cards = driver.findElements(tiketAgodaObjectRepository.flightCards);
                WebElement card = cards.get(i);
                WebElement airlineElement = null;
                try {
                    airlineElement = card.findElement(By.cssSelector("img[data-testid='airline-logo']"));
                } catch (NoSuchElementException e) {
                    airlineElement = card.findElement(By.xpath(".//p"));
                }
                String airline = airlineElement.getAttribute("alt");
                if (airline == null || !airline.equalsIgnoreCase(maskapai)) continue;
                WebElement priceEl = card.findElement(By.xpath(
                        ".//span[contains(@class,'gPcWqz')]"
                ));
                String priceText = priceEl.getText().replaceAll("[^0-9]", "");
                int price = Integer.parseInt(priceText);
                if (price < cheapestPrice) {
                    cheapestPrice = price;
                    cheapestCard = card;
                }
            } catch (StaleElementReferenceException e) {
                System.out.println("Element stale, re-try loop ke-" + i);
                i--;
            } catch (NoSuchElementException ignored) {
            }
        }
        if (cheapestCard != null) {
            try {
                By arrowLocator = tiketAgodaObjectRepository.arrowDetailByAirline(maskapai);
                wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(arrowLocator)
                ));
                WebElement button = driver.findElement(arrowLocator);
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
                button.click();
                System.out.println("Klik flight termurah maskapai " + maskapai + ": Rp " + cheapestPrice);
            } catch (StaleElementReferenceException e) {
                System.out.println("Arrow button");
                WebElement button = driver.findElement(tiketAgodaObjectRepository.arrowDetailByAirline(maskapai));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", button);
                button.click();
            }
        } else {
            System.out.println("Tidak ditemukan flight maskapai: " + maskapai);
        }
    }

}
