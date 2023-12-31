package baseUtil;

import java.lang.reflect.Method;

import static utils.IConstent.EDGE;
import static utils.IConstent.EXPLICITLY_WAIT;
import static utils.IConstent.IMPLICITLY_WAIT;
import static utils.IConstent.PAGELOAD_WAIT;
import static utils.IConstent.URL;
import static utils.IConstent.*;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.beust.jcommander.Parameter;

import common.CommonActions;
import constants.Profile;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.OperaDriverManager;
//import pages.ForgotUserId;
import pages.HomePage;
import pages.SearchPage;
//import pages.NewUserRegistration;
import reports.ExtentManager;
import reports.ExtentTestManager;
import utils.Configuration;

public class BaseClass {
	public WebDriver driver;
	public HomePage homePage;
	public SearchPage sp;
	Configuration configuration;
	public Dimension dimension;
	public Actions actions;
	public JavascriptExecutor js;
	// public Select select;
	public WebDriverWait wait;
	String browserName;
	// public NewUserRegistration newUserRegistration;
	ExtentReports report;
	ExtentTest extentTest;

	@BeforeSuite
	public void initialReporting() {
		report = ExtentManager.initialReports();
	}

	@BeforeClass
	public void beforeClassSetUp() {
		configuration = new Configuration(Profile.GENERAL);
		// default Constructor of Configuration Class will be initialized
	}

	@Parameters("browser")
	@BeforeMethod
	public void setUP(@Optional(CHROME) String browserName) {
		// If any reason our test suit doesn't have parameter or value, then
		// @Optional(FIREFOX) will work BY LINE 98
		// If there is empty value or wrong value in testng.xml suite, then browser will
		// not match and get the default one
		// WebdriverManager is instantiating the ChromeDriver
		this.browserName = browserName;
		initDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(configuration.getProperties(URL));
		// How can we convert a String to Long type
		long pageLoadWait = Long.parseLong(configuration.getProperties(PAGELOAD_WAIT));
		long implicitlyWait = Long.parseLong(configuration.getProperties(IMPLICITLY_WAIT));
		long explicitlyWait = Long.parseLong(configuration.getProperties(EXPLICITLY_WAIT));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadWait));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
		initClass();
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver; // Memorize it
	}

	public void initDriver() {
		// String browserName = configuration.getProperties(BROWSER);

		switch (browserName) {

		case CHROME:
			System.setProperty("webdriver.chrome.driver",
					"/Users/bioferdous/eclipse-workspace/com.costco.tdd/driver/chromedriver");

			ChromeOptions op = new ChromeOptions();
			// op.setBinary("./Driver/ChromeBrowser");
			driver = new ChromeDriver();
			break;

		case FIREFOX:
			System.setProperty("webdriver.gecko.driver", "./driver/geckodriver");
			driver = new FirefoxDriver();
			break;

		case EDGE:
			// System.setProperty("webdriver.edge.driver", "./driver/msedgedriver");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:

			break;

		default:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}

	}

	public void initClass() {
		homePage = new HomePage(driver);
		sp = new SearchPage(driver);
		// forgotUserId = new ForgotUserId(driver);
		// newUserRegistration = new NewUserRegistration(driver);
	}

	@BeforeMethod
	public void initialTest(Method method) {
		extentTest = ExtentTestManager.createTest(report, method.getName());
		extentTest.assignCategory(method.getDeclaringClass().getName());
	}

	@AfterMethod
	public void tearUp() {
		driver.quit();
	}

//	@AfterMethod
//	public void afterEachTest(Method method, ITestResult result) {
//		for(String group: result.getMethod().getGroups()) {
//			extentTest.assignCategory(group);
//		}
//		
//		if(result.getStatus() == ITestResult.SUCCESS) {
//			extentTest.log(Status.PASS, "Test PASSED");
//		}else if(result.getStatus() == ITestResult.FAILURE) {
//			extentTest.addScreenCaptureFromPath(CommonActions.getSreenShot(method.getName(), driver));
//			extentTest.log(Status.FAIL, "Test FAILED");
//		}else if(result.getStatus() == ITestResult.SKIP) {
//			extentTest.log(Status.SKIP, "Test SKIPPED");
//		}
//	}
//	
//	@AfterSuite
//	public void publishReport() {
//		report.flush();
//	}

}