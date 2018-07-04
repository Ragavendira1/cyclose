package SeleniumPackage;

public class Driver {
	
	public static void main(String args[]) {
				
		try {//430
			
			if(args.length == 0) {	
				TestAttributes.Run_ID = 0;
				TestAttributes.Run_Name = "Selenium-Debugging";
				TestAttributes.Instance_ID = 430;
				TestAttributes.Global_DataBase = "ACCESS";
				TestAttributes.AutomationTool = "SELENIUM"; //SELENIUM or SEETEST				
			} else {
				TestAttributes.Run_ID = Integer.parseInt(args[0].trim());
				TestAttributes.Run_Name = args[1].trim();
				TestAttributes.Instance_ID = Integer.parseInt(args[2].trim());
				TestAttributes.Global_DataBase = args[3].trim();
				TestAttributes.AutomationTool = args[4].trim();				
			}
						
			if (TestAttributes.Run_ID > 0) {				
				TestAttributes.TestInstanceControl = false;
				TestAttributes.Global_SharedDataSetList = args[5].trim();
				TestAttributes.Global_TestDataSetList = args[6].trim();
				TestAttributes.StartStepId = Integer.parseInt(args[7].trim());
			}
			
			FunctionLibrary.Driver();
			
		} catch(Exception e) {
						
			if(TestAttributes.executiontriggered == false){
			
				System.out.println("Error in execution. Possible reason coule be an issue...");
				System.out.println("with the arguments passed from front end.");
				System.out.println("while getting the test details from SQS_TEST_BATCH. (A record with instance id '" + TestAttributes.Instance_ID + "' may not be available in the batch");
				System.out.println("while retrieving the log location from SQS_INSTANCE_RUNS");			
				System.out.println("while retrieving the step id from SQS_TEST_RESULT");
				System.out.println("while retrieving the data sets from SQS_TEST_DATASET");
				
				
				if (!TestAttributes.InitialSetUpErrorMessage.trim().equalsIgnoreCase(""))
					System.out.println("Please note there is also an initial setup error : " + TestAttributes.InitialSetUpErrorMessage.trim());
				 
			}
			else{
				System.out.println("Error while Capturing screen shot / Writing results");
			}
			
			e.printStackTrace();
		}
		
	
	}
}