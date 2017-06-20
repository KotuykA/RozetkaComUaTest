package ua.com.rozetka;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;
import ua.com.rozetka.core.WebDriverTestBase;
import ua.com.rozetka.pages.MainPage;
import ua.com.rozetka.pages.PhonesPage;
import ua.com.rozetka.pages.SmartphonesPage;
import ua.com.rozetka.pages.SmartphonesTvElectronic;
import ua.com.rozetka.util.PageUtils;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@Listeners({ua.com.rozetka.core.TestListener.class})
@Title("Test for NCube")
@Description("One test for NCube test task")
public class NCubeTest extends WebDriverTestBase {

    @Test
    public void testTask() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        SmartphonesTvElectronic smartphonesTvElectronic = new SmartphonesTvElectronic(driver);
        PhonesPage phonesPage = new PhonesPage(driver);
        SmartphonesPage smartphonesPage = new SmartphonesPage(driver);

        mainPage.openPage(PageUtils.ROZETKA_BASE_URL);

        mainPage.getMainPageLink().click();

        smartphonesTvElectronic.getPhonesLink().click();

        phonesPage.getSmartphonesLink().click();

        smartphonesPage.findTopSaleProdByNumber(smartphonesPage.getFirstPageSmPhonesLink());
        smartphonesPage.findTopSaleProdByNumber(smartphonesPage.getSecondPageSmPhonesLink());
        smartphonesPage.findTopSaleProdByNumber(smartphonesPage.getThirdPageSmPhonesLink());

//        System.out.println(smartphonesPage.getProdsList());      to print List, if it is necessary

        assertTrue(smartphonesPage.checkProdQuantityOnPage(smartphonesPage.getFirstPageSmPhonesLink()));
        assertFalse(smartphonesPage.checkProdQuantityOnPage(smartphonesPage.getSecondPageSmPhonesLink()));
        assertTrue(smartphonesPage.checkProdQuantityOnPage(smartphonesPage.getThirdPageSmPhonesLink()));
    }

}
