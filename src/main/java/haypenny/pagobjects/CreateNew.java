package haypenny.pagobjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CreateNew {

	WebDriver driver;
	public CreateNew(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (id="f_createName")
	WebElement inputName;
	@FindBy (id="f_createShortDesc")
	WebElement InputShortDescp;
	@FindBy (id="f_createCheck")
	WebElement agreeCheck;
	@FindBy (id="b_creSubmit")
	WebElement createButton;
	@FindBy (css=".warn")
	WebElement errorMsg;
	@FindBy (xpath="//p[@class='info']")
	WebElement successMsg;
	public boolean createNewCurrency(String name, String shortDescp)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
	try
	{
		wait.until(ExpectedConditions.visibilityOf(inputName));
		inputName.sendKeys(name);
		InputShortDescp.sendKeys(shortDescp);
		agreeCheck.click();
		createButton.click();
		Thread.sleep(5000);
		if (successMsg.isDisplayed())
		{
			System.out.println(name + " Currency created successfully!");
			System.out.println(successMsg.getText());
			return true;
		}
	}catch(Exception e)
	{
		if (errorMsg.isDisplayed())
		{
			System.out.println(errorMsg.getText());
			return false;
		}
	}
	return false;
	}
}
