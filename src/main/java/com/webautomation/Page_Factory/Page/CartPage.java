package com.webautomation.Page_Factory.Page;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.webautomation.AbstractComponents.AbstractComponents;
import com.webautomation.Page_Factory.Object_Repository.CartObjectRepository;

public class CartPage extends AbstractComponents{
    private CartObjectRepository cartObjectRepository;
    private WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.cartObjectRepository = new CartObjectRepository(driver);
        this.driver = driver;
    }

    public void GoToCart() {
        WebElement cartLink = driver.findElement(cartObjectRepository.cartLink);
        cartLink.click();
    }

    public WebElement VerifyName(String productName) {
        waitForVisibilityElement(cartObjectRepository.listProductVerif);
        List<WebElement> productVerif = driver.findElements(cartObjectRepository.listProductVerif);
        return productVerif.stream()
        .filter(item -> {
            String name = item.findElement(cartObjectRepository.Name).getText().trim();
            return name.equalsIgnoreCase(productName);
        })
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Product name not found: " + productName));
    }

    public WebElement VerifyPrice(String productPrice) {
        waitForVisibilityElement(cartObjectRepository.listProductVerif);
        List<WebElement> productVerif = driver.findElements(cartObjectRepository.listProductVerif);
        return productVerif.stream()
        .filter(item -> {
            String price = item.findElement(cartObjectRepository.Price).getText().trim();
            return price.equalsIgnoreCase(productPrice);
        })
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Product price not found: " + productPrice));
    }

    public WebElement VerifyQty(String productQty) {
        waitForVisibilityElement(cartObjectRepository.listProductVerif);
        List<WebElement> productVerif = driver.findElements(cartObjectRepository.listProductVerif);
        return productVerif.stream()
        .filter(item -> {
            String qty = item.findElement(cartObjectRepository.Qty).getText().trim();
            return qty.equalsIgnoreCase(productQty);
        })
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Product qty not found: " + productQty));
    }

    public void VerifyProductInCart(String productName,String productPrice,String productQty) {
        VerifyName(productName);
        VerifyPrice(productPrice);
        VerifyQty(productQty);
        driver.findElement(cartObjectRepository.Continue).click();
    }
}
