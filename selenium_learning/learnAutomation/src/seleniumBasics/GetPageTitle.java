package seleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GetPageTitle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.facebook.com/login/identify/?ctx=recover&ars=facebook_login&from_login_screen=0");;
		String currentTitle = driver.getTitle();
		System.out.println("Current Page:" + currentTitle);
		driver.findElement(By.xpath("//a[@aria-label='Facebook']//*[local-name()='svg']")).click();
		driver.findElement(By.xpath("//a[@title='English (UK)']")).click();
		if(driver.getTitle().equals("Facebook – log in or sign up")) {
			System.out.println("We are back to Facebook Login Page");
		}else {
			System.out.println("We are in different page:" + driver.getTitle());
		}
		driver.close();
	}
}
