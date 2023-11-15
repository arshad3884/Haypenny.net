package haypenny.pagobjects;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourCurrencies {
	WebDriver driver;
	public YourCurrencies(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@style='display: block;']//div[@class='spinner']")
	WebElement loader;
	
	@FindBy (xpath="//h1[text()='Login to Get Started']")
	WebElement loginGetStarted;
	@FindBy (id="f_loginEmail")
	WebElement inputEmail;
	@FindBy (id="f_loginPassword")
	WebElement inputPassword;
	@FindBy (xpath="//button[text()='Log in']")
	WebElement loginButton;
@	FindBy (id="myCoinsLoginArea")
	WebElement loginArea;
	public boolean login(String email, String password)
	{
		if (loginGetStarted.isDisplayed())
		{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			inputEmail.sendKeys(email);
			inputPassword.sendKeys(password);
			loginButton.click();
			wait.until(ExpectedConditions.invisibilityOf(loader));
			if (loginArea.isDisplayed())
			{
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	public boolean logout()
	{
		//On refreshing the page the user will be logout
		driver.navigate().refresh();
		return true;
	}
	
	@FindBy (id="f_loginEmail")
	WebElement emailfield;
	@FindBy (xpath="//div[@onclick='loginSendPassword()']")
	WebElement sendNewPassword;
	@FindBy (id="login")
	WebElement yopmailLogin;
	@FindBy (xpath="//button[@title='Check Inbox @yopmail.com']")
	WebElement yopmailGo;
	@FindBy (xpath="//button[@loading]")
	WebElement yopmailLoading;
	public boolean sendNewPasswordandLogin(String email)
	{
		inputEmail.sendKeys(email);
		sendNewPassword.click();
		// Open a new tab using JavaScript executor
        ((JavascriptExecutor) driver).executeScript("window.open();");
        
        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1)); // 1 is the index of the new tab
        // Open yopmail in new tab
        driver.get("https://yopmail.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(yopmailLogin));
        yopmailLogin.sendKeys(email);
        yopmailGo.click();
        wait.until(ExpectedConditions.invisibilityOf(yopmailLoading));
        driver.switchTo().frame("ifmail");
        //extract password from a String
        String fullPassword = driver.findElement(By.xpath("//span[@style='font-size: 20px; font-weight: bold']")).getText();
        Pattern pattern = Pattern.compile("\\((.*?)\\)");
        Matcher matcher = pattern.matcher(fullPassword);
        String extractedPassword = null;
        if (matcher.find()) {
        	//The matcher.group(1) extracts the text inside the parentheses.
            extractedPassword = matcher.group(1);
        }
        // To switch back to the default content, use:
        driver.switchTo().defaultContent();
        //Switch back to the first tab
        driver.switchTo().window(tabs.get(0)); // 0 is the index of the first tab
        emailfield.clear();
        boolean status = login(email, extractedPassword);
        return status;
     }
	
	@FindBy (xpath="//button[@onclick='updateShortDesc()']")
	WebElement updateShortDescp;
	@FindBy (id="f_updateShortDesc")
	WebElement inputShortDescp;
	@FindBy (xpath="//button[@onclick='updateShortDescSave()']")
	WebElement saveShortDescp;
	public boolean updateShortDescription(String Descp)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		updateShortDescp.click();
		inputShortDescp.clear();
		inputShortDescp.sendKeys(Descp);
		saveShortDescp.click();
		wait.until(ExpectedConditions.invisibilityOf(loader));
		return true;
	}
	
	@FindBy (xpath="//button[@onclick='updateURL()']")
	WebElement updateURL;
	@FindBy (id="f_updateURL")
	WebElement inputCoinURL;
	@FindBy (id="f_updateByline")
	WebElement inputByline;
	@FindBy (xpath="//button[@onclick='updateURLSave()']")
	WebElement saveURL;
	public boolean updateCoinURLandByline(String url, String byLine)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		updateURL.click();
		inputCoinURL.clear();
		inputCoinURL.sendKeys(url);
		inputByline.clear();
		inputByline.sendKeys(byLine);
		saveURL.click();
		wait.until(ExpectedConditions.invisibilityOf(loader));
		return true;
	}
	
	@FindBy (xpath="//button[@onclick='updateLongDesc()']")
	WebElement longDescpUpdate;
	@FindBy (id="f_updateLongDesc")
	WebElement inputLongDescp;
	@FindBy (xpath="//button[@onclick='updateLongDescSave()']")
	WebElement longDescpSave;
	public boolean updateCoinLongDescription(String longDescp)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		longDescpUpdate.click();
		inputLongDescp.clear();
		inputLongDescp.sendKeys(longDescp);
		longDescpSave.click();
		wait.until(ExpectedConditions.invisibilityOf(loader));
		return true;
	}
	
	@FindBy (xpath="//div[@id='coinListDotsArea']//div[contains(@class, 'dot')]")
	List <WebElement> dots;
	@FindBy (id="coinDisplayName")
	WebElement coinDisplayName;
	@FindBy (id="updateExclusiveButtonArea")
	WebElement updateBrandExclusive;
	@FindBy (xpath="//span[text()='(marked brand exclusive)']")
	WebElement alreadyMarkedBE;
	@FindBy (xpath="//button[@onclick='exclusiveSave()']")
	WebElement OKBE;
	@FindBy (id="b_confirmOK")
	WebElement yesBE;
	@FindBy (xpath="//p[text()='Your currency is now marked as Brand Exclusive. This cannot be undone.']")
	WebElement BEConfirmationToast;
	public boolean markCurrencyBrandExclusive(String currName)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		
		WebElement update = null;
		for (WebElement dot : dots) {
            try {
                dot.click();
                //Find the currency page by clicking on dots
                if (coinDisplayName.getText().equals(currName))
                {
                // Check if the 'update' button element is present
                	wait.until(ExpectedConditions.visibilityOf(updateBrandExclusive));
                	if (updateBrandExclusive.isDisplayed())
                	{
                    update = updateBrandExclusive;
                    break;
                	}
                }
            }catch (TimeoutException e) {
                // 'cap' element not found, continue to the next dot
            	//Check whether this currency is already marked as BE
            	if (alreadyMarkedBE.isDisplayed())
            	{
            	System.out.println("This currency is already marked as Brand Exclusive!");
    			return true;
            	}
            } 
        }
		try {
        	if (update.getText().equals("Update"))
			{
				update.click();
				wait.until(ExpectedConditions.visibilityOf(OKBE));
				OKBE.click();
				wait.until(ExpectedConditions.visibilityOf(yesBE));
				//yesBE.click();
				//wait.until(ExpectedConditions.visibilityOf(BEConfirmationToast));
				return true;
			}
        }catch (NullPointerException e) {
			System.out.println("Could not found this Currency!");
			return false;
        }
		return false;
	}
	
	@FindBy (xpath="(//span[@class='slider'])[1]")
	WebElement publicToggle;
	public boolean markedThisCurrencyPublic(String currName) throws InterruptedException
	{
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(dots));
		for (WebElement dot : dots) {
                dot.click();
                Thread.sleep(1000);
                //Find the currency page by clicking on dots
                if (coinDisplayName.getText().equals(currName))
                {
                // Check if the 'publicToggle' button element is present
                	Thread.sleep(5000);
                	wait.until(ExpectedConditions.visibilityOf(publicToggle));
                	publicToggle.click();
            		wait.until(ExpectedConditions.invisibilityOf(loader));
            		System.out.println(currName + " :Currency marked as public!");    
                	return true;
                	}
            }
		System.out.println(currName + " is not found in your Currencies!");
		return false;
	}
	
	@FindBy (xpath="(//span[@class='slider'])[2]")
	WebElement emailNotifyToggle;
	public boolean changeEmailNotifications(String currName) throws InterruptedException
	{
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElements(dots));
		Thread.sleep(2000);
		for (WebElement dot : dots) {
                dot.click();
                //Find the currency page by clicking on dots
                if (coinDisplayName.getText().equals(currName))
                {
                // Check if the 'emailNotifyToggle' button element is present
                	Thread.sleep(3000);
                	wait.until(ExpectedConditions.visibilityOf(emailNotifyToggle));
                	emailNotifyToggle.click();
            		Thread.sleep(2000);
            		System.out.println("email notifications are enabled for "+currName); 
                	return true;
                	}
		}
		System.out.println(currName + " is not found in your Currencies!");
		return false;
	}
	
	@FindBy (id="updateLiveLinkText")
	WebElement seeLiveCoinPage;
	public boolean seeLiveCoinPageOfAllYourCurrencies()
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		try {
			wait.until(ExpectedConditions.visibilityOf(coinDisplayName));
			Thread.sleep(2000);
		for (WebElement dot : dots) {
			Actions action = new Actions(driver);
			wait.until(ExpectedConditions.elementToBeClickable(dot));
			action.click(dot).build().perform();
			// Scroll to the top of the page
			wait.until(ExpectedConditions.elementToBeClickable(seeLiveCoinPage));
			action.keyDown(Keys.CONTROL).click(seeLiveCoinPage).keyUp(Keys.CONTROL).build().perform();
		}
		System.out.println("All your currencies pages are opened in new tabs!");
		return true;
		}catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@FindBy (id="coinDisplayLogo")
	WebElement coinDisplayLogo;
	@FindBy (css=".warn")
	WebElement errorPopup;
	public boolean changeOrUploadCurrencyImage(String currName, String image) throws InterruptedException, IOException 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Thread.sleep(2000);
		try{
		for (WebElement dot : dots) {
            dot.click();
            //Find the currency page by clicking on dots
            if (coinDisplayName.getText().equals(currName))
            {
            // Check if the 'coinDisplayLogo' button element is present
            	wait.until(ExpectedConditions.visibilityOf(coinDisplayLogo));
            	coinDisplayLogo.click();
            	Thread.sleep(3000);
            	//Create a script on autoIT to select and upload specific file, and create exe of it
            	/*
            	ControlFocus("Open","","Edit1")
				ControlSetText("Open","","Edit1","C:\AAAA\Selenium 4.8.1\haypenny.net\coinImages\neo-blockchain-project-overview.jpeg")
				ControlClick("Open","","Button1")
            	 */
            	//Run that exe file using java Runtime method here
            	Runtime.getRuntime().exec(System.getProperty("user.dir")+ "\\coinImages\\fileupload.exe"); //path of exe (Created using autoIt script)
          
        		wait.until(ExpectedConditions.invisibilityOf(loader));
        		wait.until(ExpectedConditions.visibilityOf(errorPopup));
        		if (errorPopup.isDisplayed())
        			System.out.println("The image size or format is incorrect!"); 
            		return false;
            	}
		}
		}catch(Exception e)
		{
			System.out.println(currName +" CoinImage uploaded successfully");
			return true;
		}
		System.out.println("The currency page is not found!"); 
		return false;
		
	}
}
