package pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static common.CommonActions.*;

import java.util.Set;

import common.CommonActions;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='header_sign_in']")
	WebElement register;

	// @FindBy(css = "input#signInName")
	// WebElement userId; //

	@FindBy(xpath = "//input[@id='signInName']")
	WebElement userId; //

	@FindBy(xpath = "//input[@id='password']")
	WebElement password;

	@FindBy(xpath = "//button[@id='next']")
	WebElement signIn;

	// @FindBy(id="shopAsNonMemberBtn")
	// WebElement shopAsNonMember;
	// 4900eb1f-0c10-4bd9-99c3-c59e6c1ecebf
//spfVHSTrLSLGXYgz04fUPrraTOtplwT
	// 1&krypto=7EC%2B1YLlCa3DifNA46Tljq6tQWQYiiB
	// -1&krypto=eV7O0azCnrT9v6JrBvfpSnqpPC1IKQYMt

	@FindBy(xpath = "//input[@id='shopAsNonMemberBtn']")
	WebElement shopAsNonMember;

	@FindBy(xpath = "//input[@id='search-field']")
	WebElement searchBar;

	public void inputTextInUserIdAndPasswordFieldThenClickLoginButton() {

		clickElement(register);
		inputText(userId, "shailakhnaum7@gmail.com");
		inputText(password, "Tasfia4922!");
		pause(5);
		clickElement(signIn);

		// Set<Cookie> cookies = driver.manage().getCookies();
		// System.out.println(cookies);

		// clickElement(shopAsNonMember);
		// ca.jsExecutor(driver,"arguments[0].click();", shopAsNonMember);
		// pause(5);
		// System.out.println("*************************");
//		clickElement(searchBar);
//		inputText(searchBar,"Soap");
	pause(10);
	}

}
