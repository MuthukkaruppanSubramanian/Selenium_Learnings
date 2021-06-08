package seleniumBasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/login.html");
		WebElement userId = driver.findElement(By.xpath("//input[@id='email']"));
		userId.sendKeys("abcd@gmail.com");
		WebElement password = driver.findElement(By.xpath("//input[@id='passwd']"));
		password.sendKeys("abcdefghlkjl");
		userId.clear();
		password.clear();
		userId.sendKeys("abcd@gmail.com");
		password.sendKeys("abcdefghlkjl");
		driver.findElement(By.xpath("//span[normalize-space()='Sign in']")).submit();
		Boolean loogedIn = driver.findElement(By.xpath("//h3[normalize-space()='Successfully Logged in...']")).isDisplayed();
		System.out.println("Logged in Successfully:" + loogedIn);
		driver.close();
		

	}

}
