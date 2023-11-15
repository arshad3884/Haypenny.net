package haypenny.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import haypenny.pagobjects.AboutHaypenny;
import haypenny.pagobjects.Home;
import haypenny.pagobjects.yourWallet;
import haypenny.testComponents.BaseTest;

public class SubmitContactRequest extends BaseTest{
	@Test
	public void submitContactForm() throws InterruptedException, IOException
	{
		initializeDriver();
		driver.get("https://haypenny.net");
		Home home = new Home (driver);
		//Generating a block string
		home.gotoYourWallet();
		yourWallet wallet = new yourWallet(driver);
		wallet.addJsonDataInWallet();
		String blockString =  wallet.splitCurrency("1");
		//Go to contact form and add data and blockString
		home.gotoAbout();
		AboutHaypenny about = new AboutHaypenny(driver);
		Boolean status = about.submitContactForm(blockString, "John", "johnwick@yopmail.com", "block does not work", "could not add block to wallet");
		Assert.assertTrue(status);
		driver.close();
	}

}
