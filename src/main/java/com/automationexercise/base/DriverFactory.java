package com.automationexercise.base;

import com.automationexercise.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public final class DriverFactory {

    private DriverFactory() {
    }

    public static WebDriver createDriver() {
        String browser = ConfigReader.get("browser").toLowerCase();
        boolean headless = ConfigReader.getBoolean("headless");

        switch (browser) {
            case "firefox": {
                FirefoxOptions options = new FirefoxOptions();
                if (headless) {
                    options.addArguments("-headless");
                }
                return new FirefoxDriver(options);
            }
            case "edge": {
                EdgeOptions options = new EdgeOptions();
                if (headless) {
                    options.addArguments("--headless=new");
                }
                options.addArguments("--start-maximized");
                return new EdgeDriver(options);
            }
            case "chrome":
            default: {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new", "--window-size=1920,1080");
                }
                options.addArguments("--start-maximized", "--disable-notifications",
                        "--remote-allow-origins=*");
                return new ChromeDriver(options);
            }
        }
    }
}
