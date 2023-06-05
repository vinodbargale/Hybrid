package commonFunctions;


	
	


	import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.PropertyFileUtil;



	public class FunctionLibrary {
		public static WebDriver driver;
	//method for browser launching
		public static WebDriver startBrowser()throws Throwable
		{
			if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "D://chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}
			else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
			{
				System.setProperty("webdriver.gecko.driver", "F:geckodriver//geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else
			{
			System.out.println("Browser value is Not Matching");	
			}
			return driver;
		}
		//method for launching url
		public static void openUrl(WebDriver driver)throws Throwable
		{
			driver.get(PropertyFileUtil.getValueForKey("Url"));
		}
		//method for wait for element
		public static void waitForElement(WebDriver driver,String LocatorType,String LocatorValue,String testData)
		{
			WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(testData));
			if(LocatorType.equalsIgnoreCase("name"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
			}
			else if(LocatorType.equalsIgnoreCase("id"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
			}
			else if(LocatorType.equalsIgnoreCase("xpath"))
			{
				myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
			}
			else
			{
				System.out.println("Unable to execute Wait for element method");
			}
		}
		//method for textbox
		public static void typeAction(WebDriver driver,String Locatortype,String Locatorvalue,String testdata)
		{
			if(Locatortype.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(Locatorvalue)).clear();
				driver.findElement(By.xpath(Locatorvalue)).sendKeys(testdata);
			}
			else if(Locatortype.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(Locatorvalue)).clear();
				driver.findElement(By.name(Locatorvalue)).sendKeys(testdata);	
			}
			else if(Locatortype.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(Locatorvalue)).clear();
				driver.findElement(By.id(Locatorvalue)).sendKeys(testdata);
			}
			else
			{
				System.out.println("Unable to execute type action method");
			}
		}
		//method for buttons
		public static void clickAction(WebDriver driver,String LocatorType,String LocatorValue)
		{
			if(LocatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(LocatorValue)).click();
			}
			else if(LocatorType.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(LocatorValue)).click();
			}
			else if(LocatorType.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
			}
			else
			{
				System.out.println("Unable to Execute clickaction method");
			}
		}
		//method for title validation
		public static void validateTitle(WebDriver driver,String expectedtitle)
		{
			String actualtitle=driver.getTitle();
			try {
			Assert.assertEquals(expectedtitle, actualtitle,"Title is Not Matching");
			}catch(Throwable t)
			{
				System.out.println(t.getMessage());
			}
		//close browser
			
		}
		public static void closeBrowser(WebDriver driver)
		{
			driver.close();
		}
	}



