package mainpack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.internal.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.collections.Lists;

public class ScrapingNew {
	
	public static void getLocations(String docName, WebDriver driver){
		WebElement table =driver.findElement(By.xpath("//table[@class='styled drlocations']"));
		WebElement locationRows = table.findElement(By.tagName("tbody"));
		List<WebElement> rows=locationRows.findElements(By.tagName("tr"));
		System.out.println("Number of locations: "+rows.size());
				
		for(int i=1;i<=rows.size();i++){
			WebElement columnLocation = locationRows.findElement(By.xpath("//table[@class='styled drlocations']/tbody/tr["+ i +"]/td"));
			System.out.println("Location: "+ columnLocation.getText());
			WebElement columnAddress = locationRows.findElement(By.xpath("//table[@class='styled drlocations']/tbody/tr["+ i +"]/td[2]"));
			System.out.println("Address: "+ columnAddress.getText());
		}
	}
	
	public static void getBio(String docName, WebDriver driver){
//		WebElement bioTable = driver.findElement(By.xpath("//*[@id='item2']"));
		WebElement bioTable = driver.findElement(By.id("item2"));
//		List<WebElement> bioTableRows = bioTable.findElements(By.tagName("b"));
//		System.out.println("Number of bios: "+ bioTableRows.size());
		
		
		WebElement bioElement = bioTable.findElement(By.xpath("b[2]"));
				
		System.out.println("Bio (hidden): "+ bioElement.getAttribute("innerHTML"));
//		String str1 = bioTable.getAttribute("textContent").toString();
//		System.out.println(str1);
		
		Map<String, List<String>> parsedText = parseTextElement(bioTable);
		printParsedElement(parsedText);
		
		System.out.println("hello");
	}
	

	public static Map<String, List<String>> parseTextElement(
			WebElement textElement) {
		Map<String, List<String>> parsedText = new HashMap<>();
//		List<WebElement> keysElements = textElement.findElements(By.tagName("b"));
//		List<String> keys = new ArrayList<>();
//		for(WebElement keysElement : keysElements){
////			remove leading and trailing whitespace
//			System.out.println("key elements: "+ keysElement.getText());
//			String bioKey = removeWhiteSpaces(keysElement.getText());
//			keys.add(bioKey);
//		}
		
		List<WebElement> bioTableRows = textElement.findElements(By.tagName("b"));
		List<String> keys = new ArrayList<>();
		for(int i = 1; i<=bioTableRows.size();i++){
			WebElement bioElement = textElement.findElement(By.xpath("b["+i+"]"));
			String strBio = bioElement.getAttribute("innerHTML").toString();
			String bioKey = removeWhiteSpaces(strBio);
			keys.add(bioKey);
			System.out.println("test:" + bioKey);
		}

		
//		use html of element to help with parsing using "br" tag
		String workingText = textElement.getAttribute("innerHTML");
		System.out.println("working text: "+ workingText);
		System.out.println("keys: " + keys);
		
//		work from bottom up of keys
		Collections.reverse(keys);
		for(String key : keys){
//			System.out.println("key: "+ key);
			String block = workingText.split(key)[1];
//			System.out.println("block:" + block);
			String ReplacedBlock = block.replace("<b>", "");
			String ReplacedFinalBlock  = ReplacedBlock.replace("</b>", "");
			List<String> keyValues = Lists.newArrayList(ReplacedFinalBlock.split("<br>"));
			
//			iterate over values and clean up
			Iterator<String> iter = keyValues.iterator();
			while(iter.hasNext()){
				String keyValue = iter.next();
				keyValue = removeWhiteSpaces(keyValue);
				if(keyValue.equals("")){
					iter.remove();
				}		
			}
			//add final list of values with key to the map
			
			parsedText.put(key, keyValues);
		    
			//remove the part we just parsed out from the workingText
			workingText = workingText.split(key)[0];
		}
		
		return parsedText;
	}
	
	private static void printParsedElement(Map<String, List<String>> parsedText) {
		// TODO Auto-generated method stub
		for(Map.Entry<String, List<String>> entry : parsedText.entrySet()){
			System.out.println("Key: " + entry.getKey());
			System.out.println("Values:");
			for(String value : entry.getValue()){
				System.out.println(value);
			}
		}
	}

	private static String removeWhiteSpaces(String text) {
		return text.replaceAll("^\\s+|\\s+$", "");
	}

	public static void getSpecialities(String docName, WebDriver driver){
		WebElement specialities = driver.findElement(By.xpath("//*[@id='item3']"));
		String specialitiesText = specialities.getAttribute("textContent").toString();
		List<String> parsedText = parseSpecialities(specialitiesText);
		printParsedSpecialities(parsedText);
	}
	private static void printParsedSpecialities(List<String> parsedText) {
		// TODO Auto-generated method stub
		Iterator<String> iter = parsedText.iterator();
		while(iter.hasNext()){
			System.out.println(iter.next());
		}
		
	}

	private static List<String> parseSpecialities(String specialities) {
		// TODO Auto-generated method stub
		List<String> parsedText = new ArrayList<String>();
		String[] specialitiesArray = specialities.split(",");
		for(int i = 0; i < specialitiesArray.length; i++){
			parsedText.add(removeWhiteSpaces(specialitiesArray[i]));
		}
		return parsedText;
	}

	public static void location(){
		
	}
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
		
		driver.findElement(By.xpath("//*[contains(text(), 'Ronald Baker, MD')]")).click();
//		WebElement locationAddressTable = driver.findElement(By.className("styled drlocations"));
//      List<WebElement> tr_collection=locationAddressTable.findElements(By.xpath("className('styled drlocations')/tbody/tr"));
		System.out.println("Ronald Baker, MD");
		String docName = "Ronald Baker, MD";
		getLocations(docName, driver);
		getBio(docName, driver);
		getSpecialities(docName, driver);
		
		
	}

}
