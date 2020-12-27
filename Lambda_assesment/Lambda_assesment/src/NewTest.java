import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByTagName;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class NewTest {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	  public void beforeClass() {
		  
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"./geckodriver.exe");
			driver =new FirefoxDriver();
			
			driver.get("https://www.lambdatest.com/automation-demos/");
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  }
	
  @Test(priority=1)
  public void login() {
	  driver.get("https://www.lambdatest.com/automation-demos/");
	  driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	  driver.findElement(By.cssSelector("input#username")).sendKeys("lambda");
	  driver.findElement(By.id("password")).sendKeys("lambda123");
	  driver.findElement(By.xpath("//button[contains(text(),'Login')]")).click();
	  
	  
  }
  
  @Test(priority=2)
  public void upload() throws InterruptedException, IOException {
	  driver.findElement(By.id("developer-name")).sendKeys("devd1310@gmail.com");
	  driver.findElement(By.id("populate")).click();
	  Thread.sleep(3000);
	  Alert alert = driver.switchTo().alert();
	  alert.accept();
	  Thread.sleep(3000);
	  driver.findElement(By.id("month")).click();
	  WebElement chkbox = driver.findElement(By.xpath("//input[@type='checkbox' and @id='delivery-time']"));
	 
	  chkbox.click();
	  
	  Select se = new Select(driver.findElement(By.id("preferred-payment")));
	  se.selectByVisibleText("Cash on delivery");
	  
	  JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.scrollBy(0,350)", "");
	  
	  driver.findElement(By.id("tried-ecom")).click();
	  WebElement slider=driver.findElement(By.id("slider"));
	 
	  Actions action= new Actions(driver);
	   /* action.click(slider).build().perform();
	    Thread.sleep(1000);
	    for (int i = 0; i < 7; i++) 
	    {
	        action.sendKeys(Keys.ARROW_RIGHT).build().perform();
	        Thread.sleep(200);
	    }*/
	    int width = slider.getSize().getWidth();
	    action.moveToElement(slider, ((width*35)/100), 0).click();
	    action.build().perform();
	    String pcnt = driver.findElement(By.xpath("//div[@id='slider']//span")).getAttribute("style");
	    Assert.assertEquals("left: 88.8889%;", pcnt);
	    String oldtab = driver.getWindowHandle();
	    
	    //new browser tab operation
	   
	   WebDriver sec_driver =new FirefoxDriver();
	    sec_driver.get("https://www.lambdatest.com/selenium-automation");
	   sec_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	   String newtab = driver.getWindowHandle();
	   String title =  driver.getTitle();
	   System.out.println(title);
	    
	    
	    
	    
	    
	    WebElement logo = sec_driver.findElement(By.xpath("//img[@alt='LambdaTest Jenkins integration']"));
	    ((JavascriptExecutor) sec_driver).executeScript("arguments[0].scrollIntoView(true);"+"window.scrollBy(0,-300);", logo);
	    String url=logo.getAttribute("src");
	    Thread.sleep(5000); 
	    
	    //download image jenkin
	    
	// sec_driver.findElement(By.tagName("body")).sendKeys(Keys.CONTROL+"t");
	  sec_driver.navigate().to(url);
	  WebElement icon=sec_driver.findElement(By.id("Icons"));

	 // Get entire page screenshot
	 File screenshot = ((TakesScreenshot)sec_driver).getScreenshotAs(OutputType.FILE);
	 BufferedImage  fullImg = ImageIO.read(screenshot);

	 // Get the location of element on the page
	 Point point = icon.getLocation();
	 System.out.println(point);

	 // Get width and height of the element
	 int eleWidth = icon.getSize().getWidth();
	 int eleHeight = icon.getSize().getHeight();
	 System.out.println(eleWidth);
	 System.out.println(eleHeight);

	 // Crop the entire page screenshot to get only element screenshot
	 BufferedImage eleScreenshot= fullImg.getSubimage(point.getX(), point.getY(),
	     eleWidth, eleHeight);
	 ImageIO.write(eleScreenshot, "svg", screenshot);

	 // Copy the element screenshot to disk
	 File screenshotLocation = new File("C:\\Users\\DShukla.prt\\Desktop\\Screenshot_selenium\\jenkins.svg");
	 FileUtils.copyFile(screenshot, screenshotLocation);
	   
	 sec_driver.close();
	 
	   
	 //switching to selenium playground
	
	    System.out.println(driver.getTitle());
	    driver.findElement(By.tagName("body")).click();
	 
	 //uploading
	    Thread.sleep(4000);
	 js.executeScript("window.scrollBy(0,325)", "");
	 
	 js.executeScript("arguments[0].setAttribute('style', arguments[1])", driver.findElement(By.xpath("//input[@type='file']")), "0");
	 //js.executeScript("arguments[0].setAttribute('class', arguments[1])", driver.findElement(By.xpath("//input[@type='file']/../../div[2]")), "a");
	 driver.findElement(By.xpath("//input[@id='file']")).sendKeys("C:\\Users\\DShukla.prt\\Desktop\\Screenshot_selenium\\jenkins.svg");
	
	 //alert message
	String message1=  alert.getText();
	Assert.assertEquals(message1,"your image upload sucessfully!!");
	 alert.accept();
	 
	 
  }
  
  @Test(priority=3)
  public void submit() {
	  driver.findElement(By.id("submit-button")).click();
	  String message2=driver.findElement(By.id("message")).getText();
	  System.out.println(message2);
	 // Assert.assertEquals(message2, "You have successfully submitted the form.");
	  
  }
  
  

  @AfterClass
  public void afterClass() {
	 
	  driver.close();
  }

}
