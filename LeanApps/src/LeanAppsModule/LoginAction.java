package LeanAppsModule;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import LeanAppsPageObjects.LoginPage;
import LeanAppsUtils.Constants;
import LeanAppsUtils.ExcelRead;
import org.testng.annotations.*;
public class LoginAction {
	WebDriver driver;
	@BeforeMethod
	public void beforeMethod() throws Exception {
		System.setProperty("webdriver.chrome.driver",Constants.chromeDriverPath);
		driver=new FirefoxDriver();
		driver.get("http://52.66.147.49/automation-test/");
	}
	@Test(priority=0)
	void Login(String username,String pwd,String msg) throws Exception {
		int totalrows=9,i=1;
		while(totalrows!=1) {
			username=ExcelRead.getCellData(i, Constants.Col_user);
			pwd=ExcelRead.getCellData(i, Constants.Col_pwd);
			msg=ExcelRead.getCellData(i, Constants.Col_message);
		LoginPage.txtbx_UserName().sendKeys(username);
		System.out.println("Entered Username");
		LoginPage.txtbx_Password().sendKeys(pwd);
		System.out.println("Entered Password");
		LoginPage.btn_LogIn().click();
		System.out.println("Clicked Login Link");
		if(LoginPage.emailError().isDisplayed()) {
			String message=LoginPage.emailError().getText();
			assertEquals(message,msg);
		}
		if(LoginPage.pwdError().isDisplayed()) {
			String message=LoginPage.pwdError().getText();
			assertEquals(message,msg);
		}
		totalrows--;i++;
	}
	}
	@Test(priority=1)
	public void verifyUser() {
		JavascriptExecutor jse=((JavascriptExecutor)driver);
		jse.executeScript("window.scrollBy(0,300)", "");
		WebElement e=driver.findElement(By.xpath("//li[contains(text(),'You ']"));
		String ExpectedUser=e.getText();
		assertEquals(ExpectedUser,"You ");
		System.out.println("User Present");
	}
	@AfterMethod
	public void AfterMethod() {
		driver.quit();
	}

}
