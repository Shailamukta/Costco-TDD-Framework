package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static common.CommonActions.*;

public class SearchPage {

	WebDriver driver;

	public SearchPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	@FindBy(xpath = "//input[@id='search-field']")
	WebElement searchField;

	@FindBy(id = "retain-search-close")
	WebElement crossButton;

	@FindBy(id = "sugguestion-PopularSearch1")
	WebElement lotion;

	@FindBy(xpath = "//*[@id='formcatsearch']/div[2]/span[2]/div/div/div")
	List<WebElement> autoSuggestionFromSearch;

	@FindBy(id = "productTileLink_0")
	WebElement AveenoLotion;

	@FindBy(xpath = "//*[@id='add-to-cart-btn']")
	WebElement addToCart;

	@FindBy(xpath = "//*[@id='costcoModalText']/div[2]/div[2]/a/button")
	WebElement viewCart;

	@FindBy(xpath = "//*[@id='formcatsearch']/div[2]/span[2]/div")
	List<WebElement> autoSuggestionFromSearch2;

	@FindBy(xpath = "//input[@id='shopCartCheckoutSubmitButton']")
	WebElement checkOut;

	@FindBy(xpath = "//*[@id='productTileLink_0']/img")
	WebElement KirklandShampoo;

	@FindBy(xpath = "//*[@id='costcoModalText']/div[2]/div[1]/button")
	WebElement continueShopping;

	@FindBy(id = "cart-d")
	WebElement goToCart;

	@FindBy(xpath = "//*[@id='order-item_2']/div/div[3]/div/div/div/div")
	WebElement removeButton;

	public void searchButton() {

		clickElement(searchField);
		inputText(searchField, "Moiserizer");
		clickElement(crossButton);
		inputText(searchField, "Body Lotion");
		handleAutoSuggestionList(autoSuggestionFromSearch, "lotion");
		scrollIntoViewToTheElement(driver, "arguments[0].scrollIntoView(true);", AveenoLotion);
		clickElement(AveenoLotion);
		clickElement(addToCart);
		clickElement(viewCart);
		clickElement(searchField);
		inputTextThenClickEnter(searchField, "shampoo");
		scrollIntoViewToTheElement(driver, "arguments[0].scrollIntoView(true);", KirklandShampoo);
		clickElement(KirklandShampoo);
		clickElement(addToCart);
		clickElement(continueShopping);
		clickElement(goToCart);
		scrollIntoViewToTheElement(driver, "arguments[0].scrollIntoView(true);", removeButton);
		clickElement(removeButton);
		pause(10);
		clickElement(checkOut);

	}

}
