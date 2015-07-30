package mainpack;

import java.util.List;

import org.openqa.selenium.internal.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ScrapingNew {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.parkview.com/en/ppg/Pages/Find-a-PPG-Doctor.aspx");
		driver.findElement(By.id("ctl00_ctl19_g_3dfbac45_8ee5_4f95_9ee2_5c35c0b2d548_ctl00_btnV1ES")).click();
		
/*		for(int numberOfIterationsToLoopOverDoctors = 0; numberOfIterationsToLoopOverDoctors < 2; numberOfIterationsToLoopOverDoctors++)
		{
			driver.findElement(By.id("ctl00_ctl19_g_3dfbac45_8ee5_4f95_9ee2_5c35c0b2d548_ctl00_ucPagerUpper_btnNext")).click();
			System.out.println("Loop number: " + numberOfIterationsToLoopOverDoctors);
			List<WebElement> listOfDocs = driver.findElements(By.className("name"));
			for(int i = 0; i < listOfDocs.size(); i++)
			{
				System.out.println(listOfDocs.get(i).getText());				
			}
		}*/
		
		driver.findElement(By.xpath("//*[contains(text(), 'Mahmoud Afifi, MD')]")).click();
		WebElement locationAddressTable = driver.findElement(By.className("styled drlocations"));
        List<WebElement> tr_collection=locationAddressTable.findElements(By.xpath("className('styled drlocations')/tbody/tr"));
		
	}

}
