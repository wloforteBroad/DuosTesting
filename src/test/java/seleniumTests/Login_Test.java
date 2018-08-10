package seleniumTests;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Login_Test {
	
	private static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "/home/wloforte/bin/Drivers/chromedriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Launch

		driver.get("https://duos.dsde-dev.broadinstitute.org");

		// Find and click Sign In

		driver.findElement(By.xpath("//a[contains(text(), \"Sign In\")]")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.cssSelector("[id*=not_signed_in]")).click();

		// Set Parent Window
		String parent = driver.getWindowHandle();

		// Number of Windows
		Set<String> s1 = driver.getWindowHandles();

		// Iterate
		Iterator<String> I1 = s1.iterator();

		while (I1.hasNext()) {

			String child_window = I1.next();

			// If is not Parent do things

			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);

				System.out.println(driver.switchTo().window(child_window).getTitle());

				driver.findElement(By.xpath("//input[@id='identifierId']")).sendKeys("wloforte@broadinstitute.org");

				driver.findElement(By.xpath("//div[@id='identifierNext']")).click();

				Thread.sleep(3000);

				WebElement password = driver.findElement(By.xpath("//input[@type='password']"));

				password.sendKeys("Password03!");

				driver.findElement(By.xpath("//div[@id='passwordNext']")).click();
			}

		}

		// Switch back to parent window
		driver.switchTo().window(parent);
		System.out.println(driver.getTitle());

		// Find element to validate Sign In

		Thread.sleep(500);

		driver.findElement(By.xpath("//h2[contains(text(), \'Welcome Walter Lo Forte!\')]"));

		System.out.println("LOGIN SUCCESFULL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

		// Close the driver

		driver.quit();

	}

}
