package com.pos.qa.Inventory;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sam_pos_Inventory_onhand {
	public static WebDriver driver;
public static void main(String[] args) throws InterruptedException {
	Xls_Reader reader = new Xls_Reader("C:\\Users\\NEW\\eclipse-workspace_oxygen_sam_pos_All_2019\\Sam_Pos_Full_Inventory_Entry\\src\\test\\java\\com\\pos\\qa\\Inventory\\Sam_Pos_7.3.2019.xlsx");
	
		/*
		 * String s1=reader.getCellData("Sheet1","FirstName", 2);
		 * System.out.println(s1);
		 * 
		 * System.out.println(reader.getRowCount("Sheet1"));
		 */
	 WebDriverManager.chromedriver().setup();
	//System.setProperty("webdriver.chrome.driver", "C:\\Users\\NEW\\Desktop\\SELENIUM_All\\SELENIUM TOOLS\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().deleteAllCookies();
	driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
	driver.get("http://hrmshost-001-site7.ftempurl.com/#/Login");
	
	driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("admin");
	driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1111");
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	Thread.sleep(3000);
	driver.findElement(By.xpath("//button[@type='button']")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//body[@class='theme-white']/div/div[@class='ng-scope']/section[@class='ng-scope']/section[@class='ng-scope']/aside[@id='leftsidebar']/div[@class='menu']/ul[@class='list']/li[6]/a[1]")).click();
	Thread.sleep(1000);
	driver.findElement(By.xpath("//span[contains(text(),'Purchase Order')]")).click();
	
	driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
	Thread.sleep(3000);
int  row=reader.getRowCount("Sheet1");
	
	for (int i = 2; i <=row; i++) {
		String s=reader.getCellData("Sheet1","Sl No", i);
	    System.out.println(s);
	    
		String s1=reader.getCellData("Sheet1","Location", i);
	    System.out.println(s1);
	    
	    String s2=reader.getCellData("Sheet1","Product Description", i);
	    System.out.println(s2);
	    
	    String s3=reader.getCellData("Sheet1","Monday On hand",i);
	    System.out.println(s3);
	    
	    driver.findElement(By.xpath("//td[text()='"+s1+"']//following-sibling::td[text()='"+s2+"']//parent::tr//child::td/input[@name='OnHand']")).sendKeys(s3);

	    
	    System.out.println("_____________________________");
	}
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
	Thread.sleep(2000);
	driver.findElement(By.xpath("//button[contains(text(),'Next')]")).click();
	Thread.sleep(2000);
	
	//PURCHASE ORDER
int  row1=reader.getRowCount("Sheet2");
	System.out.println("Purchase Order Starts");
	for (int j = 2; j <=row; j++) {
		String s4=reader.getCellData("Sheet2","Sl No", j);
	    System.out.println(s4);
	    
		String s5=reader.getCellData("Sheet2","Product Description", j);
	    System.out.println(s5);
	    
	    String s6=reader.getCellData("Sheet2","To Be Order", j);
	    System.out.println(s6);
	    
	    String s7=reader.getCellData("Sheet2","Vendor Name",j);
	    System.out.println(s7);
	    
	  //td[text()='BOWL, FOAM 4-5 OZ WHITE UNLAMINATED']//following-sibling::
	    Select oSelect = new Select(driver.findElement(By.xpath("//td[text()='"+s5+"']//parent::tr//select[@name='VendorId']")));
	    oSelect.selectByVisibleText(s7);
	    Thread.sleep(1000);
	WebElement tobeorder=driver.findElement(By.xpath("//td[text()='"+s5+"']//parent::tr//input[@name='Quantity']"));
	tobeorder.clear();
	tobeorder.sendKeys(s6);
	Thread.sleep(1000);
	     
	   
	    System.out.println("_____________________________");
	}
	
	
}
}
