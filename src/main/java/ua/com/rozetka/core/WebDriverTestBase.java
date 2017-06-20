package ua.com.rozetka.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.os.WindowsUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import ua.com.rozetka.util.PropertiesCache;

import java.lang.invoke.MethodHandles;
import java.net.URL;

public abstract class WebDriverTestBase {
    private static final String WEB_DRIVER_GECKO = "webdriver.gecko";
    private static final String WEB_DRIVER_CHROME = "webdriver.chrome";
    private static final String CHROME_PATH = "chrome.path";
    private static final String GECKO_DRIVER_PATH_WIN = "geckodriver.path.win";
    private static final String GECKO_DRIVER_PATH_UNIX = "geckodriver.path.unix";
    private static final String IMPLICIT_WAIT = "webdriver.implicit.wait";
    private static final String SCRIPT_TIMEOUT = "webdriver.script.timeout";
    private static final String LOAD_TIMEOUT = "webdriver.load.timeout";
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String BROWSER = System.getProperty("browser");
    private static final Logger LOG = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    protected WebDriver driver;
    private DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

    @BeforeSuite
    public void setUp() {
        if (isBrowserSetUpFor(BrowserName.CHROME.name(), BROWSER)) {
            System.setProperty(getProperty(WEB_DRIVER_CHROME), getProperty(CHROME_PATH));
        } else if (isBrowserSetUpFor(BrowserName.FIREFOX.name(), BROWSER)) {
            if (isWindows()) {
                System.setProperty(getProperty(WEB_DRIVER_GECKO), getPath(getProperty(GECKO_DRIVER_PATH_WIN)));
            } else if (isUnix()) {
                System.setProperty(getProperty(WEB_DRIVER_GECKO), getPath(getProperty(GECKO_DRIVER_PATH_UNIX)));
            }
        }
        initializeWebDriver();
    }

    private void initializeWebDriver() {
        try {
            if (isBrowserSetUpFor(BrowserName.CHROME.name(), BROWSER)) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-extensions");
                driver = new ChromeDriver(options);
                desiredCapabilities.setBrowserName(BrowserName.CHROME.name());
            } else if (isBrowserSetUpFor(BrowserName.FIREFOX.name(), BROWSER)) {
                driver = new FirefoxDriver();
                desiredCapabilities.setBrowserName(BrowserName.FIREFOX.name());
            }
            driver.manage().window().maximize();
//            driver.manage().timeouts().setScriptTimeout(Integer.valueOf(getProperty(SCRIPT_TIMEOUT)), TimeUnit.SECONDS);
//            driver.manage().timeouts().pageLoadTimeout(Integer.valueOf(getProperty(LOAD_TIMEOUT)), TimeUnit.SECONDS);
//            driver.manage().timeouts().implicitlyWait(Integer.valueOf(getProperty(IMPLICIT_WAIT)), TimeUnit.SECONDS);
        } catch (WebDriverException e) {
            System.out.println(e.getMessage());
            WindowsUtils.killByName(desiredCapabilities.getBrowserName() + "driver" + (isWindows() ? ".exe" : ""));
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    private boolean isBrowserSetUpFor(String browserName, String browserSystemVeriable) {
        return StringUtils.isEmpty(BROWSER) || browserName.equalsIgnoreCase(browserSystemVeriable);
    }

    private boolean isWindows() {
        return OS.contains("win");
    }

    private boolean isUnix() {
        return OS.contains("nix") || OS.contains("nux") || OS.contains("aix");
    }

    public String getProperty(String key) {
        return PropertiesCache.getInstance().getProperty(key);
    }

    private String getPath(String s) {
        String path = null;
        URL url = WebDriverTestBase.class.getClassLoader().getResource(s);
        if (url != null) {
            path = url.getPath();
        } else {
            LOG.error("Not found resource");
        }
        return path;
    }

}