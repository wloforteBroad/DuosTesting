package managers;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import enums.DriverType;
import enums.EnvironmentType;

public class WebDriverManager {
	
	private WebDriver driver;
	private static DriverType driverType;
	private static EnvironmentType environmentType;
	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
 
	public WebDriverManager() {
		driverType = FileReaderManager.getInstance().getConfigReader().getBrowser();
		environmentType = FileReaderManager.getInstance().getConfigReader().getEnvironment();
	}
 
	public WebDriver getDriver() {
		if(driver == null) driver = createDriver();
		return driver;
	}
 
	private WebDriver createDriver() {
		   switch (environmentType) {	    
	        case LOCAL : driver = createLocalDriver();
	        	break;
	        case DEV : driver = createRemoteDriver();
	        	break;
	        case PROD : driver = createProdDriver();
        	break;
		   }
		   return driver;
	}
 
	private WebDriver createRemoteDriver() {
		throw new RuntimeException("RemoteWebDriver is not yet implemented");
	}
	
	private WebDriver createProdDriver() {
		throw new RuntimeException("ProdWebDriver is not yet implemented");
	}
 
	private WebDriver createLocalDriver() {
        switch (driverType) {	    
        case FIREFOX : 
        	driver = new FirefoxDriver();
	    	break;
        case CHROME : 
        	System.setProperty(CHROME_DRIVER_PROPERTY, FileReaderManager.getInstance().getConfigReader().getDriverPath());
        	driver = new ChromeDriver();
    		break;
        case INTERNETEXPLORER : 
        	driver = new InternetExplorerDriver();
    		break;
        case SAFARI : 
        	driver = new SafariDriver();
		break;
        case FIREFOX_HEADLESS : 
        	FirefoxOptions opts = new FirefoxOptions();
            opts.setHeadless(true);
        	driver = new FirefoxDriver(opts);
	    break;
        }
 
        driver.manage().timeouts().implicitlyWait(FileReaderManager.getInstance().getConfigReader().getImplicitlyWait(), TimeUnit.SECONDS);
		return driver;
	}	
	
	public String getWindowsHandle() {
		return driver.getWindowHandle();
	}
	
	public  Set<String> getWindowsHandles() {
		return driver.getWindowHandles();
	}
	
	public void switchWindow(String window) {
		driver.switchTo().window(window);
	}
 
	public void closeDriver() {
		driver.quit();
	}

}
