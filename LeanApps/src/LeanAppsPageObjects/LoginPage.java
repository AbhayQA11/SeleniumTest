package LeanAppsPageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LoginPage extends BaseClass {
	private static WebElement element = null;
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	public static WebElement txtbx_UserName() throws Exception{
    	try{
            element = driver.findElement(By.id("email"));  
    	}catch (Exception e){
       		throw(e);
       		}
       	return element;
        }
    
    public static WebElement txtbx_Password() throws Exception{
    	try{
        	element = driver.findElement(By.id("password"));
    	}catch (Exception e){
       		throw(e);
       		}
       	return element;
    }
    
    public static WebElement btn_LogIn() throws Exception{
    	try{
        	element = driver.findElement(By.cssSelector("//button[@type='submit']"));
    	}catch (Exception e){
       		throw(e);
       		}
       	return element;
    }
    public static WebElement emailError() throws Exception{
    	try{
        	element = driver.findElement(By.id("emailError"));
    	}catch (Exception e){
       		throw(e);
       		}
       	return element;
    }
    public static WebElement pwdError() throws Exception{
    	try{
        	element = driver.findElement(By.id("passwordError"));
    	}catch (Exception e){
       		throw(e);
       		}
       	return element;
    }
}
