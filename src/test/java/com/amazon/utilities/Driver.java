package com.amazon.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            URL url;
            DesiredCapabilities desiredCapabilities;
            switch (ConfigurationReader.getProperty("browser")) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    ((WebDriver)driverPool.get()).manage().window().maximize();
                    ((WebDriver)driverPool.get()).manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    ((WebDriver)driverPool.get()).manage().window().maximize();
                    ((WebDriver)driverPool.get()).manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                    break;
                case "remote-chrome":
                    String gridAdress = "54.89.242.106";

                    try {
                        url = new URL("http://" + gridAdress + ":4444/wd/hub");
                        desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                        ((WebDriver)driverPool.get()).manage().window().maximize();
                        ((WebDriver)driverPool.get()).manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                    } catch (MalformedURLException var10) {
                        var10.printStackTrace();
                    }
                    break;
                case "saucelab-chrome":
                    try {
                        url = new URL("https://oauth-sdetoscar-844c8:66e7117f-390e-4556-8105-07af96a01f7a@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                        desiredCapabilities = new DesiredCapabilities();
                        desiredCapabilities.setBrowserName("chrome");
                        driverPool.set(new RemoteWebDriver(url, desiredCapabilities));
                        ((WebDriver)driverPool.get()).manage().window().maximize();
                        ((WebDriver)driverPool.get()).manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                    } catch (MalformedURLException var9) {
                        var9.printStackTrace();
                    }
                    break;
                case "saucelab-edge":
                    EdgeOptions browserOptions = new EdgeOptions();
                    browserOptions.setCapability("platformName", "Windows 11");
                    browserOptions.setCapability("browserVersion", "latest");
                    Map<String, Object> sauceOptions = new HashMap();
                    sauceOptions.put("build", "<your build id>");
                    sauceOptions.put("name", "<your test name>");
                    browserOptions.setCapability("sauce:options", sauceOptions);
                    url = null;

                    try {
                        url = new URL("https://oauth-sdetoscar-844c8:66e7117f-390e-4556-8105-07af96a01f7a@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
                        driverPool.set(new RemoteWebDriver(url, browserOptions));
                        ((WebDriver)driverPool.get()).manage().window().maximize();
                        ((WebDriver)driverPool.get()).manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
                    } catch (MalformedURLException var8) {
                        var8.printStackTrace();
                    }
            }
        }

        return (WebDriver)driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            ((WebDriver)driverPool.get()).quit();
            driverPool.remove();
        }

    }
}
