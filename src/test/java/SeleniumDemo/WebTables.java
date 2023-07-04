package SeleniumDemo;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Predicate;

public class WebTables {
    WebDriver driver;

    @BeforeSuite
    public void setUp(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void navigateToSite(){
        driver.get("https://awesomeqa.com/webtable.html");
    }

    @Description("This test case is to get all the data from WebTable")
    @Test(priority = 1)
    public void GetTableDataTC(){

        int row = driver.findElements(By.xpath("//table[@id='customers']/tbody/tr")).size();
        int col = driver.findElements(By.xpath("//table[@id='customers']/tbody/tr[2]/td")).size();

        System.out.println("Rows: "+row+"\nColumns: "+col);

        String part1 = "//table[@id='customers']/tbody/tr[";
        String part2 = "]/td[";
        String part3 = "]";

        for (int i=2; i<=row; i++){
            for(int j=1; j<=col; j++){
                String dynamic_xpath = part1+i+part2+j+part3;
                String data = driver.findElement(By.xpath(dynamic_xpath)).getText();
                System.out.println(data+"\t");

                if(data.contains("Roland Mendel")){
                    String new_dynamic_xpath = dynamic_xpath+"/following-sibling::td";
                    String country = driver.findElement(By.xpath(new_dynamic_xpath)).getText();
                    System.out.println(data+ " is from "+country);
                }
            }
        }
    }

    @Test(priority = 2)
    public void dynamicTableData(){
        driver.get("https://awesomeqa.com/webtable1.html");

        WebElement table = driver.findElement(By.xpath("//table[@class='tsc_table_s13']/tbody"));
        List<WebElement> table_rows = table.findElements(By.tagName("tr"));

        for(WebElement rowTable : table_rows){
            List <WebElement> table_col = rowTable.findElements(By.tagName("td"));
            for(WebElement element : table_col){
                System.out.println(element.getText());
            }
        }

    }

    @AfterSuite
    public void tearDown(){
        driver.quit();
    }
}
