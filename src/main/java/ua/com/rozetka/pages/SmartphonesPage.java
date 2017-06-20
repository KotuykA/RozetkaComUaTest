package ua.com.rozetka.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ua.com.rozetka.util.PageUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SmartphonesPage extends AbstractPage {

    private Map prodsList = new HashMap();

    private WebElement firstPageSmPhonesElement;
    private WebElement secondPageSmPhonesElement;
    private WebElement thirdPageSmPhonesElement;

    private By productsBlockXPathLocator = By.xpath(".//*[@id='catalog_goods_block']/div[@name='goods_list']/div/div" +
            "/div/div/div[@class='g-i-tile-i-box-desc']");
    private By firstPageSmPhonesIDLocator = By.id("page1");
    private By secondPageSmPhonesIDLocator = By.id("page2");
    private By thirdPageSmPhonesIDLocator = By.id("page3");
    private By topRatedProdsXpathLocator = By.xpath(".//i[@name='prices_active_element_original']");
    private By prodNameXpathLocator = By.xpath(".//div[@class='g-i-tile-i-title clearfix']/a");
    private By prodPriceXpathLocator = By.xpath(".//div[@name='prices_active_element_original']/div/div[@name='price']/div");

    private String topRatedProdsClass = "g-tag g-tag-icon-middle-popularity sprite";

    public WebElement getFirstPageSmPhonesLink() {
        firstPageSmPhonesElement = driver.findElement(firstPageSmPhonesIDLocator);
        return firstPageSmPhonesElement;
    }

    public WebElement getSecondPageSmPhonesLink() {
        secondPageSmPhonesElement = driver.findElement(secondPageSmPhonesIDLocator);
        return secondPageSmPhonesElement;
    }

    public WebElement getThirdPageSmPhonesLink() {
        thirdPageSmPhonesElement = driver.findElement(thirdPageSmPhonesIDLocator);
        return thirdPageSmPhonesElement;
    }

    public void findTopSaleProdByNumber(WebElement pageElement) throws InterruptedException {
        pageElement.click();
        Thread.sleep(5000);             // a crutch for heavy rozetka page download
        List<WebElement> prods = driver.findElements(productsBlockXPathLocator);
        for (WebElement prod : prods) {
            WebElement topSaleProds = prod.findElement(topRatedProdsXpathLocator);
            if (topSaleProds.getAttribute(PageUtils.CLASS).equals(topRatedProdsClass)) {
                String prodName = prod.findElement(prodNameXpathLocator).getText();
                String prodPrice = prod.findElement(prodPriceXpathLocator).getText();
                prodsList.put(prodName, prodPrice);
            }
        }
    }

    public Map getProdsList() {
        return prodsList;
    }

    public boolean checkProdQuantityOnPage(WebElement pageElement) throws InterruptedException {
        pageElement.click();
        Thread.sleep(5000);             // a crutch for heavy rozetka page download
        List<WebElement> prods = driver.findElements(productsBlockXPathLocator);
        int prodCounter = 0;
        for (WebElement prod : prods) {
            WebElement topSaleProds = prod.findElement(topRatedProdsXpathLocator);
            if (topSaleProds.getAttribute(PageUtils.CLASS).equals(topRatedProdsClass)) {
                prodCounter++;
            }
        }
        if (prodCounter > 3) {
            return false;
        } else {
            return true;
        }
    }

    public SmartphonesPage(WebDriver driver) {
        super(driver);
    }
}
