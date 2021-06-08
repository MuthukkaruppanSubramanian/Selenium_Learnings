package seleniumBasics;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class GoibiboBookTicket {

	public static WebDriver driver;

	public static void main(String[] args) throws Exception {
		openApplication("https://www.goibibo.com/");
		driver.findElement(By.xpath("//span[@id='roundTrip']")).click();
		driver.findElement(By.xpath("//input[@id='gosuggest_inputSrc']")).sendKeys("A");
		driver.findElement(By.cssSelector("li[id='react-autosuggest-1-suggestion--1'] div[class='mainTxt clearfix'] span:nth-child(1)")).click();
		driver.findElement(By.xpath("//input[@id='gosuggest_inputDest']")).sendKeys("MAA");
		driver.findElement(By.cssSelector("li[id='react-autosuggest-1-suggestion--0'] div[class='mainTxt clearfix'] span:nth-child(2)")).click();
		Boolean clanderDisplayed = driver.findElement(By.xpath("//div[@class='DayPicker']")).isDisplayed();
		Thread.sleep(3000);
		System.out.println("Candler displayed: " + clanderDisplayed);
		List<WebElement> totalWeeks = driver.findElements(By.xpath("//div[contains(@class,'DayPicker-Week')]/child::div"));
		int lowday = getLowestDate(totalWeeks, 0);
		Thread.sleep(3000);
		List<WebElement> totalReturnWeeks = driver.findElements(By.xpath("//div[contains(@class,'DayPicker-Week')]/child::div"));
		getLowestDate(totalReturnWeeks, lowday);
		driver.findElement(By.xpath("//button[@id='gi_search_btn']")).click();
		String filghtName = driver.findElement(By.xpath("//div//div[.='Your Selection']/parent::div/child::div//span[2]")).getText();
		System.out.println("Filgt Name: " + filghtName);
		WebElement sumAmount = driver.findElement(By.xpath("//span[@class='font24 quicksand fb']"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(sumAmount));
		int totalValue = Integer.parseInt(sumAmount.getText().replace(",", ""));
		List<WebElement> values = driver.findElements(By.xpath("//*[@class='Rupee__RupeeIcon-sc-16wfe5u-0 bwjHzU']/following-sibling::span"));
		int onWardTrip = 0;
		for(WebElement value : values) {
			onWardTrip += Integer.parseInt(value.getText().replace(",", ""));
		}
		System.out.println("Total value matches each trip value: " + (onWardTrip == totalValue));
		driver.findElement(By.xpath("//div[@class='srp-card-uistyles__ResultCard-sc-3flq99-24 kkuOhu lh1'][1]//div/child::div//span[@class='hpyBlueLt padR5 fb font12']")).click();
		List<WebElement> stopDetails = driver.findElements(By.xpath("//div[contains(@class,'srp-card-uistyles__FltDetailCont-sc-3flq99-42 hirSJi width100 flexCol')]/child::div"));
		int count = 0;
		for(WebElement detail : stopDetails) {
			String text = detail.getText();
			if(text.contains("Layover")) { count +=1;}
		}
		System.out.println("No.Of Stops In Between: " + count);
		takeSnapShot(driver, "C:\\Users\\60026865.ICU_DOMAIN\\Documents\\Learnings\\goIb.png");
		driver.close();
		System.out.println("End of TC" );
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

	public static int getLowestDate(List<WebElement> totalWeeks, int daySelected) {
		HashMap<Integer, Integer> dayPrice = new HashMap<>();
		Pattern regex1 = Pattern.compile("[\\D\'[\']]+");
		for(WebElement week : totalWeeks) {
			if(week.isEnabled()) {
				String text = week.getText().toString();
				Matcher data = regex1.matcher(text);
				if(data.matches() || text.isEmpty()) {
					continue;
				}
				String [] date = text.split("\n");
				if (date.length == 2) {
					if (Integer.parseInt(date[1]) > 0) {
						dayPrice.put(Integer.parseInt(date[0]), Integer.parseInt(date[1]));
					}
				}
			}
		}
		int lowestPrice = 999999999;
		for(int price : dayPrice.values()) {
			lowestPrice = (lowestPrice < price)? lowestPrice : price;
		}
		//using the lowest price we can get the key from haspmap
		//then we can click that element
		//Then store the currentSelected Date and Select the next date with same approach
		//Click Search
		//....
		int lowday = 999999999;
		for(int day : dayPrice.keySet()) {
			if((dayPrice.get(day) == lowestPrice) && !(day == daySelected)){
				lowday = (lowday < day)? lowday : day;
			}
		}

		String tag = "";
		if(Integer.toString(lowday).split("").length==1) {
			tag = "//div[@id='fare_2021060day']".replace("day", Integer.toString(lowday));			
		} else {
			tag = "//div[@id='fare_202106day']".replace("day", Integer.toString(lowday));
		}
		driver.findElement(By.xpath(tag)).click();
		return lowday;
	}
	
	public static void takeSnapShot(WebDriver driver, String filePath) throws Exception{
		TakesScreenshot scrnShot = ((TakesScreenshot) driver);
		File srcFile = scrnShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(filePath);
		Files.copy(srcFile,destFile);
	}
}
