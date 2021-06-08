package seleniumBasics;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

import java.util.List;				

public class FindElementsInSel {
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://demo.guru99.com/test/ajax.html");
		WebElement btnNo = driver.findElement(By.id("no"));
		Boolean btnNoEnabled =  driver.findElement(By.xpath("//input[@id='no']")).isEnabled();
		if (btnNoEnabled) {
			btnNo.click(); 
		} else {
			System.out.println("Button No Not Available");	
		}
		driver.findElement(By.id("buttoncheck")).click();
		String actualText = driver.findElement(By.className("radiobutton")).getText();
		assertEquals("Radio button is checked and it's value is No",actualText);
		
		List<WebElement> elements = driver.findElements(By.name("name"));
		System.out.println("No of Elements in Name tag:" + elements.size());
		
		for(int i = 0; i<elements.size(); i++) {
			System.out.println("Radio Button Text:" + elements.get(i).getAttribute("value"));
		}
		
		driver.close();
		
	}

}
