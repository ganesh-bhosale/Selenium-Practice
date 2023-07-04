package SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

public class FileUpload {
    @Test
    public void FileUploadcDemo(){
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new EdgeDriver(options);
        driver.get("https://awesomeqa.com/selenium/upload.html");
        driver.manage().window().maximize();

        WebElement upload_file = driver.findElement(By.name("fileToUpload"));
        upload_file.sendKeys("C:\\Users\\g.dnyandeo.bhosale\\Documents\\Chrome Bookmarks.txt");

        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
    }
}
