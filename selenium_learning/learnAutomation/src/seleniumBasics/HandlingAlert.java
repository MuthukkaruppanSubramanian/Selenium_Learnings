package seleniumBasics;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingAlert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/delete_customer.php");
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("23sd");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		//Wait for Alert Confirmation
		try {
			WebDriverWait waitForAlert = new WebDriverWait(driver, 20);
			waitForAlert.until(ExpectedConditions.alertIsPresent());
			Alert alertToDelete = driver.switchTo().alert();
			alertToDelete.accept();
			waitForAlert.until(ExpectedConditions.alertIsPresent());
			Alert alertInfo = driver.switchTo().alert();
			alertInfo.accept();
			driver.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("exception Msg: "+e);
		}
	}

}
