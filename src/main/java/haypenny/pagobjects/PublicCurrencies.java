package haypenny.pagobjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PublicCurrencies {
	
	WebDriver driver;
	public PublicCurrencies(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//div[@style='display: block;']//div[@class='spinner']")
	WebElement loader;
	
	@FindBy (xpath="//div[@class='find_result_logo']")
	List<WebElement> activePublicCurrencies;
	public boolean openAllActivePublicCurrencies()
	{
		for(WebElement activePublicCurrency: activePublicCurrencies)
		{
			Actions action = new Actions(driver);
			action.keyDown(Keys.CONTROL);
			action.click(activePublicCurrency).build().perform();
			action.keyUp(Keys.CONTROL);
		}
		System.out.println("All public currencies pages opened in new tabs correctly!");
		return true;
	}
	
	@FindBy (id="f_findQuery")
	WebElement findCurrencySearch;
	@FindBy (id="b_find")
	WebElement findButton;
	@FindBy (xpath="//div[text()='No Results Found']")
	WebElement noResult;
	@FindBy (xpath="//div[@class='find_result_area']")
	WebElement searchResult;
	public boolean goToPublicCurrencyPage(String CurrName) throws InterruptedException
	{	
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		findCurrencySearch.sendKeys(CurrName);
		findButton.click();
		Thread.sleep(2000);
		try {
		if (noResult.getText().equalsIgnoreCase("No Results Found"))
			{
			System.out.println(CurrName + " is not found on Public Currency Page!");
			return false;
			}
		}
		catch (NoSuchElementException e) {
			wait.until(ExpectedConditions.invisibilityOf(loader));
			wait.until(ExpectedConditions.visibilityOf(searchResult));
			searchResult.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("coinLogo")));
			return true;
	    }
		return false;
	}
	
	@FindBy (id="coinName")
	WebElement coinName;
	@FindBy (id="coinLongDesc")
	WebElement coinLongDesc;
	@FindBy (id="coinShareUrl")
	WebElement coinShareUrl;
	public String getCurrencyInfo(String CurrName)
	{
		String result = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		findCurrencySearch.sendKeys(CurrName);
		findButton.click();
		try {
		if (noResult.getText().equalsIgnoreCase("No Results Found"))
			{
			System.out.println("No currency found for search keywords!");
			return null;
			}
		}
		catch (NoSuchElementException e) {
	        // Handle the case where the element is not found
			wait.until(ExpectedConditions.invisibilityOf(loader));
			wait.until(ExpectedConditions.visibilityOf(searchResult));
			searchResult.click();
			wait.until(ExpectedConditions.invisibilityOf(loader));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("coinLogo")));
			result = coinName.getText()+": "+ coinLongDesc.getText()+" Sharelink: " + coinShareUrl.getText();
	    }
		return result;
	}
	
	
}
