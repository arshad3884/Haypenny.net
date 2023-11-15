package haypenny.pagobjects;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Trading {
	WebDriver driver;
	public Trading(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@style='display: block;']//div[@class='spinner']")
	WebElement loader;
	
	@FindBy (id="b_tradingOffers")
	WebElement offers;
	@FindBy (xpath="//div[@id='tradingOffersTable']//div[contains(@onclick, '#offer_')]")
	List <WebElement> activeOffers;
	public boolean viewActiveOffersTable()
	{
		offers.click();
		if (activeOffers.size()==0)
		{
			System.out.println("No active offer!");
			return true;
		}
		else {
			System.out.println("Active offers are shown in the table!");
			return true;	
		}
	}
	@FindBy (id="b_tradingBids")
	WebElement bids;
	@FindBy (xpath="//div[@id='tradingBidsTable']//div[contains(@onclick, '#offer_')]")
	List <WebElement> activeBids;
	public boolean viewActiveBidsTable()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		bids.click();
		if (activeBids.size()==0)
		{
			System.out.println("No active Bids!");
			return true;
		}
		else {
			System.out.println("Active Bids are shown in the table!");
			wait.until(ExpectedConditions.visibilityOfAllElements(activeBids));
			return true;	
		}
	}
	
	@FindBy (id="b_tradingPrevious")
	WebElement archive;
	@FindBy (xpath="//div[@id='tradingOldTable']//div[contains(@onclick, '#offer_')]")
	List <WebElement> archiveBids;
	public boolean viewArchiveBidsTable() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		archive.click();
		Thread.sleep(1000);
		if (archiveBids.size()==0)
		{
			System.out.println("No Archive Bids data found!");
			return true;
		}
		else {
			System.out.println("Archive Bids are shown in the table!");
			wait.until(ExpectedConditions.visibilityOfAllElements(archiveBids));
			return true;
		}
	}
	
	@FindBy (id="b_tradingNew")
	WebElement createNew;
	@FindBy (xpath="(//button[@class='next_button'])[1]")
	WebElement next1;
	@FindBy (id="f_newOfferQuery")
	WebElement searchForCurr;
	@FindBy (id="b_newOfferSearch")
	WebElement forCurrSearch;
	@FindBy (xpath="(//button[@class='next_button'])[2]")
	WebElement next2;
	@FindBy (id="f_newOfferUnits")
	WebElement inputOfferedUnits;
	@FindBy (id="f_newOfferBinRate")
	WebElement bidRate;
	@FindBy (id="f_newOfferEnableLoRate")
	WebElement newOfferEnableLowRate;
	@FindBy (id="f_newOfferLoRate")
	WebElement startingLowBidRate;
	@FindBy (xpath="(//button[@class='next_button'])[3]")
	WebElement next3;
	@FindBy (xpath="//button[text()='Create Offer']")
	WebElement createOffer;
	@FindBy (xpath="//p[text()='New Offer Created']")
	WebElement offerCreationToast;
	public boolean createNewOffer()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		createNew.click();
		//Select Offer Currency
		Select offerCurr = new Select(driver.findElement(By.id("s_newOfferCoinId")));
		List<WebElement> options = offerCurr.getOptions();
		// Calculate the number of available currencies excluding the default option
		int availableCurrencies = options.size()-1;
		if (availableCurrencies>0)
		{
		//Create a random number to select a currency randomly
			Random random = new Random();
			int randomIndex = random.nextInt(availableCurrencies)+ 1;  // Start from 1 to skip the default option
			offerCurr.selectByIndex(randomIndex);
		wait.until(ExpectedConditions.invisibilityOf(loader));
		
		//Select a 2nd currency randomly 
		int randomIndex2 = random.nextInt(availableCurrencies)+1; // Start from 1 to skip the default option
		while (randomIndex == randomIndex2)
		{
			randomIndex2 = random.nextInt(availableCurrencies)+1; // Start from 1 to skip the default option
		}
		WebElement ForCurrName = offerCurr.getOptions().get(randomIndex2);
		String ForCurrencyName = ForCurrName.getText();
		next1.click();
		// Select For Currency
		wait.until(ExpectedConditions.visibilityOf(searchForCurr));
		searchForCurr.sendKeys(ForCurrencyName);
		forCurrSearch.click();
		wait.until(ExpectedConditions.invisibilityOf(loader));
		next2.click();
		// Enter Offered units
		wait.until(ExpectedConditions.visibilityOf(inputOfferedUnits));
		inputOfferedUnits.sendKeys("1000000");
		// Select Starting Bid Rate
		bidRate.clear();
		bidRate.sendKeys("1.0");
		//Enabling starting low bid rate
		newOfferEnableLowRate.click();
		next3.click();
		//Select Offer Duration
		Select duration = new Select(driver.findElement(By.id("s_newOfferTimeout")));
		duration.selectByVisibleText("Until Sold Out");
		createOffer.click();
		//Wait for loader and process to complete
		//wait.until(ExpectedConditions.invisibilityOf(loader));
		wait.until(ExpectedConditions.visibilityOf(offerCreationToast));
		System.out.println(driver.getCurrentUrl());
		return true;
		}
		else return false;
	}
	
	@FindBy (xpath="//div[@style=\"display: block; text-align: center; margin-top: 20px;\"]//button[@onclick='offerShowBid()']")
	WebElement bidOnOffer;
	@FindBy (id="f_bidUnits")
	WebElement unitDesired;
	@FindBy (id="f_bidRate")
	WebElement inputBidRate;
	@FindBy (xpath="//button[@onclick='offerBid(this)']")
	WebElement placeBidButton;
	@FindBy (css=".info")
	WebElement infoMsg;
	@FindBy (css=".warn")
	WebElement warnMsg;
	public boolean placeBidOnActiveOffer(String units, String bidRate)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(offers));
		offers.click();
		if (activeOffers.size()>0) {
		// Create a Random object
		Random rand = new Random();
		// Generate a random index within the bounds of the list of offers
		int randomIndex = rand.nextInt(activeOffers.size());
		// Get a random offer from list of active offers
		WebElement randomOffer = activeOffers.get(randomIndex);
		Actions action = new Actions(driver);
		action.moveToElement(randomOffer).perform();
		action.click(randomOffer).build().perform();
		wait.until(ExpectedConditions.invisibilityOf(loader));
		}else 
		{
			System.out.println("No active offer is found to place a bid!");
			return false;
		}
	try {
		if (bidOnOffer.isDisplayed())
		{
			bidOnOffer.click();
			if (inputBidRate.isEnabled())
			{
			inputBidRate.clear();
			inputBidRate.sendKeys(bidRate);
			}
			unitDesired.sendKeys(units);
			placeBidButton.click();
			wait.until(ExpectedConditions.invisibilityOf(loader));
			wait.until(ExpectedConditions.visibilityOf(infoMsg));
			System.out.println(infoMsg.getText());
			return true;
		}
	}catch(TimeoutException e)
	{
		if (warnMsg.isDisplayed())
		{
			System.out.println(warnMsg.getText());
			return false;
		}
	}catch(NoSuchElementException e)
	{
		System.out.println("You do not have sufficient currency balance to place bid on this offer!");
		return false;
	}
	return false;
		
	}
	
	public boolean placeBidOnCreatedOffer(String units, String bidRate)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	try {
		if (bidOnOffer.isDisplayed())
		{
			bidOnOffer.click();
			if (inputBidRate.isEnabled())
			{
			inputBidRate.clear();
			inputBidRate.sendKeys(bidRate);
			}
			unitDesired.sendKeys(units);
			placeBidButton.click();
			wait.until(ExpectedConditions.invisibilityOf(loader));
			wait.until(ExpectedConditions.visibilityOf(infoMsg));
			System.out.println(infoMsg.getText());
			return true;
		}
	}catch(TimeoutException e)
	{
		if (warnMsg.isDisplayed())
		{
			System.out.println(warnMsg.getText());
			return false;
		}
	}catch(NoSuchElementException e)
	{
		System.out.println("You do not have sufficient currency balance to place bid on this offer!");
		return false;
	}
	return false;
	}
	
	@FindBy (id="b_offerInfoManualEnd")
	WebElement endNow;
	@FindBy (id="b_confirmOK")
	WebElement endAuctionOK;
	@FindBy (xpath="//div[@style='display: block; text-align: center; width: 100%; margin-top: 20px;']//button")
	WebElement collectOffer;
	public boolean endAndCollectActiveOffer() throws InterruptedException
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(offers));
		offers.click();
		if (activeOffers.size()>0) {
			WebElement firstActiveOffer = activeOffers.get(0);
			Actions action = new Actions(driver);
			action.moveToElement(firstActiveOffer).perform();
			action.click(firstActiveOffer).build().perform();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(endNow));
		endNow.click();
		wait.until(ExpectedConditions.visibilityOf(endAuctionOK));
		endAuctionOK.click();
		Thread.sleep(10000);
		driver.navigate().refresh();
		Thread.sleep(5000);
		wait.until(ExpectedConditions.visibilityOf(collectOffer));
		collectOffer.click();
		try {
		wait.until(ExpectedConditions.visibilityOf(infoMsg));
		System.out.println(infoMsg.getText());
		return true;
		}catch(Exception e)
		{
			if (warnMsg.isDisplayed())
			{
				System.out.println(warnMsg.getText());
				return false;
			}
		}	
	}
		System.out.println("No active offer available!");
		return false;
	}
}
