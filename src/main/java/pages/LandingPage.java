package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage extends BasePage {
  
	AdminPage adminPage;
	
	public LandingPage()
	{
		super();
	}
	
	By adminLink=By.xpath("(//div[@class='oxd-sidepanel-body']//span)[1]");
	
	public String getPageTitle()
	{
		return driver.getTitle();
	}
	
	public AdminPage clickOnAdmin()
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(Integer.parseInt(prop.getProperty("explicittimeout")))); 
	    wait.until(ExpectedConditions.presenceOfElementLocated(adminLink));
	    return new AdminPage();
	
	}
	
	public String getURL()
	{
		return driver.getCurrentUrl();
	}
}
