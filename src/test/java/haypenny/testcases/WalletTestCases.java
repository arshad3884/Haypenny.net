package haypenny.testcases;

import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import org.testng.Assert;

import haypenny.pagobjects.Home;
import haypenny.pagobjects.yourWallet;
import haypenny.testComponents.BaseTest;

public class WalletTestCases extends BaseTest {

	
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
	public void exportWalletDataToJsonFile() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		Boolean status = wallet.exportWalletDataToJsonFile("exportedWalletData.json");
		Assert.assertTrue(status);
		driver.close();
	}

	@Test
	public void setUpWalletPassword() throws InterruptedException {
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		Boolean status = wallet.setupWalletPassword("123");
		Assert.assertTrue(status);
	}

	@Test
	public void removeWalletPassword() throws InterruptedException, IOException {
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		Boolean status = wallet.removeWalletPassword();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void viewWalletTransactions() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		Boolean status = wallet.viewWalletTransactions();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void viewWalletCurrenciesBlockList() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		Boolean status = wallet.viewWalletBlockLost();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void clearWalletData() throws InterruptedException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		Boolean status = wallet.clearWalletData();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void refreshWalletData() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		Boolean status = wallet.refreshWalletData();
		Assert.assertTrue(status);
		driver.close();
	}
	
	@Test
	public void openPublicCurrencyPage() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		Boolean status = wallet.openAllWalletCurrencies();
		Assert.assertTrue(status);
		driver.quit();
	}
	@Test 
	public void changeCurrencyUnitView() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		Boolean status = wallet.changeCurrencyUnitView();
		Assert.assertTrue(status);
		driver.close();
	}
	
	@Test
	public void splitCurrencyToGetBlockString() throws InterruptedException, IOException
	{	
		initializeDriver();
		driver.get("https://haypenny.net/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		String blockString = wallet.splitCurrency("1");
		System.out.println("The splitted block string is: "+ blockString);
		Boolean status;
		if (!(blockString == null))
		{
			 status = true;
		}
		else status = false;
		//Export to backup file
		System.out.println("Exporting data to Haypennydata.json file.....");
		home.gotoYourWallet();
		wallet.exportWalletDataToJsonFile("Haypennydata.json");
		System.out.println("Wallet Data export is completed!");
		Assert.assertTrue(status);
		driver.close();
	}
	
	@Test
	public void addBlockStringToWallet() throws InterruptedException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		Boolean status = wallet.addBlockStringToWallet("I2em74L6aDebR5BEJ2Ihvp");
		Assert.assertTrue(status);
		driver.close();
	}
}
