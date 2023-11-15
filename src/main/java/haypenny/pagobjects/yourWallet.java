package haypenny.pagobjects;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class yourWallet {
	WebDriver driver;

	public yourWallet(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "alertDialog")
	WebElement alertPopup;
	@FindBy(id = "b_alertOK")
	WebElement popupOK;
	@FindBy(xpath = "//h1[text()='Your Wallet is Empty']")
	WebElement walletEmpty;
	@FindBy(xpath = "//a[@href='#walletdata']")
	WebElement walletData;
	@FindBy(id = "f_walletText")
	WebElement walletdataTextBox;

	@FindBy(xpath = "//button[text()='Password']")
	WebElement walletPassword;
	@FindBy(id = "f_walletPassword")
	WebElement inputWalletPassword;
	@FindBy(id = "b_walletPasswordOK")
	WebElement walletPasswordOK;
	@FindBy(id = "b_walletPasswordCancel")
	WebElement walletpasswordCancel;
	@FindBy(xpath = "//p[text()='Wallet encrypted with new password.']")
	WebElement PasswordChangedToast;
	@FindBy(xpath = "//p[text()='Wallet now stored without encryption.']")
	WebElement removePasswordToast;

	@FindBy(xpath = "//button[text()='Transactions']")
	WebElement walletTransactions;
	@FindBy(xpath = "//button[text()='Blocks']")
	WebElement walletBlocks;
	@FindBy(xpath = "(//button[text()='Copy'])[3]")
	WebElement walletCopy;
	@FindBy(xpath = "//p[text()='Wallet Data Copied to Clipboard, Backup Time Set to Now']")
	WebElement copyConfirmationToast;

	@FindBy(xpath = "//button[text()='Clear']")
	WebElement walletClear;
	@FindBy(id = "b_confirmOK")
	WebElement clearOK;
	@FindBy(xpath = "//button[text()='Save']")
	WebElement walletSave;
	@FindBy(xpath = "//p[text()='Wallet data changed to text box contents.']")
	WebElement saveSuccessfullToast;

	@FindBy(xpath = "//a[text()='Return to Wallet']")
	WebElement returnToWalletPage;
	@FindBy(xpath = "//div[@style='display: block;']//div[@class='spinner']")
	WebElement loader;

	public boolean exportWalletDataToJsonFile(String fileName) throws InterruptedException {
			Thread.sleep(3000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
		} else {
			walletData.click();
		}
		Thread.sleep(2000);
		//Remove password if enabled
		walletPassword.click();
		walletPasswordOK.click();
		//Copy the wallet data and export it in json file
		walletCopy.click();
		Thread.sleep(2000);
		if (copyConfirmationToast.isDisplayed()) {
			try {
				// Get data from the clipboard
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				Transferable transferable = clipboard.getContents(null);

				if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
					String clipboardData = (String) transferable.getTransferData(DataFlavor.stringFlavor);

					// Use JSONTokener to parse the clipboard data and remove extra backslashes
					JSONTokener jsonTokener = new JSONTokener(clipboardData);
					JSONObject jsonObject = new JSONObject(jsonTokener);

					// Specify the file path to export the JSON data
					String filePath = System.getProperty("user.dir") + "\\"+ fileName;

					// Write the JSON object to a file
					try (FileWriter fileWriter = new FileWriter(filePath)) {
						fileWriter.write(jsonObject.toString(4)); // The "4" is for indentation
						System.out.println("Data exported to " + filePath);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Clipboard does not contain string data.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		} else
			return false;
	}

	public boolean addJsonDataInWallet() throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
		} else {
			walletData.click();
		}
		// Read the JSON file content
		String jsonFilePath = System.getProperty("user.dir") + "\\Haypennydata.json";
		StringBuilder jsonContent = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				jsonContent.append(line);
			}
		}

		// Clear the existing content (if needed)
		walletdataTextBox.clear();

		// Send the JSON content to the textarea
		walletdataTextBox.sendKeys(jsonContent.toString());
		walletSave.click();
		wait.until(ExpectedConditions.visibilityOf(saveSuccessfullToast));
		if (saveSuccessfullToast.isDisplayed()) {
			wait.until(ExpectedConditions.invisibilityOfElementLocated(
					By.xpath("//div[@id='loadingDisplayArea' and @style='display: block;']")));
			returnToWalletPage.click();
			Thread.sleep(2000);
			return true;
		} else
			return false;
	}

	public boolean setupWalletPassword(String password) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
		} else {
			walletData.click();
		}
		Thread.sleep(2000);
		// wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
		walletPassword.click();
		inputWalletPassword.sendKeys(password);
		walletPasswordOK.click();
		Thread.sleep(2000);
		if (PasswordChangedToast.isDisplayed()) {
			returnToWalletPage.click();
			return true;
		} else
			return false;
	}

	public boolean removeWalletPassword() throws InterruptedException {
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
		} else {
			walletData.click();
		}
		Thread.sleep(2000);
		walletPassword.click();
		walletPasswordOK.click();// Click OK Without adding new password it will remove the previous one
		Thread.sleep(2000);
		if (removePasswordToast.isDisplayed()) {
			System.out.println(removePasswordToast.getText());
			returnToWalletPage.click();
			return true;
		} else
			return false;
	}

	@FindBy(xpath = "//span[text()='Transactions']")
	WebElement transactionPageTitle;

	public boolean viewWalletTransactions() throws InterruptedException {
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
		} else {
			walletData.click();
		}
		Thread.sleep(2000);
		walletTransactions.click();
		Thread.sleep(1000);
		if (transactionPageTitle.isDisplayed()) {
			return true;
		} else
			return false;
	}

	@FindBy(xpath = "//span[text()='Wallet Block List']")
	WebElement blockListPage;

	public boolean viewWalletBlockLost() throws InterruptedException {
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
		} else {
			walletData.click();
		}
		Thread.sleep(2000);
		walletBlocks.click();
		Thread.sleep(1000);
		if (blockListPage.isDisplayed()) {
			return true;
		} else
			return false;
	}

	@FindBy(xpath = "//p[text()='Wallet Cleared.']")
	WebElement clearConfirmation;

	public boolean clearWalletData() throws InterruptedException {
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
		} else {
			walletData.click();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(walletClear));
		walletClear.click();
		wait.until(ExpectedConditions.visibilityOf(clearOK));
		clearOK.click();
		wait.until(ExpectedConditions.visibilityOf(clearConfirmation));
		if (clearConfirmation.isDisplayed()) {
			return true;
		} else
			return false;
	}

	@FindBy(xpath = "//button[text()='Refresh']")
	WebElement walletRefresh;
	@FindBy(id = "b_confirmOK")
	WebElement refreshOK;
	@FindBy(xpath = "//p[text()='Wallet Data Refreshed.']")
	WebElement refreshSuccessfulToast;

	public boolean refreshWalletData() throws InterruptedException {
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
			System.out.println("Your wallet is currently empty.");
			return true;
		} else {
			walletData.click();
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
		wait.until(ExpectedConditions.visibilityOf(walletRefresh));
		walletRefresh.click();
		wait.until(ExpectedConditions.visibilityOf(refreshOK));
		refreshOK.click();
		wait.until(ExpectedConditions.visibilityOf(refreshSuccessfulToast));
		if (refreshSuccessfulToast.isDisplayed()) {
			System.out.println(refreshSuccessfulToast.getText());
			return true;
		} else
			return false;
	}

	@FindBy(xpath = "//input[@placeholder='Paste a Block String']")
	WebElement pastaBlockString;
	@FindBy(xpath = "//p[@class]")
	WebElement toast;

	public boolean addBlockStringToWallet(String blockString) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
		} else {
		}
		wait.until(ExpectedConditions.visibilityOf(pastaBlockString));
		pastaBlockString.sendKeys(blockString);

		// Wait for the message toast to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class]")));
		if (toast.getText().equalsIgnoreCase(" added to your Wallet.")) {
			System.out.println("Valid block added to the wallet!");
			return true;
		} else if (toast.getText()
				.equalsIgnoreCase("This block is not valid. (Perhaps it has already been combined).")) {
			System.out.println("This block is not valid. (Perhaps it has already been combined)!");
			return true;
		}
		System.out.println(toast.getText());
		return false;
	}

	@FindBy(xpath = "//button[@onclick='walletClickTitle()']")
	WebElement publicCurrencyLink;
	@FindBy(xpath = "//div[@class='dot']")
	List<WebElement> iconDots;

	public boolean openAllWalletCurrencies() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
			System.out.println("Your wallet is currently empty.");
			return false;
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='dot']")));
			for (WebElement iconDot : iconDots) {
				iconDot.click();
				Thread.sleep(1000);
				Actions action = new Actions(driver);
				action.keyDown(Keys.CONTROL);
				action.click(publicCurrencyLink).build().perform();
				action.keyUp(Keys.CONTROL);
				Thread.sleep(1000);
			}
			return true;
		}
	}

	@FindBy(id = "b_walletBalanceHelp")
	WebElement CurrencyUnitView;
	@FindBy(id = "b_confirmOK")
	WebElement popOK;

	public boolean changeCurrencyUnitView() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
			System.out.println("Your wallet is currently empty.");
			return false;
		} else {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("b_walletBalanceHelp")));
			CurrencyUnitView.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("b_confirmOK")));
			popOK.click();
			wait.until(ExpectedConditions.invisibilityOf(loader));
			return true;
		}
	}

	@FindBy(id = "walletCoinLogo")
	WebElement walletCoinLogo;
	@FindBy(id="b_splitAmountUnits")
	WebElement splitAmountUnits;
	@FindBy (id="f_splitAmount")
	WebElement splitAmount;
	@FindBy (xpath="//button[text()='Split']")
	WebElement split;
	@FindBy (id="splitDialogBlockText")
	WebElement splitDialogBlockText;
	@FindBy (id="confirmDialog")
	WebElement confirmDialog;
	@FindBy (id="b_confirmOK")
	WebElement OKConfirmation;
	@FindBy (xpath="//button[@onclick='unShowSplit()']")
	WebElement unShowSplit;
	public String splitCurrency(String units) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Thread.sleep(2000);
		if (alertPopup.isDisplayed()) {
			popupOK.click();
			walletData.click();
			System.out.println("Your wallet is currently empty.");
			return null;
		} else {
			wait.until(ExpectedConditions.visibilityOf(walletCoinLogo));
			walletCoinLogo.click();
			wait.until(ExpectedConditions.visibilityOf(splitAmountUnits));
			splitAmountUnits.click();
			splitAmount.sendKeys(units);
			split.click();
			if (confirmDialog.isDisplayed())
			{
				OKConfirmation.click();
			}	
			wait.until(ExpectedConditions.visibilityOf(splitDialogBlockText));
			String blockString = splitDialogBlockText.getText();
			unShowSplit.click();
			driver.navigate().refresh();
			return blockString;
		}

	}
}
