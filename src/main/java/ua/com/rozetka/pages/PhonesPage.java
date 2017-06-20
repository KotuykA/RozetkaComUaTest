package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PhonesPage extends AbstractPage {

    private WebElement smartphonesLinkElement;
    private By smartphonesLinkXPathLocator = By.xpath(".//*[@id='menu_categories_left']/li[1]/div/a[1]");

    public PhonesPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSmartphonesLink() {
        smartphonesLinkElement = driver.findElement(smartphonesLinkXPathLocator);
        return smartphonesLinkElement;
    }
}
