package seleniumBasics;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingWindow {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.seleniumeasy.com/test/window-popup-modal-demo.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		String mainWindow = driver.getWindowHandle();
//		driver.findElement(By.xpath("//a[normalize-space()='Follow On Twitter']")).click();
		driver.findElement(By.xpath("//a[normalize-space()='Follow All']")).click();
		String [] arr = new String [] {"Selenium Easy (@seleniumeasy) / Twitter"};
		waitforWindowToOpen(arr);
		driver.findElement(By.xpath("//a[normalize-space()='Like us On Facebook']")).click();
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> navigateTo = windows.iterator();
		while(navigateTo.hasNext()) {
			String currentWindow = navigateTo.next();
			driver.switchTo().window(currentWindow);
			String currentTitle = driver.getTitle();
			System.out.println("Current page Title: " + currentTitle);
			if(currentTitle.equalsIgnoreCase("Selenium Easy (@seleniumeasy) / Twitter")) {
				driver.findElement(By.xpath("//span[contains(text(),'No, thanks')]")).click();
			} else if (currentTitle.endsWith("Facebook")) {
				try {
					WebDriverWait wait = new WebDriverWait(driver, 20);
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody//tr//td//input[@id='email']")));
					driver.findElement(By.xpath("//tbody//tr//td//input[@id='email']")).click();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception Msg: " + e.toString());
				}
			}
			if (!currentWindow.equals(mainWindow)) {
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);
		driver.close();
		System.out.println("End of TC");
	}

	static <T>void waitforWindowToOpen(T[] arr) {
		long startTime = System.currentTimeMillis();
		long end = startTime+84000;
		String className = arr[0].getClass().getName();
		Boolean isAString = className.equals("java.lang.String");
		Set<String> windows = HandlingWindow.driver.getWindowHandles();
		while (true) {
			Boolean expWin = false;
			if(System.currentTimeMillis() > end){
				break;
			} else if(isAString){
				for(String currWindow : windows) {
					String title = HandlingWindow.driver.switchTo().window(currWindow).getTitle();
					expWin = title.equals(arr[0]);
					if(expWin) {
						System.out.println("Second Window Launched");
						switchToFirstWindow(windows);
						break;
					}
				}
			} else if ((!isAString) && (windows.size() == 2)) {
				System.out.println("Second Window Launched");
				break;
			}
			if(expWin) {
				System.out.println("Break from While Loop");
				break;
			}
		}
	}
	
	public static void switchToFirstWindow(Set<String> windows ) {
		try {
			for(String currWindow : windows) {
				HandlingWindow.driver.switchTo().window(currWindow);
				String title = HandlingWindow.driver.getTitle();
				System.out.println("Current Window Title: " + title);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Unable to Switch to first window");
			System.out.println("Exception Msg: "+ e.toString());
		}
	}
}
