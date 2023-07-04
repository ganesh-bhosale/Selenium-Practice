package SeleniumDemo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTDemo {

    @Test(dataProvider = "getMetaData")
    public void testForLogin(String email, String pass, String status){
        System.out.println(email+" "+ pass+" "+status);
    }

    @DataProvider(name = "getMetaData")
    public Object[][] getMeDataforLogin(){
        return new Object[][]{
                {"valid@gmail.com", "Wingify", "Valid"},
                {"invalid@gmail.com", "Wingify","Invalid"}
        };
    }
}
