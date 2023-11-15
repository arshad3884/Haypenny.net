package haypenny.testcases;

import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.Assert;

import haypenny.pagobjects.Home;
import haypenny.pagobjects.Trading;
import haypenny.pagobjects.yourWallet;
import haypenny.testComponents.BaseTest;

public class BidingAndCollectingOffers extends BaseTest{
	
	@Test
	public void createNewOffer() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net");
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		home.gotoTrading();
		Trading trading = new Trading(driver);
		boolean status  = trading.createNewOffer();
		System.out.println("Exporting data to Haypennydata.json file.....");
		home.gotoYourWallet();
		wallet.exportWalletDataToJsonFile("Haypennydata.json");
		System.out.println("Wallet Data export is completed!");
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void placeBidOnActiveOffer() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net");
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		home.gotoTrading();
		Trading trading = new Trading(driver);
		boolean status  = trading.placeBidOnActiveOffer("10000", "1.0");
		System.out.println("Exporting data to Haypennydata.json file.....");
		home.gotoYourWallet();
		wallet.exportWalletDataToJsonFile("Haypennydata.json");
		System.out.println("Wallet Data export is completed!");
		Assert.assertTrue(status);
		driver.close();
	}
	@Test 
	public void CreateOfferandPlaceABid() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net");
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		home.gotoTrading();
		Trading trading = new Trading(driver);
		boolean offerStatus  = trading.createNewOffer();
		if (offerStatus == true)
		{
			Thread.sleep(2000);
			boolean status  = trading.placeBidOnCreatedOffer("10000","1.0");
			System.out.println("Exporting data to Haypennydata.json file.....");
			home.gotoYourWallet();
			wallet.exportWalletDataToJsonFile("Haypennydata.json");
			System.out.println("Wallet Data export is completed!");
			Assert.assertTrue(status);
		}
		else if (offerStatus == false)
		{ 
			System.out.println("Error in creating a new offer!");
			Assert.assertTrue(false);
		}
		driver.close();
	}
	
	@Test 
	public void endAndCollectActiveOffer() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net");
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		home.gotoTrading();
		Trading trading = new Trading(driver);
		boolean status  = trading.endAndCollectActiveOffer();
		System.out.println("Exporting data to Haypennydata.json file.....");
		home.gotoYourWallet();
		wallet.exportWalletDataToJsonFile("Haypennydata.json");
		System.out.println("Wallet Data export is completed!");
		Assert.assertTrue(status);
		driver.close();
	}
}
