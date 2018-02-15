package LeanAppsModule;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import LeanAppsPageObjects.LoginPage;
import LeanAppsUtils.Constants;
import LeanAppsUtils.ExcelRead;
import org.testng.annotations.*;
public class LoginAction {
	WebDriver driver;
	@BeforeClass
	public void beforeMethod() throws Exception {
		System.setProperty("webdriver.chrome.driver",Constants.chromeDriverPath);
		driver=new ChromeDriver();
		driver.get("http://52.66.147.49/automation-test/");
	}
	@Test(priority=0)
	public void Login() throws Exception {
		int totalrows=9,i=1;
		while(ExcelRead.totalRows!=1) {
			ExcelRead.userVal=ExcelRead.getCellData(i, Constants.Col_user);
			ExcelRead.userPwd=ExcelRead.getCellData(i, Constants.Col_pwd);
			ExcelRead.userMessage=ExcelRead.getCellData(i, Constants.Col_message);
		LoginPage.txtbx_UserName().sendKeys(ExcelRead.userVal);
		System.out.println("Entered Username");
		LoginPage.txtbx_Password().sendKeys(ExcelRead.userPwd);
		System.out.println("Entered Password");
		LoginPage.btn_LogIn().click();
		System.out.println("Clicked Login Link");
		if(LoginPage.emailError().isDisplayed()) {
			String message=LoginPage.emailError().getText();
			assertEquals(message,ExcelRead.userMessage);
		}
		if(LoginPage.pwdError().isDisplayed()) {
			String message=LoginPage.pwdError().getText();
			assertEquals(message,ExcelRead.userMessage);
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
	@AfterClass
	public void AfterMethod() {
		driver.quit();
	}

}
