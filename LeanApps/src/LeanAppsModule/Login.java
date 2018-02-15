package LeanAppsModule;

import org.testng.annotations.Test;
import LeanAppsUtils.Constants;
import LeanAppsUtils.ExcelRead;
import LeanAppsUtils.ScreenshotCapture;

import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertEquals;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.testng.Reporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class Login {
	public WebDriver driver;
  @Test
  public void loginValidation() {
		String ListTitle="List of Employees";
		int i=1;
		try {
		while(ExcelRead.totalRows!=1 && i<10) {
			ExcelRead.userVal=ExcelRead.getCellData(i, Constants.Col_user);
			ExcelRead.userPwd=ExcelRead.getCellData(i, Constants.Col_pwd);
			driver.navigate().refresh();
			Thread.sleep(5000);
		System.out.println("**************************Validation instance"+i+"************************");
		Reporter.log("Validate User id - "+i);
		driver.findElement(By.id("email")).sendKeys(ExcelRead.userVal);
		System.out.println("*****************************Entered Username*****************************");
		Reporter.log("Entered Username");
		driver.findElement(By.id("password")).sendKeys(ExcelRead.userPwd);
		System.out.println("*****************************Entered Password*****************************");
		Reporter.log("Entered Password");
		driver.findElement(By.xpath("//*[@id='myForm']/button")).click();
		System.out.println("****************************Clicked Login Link****************************");
		Reporter.log("Clicked Login Button");
		ExcelRead.userMessage=ExcelRead.getCellData(i, Constants.Col_message);
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'valid')]"));
		if(list.size()>0 && ExcelRead.userMessage!="") {
			String text=list.get(0).getText();
			Reporter.log("Expected Error Message matched application error - "+text);
			Thread.sleep(2000);
			ScreenshotCapture.capture(driver, ScreenshotCapture.screenshotPath()+i+".png");
			}
		else{
			assertEquals(driver.getTitle(),ListTitle);
		}
		ExcelRead.totalRows--;i++;
	}
  }
		catch(Exception e) {
			System.out.println("Exception encountered " + e);
		}
  }
  //Verify if User is Present 
  @Test(dependsOnMethods= {"loginValidation"})
  public void verifyUser() {
	  try {
	  JavascriptExecutor jse=((JavascriptExecutor)driver);
		jse.executeScript("window.scrollBy(0,300)", "");
		Reporter.log("Page Scrolled Down");
		Thread.sleep(3000);
		WebElement e=driver.findElement(By.xpath("html/body/div[1]/div[2]/div/div/ul/li[16]"));
		String ExpectedUser=e.getText();
		assertEquals(ExpectedUser,"You");
		Reporter.log("User YOU present in List");
		System.out.println("User Present");
		ScreenshotCapture.capture(driver, ScreenshotCapture.screenshotPath());
  }
	  catch(Exception e) {
		  System.out.println("Exception encountered - "+e);
	  }
  }
  @BeforeClass
  public void beforeClass() {
		//Set the Driver path	
	System.setProperty("webdriver.chrome.driver",Constants.chromeDriverPath);
	//Open ChromeDriver
	driver=new ChromeDriver();
	driver.get("http://52.66.147.49/automation-test/");
	Reporter.log("Opened URL: http://52.66.147.49/automation-test/");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.manage().window().maximize();
  }

  @AfterClass
  public void afterClass() {
	  driver.close();
	  Reporter.log("Browser Closed");
  }

}
