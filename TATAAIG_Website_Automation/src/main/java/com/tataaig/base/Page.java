package com.tataaig.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.tataaig.pages.InsuranceTabs;
import com.tataaig.utilities.ExcelReader;
import com.tataaig.utilities.ExtentManager;
import com.tataaig.utilities.Utilities;
import com.thoughtworks.selenium.Wait;

public class Page {		

	/*
	 * WebDriver
	 * 
	 * ExcelReader
	 * Logs
	 * WebDriverWait
	 * ExtentReports
	 * 
	 * 
	 * 
	 */
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;	
	public static InsuranceTabs insuranceTabs;
	
	public Page(){
		if (driver==null) {

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
			if (config.getProperty("browser").equals("firefox")) {

				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			driver = new ChromeDriver(options);
			}else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 20);
			insuranceTabs = new InsuranceTabs(driver);	
			test = new ExtentTest("CarInsurance test", "Booking Car Insuarance for new customer");
		}
	}	
	public static void quitBrowser(){		
		driver.quit();		
	}
	public static String getHomePageTitle() {
		String PageTitle = driver.getTitle();
		return PageTitle;
	
	}
	//Common Keywords

	public static void selectFromOptions(String locator,String value) {
			
		List<WebElement>options =driver.findElements(By.xpath(OR.getProperty(locator)));		
		wait.until(ExpectedConditions.visibilityOfAllElements(options));
		for(WebElement ele:options) {
			if (value.equalsIgnoreCase(ele.getText())){
				ele.click();		
				break;
			}
		}
		log.debug("Clicking on an Element : "+locator);
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	
	}
	
	
	public static void click(String locator) {
			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).click();
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).click();
			}
			log.debug("Clicking on an Element : "+locator);
			test.log(LogStatus.INFO, "Clicking on : " + locator);
		}

		public static void type(String locator, String value) {
			if (locator.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			} else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
			}else if (locator.endsWith("_NAME")) {
				driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
			}
			log.debug("Typing in an Element : "+locator+" entered value as : "+value);			
			test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);

		}
		
		static WebElement dropdown;

		public void select(String locator, String value) {

			if (locator.endsWith("_CSS")) {
				dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
			} else if (locator.endsWith("_XPATH")) {
				dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
			} else if (locator.endsWith("_ID")) {
				dropdown = driver.findElement(By.id(OR.getProperty(locator)));
			}
			
			Select select = new Select(dropdown);
			select.selectByVisibleText(value);

			log.debug("Selecting from an element : "+locator+" value as : "+value);
			test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

		}

		public static boolean isElementPresent(By by) {

			try {

				driver.findElement(by);
				return true;

			} catch (NoSuchElementException e) {

				return false;

			}

		}
		
		static WebElement webList;
		static List<WebElement> webListItems;		
		public static void selectFromDropDown(String locator_1,String locator_2, String value){
			
			webList = driver.findElement(By.cssSelector(OR.getProperty(locator_1)));			
					
			wait.until(ExpectedConditions.elementToBeClickable(webList));
			webList.click();	
			
			List<WebElement> webListItems = driver.findElements(By.cssSelector(OR.getProperty(locator_2)));	
			wait.until(ExpectedConditions.visibilityOfAllElements(webListItems));
			for (WebElement ele:webListItems) {
				if (value.equalsIgnoreCase(ele.getText())) {
					ele.click();		
					break;
				}
			}
			log.debug("Selecting from an element : "+locator_1+" value as : "+value);
			test.log(LogStatus.INFO, "Selecting from dropdown : " + locator_1 + " value as " + value);
		}

		public static void verifyEquals(String expected, String actual) throws IOException {

			try {

				Assert.assertEquals(actual, expected);

			} catch (Throwable t) {

				Utilities.captureScreenshot();
				// ReportNG
				Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
				Reporter.log("<a target=\"_blank\" href=" + Utilities.screenshotName + "><img src=" + Utilities.screenshotName
						+ " height=200 width=200></img></a>");
				Reporter.log("<br>");
				Reporter.log("<br>");
				// Extent Reports
				test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
				test.log(LogStatus.FAIL, test.addScreenCapture(Utilities.screenshotName));

			}

	}
}
