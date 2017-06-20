package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends AbstractPage {

    private WebElement mainPageLinkElement;
    private By mainPageLinkIDLocator = By.id("3361");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getMainPageLink() {
        mainPageLinkElement = driver.findElement(mainPageLinkIDLocator);
        return mainPageLinkElement;
    }
}
