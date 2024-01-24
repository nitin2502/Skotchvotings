package Voting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class loginexcel
{
	WebDriver d;
	private WebDriver driver;
	private ExtentReports extent;
	private ExtentTest test;


	@BeforeTest
	public void Zplogin () 
	{

		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.http.factory", "jdk-http-client");	
		d = new ChromeDriver();
		d.get("https://satara.shikshandarpan.com/dashboard"); 
		d.manage().window().maximize();
		extent = extentmanager.getInstance();
		test = extent.createTest("Login", "Excel");
	}


	@Test()
	public void votingDone() throws InterruptedException, IOException {

		File file = new File("C:\\Users\\niting\\eclipse-workspace\\Skochvotings\\Excel\\SAtaralogin.xls");
		FileInputStream inputStream = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = wb.getSheet("Sheet1");
		sheet.createRow(0).createCell(0).setCellValue("Age");
		int lastRow = sheet.getLastRowNum();
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println(rowCount);
		test.log(Status.INFO, "Total Rows: " + rowCount);
		for (int i = 1; i <= rowCount; i++) {


			WebElement usrnm = d.findElement(By.xpath("//input[@formcontrolname='userName'][1]"));
			usrnm.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());

			Thread.sleep(1000);


			WebElement pwd = d.findElement(By.xpath("//input[@formcontrolname='password'][1]"));
			pwd.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
			Thread.sleep(1000);

			
			WebElement view = d.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/mat-form-field/div[1]/div/div[3]/button/span[4]"));
			view.click() ;
			Thread.sleep(1000);
			WebElement submit = d.findElement(By.xpath("//button[@type='submit'][1]"));
			submit.click();
			Thread.sleep(1000);
			if(d.getPageSource().contains("Dashboard")) {
				System.out.println("Dashboard");
			}
			else{
					System.out.println("file not uploaded");
				}
			d.navigate().refresh();
			Thread.sleep(1000);

			String cellValue = sheet.getRow(i).getCell(0).getStringCellValue();
			test.log(Status.INFO, "UserName: " + cellValue);

			String colvalue =sheet.getRow(i).getCell(1).getStringCellValue();
			test.log(Status.INFO, "Password: " + colvalue);

			Thread.sleep(1000);

		}  

		d.close();
		extent.flush(); 
	}
}
