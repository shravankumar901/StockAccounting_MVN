package CommonFunctionLibrary;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;

public class FunctionLibrary { 

	public static WebDriver driver;
	
	public static WebDriver startBrowser() throws Exception{
		
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome")){			
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			 driver =new ChromeDriver();		
		}else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
			 driver =new FirefoxDriver();		
		}
		else{
			System.out.println("invalid browser");
		}
		
		return driver;
	}
	
	public static void openApplication() throws Exception{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
		driver.manage().window().maximize();
	}
	
	public static void waitForElement(WebDriver driver,String locatortype,
			String locatorvalue,String waittitme){
		
		WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waittitme));
		
		if(locatortype.equalsIgnoreCase("id")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("name")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorvalue)));
		}else if(locatortype.equalsIgnoreCase("xpath")){
			mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
		}else
		{
			System.out.println("unable to locate for waitForElement method");
		}
	}	
	
	public static void typeAction(WebDriver driver,String locatortype,
				String locatorvalue,String testdata){
		if(locatortype.equalsIgnoreCase("id")){
			driver.findElement(By.id(locatorvalue)).clear();
		   driver.findElement(By.id(locatorvalue)).sendKeys(testdata);
		}else if(locatortype.equalsIgnoreCase("name")){
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.name(locatorvalue)).sendKeys(testdata);
		}else if(locatortype.equalsIgnoreCase("xpath")){
			driver.findElement(By.id(locatorvalue)).clear();
			driver.findElement(By.xpath(locatorvalue)).sendKeys(testdata);
		}
		else{
			System.out.println("Locator not matching for typeAction method");
		}
	
	}
	
	public static void clickAction(WebDriver driver,String locatortype,String locatorvalue){
			if(locatortype.equalsIgnoreCase("id")){
			   driver.findElement(By.id(locatorvalue)).click();
			}else if(locatortype.equalsIgnoreCase("name")){
				driver.findElement(By.name(locatorvalue)).click();
			}else if(locatortype.equalsIgnoreCase("xpath")){
				driver.findElement(By.xpath(locatorvalue)).click();
			}
			else{
				System.out.println("Locator not matching for clickAction method");
			}
	}
	
	public static void closeBrowser(WebDriver driver){
		driver.close();
	}
	
	
	public static String generateDate(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
	}
	
	
		
}
