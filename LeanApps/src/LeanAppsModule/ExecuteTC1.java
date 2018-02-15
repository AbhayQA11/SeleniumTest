package LeanAppsModule;

import LeanAppsModule.LoginValidation;

public class ExecuteTC1 {

	public static void main(String[] args) throws Exception {		
		
		try {
			System.out.println("Validating Login");		
			LoginValidation obj=new LoginValidation();
			obj.Login();
			System.out.println("Validating User");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.out.println("Finished");
		}
	}
}
