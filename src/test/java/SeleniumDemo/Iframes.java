package SeleniumDemo;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class Iframes {
    WebDriver driver;
    @BeforeSuite
    public void setUp(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();

    }

    @Description("Test Iframes")
    @Test
    public void iFrameDemo() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/frames");

        driver.findElement(By.linkText("iFrame")).click();
        String parentFrame_msg = driver.findElement(By.tagName("h3")).getText();
        System.out.println(parentFrame_msg);

        Thread.sleep(2000);
        WebElement paragraph_button = driver.findElement(By.cssSelector(".tox-tbtn__select-label"));
        paragraph_button.click();

        Actions actions = new Actions(driver);
        WebElement inline_menu = driver.findElement(By.xpath("//div[text()='Inline']"));
        actions.moveToElement(inline_menu).build().perform();
        actions.click(driver.findElement(By.xpath("//div/span[text()='Strikethrough']"))).click().perform();

        String iFrame = "mce_0_ifr";
        driver.switchTo().frame(iFrame);

        WebElement input_box = driver.findElement(By.id("tinymce"));
        input_box.click();
        input_box.clear();
        input_box.sendKeys("Yes, Entered text into iFrame !");
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
