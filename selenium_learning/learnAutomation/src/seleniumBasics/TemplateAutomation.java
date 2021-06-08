package seleniumBasics;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TemplateAutomation {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		openApplication("https://www.goibibo.com/");
		driver.findElement(By.xpath("//span[@id='roundTrip']")).click();
		driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']")).sendKeys("A");
		driver.findElement(By.cssSelector("li[id='react-autosuggest-1-suggestion--1'] div[class='mainTxt clearfix'] span:nth-child(1)")).click();
		driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']")).sendKeys("MAA");
		driver.findElement(By.cssSelector("li[id='react-autosuggest-1-suggestion--0'] div[class='mainTxt clearfix'] span:nth-child(2)")).click();
		Boolean clanderDisplayed = driver.findElement(By.xpath("//div[@class='DayPicker']")).isDisplayed();
		System.out.println("Candler displayed: " + clanderDisplayed);
		List<WebElement> totalWeeks = driver.findElements(By.xpath("//div[contains(@class,'DayPicker-Week')]/child::div"));
		HashMap<Integer, Integer> dayPrice = new HashMap<>();
		for(WebElement week : totalWeeks) {
			String[] date = week.getText().split("\n");
			dayPrice.put(Integer.parseInt(date[0]), Integer.parseInt(date[0]));
		}
		Collection<Integer> lowestPrice = dayPrice.values();
		for(int price : dayPrice.values()) {
			System.out.println(price);
		}
		System.out.println("End of TC");
	}
	public static void openApplication(String url) {
		try {
			System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			// TODO: handle exception
			printException("unable to open app", e);
		}
	}
	
	public static void printException(String message, Exception e) {
		System.out.println(message);
		System.out.println("Exception Message: " + e.toString() + "\nBacktrace:");
		e.printStackTrace(System.out);
	}
}
