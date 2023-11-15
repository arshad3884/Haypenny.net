package haypenny.testcases;

import org.testng.annotations.Test;
import org.testng.Assert;
import haypenny.pagobjects.CreateNew;
import haypenny.pagobjects.Home;
import haypenny.pagobjects.YourCurrencies;
import haypenny.pagobjects.yourWallet;
import haypenny.testComponents.BaseTest;

public class CreateNewCurrency extends BaseTest {
	@Test
	public void createNewCurrency() throws InterruptedException {
		String email = "johnwick@yopmail.com";
		String password = "hoccurhalhog";
		String currencyName = "Victoria";
		String shortDescription = "Una and the Lion five pound piece";
		initializeDriver();
		driver.get("https://haypenny.net");
		Home home = new Home(driver);
		home.gotoYourCurrencies();
		YourCurrencies yourCurr = new YourCurrencies(driver);
		boolean loginSucess = yourCurr.login(email, password);
		Boolean status = null;
		if (loginSucess == true) {
			home.gotoCreatNewCurrency();
			CreateNew createNew = new CreateNew(driver);
			status = createNew.createNewCurrency(currencyName, shortDescription);
		}
		Assert.assertTrue(status);
		driver.close();
	}

}
