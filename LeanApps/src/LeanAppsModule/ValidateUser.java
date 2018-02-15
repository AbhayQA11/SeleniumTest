package LeanAppsModule;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidateUser {
	public WebDriver driver;
	public void verifyUser() {
		
		JavascriptExecutor jse=((JavascriptExecutor)driver);
		jse.executeScript("window.scrollBy(0,300)", "");
		WebElement e=driver.findElement(By.xpath("//li[contains(text(),'You ']"));
		String ExpectedUser=e.getText();
		assertEquals(ExpectedUser,"You ");
		System.out.println("User Present");
		
	}

}
