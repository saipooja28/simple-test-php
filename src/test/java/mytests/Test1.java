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
	
		/*System.setProperty("webdriver.chrome.driver","D:\\drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver(); 
		driver.manage().window().maximize();*///http://35.190.187.1:4444/wd/hubhttp://104.155.135.94:4444/wd/hub
		
		/*System.setProperty("webdriver.ie.driver","D:\\drivers\\IEDriverServer_Win32_3.0.0\\IEDriverServer.exe");
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
	  	driver.findElement(By.xpath("//form[@id='loginfrm']/button")).click();//working 20 sep
		System.out.println("loggedin");
	  
  }

  @Test
  public void test1() throws Exception {
	//this is the first test
	WebDriverWait wait = new WebDriverWait(driver,1000);
	//Thread.sleep(1000);
	//Thread.sleep(1000);
	//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='body-section']/div/div[3]/div/div[1]/ul/li[1]/a"))).click();
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Bookings"))).click();//working on 20 sep
	//By.xpath("//*[@id='body-section']/div/div[3]/div/div[1]/ul/li[1]/a")).click();
	//driver.findElement(By.xpath("//*[@id='body-section']/div/div[3]/div/div[1]/ul/li[1]/a")).click();
	System.out.println("clicked on booking");
	//String x=driver.findElement(By.xpath("//*[@id='body-section']/div/div[1]/div/div[1]/h3")).getText();
	String x=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='body-section']/div/div[1]/div/div[1]/h3"))).getText();
	if(x.equals("Hi, Johny Smith")){
		System.out.println("testcase1 passed-entered main page");
	}
	else{
		System.out.println("testcase1 failed");
	}
			
	//driver.findElement(By.linkText("Invoice")).click();
	wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='bookings']/div[2]/div[4]/a"))).click();
	/*String  handle= driver.getWindowHandle();
	 driver.switchTo().window("http://www.phptravels.net/invoice?id=73&sessid=6897");*/
	 
	 Set<String> windows=driver.getWindowHandles();
	 System.out.println(windows.size());
		Iterator<String> it=windows.iterator();
		parent=it.next();
		child=it.next();
		driver.switchTo().window(child); 
	//System.out.println("");
	//driver.navigate().to("http://www.phptravels.net/invoice?id=73&sessid=6897");
	//String b=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='invoiceTable']/tbody/tr[3]/td/table/tbody/tr[2]/td/table/tbody/tr[5]/td/strong"))).getText();//worked
	String b=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@id='invoiceTable']/tbody/tr[2]/td/div/table/tbody/tr/td/div[3]"))).getText();//working 20 sep
	System.out.println(b);
	 if(b.equalsIgnoreCase("Check out")){
		 System.out.println("testcase passed");
		 
	 }
	 else{
		 System.out.println("testcase failed");
	 }
	
	driver.findElement(By.xpath("//*[@id='btn']")).click();
	System.out.println("download");
	  
  }
  
	/*@Test
	public void test2() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,1000);
	//System.out.println("title"+driver.getTitle());
	//driver.findElement(By.xpath("//*[@id='body-section']/div/div[3]/div/div[1]/ul/li[3]/a/span")).click();
 // Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Wishlist"))).click();
	//div[@id='body-section']/div/div[3]/div/div/ul/li[3]/a/span
	System.out.println("wishlist");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wish6']/div[3]/a"))).click();
	 Set<String> windows=driver.getWindowHandles();
	 System.out.println(windows.size());
		Iterator<String> it=windows.iterator();
		it.next();
		it.next();
		String preview =it.next();
		driver.switchTo().window(preview); 
	String h= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='body-section']/div[2]/div/div/div[2]/div/span[1]/strong"))).getText();
	if(h.equalsIgnoreCase("HYATT REGENCY PERTH")){
		System.out.println("on preview page");
			}else {
				System.out.println("on home page");
			}
		
	}
	@Test
	public void test3() throws Exception{
		//Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver,1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("My Profile"))).click();
		String firstname= driver.findElement(By.name("firstname")).getAttribute("value");
		System.out.println(firstname);
		String lastname= driver.findElement(By.name("lastname")).getAttribute("value");
		System.out.println(lastname);
		String username= driver.findElement(By.xpath("//*[@id='body-section']/div/div[1]/div/div[1]/h3")).getText();
		
	    String actual=username.substring(4, username.length());
		System.out.println(actual);
		if((firstname+" "+lastname).equalsIgnoreCase(actual)){
			System.out.println("valid username");
		}else{
			System.out.println("invalid user");
		}
	}*/
  
  
  @AfterTest
  public void afterTest() throws Exception {
	  WebDriverWait wait = new WebDriverWait(driver,1000);
		driver.switchTo().window(parent);
		System.out.println("Switched");
		/*Select lg =new Select(driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/ul/ul/li[1]/ul")));
		System.out.println("elememt selected");
		lg.selectByVisibleText("Logout");*/
		//hread.sleep(1000);
		//driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/ul/ul/li[1]/ul")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='collapse']/ul[2]/ul/li[1]/a"))).click();
		System.out.println("clicked");
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
