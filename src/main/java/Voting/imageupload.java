package Voting;
 
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
class imageupload{
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.http.factory", "jdk-http-client");	
		driver = new ChromeDriver();
		
		driver.get("https://the-internet.herokuapp.com/upload");
		//we want to import selenium-snapshot file. 
	
		driver.findElement(By.id("file-upload")).sendKeys("C:\\Users\\niting\\Desktop\\AadiVanMitra12.pdf");
		Thread.sleep(2000);
		driver.findElement(By.id("file-submit")).submit();
		if(driver.getPageSource().contains("File Uploaded!")) {
			System.out.println("file uploaded");
		}
		else{
				System.out.println("file not uploaded");
			}
		driver.quit();
	}
}

  