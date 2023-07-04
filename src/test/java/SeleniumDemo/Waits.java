package SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Waits {

    @Test
    public void impicitWaits(){
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://onecompiler.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement mainHeader= driver.findElement(By.cssSelector("div.MuiBox-root"));
        mainHeader.click();
        driver.close();
    }

    @Test
    public void explicitWaits(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        WebDriver driver = new EdgeDriver(options);
        driver.get("https://onecompiler.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));



    }
}
