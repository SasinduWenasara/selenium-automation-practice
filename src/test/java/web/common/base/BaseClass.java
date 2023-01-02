package web.common.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;


public class BaseClass {

    protected WebDriver driver;

    @BeforeTest
    public void setupDriver() throws MalformedURLException {
        if (EnvParameters.EXECUTION_ENV.equals("aws")) {
            String host = "localhost";
            MutableCapabilities mc;

            if (System.getProperty("BROWSER")!= null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
                mc = new FirefoxOptions();
            } else
            {
                mc = new ChromeOptions();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
                options.addArguments("--no-sandbox"); // Bypass OS security model
                options.addArguments("start-maximized"); // open Browser in maximized mode
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--disable-gpu"); // applicable to windows os only
                mc.setCapability(ChromeOptions.CAPABILITY, options);
            }

            if (System.getProperty("HUB_HOST")!= null) {
                host = System.getProperty("HUB_HOST");
            }
            String completeUrl = "http://" + host + ":4444/wd/hub";
            this.driver = new RemoteWebDriver(new URL(completeUrl),mc);
        }
        if (EnvParameters.EXECUTION_ENV.equals("local")) {
            if (EnvParameters.WEB_BROWSER.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("start-maximized");
                options.addArguments("enable-automation");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-browser-side-navigation");
                options.addArguments("--disable-gpu");
                options.setHeadless(false);
                this.driver = new ChromeDriver(options);
            }
            if (EnvParameters.WEB_BROWSER.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("start-maximized");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-infobars");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-browser-side-navigation");
                options.addArguments("--disable-gpu");
                this.driver = new FirefoxDriver(options);
            }
        }
    }

    @AfterTest
    public void quit() {
        this.driver.quit();
    }
}

