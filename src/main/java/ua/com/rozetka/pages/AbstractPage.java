package ua.com.rozetka.pages;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage{

    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage(String url) throws InterruptedException {
        driver.get(url);
        Thread.sleep(5000);             // a crutch for heavy rozetka page download
    }
}
