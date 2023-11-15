package haypenny.testcases;


import java.io.IOException;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;

import haypenny.pagobjects.Home;
import haypenny.pagobjects.PublicCurrencies;
import haypenny.pagobjects.YourCurrencies;
import haypenny.testComponents.BaseTest;

public class YourCurrenciesTestCases extends BaseTest{
	@Test
	public void loginUsingValidCreds()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		Boolean status = yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void loginByRecoveringPassword()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		Boolean status = yourCurr.sendNewPasswordandLogin("tester123@yopmail.com");
		Assert.assertTrue(status);
		driver.quit();
	}
	@Test 
	public void logout()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
		Boolean status = yourCurr.logout();
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void updateShortDescription()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
		Boolean status = yourCurr.updateShortDescription("The Queen Victoria Sovereign coin");
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void updateCoinURLandByline()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
		Boolean status = yourCurr.updateCoinURLandByline("https://www.baldwin.co.uk/british-coins-for-sale/victorian/#:~:text=Numismatically%2C%20Victorian%20coins%20remain%20hugely,head%20pennies%20and%20their%20halves.", "Victoria Coins");
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void updateCoinLongDescription()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
		Boolean status = yourCurr.updateCoinLongDescription("Lower value coins were made of copper (bronze from 1860). The penny was accompanied by the halfpenny (pronounced hape-nee and sometimes written ha'penny) and the farthing, worth a quarter of a penny. Half farthings were also minted for some of Victoria's reign but were unpopular because of their sma");
		Assert.assertTrue(status);
		driver.close();
	}
	
	@Test
	public void markCurrencyBrandExclusive()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
	 	Boolean status = yourCurr.markCurrencyBrandExclusive("Charles III");
	 	Assert.assertTrue(status);
	 	driver.close();
	}
	@Test
	public void markedThisCurrencyPublic() throws InterruptedException
	{
		String currName = "Charles III";
		Boolean status = null;
		
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
		home.gotoPublicCurrencies();
		PublicCurrencies publicCurr = new PublicCurrencies(driver);
		Boolean isPublic = publicCurr.goToPublicCurrencyPage(currName);
		if (isPublic.equals(true))
		{
			System.out.println(currName + ": Currency is already marked as public!");
			status = true;
		}
		else if (isPublic.equals(false)){
		home.gotoYourCurrencies();
	 	status = yourCurr.markedThisCurrencyPublic(currName);
		}
	 	Assert.assertTrue(status);
	 	driver.close();
	}
	@Test
	public void changeEmailNotifications() throws InterruptedException
	{
		String currName = "Charles III";
		
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
	 	Boolean status = yourCurr.changeEmailNotifications(currName);
	 	Assert.assertTrue(status);
	 	driver.close();
	}	
	@Test
	public void seeLiveCoinPageOfAllYourCurrencies()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
	 	Boolean status = yourCurr.seeLiveCoinPageOfAllYourCurrencies();
	 	Assert.assertTrue(status);
	 	driver.quit();
	}
	@Test
	public void changeOrUploadCurrencyImage() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		yourCurr.login("johnwick@yopmail.com", "hoccurhalhog");
	 	Boolean status = yourCurr.changeOrUploadCurrencyImage("Charles III","");
	 	Assert.assertTrue(status);
	 	driver.close();
	}
	
}
