package seleniumBasics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MouseAction {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", "C:\\WebDriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		//Mousehover Example
		/*
		 * driver.get("http://demo.guru99.com/test/newtours/"); WebElement linkHome =
		 * driver.findElement(By.xpath("//a[normalize-space()='Home']")); WebElement
		 * colorOfSelected = driver.findElement(By.xpath(
		 * "//body[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[1]/table[1]/tbody[1]/tr[1]/td[1]"
		 * )); Actions builder = new Actions(driver); Action mouseHover = builder
		 * .moveToElement(linkHome) .build();
		 * 
		 * String bgColor = colorOfSelected.getCssValue("background-color");
		 * System.out.println("BackGround color before mouse hover: " + bgColor);
		 * mouseHover.perform(); bgColor =
		 * driver.findElement(By.xpath("//tr[@class='mouseOver']//td[1]")).getCssValue(
		 * "background-color");
		 * System.out.println("BackGround color after mouse hover: " + bgColor);
		 */

		//KeysDown and KeyUp Example
		/* 
		 * driver.get("https://www.facebook.com/"); driver.manage().window().maximize();
		 * driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		 * WebElement txtUsername =
		 * driver.findElement(By.xpath("//input[@id='email']")); Actions builders = new
		 * Actions(driver); Action enterValues = builders .moveToElement(txtUsername)
		 * .contextClick() .click() .keyDown(txtUsername, Keys.SHIFT)
		 * .sendKeys(txtUsername, "hello") .keyUp(txtUsername, Keys.SHIFT)
		 * .doubleClick(txtUsername) .contextClick() .build(); enterValues.perform();
		 */

		//Drag and Drop Example
		driver.get("https://demoqa.com/droppable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='draggable']")));
		WebElement objToDrag = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement objToDrop = driver.findElement(By.xpath("//div[@id='simpleDropContainer']//div[@id='droppable']"));

		Actions builder = new Actions(driver);
		int dragX = objToDrag.getLocation().getX()+20;
		int dragY = objToDrag.getLocation().getY();
		builder.dragAndDropBy(objToDrag, dragX, dragY).perform();
		Thread.sleep(10000);
		builder.dragAndDrop(objToDrag, objToDrop).perform();
		Boolean action = objToDrop.getText().equals("Dropped!");
		System.out.println("Is element dropped:"+action);
		driver.close();
	}

}
