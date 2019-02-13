package mytests;

import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

public class Test1 {
	static WebDriver driver;
	static String parent;
	static String child;
	
	//static WebDriverWait wait = new WebDriverWait(driver,1000);
	
	static String url ="https://www.phptravels.net/login";
	//String url="https://www.google.co.in/";
	

  @BeforeTest
  public void beforeTest() throws Exception {
	  DesiredCapabilities dcp = new DesiredCapabilities();
		dcp.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
		dcp.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
		dcp.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, true);
		dcp.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
		dcp.setCapability("name", "PHP");		
		dcp.setCapability("idleTimeout", 150);
		driver = new RemoteWebDriver(new URL("http://35.202.17.149:4444/wd/hub"),dcp);
		/*System.setProperty("webdriver.chrome.driver","D:\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();*///http://35.190.187.1:4444/wd/hubhttp://104.155.135.94:4444/wd/hub
		
		/*System.setProperty("webdriver.ie.driver","D:\\IEDriverServer.exe");
		driver = new InternetExplorerDriver(); 
		driver.manage().window().maximize();
		*/
		driver.get(url);
	  
	  
	  System.out.println("Title:-"+driver.getTitle());
		
		//Thread.sleep(1000);
		
		driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
		System.out.println("username entered");
		
		driver.findElement(By.name("password")).sendKeys("demouser");
		System.out.println("pass entered");
		//driver.findElement(By.xpath("//*[@id='loginfrm']/div[1]/div[5]/button")).click();
	  	driver.findElement(By.xpath("//form[@id='loginfrm']/button")).click();
		System.out.println("loggedin");
	  
  }
  
  
  @AfterTest
  public void afterTest() throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver,1000);
		driver.findElement(By.linkText("Logout")).click();
		System.out.println("logout");
		Thread.sleep(1000);
		String s= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='loginfrm']/div[1]/div[1]"))).getText();
		System.out.println("s="+s);
		if(s.equalsIgnoreCase("Login")){
			System.out.println("logout successful");
			
		}else{
			System.out.println("logout failed");
		}
		
		driver.quit();
  }
  

}
