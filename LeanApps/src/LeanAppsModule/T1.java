package LeanAppsModule;

import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;

import LeanAppsUtils.Constants;

public class T1 {


	public static void main(String[] args) {
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add(Constants.userDir+"\\src\\testng.xml");//path to xml..
		testng.setTestSuites(suites);
		testng.run();

	}

}
