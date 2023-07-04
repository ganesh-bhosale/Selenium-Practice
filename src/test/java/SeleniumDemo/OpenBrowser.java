package SeleniumDemo;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

@Test
public class OpenBrowser {

    // @Test for the TestNg
    // Create a session
    // open app.vwo.com

    @Test
    public void testOpenEdgeBrowser(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);;
        options.setCapability("ms:inPrivate", true);
        options.setHeadless(true);
        WebDriver driver = new EdgeDriver(options);
        driver.get("https://app.vwo.com");
        System.out.println(driver.getTitle());
        driver.quit();
    }

    @Test
    public void testEdgeOptions(){
        EdgeOptions options = new EdgeOptions();
        Proxy proxy = new Proxy();
        proxy.setHttpProxy("proxy.example.com:8080");
        options.setProxy(proxy);
        options.addArguments("--start-maximized");
        // options.setUnhandledPromptBehaviour(UnhandledPromptBehaviour.ACCEPT);
        options.setCapability("ms:inPrivate", true);
        WebDriver driver = new EdgeDriver(options);
        driver.get("https://onecompiler.com/javascript");
        driver.getTitle();
        driver.close();

    }

    // ChromeDriver is not working
    public void testOpenChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\g.dnyandeo.bhosale\\Desktop\\Automation Testing\\chromedriver_113.exe");
        ChromeOptions optionsBeta = new ChromeOptions();
        optionsBeta.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        WebDriver driver = new ChromeDriver(optionsBeta);
        driver.get("https://onecompiler.com/javascript");
    }


    public void OpenChrome(){
        WebDriver driver = new ChromeDriver();
        }
    }

