package LeanAppsModule;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import LeanAppsPageObjects.BaseClass;
import LeanAppsPageObjects.LoginPage;
import LeanAppsUtils.Constants;
import LeanAppsUtils.ExcelRead;

public class LoginValidation {
	public WebDriver driver;
	
	public void Login() throws Exception {

		//Set the Driver path	
	System.setProperty("webdriver.chrome.driver",Constants.chromeDriverPath);
	//Open ChromeDriver
	driver=new ChromeDriver(); 
	driver.get("http://52.66.147.49/automation-test/");
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	String ListTitle="List of Employees";
	int i=1;
	while(ExcelRead.totalRows!=1) {
		ExcelRead.userVal=ExcelRead.getCellData(i, Constants.Col_user);
		ExcelRead.userPwd=ExcelRead.getCellData(i, Constants.Col_pwd);
		driver.navigate().refresh();
		Thread.sleep(5000);
	System.out.println("**************************Validation instance"+i+"************************");	
	driver.findElement(By.id("email")).sendKeys(ExcelRead.userVal);
	System.out.println("*****************************Entered Username*****************************");
	driver.findElement(By.id("password")).sendKeys(ExcelRead.userPwd);
	System.out.println("*****************************Entered Password*****************************");
	driver.findElement(By.xpath("//*[@id='myForm']/button")).click();
	System.out.println("****************************Clicked Login Link****************************");
	ExcelRead.userMessage=ExcelRead.getCellData(i, Constants.Col_message);
	System.out.println("Expected Error message"+ ExcelRead.userMessage);
	List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'valid')]"));
	if(list.size()>0 && ExcelRead.userMessage!="") {
		String text=list.get(0).getText();
		System.out.println("Actual Error message"+ ExcelRead.userMessage);
		if(text==ExcelRead.userMessage){
		System.out.println("Credential Validation");
		}
	}
	else{
		assertEquals(driver.getTitle(),ListTitle);
	}
	
	ExcelRead.totalRows--;i++;
}
}
	
	
}

