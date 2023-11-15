package haypenny.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;

import haypenny.pagobjects.Home;
import haypenny.pagobjects.PublicCurrencies;
import haypenny.testComponents.BaseTest;

public class PublicCurrenciesTests extends BaseTest{
	@Test
	public void openAllActivePublicCurrencies()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoPublicCurrencies();
		PublicCurrencies publicCurr = new PublicCurrencies(driver);
		Boolean status = publicCurr.openAllActivePublicCurrencies();
		Assert.assertTrue(status);
		driver.quit();
	}
	@Test
	public void goToPublicCurrencyPage() throws InterruptedException
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoPublicCurrencies();
		PublicCurrencies publicCurr = new PublicCurrencies(driver);
		Boolean status = publicCurr.goToPublicCurrencyPage("Sky");
		Assert.assertTrue(status);
		driver.close();
	}
	@Test
	public void getCurrencyInfo()
	{
		initializeDriver();
		driver.get("https://haypenny.net/");
		Home home = new Home(driver);
		home.gotoPublicCurrencies();
		PublicCurrencies publicCurr = new PublicCurrencies(driver);
		String info = publicCurr.getCurrencyInfo("Sky");
		System.out.println(info);
		Boolean status;
		if (!(info == null)) 
		{status = true;}
		else {status = false;}
		Assert.assertTrue(status);
		driver.close();
	}
	
}
