package haypenny.testcases;

import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import org.testng.Assert;

import haypenny.pagobjects.Home;
import haypenny.pagobjects.Trading;
import haypenny.pagobjects.yourWallet;
import haypenny.testComponents.BaseTest;

public class TradingTestCases extends BaseTest{

	@Test
	public void addWalletDataInEmptyWallet() throws IOException, InterruptedException {
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		Boolean status = wallet.addJsonDataInWallet();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void viewActiveOffersTable() throws InterruptedException, IOException
	{	
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		home.gotoTrading();
		Trading trading = new Trading(driver);
		boolean status = trading.viewActiveOffersTable();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void viewActiveBidsTable() throws IOException, InterruptedException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		home.gotoTrading();
		Trading trading = new Trading(driver);
		boolean status = trading.viewActiveBidsTable();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void viewArchiveBidsTable() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		home.gotoTrading();
		Trading trading = new Trading(driver);
		boolean status = trading.viewArchiveBidsTable();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void createNewOffer() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();	
		home.gotoTrading();
		Trading trading = new Trading(driver);
		boolean status = trading.createNewOffer();
		//Export data in json file
		System.out.println("Exporting updated wallet data to backup file (Haypennydata.json).....");
		home.gotoYourWallet();
		wallet.exportWalletDataToJsonFile("Haypennydata.json");
		System.out.println("Data backup is completed!");
		Assert.assertTrue(status);
		driver.close();
	}
	
}
