package ua.com.rozetka.util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.lang.invoke.MethodHandles;

import static com.google.common.io.Files.toByteArray;

public class WebDriverUtils {

    private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Attachment(value = "{2}")
    public byte[] saveScreenshot(WebDriver driver, String pathToFolder, String screenshotName) {
        try {
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(pathToFolder + screenshotName + ".png"));
            return toByteArray(scrFile);
        } catch (Exception e) {
            LOG.error("Is not saved screenshot" + e);
        }
        return new byte[0];
    }

    public void waitForExpectedCondition(WebDriver driver, ExpectedCondition expectedCondition) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(expectedCondition);
    }

    public boolean checkAlertPresent(WebDriver driver, String textAlert, String action) {
        String accessAlert = "access";
        String dismissAlert = "dismiss";

        try {
            waitForExpectedCondition(driver, ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            if (alert.getText().contains(textAlert)) {
                if (accessAlert.equals(action)) {
                    alert.accept();
                    return true;
                }
                if (dismissAlert.equals(action)) {
                    alert.dismiss();
                    return true;
                }
            }
        } catch (Exception e) {
            LOG.error("Alert window is not detected!" + e);
        }
        return false;
    }
}
