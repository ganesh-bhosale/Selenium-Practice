package SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XpathAdvanced {

    WebDriver driver;
    @BeforeClass(alwaysRun = true)
    public void openApplication(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        driver = new EdgeDriver(options);
        driver.navigate().to("https://katalon-demo-cura.herokuapp.com/");
    }

    // Xpath using functions
    @Test
    public void xpathFunctions(){
        WebElement menu = driver.findElement(By.xpath("//a[contains(@id,'-toggle')]"));
        menu.click();

        WebElement makeAppointment = driver.findElement(By.xpath("//a[starts-with(@id,'btn-')]"));
        makeAppointment.click();

        WebElement username = driver.findElement(By.xpath("//input[contains(@id,'username')]"));
        username.sendKeys("John Doe");

        WebElement password = driver.findElement(By.xpath("//input[@class='form-control' and contains(@id,'password')]"));
        password.sendKeys("ThisIsNotAPassword");

        WebElement login = driver.findElement(By.id("btn-login"));
        login.click();
    }

    // ----- Xpath Axes Examples -----

    @Test
    public void xpathAxes(){
        driver.get("https://awesomeqa.com/xpath/");
        // Different Xpaths ->
        String mammal = "//div[@class='Mammal']";
        String Ancestors = "//div[@class='Mammal']/ancestor::div";
        String AncestorSelf = "//div[@class='Mammal']/ancestor-or-self::div";
        String following = "//div[@class='Mammal']/following::div";
        String follSibling = "//div[@class='Mammal']/following-sibling::div";
        String parent = "//div[@class='Mammal']/parent::div";
        String child = "//div[@class='Mammal']/child::div";

    }
    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
