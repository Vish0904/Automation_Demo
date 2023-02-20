package pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public static WebDriver driver;
	public static Properties prop;
	
	public BasePage()
	{
		String path=System.getProperty("user.dir")+"/src/test/resources/config.properties";
		try {
			FileInputStream fis=new FileInputStream(path);
			 prop=new Properties();
			 prop.load(fis);
		} 
		catch (FileNotFoundException e) {
			System.out.println("File Not Found!!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error in File Reading!!");
			e.printStackTrace();
		}
	}
	
	
	public void init()
	{
		String browser;
		 browser = System.getProperty("browser");
		 
		 if(browser==null)
			 browser = prop.getProperty("browser");
		 
		if(browser.equalsIgnoreCase("CHROME"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("EDGE"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else
			throw new RuntimeException("Invalid browser.....");
		
		driver.get(prop.getProperty("url"));
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicittimeout"))));
		
		boolean max = Boolean.parseBoolean(prop.getProperty("maximize"));
		if(max)
			driver.manage().window().maximize();
	}
}
