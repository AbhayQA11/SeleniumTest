package LeanAppsUtils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import LeanAppsModule.Login;

public class ScreenshotCapture {
	static int n=0;
	//Method to Capture Screenshot
	  public static void capture(WebDriver driver,String Filep) throws IOException{
		  TakesScreenshot scr=(TakesScreenshot)driver;
		  File source=scr.getScreenshotAs(OutputType.FILE);
		  File destination=new File(Filep);
		  FileUtils.copyFile(source, destination);
		  
	  }
	//Method to Provide screenshot destination path
	  public static String screenshotPath(){
		  
		  Method[] m=Login.class.getMethods();
		  String s=m[n].getName();
		  n++;
		  System.out.println(s);
		  String path=Constants.scrPath;
		  return path;
	  }

}
