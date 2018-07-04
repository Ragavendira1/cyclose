package SeleniumPackage;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.net.URISyntaxException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;

public class CommonKeywordLibrary {
	//========================================================================================================================================'
	public static void IF() throws SQLException{
		TestAttributes.TakeScreenShotFlag = true;
		try {
			TestAttributes.IfEvaluation = Arrays.copyOf(TestAttributes.IfEvaluation, TestAttributes.IfEvaluation.length + 1);
			TestAttributes.iflevelinaction = TestAttributes.iflevelinaction + 1;	
			String[] DataSplit = TestAttributes.Data.split("\\|\\|",-1);
			
			if(DataSplit.length < 2) {
				TestAttributes.Status = "Error";				
				TestAttributes.ActualResult = "Data error.Please check the parameters for the keyword 'IF'.";							
				TestAttributes.TakeScreenShotFlag= false;
				FunctionLibrary.WriteResults();				

			} else {
				String keyword = DataSplit[0].trim();
				String backupdata = TestAttributes.Data.trim();
				TestAttributes.Data = DataSplit[1].trim();
				
				//=============================================================================================\\
				Class<?> cif = null;
				
				if(TestAttributes.AutomationApp.trim().equalsIgnoreCase("WEB"))
					cif = Class.forName("SeleniumPackage.WebKeywordLibrary");
				
				if(TestAttributes.AutomationApp.trim().equalsIgnoreCase("MOBILE"))
					cif = Class.forName("SeleniumPackage.MobileKeywordLibrary");
				
				Object objif = cif.newInstance();
				Method methodif = cif.getMethod(keyword.toUpperCase());
				methodif.invoke(objif);
				
				//=============================================================================================\\
				TestAttributes.Data = backupdata;
				
				TestAttributes.TakeScreenShotFlag = true;
				
				if(TestAttributes.Status.equalsIgnoreCase("Passed")) {
					TestAttributes.IfEvaluation[TestAttributes.iflevelinaction] = true;
					TestAttributes.ActualResult = "'IF' condition is evaluated to be 'True'. Executing the steps under 'IF'...";        //only for logs not for results
				}
				
				if(TestAttributes.Status.equalsIgnoreCase("Failed")) {
					TestAttributes.IfEvaluation[TestAttributes.iflevelinaction] = false;
					TestAttributes.ActualResult = "'IF' condition is evaluated to be 'False'. Not executing the steps under 'IF'.";   //only for logs not for results
				}
				
				if(TestAttributes.Status.equalsIgnoreCase("Error")) {
					TestAttributes.ActualResult = TestAttributes.ActualResult + "Error while evaluating 'IF' condition";
					FunctionLibrary.WriteResults();
				}
			}
		} catch(Exception e) {			
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while evaluating 'IF' condition";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
			FunctionLibrary.WriteResults();
		}
	}
	
	//========================================================================================================================================'
	public static void ELSE() {
		TestAttributes.TakeScreenShotFlag= false;		
		TestAttributes.ELSE = Arrays.copyOf(TestAttributes.ELSE, TestAttributes.ELSE.length + 1);
		TestAttributes.ELSE[TestAttributes.iflevelinaction] = "True";
		if(TestAttributes.IfEvaluation[TestAttributes.iflevelinaction]==true)
			TestAttributes.ActualResult = "Not executing the steps under 'ELSE'";    //only for logs not for results
		else
			TestAttributes.ActualResult = "Executing the steps under 'ELSE'...";	 //only for logs not for results 
	}
	
	
	//========================================================================================================================================'
	public static void ENDIF() {
		TestAttributes.TakeScreenShotFlag= false;		
		TestAttributes.ELSE[TestAttributes.iflevelinaction] = "";
		TestAttributes.IfEvaluation[TestAttributes.iflevelinaction] = false;
		TestAttributes.iflevelinaction = TestAttributes.iflevelinaction - 1;
		TestAttributes.ActualResult = "End of 'IF'"; //only for logs not for results
	}
	
	//========================================================================================================================================'
	public static void WAIT() {
		TestAttributes.TakeScreenShotFlag= false;
		try {
			int WaitTimeInt = Integer.parseInt(TestAttributes.Data) * 1000;
			Thread.sleep(WaitTimeInt);
			TestAttributes.ActualResult = "Application has waited for '" + TestAttributes.Data + "' Seconds.";

		} catch(Exception e){
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while retrieving the wait time / Application is not able to wait for '" + TestAttributes.Data + "' Seconds.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	public static void CALLTEST() throws InvalidFormatException, IOException, XmlException, SQLException, InterruptedException{
		TestAttributes.TakeScreenShotFlag= false;
		
		TestAttributes.calltesttestid = Arrays.copyOf(TestAttributes.calltesttestid, TestAttributes.calltesttestid.length + 1);
		TestAttributes.calltestworkflowname = Arrays.copyOf(TestAttributes.calltestworkflowname, TestAttributes.calltestworkflowname.length + 1);
		TestAttributes.calltestshareddataset = Arrays.copyOf(TestAttributes.calltestshareddataset, TestAttributes.calltestshareddataset.length + 1);
		TestAttributes.calltesttestdataset = Arrays.copyOf(TestAttributes.calltesttestdataset, TestAttributes.calltesttestdataset.length + 1);
		//TestAttributes.calltestresultset = Arrays.copyOf(TestAttributes.calltestresultset, TestAttributes.calltestresultset.length + 1);
		//TestAttributes.calltestresultsetrow = Arrays.copyOf(TestAttributes.calltestresultsetrow, TestAttributes.calltestresultsetrow.length + 1);
		
		
		TestAttributes.calltesttestid[TestAttributes.calltestcounter] = TestAttributes.Test_Id;		
		TestAttributes.calltestworkflowname[TestAttributes.calltestcounter] = TestAttributes.Workflow_Name;
		TestAttributes.calltestshareddataset[TestAttributes.calltestcounter] = TestAttributes.SharedDataSet;
		TestAttributes.calltesttestdataset[TestAttributes.calltestcounter] = TestAttributes.TestDataSet;
		//TestAttributes.calltestresultset[TestAttributes.calltestcounter] = FunctionLibrary.TestStepsResults;
		//TestAttributes.calltestresultsetrow[TestAttributes.calltestcounter] = FunctionLibrary.TestStepsResults.getRow();
		
		TestAttributes.calltestcounter = TestAttributes.calltestcounter + 1;					
		
		String[] DataSplit = TestAttributes.Data.split("::",-1);
		System.out.println(FunctionLibrary.TestStepsResults);
		if (DataSplit.length == 4) {
			System.out.println("Sub script '" + DataSplit[0].trim() + "' is triggered."); //only for logs not for results
			
			FunctionLibrary.ExecuteSteps1(DataSplit[0].trim(), DataSplit[1].trim(), DataSplit[2].trim(), DataSplit[3].trim(), 0);
			
			if(TestAttributes.Status.equalsIgnoreCase("Error"))		
				TestAttributes.ActualResult = "Sub script '" + DataSplit[0] + "' is completed with error."; //only for logs not for results
			else
				TestAttributes.ActualResult = "Sub script '" + DataSplit[0] + "' is completed.";	//only for logs not for results			

		} else {
			TestAttributes.ActualResult = "Data error.Please check the parameters for the keyword 'CallTest'.";		
			TestAttributes.Status = "Error";
			TestAttributes.Keyword = "CallTest";			
			TestAttributes.TakeScreenShotFlag= false;
			FunctionLibrary.WriteResults();
		}
		
		TestAttributes.calltestcounter = TestAttributes.calltestcounter - 1;		
		TestAttributes.Test_Id = TestAttributes.calltesttestid[TestAttributes.calltestcounter]; 
		TestAttributes.Workflow_Name = TestAttributes.calltestworkflowname[TestAttributes.calltestcounter]; 
		TestAttributes.SharedDataSet = TestAttributes.calltestshareddataset[TestAttributes.calltestcounter]; 
		TestAttributes.TestDataSet = TestAttributes.calltesttestdataset[TestAttributes.calltestcounter];
		//FunctionLibrary.TestStepsResults = TestAttributes.calltestresultset[TestAttributes.calltestcounter];
		//FunctionLibrary.TestStepsResults = TestAttributes.calltestresultsetrow[TestAttributes.calltestcounter];
		
		System.out.println(FunctionLibrary.TestStepsResults.getRow());
		 
		
		TestAttributes.Keyword = "CallTest";
		TestAttributes.TakeScreenShotFlag= false;
	}
	
	//========================================================================================================================================'
	public static void STARTLOOP() throws InvalidFormatException, IOException, XmlException, SQLException, InterruptedException {
		TestAttributes.TakeScreenShotFlag = false;
		try {
			String[] DataSplit = TestAttributes.Data.split("to");
			
			if(DataSplit.length!=2) {
				TestAttributes.ActualResult  = "Data error.Please check the parameters for the keyword 'CallTest'.";	//only for logs not for results
				TestAttributes.Status = "Error";
				TestAttributes.TakeScreenShotFlag= false;
				FunctionLibrary.WriteResults();

			} else {
				TestAttributes.ActualResult = "Start of loop";
				TestAttributes.looplevel = TestAttributes.looplevel + 1;
				TestAttributes.LoopCounters = Arrays.copyOf(TestAttributes.LoopCounters, TestAttributes.LoopCounters.length + 1);
				TestAttributes.LoopCounters[TestAttributes.looplevel] = (Integer.parseInt(DataSplit[1].trim()) - Integer.parseInt(DataSplit[0].trim())) + 1;
				TestAttributes.StepsIds = Arrays.copyOf(TestAttributes.StepsIds, TestAttributes.StepsIds.length + 1);
				TestAttributes.StepsIds[TestAttributes.looplevel] = FunctionLibrary.TestStepsResults.getRow();
			}

		} catch(Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while initiating the loop.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
			FunctionLibrary.CaptureScreenShot();
			FunctionLibrary.WriteResults();
		}
	}
	
	//========================================================================================================================================'
	public static void ENDLOOP() {
		TestAttributes.TakeScreenShotFlag = false;
		try {
			TestAttributes.LoopCounters[TestAttributes.looplevel] = TestAttributes.LoopCounters[TestAttributes.looplevel] -1;
			
			if(TestAttributes.LoopCounters[TestAttributes.looplevel]!=0)				
				FunctionLibrary.TestStepsResults.absolute(TestAttributes.StepsIds[TestAttributes.looplevel]);
			else
				TestAttributes.looplevel = TestAttributes.looplevel - 1;
			
			TestAttributes.ExitLoop = false;
			TestAttributes.ActualResult = "End of loop";

		} catch(Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while ending the loop.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}
	
	//========================================================================================================================================'
	public static void EXITLOOP() {
		TestAttributes.TakeScreenShotFlag = false;
		try {
			TestAttributes.ExitLoop = true;
			TestAttributes.ActualResult = "Exiting the loop";
			
		} catch(Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while exiting the loop.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}
	
	//========================================================================================================================================'
	public static void CALLFUNCTION() {
		try {
			TestAttributes.TakeScreenShotFlag = true;
			String[] DataSplit = TestAttributes.Data.split("\\|\\|");
			
			if (DataSplit.length!=0) {				
				FunctionLibrary.setParameters();
				System.out.println("Function '" + DataSplit[0].trim() + "' is triggered."); //only for logs not for results
				
				Class<?> ccf = null;
				
				if (TestAttributes.AutomationApp.trim().equalsIgnoreCase("WEB"))
					ccf = Class.forName("SeleniumPackage.WebKeywordLibrary");
				
				if (TestAttributes.AutomationApp.trim().equalsIgnoreCase("MOBILE"))
					ccf = Class.forName("SeleniumPackage.MobileKeywordLibrary");
				
				Object objcf = ccf.newInstance();
				Method methodcf = ccf.getMethod(DataSplit[0].trim().toUpperCase());
				methodcf.invoke(objcf);
				
				
				if (TestAttributes.Status.equalsIgnoreCase("Error"))		
					System.out.println("Function '" + DataSplit[0] + "' is completed with error."); //only for logs not for results
				else
					System.out.println("Function '" + DataSplit[0] + "' is completed.");	//only for logs not for results			

			} else {			
				TestAttributes.ActualResult = "Data error.Please check the parameters for the keyword 'CallFunction'. Please check if atleast the function name is specified";			
				TestAttributes.Status = "Error";
				TestAttributes.TakeScreenShotFlag= false;
			}
			TestAttributes.setInOutParameters();

		} catch(Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while executing the keyword 'CallFunction'";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
			TestAttributes.setInOutParameters();
		}
	}
	
	//========================================================================================================================================'
	/* The below is an example function which can be used as a model by the developer when he writes a custom function  
	   that needs to be called using the keyword 'CallFunction'. Please note few points while writing a custom function
	  
	 Pt1. The 'CallFunction' keyword takes three parameters separated by '||'
	 		 1 - Name of the function to be called. Change the name as appropriate and required.This is a mandatory field	 					 			
	 		 2 - List of input parameters separated by ';;'. This is an optional field
	 		 3 - List of output parameters separated by ';;'. This is an optional field
	 		
	 		 e.g 'Calculate||20;;10||AddResult;;SubResult' can be a typical work flow data for 'CallFunction' keyword
	 		
	 		 Calculate is the name of the function (Not necessarily be in caps here. But while writing code, the function name must be in upper case i.e 'CALCULATE')
	 	 	 20;;10 is a list of input parameters
	 		 AddResult;;SubResult is a list of output parameters 
	 		
	 			The format of input and output parameters can be in any typical ARTEMIS data format
	 			Input and output parameters are together optional. They can both be present or both absent
	 			
	 			If the logic of the function does not demand input parameters and requires only output parameters the syntax can be	 			
	 			Calculate||||AddResult;;SubResult
	 			
	 			If the logic of the function does not demand output parameters and requires only input parameters the syntax can be	 			
	 			Calculate||20;;10||
	 			
	 			If the logic of the function does not require both input and output parameters the syntax can be	 			
	 			Calculate
	 			
	 Pt2. The work flow data can be accessed using the variable 'TestAttributes.Data' if required
	 
	 Pt3. The web element associated with the keyword 'CallFunction' can be accessed using the variable 'TestAttributes.element'
	 	  Please note that the 'CallFunction' keyword can either be associated with a web element or not 
	 
	 Pt4. The list of input parameters can be accessed using TestAttributes.InputParameters[i] i.e first input parameter can be accessed like
	 TestAttributes.InputParameters[0] , second parameter like TestAttributes.InputParameters[1] and so on 
	
	 Pt5. The list of output parameters can be accessed using TestAttributes.OutputParameters[i] i.e first output parameter can be accessed like
	 TestAttributes.OutputParameters[0] , second parameter like TestAttributes.OutputParameters[1] and so on
	
	 Pt6. The input and output parameters can be accessed as above only as a 'String'. The developer of the function has to change it to the
	 required data type
	 
	 Pt7. Output parameters are typically the variables which carry values to be used in the subsequent steps. Hence the values have to be stored
	 in the variables (Output parameters). This can be achieving as below
	 	
	 	FunctionLibrary.StoreValue(TestAttributes.OutputParameters[i], Val);
	 	
	 	Here the Val is also a String variable which takes the value to be stored in the corresponding output variable
	 
	 Pt8. If this function is a 'Not a verification function' , the developer must assign the actual result as appropriate for reporting purpose
	 	   e.g TestAttributes.ActualResult = "The contract created is selected from the 'Contracts' table
	
	 Pt9. If this function is a 'Verification function' the developer must assign both the status and actual result as appropriate in the logic again for reporting purpose
	 	   e.g 
	 	   
	 	   	if(TestAttributes.OutputParameters[0].equalsIgnoreCase(TestAttributes.OutputParameters[1])){
				TestAttributes.Status = "Passed";
				TestAttributes.ActualResult = "The contract value matches";
			}
			else{
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "The contract value does not match";
			}
			
	 Pt10. By default screen shot will be taken at the end of the keyword. In case if screen shot is not at all required for this
	 	   function, let the first line of code be 'TestAttributes.TakeScreenShotFlag = false;'
	 	  
	 Pt11. If more screenshots are required in between the logic of the function call 'FunctionLibrary.CaptureScreenShot();'
	  	   where ever required
	 
	 */
	
	
	public static void CALCULATE() {
		// This is an example function 
		// The name of the function should always be in upper case where as in the work flow data below it can be in any case
		try {
			
			//This is an example try and catch block for a 'not a verification function'.
			//Let us assume the work flow data is 'Calculate||20;;10||AddResult;;SubResult'
			
			TestAttributes.TakeScreenShotFlag = false;  // as no screen shot is required for this action
			
			int addvalue = Integer.parseInt(TestAttributes.InputParameters[0]) + Integer.parseInt(TestAttributes.InputParameters[1]);
			int subvalue = Integer.parseInt(TestAttributes.InputParameters[0]) - Integer.parseInt(TestAttributes.InputParameters[1]);
			
			FunctionLibrary.StoreValue(TestAttributes.OutputParameters[0], Integer.toString(addvalue));
			FunctionLibrary.StoreValue(TestAttributes.OutputParameters[1], Integer.toString(subvalue));
		
			TestAttributes.ActualResult = "The calculated values are stored successfully";
			
			/*Subsequent test steps can use
			 
			'AddResult @@ StoredValue' as work flow data to access the value 30 and
			'SubResult @@ StoredValue' as work flow data to access the value 10
			 Note that both the values are stored as string
			  
			 */
		} catch(Exception e) {
			TestAttributes.TakeScreenShotFlag = false; // as required
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while executing the function 'CALCULATE'.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
		
		//===========================================================================================================
		try {
			//This is an example try and catch block for a 'Verification function'.
			//Let us assume the work flow data is 'Calculate||PreviousNoOfContracts @@ StoredValue;;MaximumContracts @@ TestDataSet||'
				
			int previouscontracts = Integer.parseInt(TestAttributes.InputParameters[0]);
			int maximumcontracts = Integer.parseInt(TestAttributes.InputParameters[1]);
			
			int currentcontracts = Integer.parseInt(TestAttributes.element.getText().trim());    //Assuming an object is associated to the 'CallFunction' keyword
			
			int TotalContracts = previouscontracts + currentcontracts;
			
			if (TotalContracts < maximumcontracts) {
				TestAttributes.Status = "Failed";		
				TestAttributes.ActualResult = "The maximum limit is not reached";
	
			} else {
				TestAttributes.Status = "Passed";		
				TestAttributes.ActualResult = "The maximum limit is reached";
			}
			//By default a Screenshot will be captured at the end of the keyword.
		} catch(Exception e) {
			TestAttributes.TakeScreenShotFlag = true; // as required
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while executing the function 'CALCULATE'.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
		//If you note that the catch block remains the same for both
	}
}
