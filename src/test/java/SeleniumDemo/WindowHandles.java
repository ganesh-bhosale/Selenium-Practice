package SeleniumDemo;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;

public class WindowHandles {

    @Test
    public void WindowHandleDemo(){
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/windows");

        driver.findElement(By.xpath("//a[text()='Click Here']")).click();

        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println(windowHandles);

        for(String handle : windowHandles){
            driver.switchTo().window(handle);
            if(driver.getPageSource().contains("New Window")){
                System.out.println("Found it!");
            }
        }

        driver.close();

    }

    @Test
    public void windowHandlesDemo2(){
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        WebDriver driver = new EdgeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/windows");

        String parentWindow = driver.getWindowHandle();
        driver.findElement(By.xpath("//a[text()='Click Here']")).click();
        Set<String> windowHandles = driver.getWindowHandles();

        for(String handle : windowHandles){
            if(!handle.equals(parentWindow)){
                driver.switchTo().window(handle);
                break;
            }
        }

        String nw_text = driver.findElement(By.tagName("h3")).getText();
        System.out.println(nw_text);

        driver.switchTo().window(parentWindow);

        String pw_text = driver.findElement(By.xpath("//a[@target='_blank']")).getText();
        System.out.println(pw_text);

        Iterator<String> iterator = windowHandles.iterator();
        while (iterator.hasNext()){
            String childWindow = iterator.next();
            if(!parentWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
            }
        }
}
}

