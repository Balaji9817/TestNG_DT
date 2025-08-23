package Test;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class Code_TDD_RW {
	
	public WebDriver driver;
	public WebDriverWait wait;
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		 driver = new ChromeDriver();
		 WebDriverManager.chromedriver().setup();
		 driver.get("https://demoqa.com/");
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	 	
	}
	
	@Test
	public void testDataProcessing() {
		List<WebElement> ele1 = driver.findElements(By.cssSelector("div[class='card-body']"));
		for(WebElement ele : ele1) {
			if(ele.getText().equals("Elements")) {
				ele.click();
				break;
			}
		}
		wait= new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Text Box']")));
		driver.findElement(By.xpath("//span[text()='Text Box']")).click();
		System.out.println(driver.getCurrentUrl());
		
	}
	
	@AfterClass
	public void tearDownAfterClass() throws Exception {
		// Code to clean up after all tests in this class have run
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.quit();
	}

}
