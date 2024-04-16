package Voting;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Loginds {

	private WebDriver driver;

	@BeforeTest
	public void setUp() {
		// Setup WebDriver using WebDriverManager
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.http.factory", "jdk-http-client");	
		driver = new ChromeDriver();
		driver.get("https://satara.shikshandarpan.com/login");
	} 

	@Test(dataProvider = "loginData")
	public void loginTest(String username, String password)
	{
		// Navigate to the login page
		

		// Find login form elements
		WebElement usernameInput = driver.findElement(By.id("mat-input-0"));
		WebElement passwordInput = driver.findElement(By.id("mat-input-1"));
		WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));



		// Perform login
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();

		// Add assertions or further verifications based on your test requirements
		// For example, you can check if the login was successful using assertions.
	}

	@DataProvider(name = "loginData")
	public Object[][] loginData() throws IOException {
		String filePath = "C:\\Users\\niting\\eclipse-workspace\\Skochvotings\\src\\main\\resources\\ExcelData\\sataraLogin.xlsx";
		FileInputStream fis = new FileInputStream(filePath);

		  Workbook workbook = WorkbookFactory.create(fis);
	        org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

	        int rowCount = sheet.getPhysicalNumberOfRows();
	        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

	        Object[][] data = new Object[rowCount - 1][colCount];

	        for (int i = 1; i < rowCount; i++) {
	            Row row = sheet.getRow(i);
	            for (int j = 0; j < colCount; j++) {
	                data[i - 1][j] = row.getCell(j).getStringCellValue();
	            }
	        }

	        workbook.close();
	        fis.close();

	        return data;
	}

	@AfterTest
	public void tearDown() {
		// Close the browser
		if (driver != null) {
			// driver.quit();
		}
	}
}
