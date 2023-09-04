package Voting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelUpload
{
	WebDriver d;
	@Test()
	public void votingDone() throws InterruptedException, IOException {

		File file = new File("C:\\Users\\niting\\Downloads\\Data_3.xls\\");
		FileInputStream inputStream = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = wb.getSheet("Sheet1");
		sheet.createRow(0).createCell(0).setCellValue("Age");
		// int lastRow = sheet.getLastRowNum();
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println(rowCount);
		for (int i = 1; i <= rowCount; i++) {
			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.http.factory", "jdk-http-client");	
			d = new ChromeDriver();
			String url = "https://exhibition.skoch.in/exhibition-voting/";
			d.get(url);
			d.manage().deleteAllCookies();
			Thread.sleep(1000);
			System.out.println("Test  running in Chrome");	
			WebElement ifra = d.findElement(By.tagName("iframe"));
			d.switchTo().frame(ifra);



			WebElement Name = d.findElement(
					By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/input"));
			// Name.sendKeys("Ganesh");

			WebElement LastName = d.findElement(
					By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[1]/input"));
			// LastName.sendKeys("Jadhav");

			WebElement Desi = d.findElement(
					By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[4]/div/div/div[2]/div/div[1]/div/div[1]/input"));
			// Desi.sendKeys("Sales and Marketing Manager");

			WebElement Email = d.findElement(
					By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[5]/div/div/div[2]/div/div[1]/div/div[1]/input"));
			// email.sendkeys("ganeshjadhav@gmail.com");

			WebElement OrgName = d.findElement(
					By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[1]/div/div[1]/input"));
			// OrgName.sendKeys("Shaurya technosoft");

			WebElement OrgCity = d.findElement(
					By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[7]/div/div/div[2]/div/div[1]/div/div[1]/input"));
			// OrgCity.sendKeys("pune");

			WebElement Mobnumber = d.findElement(By.xpath(
					"//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[9]/div/div/div[2]/div/div[1]/div/div[1]/input"));
			System.out.println(i);
			
			
			System.out.println(sheet.getRow(i).getCell(1).getStringCellValue());
			// Enter the values read from Excel in firstname,lastname,mobile,email,address
		
			Name.sendKeys(sheet.getRow(i).getCell(0).getStringCellValue());
			
			LastName.sendKeys(sheet.getRow(i).getCell(1).getStringCellValue());
		
			Desi.sendKeys(sheet.getRow(i).getCell(2).getStringCellValue());
			
			Email.sendKeys(sheet.getRow(i).getCell(3).getStringCellValue());
			
			OrgName.sendKeys(sheet.getRow(i).getCell(4).getStringCellValue());
			
			OrgCity.sendKeys(sheet.getRow(i).getCell(5).getStringCellValue());

			 Mobnumber.sendKeys(sheet.getRow(i).getCell(6).getStringCellValue());
			
			WebElement OrgState = d.findElement(By.xpath("//span[text()=\"Maharashtra\"]"));
			OrgState.click();

			
			
			//Mobnumber.sendKeys("8730021254");

		
			WebElement Listofprjct = d.findElement(By.xpath(
					"//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[10]/div/div/div[2]/div/div/span/div/div[2]/label/div/div[2]/div/span"));
			Listofprjct.click();

			
			WebElement accptterms = d.findElement(By.xpath(
					"//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[12]/div/div/div[2]/div/div/span/div/div/label/div/div[2]/div/span"));
			accptterms.click();

			
			WebElement Submit = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[3]/div[1]/div[1]/div/span/span"));
			Submit.click();
			Thread.sleep(1000);
                     
             d.close();
			d.quit();
		}

	}

	
	
}