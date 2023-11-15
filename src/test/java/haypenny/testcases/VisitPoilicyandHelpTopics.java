package haypenny.testcases;

import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import haypenny.pagobjects.Home;
import haypenny.testComponents.BaseTest;

public class VisitPoilicyandHelpTopics extends BaseTest{

	@Test
	public void visitPrivacyPolicyPage() throws InterruptedException
	{
		initializeDriver();
		driver.get("https://haypenny.net");
		Home home = new Home(driver);
		home.gotoPrivacyPolicy();
		Thread.sleep(3000);
		Assert.assertTrue(true);
		driver.close();
	}
	@Test
	public void visitHelpTopicPage() throws InterruptedException
	{
		initializeDriver();
		driver.get("https://haypenny.net");
		Home home = new Home(driver);
		home.gotoHelpTopics();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement tradingHelp = driver.findElement(By.xpath("//div[@id='helpHeaderMain']//div[@class='help_header readable']//span//a[@href='#help-trading'][normalize-space()='Trading Guide']"));
		wait.until(ExpectedConditions.visibilityOf(tradingHelp));
		Actions action = new Actions(driver);
		action.moveToElement(tradingHelp).keyDown(Keys.CONTROL).click(tradingHelp).keyUp(Keys.CONTROL).build().perform();
		Thread.sleep(3000);
		Assert.assertTrue(true);
		driver.quit();
	}
}
