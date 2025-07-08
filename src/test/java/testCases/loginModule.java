package testCases;

import java.io.IOException;

import org.testng.annotations.Test;

import baseClass.base;
import pageObjectClasses.loginFun;
import utilities.Excelutlity;

public class loginModule extends base {

    @Test
    public void logintest() throws IOException, InterruptedException {

        String filePath = "C:\\Users\\bhanu\\eclipse-myworkspace\\OrangeHRM\\TestData\\LoginTestData.xlsx";
        String sheetName = "Sheet1";
        String expectedSuccessUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";

        Excelutlity excel = new Excelutlity(filePath);
        loginFun login = new loginFun(driver);

        int rowCount = excel.getrowcount(sheetName);
        System.out.println("📘 Total Test Cases: " + rowCount);

        for (int i = 1; i <= rowCount; i++) {
        	logger.info("Starting the username and passowrd :"+i);

            String username = excel.getcelldata(sheetName, i, 1);
            String password = excel.getcelldata(sheetName, i, 2);

            System.out.println("\n Test " + i + ": Username = " + username + ", Password = " + password);

            login.loginPage(username, password);
            
            login.btn();
            Thread.sleep(2000); // Wait for login page to redirect

            String actualUrl = driver.getCurrentUrl();

            if (actualUrl.equals(expectedSuccessUrl)) {
                System.out.println("Login Success");
                logger.info("Successful");
                login.logoutbtn();
                Thread.sleep(3000);// Return to login for next test
                
            } else {
            	logger.info("unSuccessful login");
                System.out.println("Login Failed");
                
                Thread.sleep(3000);
            }
        }
    }
}
