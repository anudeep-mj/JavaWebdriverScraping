package mainpack;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;


public class IN_WithamHealthServices_Scraper {
//	public WebDriver driver = new FirefoxDriver();
//	
//	public void openSite() {
//		driver.navigate().to("http://www.witham.org/body.cfm?id=12");
//	}
//	
//	public void firstOpen() {
//		WebElement submit_button = driver.findElement(By.xpath("//input[@value='Find']"));
//		submit_button.click();
//	}
//	
//	public void test() 
//	  {
//	   driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//	   String  datentime = driver.findElement(By.className("metalist")).getText();//Locating element by className and store its text to variable datentime.
//	   System.out.print(datentime);
//	  }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		IN_WithamHealthServices_Scraper webScrapper = new IN_WithamHealthServices_Scraper();
//		webScrapper.openSite();
//		webScrapper.firstOpen();
//		webScrapper.test();
		
		WebDriver driver = new FirefoxDriver();
	    String baseUrl = "http://www.witham.org/body.cfm?id=12";
//	    String baseUrl = "http://www.google.com";
//	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    

//		  File resultWriter = new File("C:/Users/Chris/Documents/Results.csv");
//			FileWriter writer = new FileWriter(resultWriter, true);
			
	    driver.get(baseUrl);
	    driver.findElement(By.xpath("//input[@value='Find']")).click();
	    
//	    List<WebElement> anchors = driver.findElements(By.tagName("a"));
//	    Iterator<WebElement> i = anchors.iterator();
//	    while(i.hasNext()) {
//	        WebElement anchor = i.next();
//	        anchor.getText();
//	    }
//	    driver.findElement(By.className("")).click();
	    List<WebElement> anchors = driver.findElements(By.xpath("//a[@class='metalist']")); 
	    Iterator<WebElement> i = anchors.iterator();
	    while(i.hasNext()) {
	        WebElement anchor = i.next();
	        anchor.getText();
	    }
	}

}
