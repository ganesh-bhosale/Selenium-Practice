package SeleniumDemo;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginPageTC {

    WebDriver driver;
    // Selectors
    String username = "login-username";    // ID
    String password = "login-password";   // ID
    String signinbutton = "btn--positive";  // Class
    String notification = "js-notification-box-msg";   // ID
    String TITLE = "Dashboard";
    String failureMsg = "Your email, password, IP address or location did not match";

    @BeforeSuite
    public void setUp(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void NavigatetoURL(){
        driver.get("https://app.vwo.com");
    }

    @Description("Verify User Login with Valid Username & Invalid Password")
    @Test(priority = 1, groups = "negative")
    public void negativeTC01() throws InterruptedException {
        WebElement userName = driver.findElement(By.id(username));
        userName.sendKeys("93npu2yyb0@esiix.com");
        WebElement passWord = driver.findElement(By.id(password));
        passWord.sendKeys("pass@123");
        WebElement sigIn = driver.findElement(By.className(signinbutton));
        sigIn.click();
        Thread.sleep(3000);
        WebElement notificationMsg = driver.findElement(By.id(notification));
        Assert.assertEquals(notificationMsg.getText(),failureMsg);
    }

    @Description("Verify User Login with Invalid Username  & Valid Password")
    @Test(priority = 2, groups = "negative")
    public void negativeTC02() throws InterruptedException {
        WebElement userName = driver.findElement(By.id(username));
        userName.sendKeys("93npu2yyb0");
        WebElement passWord = driver.findElement(By.id(password));
        passWord.sendKeys("Wingify@123");
        WebElement sigIn = driver.findElement(By.className(signinbutton));
        sigIn.click();
        Thread.sleep(3000);
        WebElement notificationMsg = driver.findElement(By.id(notification));
        Assert.assertEquals(notificationMsg.getText(),failureMsg);

    }
    @Description("Verify User Login with Valid Username  & Valid Password")
    @Test(priority = 3, groups = "positive")
    public void positiveTC01() throws InterruptedException {
        WebElement userName = driver.findElement(By.id(username));
        userName.sendKeys("93npu2yyb0@esiix.com");
        WebElement passWord = driver.findElement(By.id(password));
        passWord.sendKeys("Wingify@123");
        WebElement sigIn = driver.findElement(By.className(signinbutton));
        sigIn.click();
        Thread.sleep(5000);
        System.out.println(driver.getTitle());
        String homeTitle = driver.getTitle();
        Assert.assertEquals(homeTitle, TITLE);
    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
