package SeleniumDemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

import javax.swing.text.html.Option;

public class WebDriverBasic {
    @Test
    public void navigateDemo(){
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://google.com");
        driver.navigate().to("https://jsoncrack.com/editor");
        System.out.println(driver.getTitle());
        driver.navigate().back();
        System.out.println(driver.getTitle());
        driver.navigate().forward();
        driver.navigate().refresh();
        System.out.println(driver.getTitle());
        driver.quit();
    }
}
