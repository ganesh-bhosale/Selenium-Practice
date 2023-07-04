package SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class DDT {

    WebDriver driver;

    @BeforeClass
    public void setUp(){
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--incognito");
        driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(dataProvider = "getMetaData")
    public void testLoginExcelData(String email, String pass, String expectedResult) throws InterruptedException {
        driver.get("https://app.vwo.com");
        WebElement username = driver.findElement(By.id("login-username"));
        username.clear();
        username.sendKeys(email);

        WebElement password = driver.findElement(By.id("login-password"));
        password.clear();
        password.sendKeys(pass);

        driver.findElement(By.id("js-login-btn")).click();
        Thread.sleep(5000);

        if(expectedResult.equalsIgnoreCase("Invalid")){
            WebElement error_message = driver.findElement(By.xpath("//div[@id='js-notification-box-msg']"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(error_message));
            Assert.assertTrue(error_message.isDisplayed());
            String errorMsgText = error_message.getText();
            System.out.println(errorMsgText);
            String errorMsg = "Your email, password, IP address or location did not match";
            Assert.assertEquals(errorMsgText, errorMsg);
        }

        if(expectedResult.equalsIgnoreCase("Valid")){
            String text = driver.findElement(By.xpath("//span[@data-qa='lufexuloga']")).getText();
            System.out.println(text);
            Assert.assertEquals(text, "Wingify");
            String title = driver.getTitle();
            System.out.println(title);
            Assert.assertEquals(title, "Dashboard");
        }

    }

    @DataProvider(name = "getLoginData")
    public Object[][] getLoginData() throws IOException {
        String filepath = "src/test/resource/LoginFataDDT.xlsx";
        ExcelReader excelReader = new ExcelReader(filepath);
        String [][] data = excelReader.getDataFromSheet(filepath, "LoginData");
        return data;
    }


    @DataProvider(name = "getMetaData")
    public Object[][] getMeDataforLogin(){
        return new Object[][]{
                {"invalid@gmail.com", "Wingify", "Invalid"},
                {"93npu2yyb0@esiix.com", "Wingify@123","Valid"}
        };
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
