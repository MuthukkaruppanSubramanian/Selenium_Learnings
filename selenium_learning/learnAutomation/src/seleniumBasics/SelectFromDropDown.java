package seleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectFromDropDown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/newtours/register.php");
		Select dropDownCountry = new Select(driver.findElement(By.xpath("//select[@name='country']")));
		dropDownCountry.selectByIndex(2);
		Thread.sleep(100);
		dropDownCountry.selectByValue("GREENLAND");
		Thread.sleep(100);
		dropDownCountry.selectByVisibleText("ZAIRE");
		driver.close();
	}

}
