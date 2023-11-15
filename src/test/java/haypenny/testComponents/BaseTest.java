package haypenny.testComponents;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTest {
	public WebDriver driver;
	public WebDriver initializeDriver()
	{
		//Chrome browser
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		//options.addArguments("--user-data-dir=C:\\Users\\hp\\AppData\\Local\\Google\\Chrome\\User Data\\");
		//options.addArguments("--profile-directory=Profile 1");
		options.addArguments("start-maximized"); // open Browser in maximized mode
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
		
			
		/*Firefox
		//System.setProperty("webdriver.gecko.driver","C:\\AAAA\\Selenium 4.8.1\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return driver;
		*/
		
		//Edge Browser
		//EdgeOptions options = new EdgeOptions();
		//options.addArguments("--remote-allow-origins=*");
		//driver = new EdgeDriver(options);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().window().maximize();
		//return driver;
	}

	// screenshot
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		// this method take screenshot and return the path not the file
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}


}
