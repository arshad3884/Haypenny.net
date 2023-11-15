package haypenny.pagobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutHaypenny {
	WebDriver driver;
	public AboutHaypenny(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@style='display: block;']//div[@class='spinner']")
	WebElement loader;
	
	@FindBy (id="f_block")
	WebElement haypennyBlock;
	@FindBy (id="f_name")
	WebElement yourName;
	@FindBy (id="f_email")
	WebElement emailAdd;
	@FindBy (id="f_subject")
	WebElement msgSubject;
	@FindBy (id="f_message")
	WebElement yourMsg;
	@FindBy (xpath="//button[@onclick='sendContact()']")
	WebElement sendMessage;
	@FindBy (xpath="//p[@class='warn' or @class='info']")
	WebElement toastPopup;
	@FindBy (xpath="//p[text()='The Haypenny block string you offered was not valid.']")
	WebElement invalidBlockError;
	@FindBy (xpath="//p[text()='Your message was sent. We will reply as soon as possible.']")
	WebElement msgSentConfirmation;
	@FindBy (xpath="//span[@id='contactMessageArea'][normalize-space()]")
	WebElement requiredFieldError;
	public boolean submitContactForm(String block, String name, String email, String subject, String message)
	{
		WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(haypennyBlock));
		haypennyBlock.sendKeys(block);
		yourName.sendKeys(name);
		emailAdd.sendKeys(email);
		msgSubject.sendKeys(subject);
		yourMsg.sendKeys(message);
		sendMessage.click();
	try 
	{
		wait.until(ExpectedConditions.invisibilityOf(loader));
		
		if (toastPopup.getText().equals("Your message was sent. We will reply as soon as possible."))
		{
			System.out.println("Your message was sent. We will reply as soon as possible");
			return true;
		}
		if (toastPopup.getText().equals("The Haypenny block string you offered was not valid."))
		{
			System.out.println("The Haypenny block string you offered was not valid.");
			return false;
		}
	}catch(Exception e)
	{
		if (requiredFieldError.isDisplayed())
		{
			System.out.println(requiredFieldError.getText());
			return false;
		}
	}
	return false;
	}

}
