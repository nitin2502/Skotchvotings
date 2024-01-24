package Voting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class extent
{
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setUp() {
    	
    	WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.http.factory", "jdk-http-client");	
		driver = new ChromeDriver();
        extent = extentmanager.getInstance();
        test = extent.createTest("Login", "Excel");
    }

    @Test
    public void myTestCase() {
        test.log(Status.INFO, "Starting test case");
        driver.get("https://satara.shikshandarpan.com/add-student");
        test.log(Status.INFO, "Opened website");
        // Your test code here
        test.log(Status.PASS, "Test case passed");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
        extent.flush(); // Flush the report at the end of the test
    }
}
