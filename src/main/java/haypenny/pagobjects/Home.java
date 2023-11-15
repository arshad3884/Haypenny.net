package haypenny.pagobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
	WebDriver driver;

	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "welcomeOfferListArea")
	WebElement latestOffer;
	@FindBy(xpath = "(//a[@href='#welcome'])[1]")
	WebElement logoIcon;
	@FindBy(xpath = "(//a[@href='#welcome'])[2]")
	WebElement homeMenu;
	@FindBy(xpath = "//div[text()='Your Wallet']")
	WebElement yourWallet;
	@FindBy(xpath = "//div[text()='Public Currencies']")
	WebElement publicCurrencies;
	@FindBy(xpath = "//div[text()='Trading']")
	WebElement trading;
	@FindBy(xpath = "//div[text()='Your Currencies']")
	WebElement yourCurrencies;
	@FindBy(xpath = "//div[text()='Create New']")
	WebElement createNew;
	@FindBy(xpath = "//div[text()='About Haypenny']")
	WebElement aboutHaypenny;
	@FindBy(xpath = "//div[text()='Extreme Privacy Policy']")
	WebElement privacyPolicy;
	@FindBy(xpath = "//div[text()='Help Topics']")
	WebElement helpTopics;
	@FindBy(xpath = "//div[@style='display: block;']//div[@class='spinner']")
	WebElement loader;

	public void gotoHomePage() {
		homeMenu.click();
	}

	public void gotoYourWallet() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		yourWallet.click();
	}

	public void gotoPublicCurrencies() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		publicCurrencies.click();
		//wait.until(ExpectedConditions.invisibilityOf(loader));
	}

	public void gotoTrading() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		trading.click();
		//	wait.until(ExpectedConditions.invisibilityOf(loader));
	}

	public void gotoYourCurrencies() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		yourCurrencies.click();
		//wait.until(ExpectedConditions.invisibilityOf(loader));
	}

	public void gotoCreatNewCurrency() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		createNew.click();
	}

	public void gotoAbout() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		aboutHaypenny.click();
	}

	public void gotoPrivacyPolicy() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		privacyPolicy.click();
	}

	public void gotoHelpTopics() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		helpTopics.click();
	}

	public void gotoLatestOffer() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(loader));
		latestOffer.click();
		wait.until(ExpectedConditions.invisibilityOf(loader));
	}
}