package tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.LandingPage;
import pages.LoginPage;

public class LoginPageTest extends BasePage{
	LoginPage   loginPage;
	LandingPage landingPage;
	
	public LoginPageTest()
	{
		super();
	}

	@BeforeMethod
	public void setUp()
	{
		init();
		loginPage=new LoginPage();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test
	public void verifyLogin()
	{
		landingPage=loginPage.loginToApplication("Admin","admin123");
		String expURL="https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		Assert.assertEquals(landingPage.getURL(),expURL,"landing Page URL is not matching with Expected URL"+expURL);
	}
	
	@Test(dataProvider="getCredentials")
	public void verifyInvalidLogin(String un,String pwd)
	{
		loginPage.enterUsername(un);
		loginPage.enterPassword(pwd);
		loginPage.login();
		
		if(un.isBlank() || pwd.isBlank())
			Assert.assertEquals("Required",loginPage.getErrorMsg(),"Error Message for Blank Username/Password!!");
		else
			Assert.assertEquals("Invalid credentials",loginPage.msgOfInvalidCredentials(),"Wrong Error Message for Invalid username and password !!!");
	}
	
	@DataProvider(name="getCredentials")
	public Object[][] getData()
	{
		Object[][] data= {
			             {"abc","abc"},
				         {" ","abc"},
				         {"abc"," "}
		};
		return data;
	}
	
	
}
