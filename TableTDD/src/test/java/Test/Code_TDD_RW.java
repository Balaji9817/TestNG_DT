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
import org.testng.annotations.DataProvider;
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
	
	@Test(priority=1)
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
	
	@Test(dataProvider="check", priority=2)
	public void Wdtable(String name,String Lname,String age,String gmail,String sal,String dept )
	{
		wait= new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='element-list collapse show'] li[id='item-3']")));
		driver.findElement(By.cssSelector("div[class='element-list collapse show'] li[id='item-3']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("h1[class='text-center']")));
		driver.findElement(By.cssSelector("button[id='addNewRecordButton']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='modal-content']")));
		
		List<WebElement> eledata=driver.findElements(By.cssSelector("input[class=' mr-sm-2 form-control']"));
		String str[]= {name, Lname, age, sal, dept};
		
		for(int i=0;i<eledata.size();i++)
		{
			eledata.get(i).click();
			eledata.get(i).sendKeys(str[i]);
		}
		driver.findElement(By.cssSelector("input[class='mr-sm-2 form-control']")).click();
		driver.findElement(By.cssSelector("input[class='mr-sm-2 form-control']")).sendKeys(gmail);
		
		driver.findElement(By.cssSelector("button[id=submit]")).click();
			        
	}
	
	@DataProvider(name="check")
	public Object[][] getdata()
	{
		Object[][] data=new Object[2][6];
		
		data[0][0]="Sidth";
		data[0][1]="vick";
		data[0][2]="36";
		data[0][3]="sidth@ggmil.com";
		data[0][4]="234567";
		data[0][5]="Mech";
		
		data[1][0]="keerthi";
		data[1][1]="daugth";
		data[1][2]="56";
		data[1][3]="keerti@ggmil.com";
		data[1][4]="56874";
		data[1][5]="ECE";
		
		return data;
	}
	
	
	
//	@AfterClass
//	public void tearDownAfterClass() throws Exception {
//		// Code to clean up after all tests in this class have run
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.quit();
//	}

}
