package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {


	By txtUsername = By.name("username");
	By txtPassword = By.name("password");
	By btnLogin = By.xpath("//button[@type='submit']");
	By errMsg = By.xpath("(//div[@class='oxd-form-row']//span)[1]");
	By invalidCred = By.xpath("(//p)[1]");

	public LoginPage()
	{
		super();
	}

	public void enterUsername(String un) {
		WebDriverWait	wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		WebElement userName = wait.until(ExpectedConditions.presenceOfElementLocated(txtUsername));
		userName.sendKeys(un);
	}

	public void enterPassword(String pwd) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		WebElement password = wait.until(ExpectedConditions.presenceOfElementLocated(txtPassword));
		password.sendKeys(pwd);
	}

	public void login() {
		WebDriverWait	wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		WebElement loginbtn = wait.until(ExpectedConditions.presenceOfElementLocated(btnLogin));
		loginbtn.click();
	}

	public LandingPage loginToApplication(String un, String pwd) {
		enterUsername(un);
		enterPassword(pwd);
		login();
		return new LandingPage();

	}

	public String getErrorMsg() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		return wait.until(ExpectedConditions.presenceOfElementLocated(errMsg)).getText();
	}

	public String msgOfInvalidCredentials() {
		WebDriverWait	wait = new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout"))));
		return wait.until(ExpectedConditions.presenceOfElementLocated(invalidCred)).getText();
	}

}
