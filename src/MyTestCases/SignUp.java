package MyTestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignUp {
		
	WebDriver driver=  new ChromeDriver() ;
	
	
	@BeforeTest
	public void SetUp() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
	}
	
	
	@Test
    public void SignUp() {
		driver.get("https://magento.softwaretestingboard.com/");
		driver.findElement(By.linkText("Create an Account")).click();
		WebElement firstName = driver.findElement(By.id("firstname"));
		WebElement lastName = driver.findElement(By.id("lastname"));
		WebElement email = driver.findElement(By.id("email_address"));
		WebElement Password = driver.findElement(By.id("password"));
		WebElement confirmPassword = driver.findElement(By.id("password-confirmation"));
		
		WebElement createAccountButon = driver.findElement(By.cssSelector("button[title='Create an Account']"));
		createAccountButon.click();
	}

}
