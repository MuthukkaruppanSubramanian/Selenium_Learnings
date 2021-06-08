package seleniumBasics;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUpload {

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/upload/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		String fileToUpload = "C:\\Users\\60026865.ICU_DOMAIN\\Downloads\\SILVER_tests_1-2-2021_23-32-48\\results\\TC-A-CCA-Selection_RESULTS_2021.01.02_23.01.18.html";
		WebElement uploadFiled = driver.findElement(By.xpath("//input[@id='uploadfile_0']"));
		/*
		 * driver.findElement(By.xpath("//input[@id='uploadfile_0']")).click(); Robot r
		 * = new Robot(); r.keyPress(KeyEvent.VK_C); r.keyRelease(KeyEvent.VK_C);
		 * r.keyPress(KeyEvent.VK_COLON); r.keyRelease(KeyEvent.VK_COLON);
		 * r.keyPress(KeyEvent.VK_SLASH); r.keyRelease(KeyEvent.VK_SLASH);
		 * r.keyPress(KeyEvent.VK_E); r.keyRelease(KeyEvent.VK_E);
		 * r.keyPress(KeyEvent.VK_F); r.keyRelease(KeyEvent.VK_F);
		 * r.keyPress(KeyEvent.VK_I); r.keyRelease(KeyEvent.VK_I);
		 * r.keyPress(KeyEvent.VK_4); r.keyRelease(KeyEvent.VK_4);
		 * r.keyPress(KeyEvent.VK_ENTER); r.keyRelease(KeyEvent.VK_ENTER);
		 */
		uploadFiled.sendKeys(fileToUpload);
		driver.findElement(By.xpath("//input[@id='terms']")).click();
		driver.findElement(By.xpath("//button[normalize-space()='Submit File']")).click();
		FileUpload t = new FileUpload();
		try {
			t.fileDownload(driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fileDownload(WebDriver driver) throws InterruptedException {
		driver.get("http://demo.guru99.com/test/yahoo.html");
		WebElement downloadbtn = driver.findElement(By.xpath("//a[normalize-space()='Download Now']"));
		String soruceLocation = downloadbtn.getAttribute("href");
		System.out.println(soruceLocation);
		String command = "cmd /c C:\\Wget\\wget.exe -P C: --no-check-certificate " + soruceLocation;
		
		try {
			Process exec = Runtime.getRuntime().exec(command);
			int exitVal = exec.waitFor();
			System.out.println("Exit value for the system command" + exitVal);
		} catch (IOException ex) {
			// TODO: handle exception
			System.out.println("Exception:" + ex.toString());
		}
		driver.close();
		File downloadedFile = new File("C:\\msgr11us.exe");
		System.out.println("File msgr11us.exe Downloaded?" +downloadedFile.exists());
	}

}
