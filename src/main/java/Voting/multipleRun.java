package Voting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class multipleRun{
	WebDriver d;
	@Test(invocationCount =5000)
	public void testIFrameHandling() throws InterruptedException {
          WebDriverManager.chromedriver().setup();
		
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		d=new ChromeDriver();
		String url ="https://exhibition.skoch.in/exhibition-voting/";
		d.get(url);
		d.manage().deleteAllCookies();
		
		Thread.sleep(1000);			
		
		WebElement ifra= d.findElement(By.tagName("iframe"));
		d.switchTo().frame(ifra);
		
		WebElement Name = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/div/div[1]/div/div[1]/input"));
		Name.sendKeys("ganesh");
		
		WebElement LastName = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[3]/div/div/div[2]/div/div[1]/div/div[1]/input"));
		LastName.sendKeys("Stha");
		
		WebElement Desi = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[4]/div/div/div[2]/div/div[1]/div/div[1]/input"));
		Desi.sendKeys("Developer");
		
		WebElement Email = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[5]/div/div/div[2]/div/div[1]/div/div[1]/input"));
		Email.sendKeys("shubham056@gmail.com");
	
		WebElement OrgName = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[6]/div/div/div[2]/div/div[1]/div/div[1]/input"));
		OrgName.sendKeys("Onkar Dies & Mould Pvt ltd ,Solapur ");
		
		WebElement OrgCity = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[7]/div/div/div[2]/div/div[1]/div/div[1]/input"));
		OrgCity.sendKeys("pune");
		
		WebElement OrgState = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[8]/div/div/div[2]/div/div/span/div/div[21]/label/div/div[2]/div/span"));
		OrgState.click();
		
		WebElement Mobnumber = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[9]/div/div/div[2]/div/div[1]/div/div[1]/input"));
		Mobnumber.sendKeys("8839874019");
		
		
		WebElement Listofprjct = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[10]/div/div/div[2]/div/div/span/div/div[2]/label/div/div[2]/div/span"));
		Listofprjct.click();
		
		
		WebElement accptterms = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[2]/div[12]/div/div/div[2]/div/div/span/div/div/label/div/div[2]/div/span"));
		accptterms.click();
		
		
		WebElement Submit = d.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div[1]/div[3]/div[1]/div[1]/div/span/span"));
		Submit.click();
		
	
		d.switchTo().defaultContent();
		
	  Thread.sleep(1000);
	    d.close();
	    d.quit();
	   
	}
	
}