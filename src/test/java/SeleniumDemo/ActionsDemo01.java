package SeleniumDemo;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class ActionsDemo01 {
    WebDriver driver;

    @BeforeSuite
    public void setup(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @Description("Test Case for Actions Class Demo")
    @Test(priority = 1)
    public void spiceJet(){
        driver.get("https://www.spicejet.com/");
        WebElement from_textbox = driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-13awgt0 r-18u37iz']/div/div/div/input"));

        Actions actions = new Actions(driver);
        actions.moveToElement(from_textbox).click().sendKeys("Pune");
    }

    @Test(priority = 2)
    public void makeMyTrip() throws InterruptedException {
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(10000);

        WebElement fromCity = driver.findElement(By.id("fromCity"));

        Actions actions = new Actions(driver);
        actions.moveToElement(fromCity).click().sendKeys("Mumbai").build().perform();

        List<WebElement> list = driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
        System.out.println(list);
        for(WebElement element : list){
            if (element.getText().contains("Mumbai")){
                element.click();
                break;
            }
        }

    }

    @Test
    public void DragAndDrop(){
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions actions = new Actions(driver);

        WebElement source_box = driver.findElement(By.id("column-a"));
        WebElement target_box = driver.findElement(By.id("column-b"));

        actions.dragAndDrop(source_box,target_box).build().perform();
    }
}
