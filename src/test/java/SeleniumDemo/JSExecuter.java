package SeleniumDemo;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class JSExecuter {

    WebDriver driver;
    @BeforeSuite
    public void setUp(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void JSExecuterDemo(){
        driver.get("https://the-internet.herokuapp.com");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String title = js.executeScript("return document.title").toString();
        System.out.println(title);

        js.executeScript("alert('Hello, JavaScript Alert !')");
        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();
        System.out.println(alertText);
        alert.accept();

        // Scroll to bottom of page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

    }

    @Test
    public void JSExecutorScripts(){
        driver.get("https://phptravels.com/demo/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement pricing_button = driver.findElement(By.xpath("//a[text()='Pricing']"));

        // Clicking on Element
        js.executeScript("arguments[0].click();", pricing_button);

        // Changing the Value of an Input Field
        driver.navigate().back();

        WebElement firstName = driver.findElement(By.name("first_name"));
        String firstNameValue = "Ganesh";

        WebElement lastName = driver.findElement(By.name("last_name"));
        String lastNameValue = "Bhosale";

        js.executeScript("arguments[0].value = arguments[1];", firstName, firstNameValue);
        js.executeScript("arguments[0].value = arguments[1];", lastName, lastNameValue);

    }

    @Test
    public void highlightElementJS() throws InterruptedException {
        driver.get("https://phptravels.com/demo/");
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        WebElement headerLine = driver.findElement(By.tagName("h1"));

        String originalStyle = headerLine.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', 'background-color: yellow; border: 2px solid red;');", headerLine);
        // Add a delay to keep the element highlighted for a few seconds
        Thread.sleep(2000);
        // Reset the style
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]);", headerLine, originalStyle);

    }

    @AfterSuite
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }
}
