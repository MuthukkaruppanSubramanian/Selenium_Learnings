package seleniumBasics;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WorkWithTables {

	public static WebDriver driver;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		openApplication("https://www.seleniumeasy.com/test/table-pagination-demo.html");
//		System.out.println(driver.findElement(By.xpath("//th[normalize-space()='Table heading 5']")).getText());
//		System.out.println(driver.findElement(By.xpath("//tbody/tr[3]/td[6]")).getText());
//		driver.findElement(By.xpath("//a[normalize-space()='2']")).click();
//		System.out.println(driver.findElement(By.xpath("//a[normalize-space()='2']")).isDisplayed());
		
//		openApplication("https://www.seleniumeasy.com/test/table-search-filter-demo.html");
//		driver.findElement(By.xpath("//input[@id='task-table-filter']")).sendKeys("Em");
//		List<WebElement> column = driver.findElements(By.xpath("//div[@class='panel panel-primary']//th"));
//		System.out.println("No.of Colum's: " + column.size());
//		
//		List<WebElement> rows = driver.findElements(By.xpath("//table[@class = 'table table-hover']//tr"));
//		System.out.println("No.of Rows: " + rows.size());
//		
//		for (int i=0; i < rows.size(); i++) {
//			WebElement eachRow = rows.get(i);
//			List<WebElement> colVals = eachRow.findElements(By.tagName("td"));
//			List<WebElement> colValues = (colVals.isEmpty())? eachRow.findElements(By.tagName("th")) : colVals;
//			for(int j = 0; j < column.size(); j++) {
//				WebElement cell = colValues.get(j);
//				if(cell.isDisplayed()) {
//					System.out.println(cell.getText());
//				}
//			}
//		}

		/*
		 * driver.findElement(By.
		 * xpath("//button[@class='btn btn-default btn-xs btn-filter']")).click();
		 * driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(
		 * "Zi");
		 * 
		 * List<WebElement> column =
		 * driver.findElements(By.xpath("//table[@class = 'table']//th"));
		 * System.out.println("No.of Colum's: " + column.size());
		 * 
		 * List<WebElement> rows =
		 * driver.findElements(By.xpath("//table[@class = 'table']//tr"));
		 * System.out.println("No.of Rows: " + rows.size());
		 * 
		 * for (int i=0; i < rows.size(); i++) { WebElement eachRow = rows.get(i);
		 * List<WebElement> colVals = eachRow.findElements(By.tagName("td"));
		 * List<WebElement> colValues = (colVals.isEmpty())?
		 * eachRow.findElements(By.tagName("th")) : colVals; for(int j = 0; j <
		 * column.size(); j++) { WebElement cell = colValues.get(j);
		 * if(cell.isEnabled()) { System.out.println(cell.getText()); } } }
		 */
		int number = 0;
		openApplication("https://www.seleniumeasy.com/test/table-sort-search-demo.html");
		driver.findElement(By.xpath("//input[@type='search']")).clear();
		String totalEntires = driver.findElement(By.xpath("//div[@id='example_info']")).getText();
		Pattern regex = Pattern.compile("Showing \\d to \\d+ of (\\d+) entries");
		Matcher matched = regex.matcher(totalEntires);
		if (matched.matches()) {
			number = Integer.parseInt(matched.group(1));
		}
		int remainder = ((number%10) > 0)? 1 : 0;
		int pressNextCount = ((number/10)+remainder);
		int salary[] = new int[number+110];
		int count = 0;
		int hightestSalary = 0;
		for (int k=0; k <= pressNextCount; k++) {
			List<WebElement> column = driver.findElements(By.xpath("//table[@id= 'example']//th"));
			System.out.println("No.of Colum's: " + column.size());
			
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id= 'example']//tr"));
			System.out.println("No.of Rows: " + rows.size());
			
			for (int i=0; i < rows.size(); i++) {
				WebElement eachRow = rows.get(i);
				List<WebElement> colVals = eachRow.findElements(By.tagName("td"));
				List<WebElement> colValues = (colVals.isEmpty())? eachRow.findElements(By.tagName("th")) : colVals;
				for(int j = 0; j < column.size(); j++) {
					String cell = colValues.get(j).getText();
					if(j == column.size()-1) {
						Pattern regex1 = Pattern.compile("\\$([\\d+,]+)/y");
						Matcher data = regex1.matcher(cell);
						if(data.matches()) {
							int  value  = Integer.parseInt(data.group(1).replace(",", ""));
							salary[count] = value;
							if(salary.length > 1) {
								hightestSalary = (value > hightestSalary)? value :hightestSalary;
							}
							count++;
						}
					}
				}
			}
			driver.findElement(By.xpath("//a[@id='example_next']")).click();
		}
		System.out.println("Hightest Salary: "+hightestSalary);
		driver.close();
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
