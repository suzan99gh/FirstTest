package MyTestCases;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class SignUp extends Parameters {

	WebDriver driver = new ChromeDriver();

	@BeforeTest
	public void SetUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1, enabled = false)
	public void SignUp() {
		driver.get("https://magento.softwaretestingboard.com/");
		driver.findElement(By.linkText("Create an Account")).click();
		WebElement firstName = driver.findElement(By.id("firstname"));
		WebElement lastName = driver.findElement(By.id("lastname"));
		WebElement email = driver.findElement(By.id("email_address"));
		WebElement Password = driver.findElement(By.id("password"));
		WebElement confirmPassword = driver.findElement(By.id("password-confirmation"));

		WebElement createAccountButtonElement = driver
				.findElement(By.cssSelector("button[title='Create an Account'] span"));

		WebElement createAccountButon = driver.findElement(By.cssSelector("button[title='Create an Account']"));
		createAccountButon.click();
		firstName.sendKeys(FirstNames[randomIndex]);
		lastName.sendKeys(lastNames[randomIndex]);
		email.sendKeys(emailID);
		Password.sendKeys(CommonPassword);
		confirmPassword.sendKeys(CommonPassword);

		createAccountButtonElement.click();

		String WelcomeMsg = driver.findElement(By.className("message-success")).getText();

		assertEquals(WelcomeMsg, "Thank you for registering with Main Website Store.");

	}

	@Test(priority = 2, enabled = false)
	private void logOut() {
		driver.get("https://magento.softwaretestingboard.com/customer/account/logout/");
		assertEquals(driver.getCurrentUrl().contains("/logoutSuccess/"), true);
	}

	@Test(priority = 3, enabled = false)
	public void SignIn() throws InterruptedException {
		driver.findElement(By.linkText("Sign In")).click();
		driver.findElement(By.id("email")).sendKeys(emailID);
		driver.findElement(By.id("pass")).sendKeys(CommonPassword);

		Thread.sleep(2000);
		String WelcomeMessage = driver.findElement(By.cssSelector(".greet.welcome")).getText();
		assertEquals(WelcomeMessage.contains("Welcome"), true);
	}

	@Test(priority = 4)
	public void Counter() {
		driver.get("https://magento.softwaretestingboard.com/");
		WebElement footer = driver.findElement(By.cssSelector(".footer.content"));
		int numberOfLinkOnFooter = footer.findElements(By.tagName("a")).size();
		System.out.println(numberOfLinkOnFooter + "Total of Links on footer");
	}

	@Test(priority = 5)
	public void addOneRandomItem() throws InterruptedException {
		driver.get("https://magento.softwaretestingboard.com/");

		WebElement ItemsContainer = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));

		int numberofitems = ItemsContainer.findElements(By.tagName("li")).size();
		System.out.println(numberofitems);

		int randomItemToSelect = rand.nextInt(4);
		ItemsContainer.findElements(By.tagName("li")).get(randomItemToSelect).click();

		WebElement sizesContainer = driver.findElement(By.cssSelector(".swatch-attribute.size"));

		int theSizes = sizesContainer.findElements(By.className("swatch-option")).size();
		sizesContainer.findElements(By.className("swatch-option")).get(rand.nextInt(theSizes)).click();
		Thread.sleep(2000);

		WebElement colorsContainer = driver.findElement(By.cssSelector(".swatch-attribute.color"));
		int theColors = colorsContainer.findElements(By.className("swatch-option")).size();
		colorsContainer.findElements(By.className("swatch-option")).get(rand.nextInt(theColors)).click();
		Thread.sleep(2000);
		driver.findElement(By.id("product-addtocart-button")).click();
		Thread.sleep(3000);

		String ActualMsg = driver.findElement(By.cssSelector(".page.messages")).getText();

		assertEquals(ActualMsg.contains("You added"), true);

	}

}
