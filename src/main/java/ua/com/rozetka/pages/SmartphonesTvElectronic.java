package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SmartphonesTvElectronic extends AbstractPage {

    private WebElement phonesLinkElement;
    private By phonesLinkXPathLocator = By.xpath(".//*[@id='menu_categories_left']/li[1]/h4/a");

    public SmartphonesTvElectronic(WebDriver driver) {
        super(driver);
    }

    public WebElement getPhonesLink() {
        phonesLinkElement = driver.findElement(phonesLinkXPathLocator);
        return phonesLinkElement;
    }
}
