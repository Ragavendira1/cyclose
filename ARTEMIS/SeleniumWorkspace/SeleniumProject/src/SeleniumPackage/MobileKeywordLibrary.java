package SeleniumPackage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;

public class MobileKeywordLibrary {
	//========================================================================================================================================'
	public static void IF() throws SQLException{
		CommonKeywordLibrary.IF();
	}
				
	//========================================================================================================================================'
	public static void ELSE() {
		CommonKeywordLibrary.ELSE(); 
	}
				
	//========================================================================================================================================'
	public static void ENDIF() {
		CommonKeywordLibrary.ENDIF();
	}
	
	//========================================================================================================================================'
	public static void STARTLOOP() throws InvalidFormatException, IOException, XmlException, SQLException, InterruptedException{
		CommonKeywordLibrary.STARTLOOP();
	}
	
	//========================================================================================================================================'
	public static void ENDLOOP() {
		CommonKeywordLibrary.ENDLOOP();	
	}
	
	//========================================================================================================================================'
	public static void EXITLOOP() {
		CommonKeywordLibrary.EXITLOOP();
	}
	
	//========================================================================================================================================'
	public static void CALLTEST() throws InvalidFormatException, IOException, XmlException, SQLException, InterruptedException{
		CommonKeywordLibrary.CALLTEST();
	}
		
	//========================================================================================================================================'
	public static void CALLFUNCTION() {		
		CommonKeywordLibrary.CALLFUNCTION();
	}
	
	//========================================================================================================================================'
	public static void WAIT() {		
		CommonKeywordLibrary.WAIT();
	}
	
	//========================================================================================================================================'
	public static void LAUNCHAPP() {
		TestAttributes.TakeScreenShotFlag= true;
		try {
			TestAttributes.mobiledriver.client.applicationClose(TestAttributes.Data);
			TestAttributes.mobiledriver.client.launch(TestAttributes.Data, true, true);
			TestAttributes.ActualResult = "Application '" + TestAttributes.Data + "' is launched.";

		} catch (Exception e) {
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while launching the application '" + TestAttributes.Data + "'.";			
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}	
	}
				
	//========================================================================================================================================'
	
	public static void ISEXIST() {
		TestAttributes.TakeScreenShotFlag= true;

		try {
			Boolean FoundFlag = TestAttributes.mobiledriver.client.isElementFound(TestAttributes.ObjectType, TestAttributes.MobileLocator);
			
			if (FoundFlag && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is present in the screen '" + TestAttributes.Screen_Name + "'.";
			}
				
			if (!FoundFlag && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not present in the screen '" + TestAttributes.Screen_Name + "'.";
			}
			
			if (FoundFlag && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
				TestAttributes.Status = "Passed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is present in the screen '" + TestAttributes.Screen_Name + "'.";
			}
				
			if (!FoundFlag && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
				TestAttributes.Status = "Passed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not present in the screen '" + TestAttributes.Screen_Name + "'.";
			}					
		} catch(Exception e) {
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while verifying the existence of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
					
	//========================================================================================================================================'
	public static void ISENABLED() {
		TestAttributes.TakeScreenShotFlag= true;
		
		try {
			if (TestAttributes.mobiledriver.client.isElementFound(TestAttributes.ObjectType, TestAttributes.MobileLocator)) {
				String CurrentStatus = TestAttributes.mobiledriver.client.elementGetProperty(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, "enabled");
				
				if (CurrentStatus.equalsIgnoreCase("True") && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is enabled in the screen '" + TestAttributes.Screen_Name + "'.";
				}
					
				if (CurrentStatus.equalsIgnoreCase("False") && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not enabled in the screen '" + TestAttributes.Screen_Name + "'.";
				}
				
				if (CurrentStatus.equalsIgnoreCase("True") && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
					TestAttributes.Status = "Passed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is enabled in the screen '" + TestAttributes.Screen_Name + "'.";
				}
					
				if (CurrentStatus.equalsIgnoreCase("False") && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
					TestAttributes.Status = "Passed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not enabled in the screen '" + TestAttributes.Screen_Name + "'.";
				}	
			
			} else {
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not present in the screen '" + TestAttributes.Screen_Name + "'.";
			}

		} catch (Exception e) {
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while verifying the enabled status of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
					
	//========================================================================================================================================'
	public static void INPUT() {
		try {
			TestAttributes.TakeScreenShotFlag= false;
			String CurrentStatus = "";
			Boolean FoundFlag = TestAttributes.mobiledriver.client.isElementFound(TestAttributes.ObjectType, TestAttributes.MobileLocator);
			
			if (FoundFlag) {
				switch (TestAttributes.ElementType.toUpperCase().trim()) {
				case "TEXTBOX":	
					TestAttributes.mobiledriver.client.elementSendText(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, TestAttributes.Data);
					TestAttributes.ActualResult = "Value '" + TestAttributes.Data + "' is entered in the field '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";					
					break;
							
				case "BUTTON":
					TestAttributes.mobiledriver.client.click(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, 1);
					TestAttributes.ActualResult = "Button '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is clicked.";				
					break;
					
				case "RADIOBUTTON":
					CurrentStatus = TestAttributes.mobiledriver.client.elementGetProperty(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, "checked");
					
					if (CurrentStatus.equalsIgnoreCase("False")) {
						TestAttributes.mobiledriver.client.click(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, 1);
						TestAttributes.ActualResult = "Radio button '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is selected.";
						break;
					}
										
					if (CurrentStatus.equalsIgnoreCase("True")) {
						TestAttributes.ActualResult = "Radio button '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already selected.";
						break;
					}
					
				case "LINK":
					TestAttributes.mobiledriver.client.click(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, 1);
					TestAttributes.ActualResult = "Link '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is clicked.";
					break;
					
				case "IMAGE":
					TestAttributes.mobiledriver.client.click(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, 1);
					TestAttributes.ActualResult = "Image '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is clicked.";
					break;
					
				case "ELEMENT":
					TestAttributes.mobiledriver.client.click(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, 1);
					TestAttributes.ActualResult = "Element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is clicked.";
					break;
				
				case "LIST":
					TestAttributes.mobiledriver.client.elementListSelect(TestAttributes.MobileLocator, "text=" + TestAttributes.Data.trim(), 0, true);
					TestAttributes.ActualResult = "Value '" + TestAttributes.Data + "' is selected from the list '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
					break;
									
				case "CHECKBOX":
					CurrentStatus = TestAttributes.mobiledriver.client.elementGetProperty(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, "checked");
					
					if (CurrentStatus.equalsIgnoreCase("False") && TestAttributes.Data.trim().equalsIgnoreCase("ON")) {
						TestAttributes.mobiledriver.client.click(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, 1);
						TestAttributes.ActualResult = "Checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is checked.";
						break;
					}
					
					if (CurrentStatus.equalsIgnoreCase("True") && TestAttributes.Data.trim().equalsIgnoreCase("OFF")) {
						TestAttributes.mobiledriver.client.click(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, 1);
						TestAttributes.ActualResult = "Checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is unchecked.";
						break;
					}
										
					if (CurrentStatus.equalsIgnoreCase("True") && TestAttributes.Data.trim().equalsIgnoreCase("ON")) {
						TestAttributes.ActualResult = "Checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already checked.";
						break;
					}
					
					if (CurrentStatus.equalsIgnoreCase("False") && TestAttributes.Data.trim().equalsIgnoreCase("OFF")) {
						TestAttributes.ActualResult = "Checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already unchecked.";
						break;
					}
				}

			} else {
				TestAttributes.TakeScreenShotFlag= true;
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "No such object exists";
			}
			
		} catch (Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			
			switch (TestAttributes.ElementType.toUpperCase().trim()) {
			case "TEXTBOX":
				TestAttributes.ActualResult = "Error while entering the value '" + TestAttributes.Data + "' in the field '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "BUTTON":
				TestAttributes.ActualResult = "Error while clicking the button '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "RADIOBUTTON":
				TestAttributes.ActualResult = "Error while selecting/deselecting the radio button '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "LINK":
				TestAttributes.ActualResult = "Error while clicking the link '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "IMAGE":
				TestAttributes.ActualResult = "Error while clicking the image '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "ELEMENT":
				TestAttributes.ActualResult = "Error while clicking the element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "LIST":
				TestAttributes.ActualResult = "Error while selecting the value '" + TestAttributes.Data + "' from the list '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "CHECKBOX":
				TestAttributes.ActualResult = "Error while checking/unchecking the checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
								
			}
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}
				
	//========================================================================================================================================'

	//========================================================================================================================================'
	public static void VERIFYVALUE() {
		TestAttributes.TakeScreenShotFlag= true;
								
		try {
			String ActualValue = "";
			Boolean FoundFlag = TestAttributes.mobiledriver.client.isElementFound(TestAttributes.ObjectType, TestAttributes.MobileLocator);
			
			if (FoundFlag) {
				switch(TestAttributes.ElementType.toUpperCase().trim()){
				case "TEXTBOX":
				case "BUTTON":
					ActualValue = TestAttributes.mobiledriver.client.elementGetText(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0).trim();
					
					if (ActualValue.equalsIgnoreCase(TestAttributes.Data)) {
						TestAttributes.Status = "Passed";
						TestAttributes.ActualResult = "Expected Value '" + TestAttributes.Data + "' matches with the value in the application.";
					} else {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "Expected Value is '" + TestAttributes.Data + "'. But the Actual value is '" + ActualValue + "'."; 
					}
					break;
					
				case "LINK":				
				case "ELEMENT":
					ActualValue = TestAttributes.mobiledriver.client.elementGetProperty(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, "contentDescription");
					
					if (ActualValue.equalsIgnoreCase(TestAttributes.Data)) {
						TestAttributes.Status = "Passed";
						TestAttributes.ActualResult = "Expected Value '" + TestAttributes.Data + "' matches with the value in the application.";

					} else {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "Expected Value is '" + TestAttributes.Data + "'. But the Actual value is '" + ActualValue + "'."; 
					}
					break;
					
				case "RADIOBUTTON":
				case "CHECKBOX":
					String CurrentStatus = TestAttributes.mobiledriver.client.elementGetProperty(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, "checked");
					
					if (CurrentStatus.equalsIgnoreCase("True") && TestAttributes.Data.trim().equalsIgnoreCase("OFF")) {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is selected in the screen '" + TestAttributes.Screen_Name + "'.";
					}
						
					if (CurrentStatus.equalsIgnoreCase("False") && TestAttributes.Data.trim().equalsIgnoreCase("ON")) {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not selected in the screen '" + TestAttributes.Screen_Name + "'.";
					}
					
					if (CurrentStatus.equalsIgnoreCase("True") && TestAttributes.Data.trim().equalsIgnoreCase("ON")) {
						TestAttributes.Status = "Passed";
						TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is selected in the screen '" + TestAttributes.Screen_Name + "'.";
					}
						
					if (CurrentStatus.equalsIgnoreCase("False") && TestAttributes.Data.trim().equalsIgnoreCase("OFF")) {	
						TestAttributes.Status = "Passed";
						TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not selected in the screen '" + TestAttributes.Screen_Name + "'.";
					}	
					break;
				}

			} else {
				TestAttributes.TakeScreenShotFlag= true;
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "No such object exists";
			}

		} catch (Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			
			switch (TestAttributes.ElementType.toUpperCase().trim()) {
			case "TEXTBOX":
			case "BUTTON":
			case "LINK":
			case "LABEL":
			case "ELEMENT":
				TestAttributes.ActualResult = "Error while verifying the value '" + TestAttributes.Data + "' in the field '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "RADIOBUTTON":
			case "CHECKBOX":
				TestAttributes.ActualResult = "Error while verifying the selection status of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
								
			}
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}
				
	//========================================================================================================================================'
	public static void STOREVALUE() {
		TestAttributes.TakeScreenShotFlag = false;
								
		try {
			String[] DataSplit;
			
			String StoreVariable = TestAttributes.Data;
			String StoreValue = "";
				
			switch (TestAttributes.ElementType.toUpperCase().trim()) {
			case "TEXTBOX":
			case "BUTTON":
				StoreValue = TestAttributes.mobiledriver.client.elementGetText(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0).trim();
				break;
			
			case "ELEMENT":			
			case "LINK":
				StoreValue = TestAttributes.mobiledriver.client.elementGetProperty(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, "contentDescription");				
				break;
				
			case "RADIOBUTTON":
			case "CHECKBOX":
				String CurrentStatus = TestAttributes.mobiledriver.client.elementGetProperty(TestAttributes.ObjectType, TestAttributes.MobileLocator, 0, "checked");
				if (CurrentStatus.equalsIgnoreCase("True"))
					StoreValue = "ON";
				else
					StoreValue = "OFF";
				break;
				
			case "":
				StoreValue = "";
				DataSplit = TestAttributes.Data.split("\\|\\|");
				StoreVariable = DataSplit[1];
				
				String ValuesSplit[] = DataSplit[0].split(";;");
				
				for (int i = 0; i<ValuesSplit.length; i++) {
					String CurrentValue = ValuesSplit[i].trim();
					
					if (CurrentValue.contains("\""))
						StoreValue = StoreValue + CurrentValue.substring(1, CurrentValue.length()-1);
					else {
						TestAttributes.Keyword = "TEMP";
						StoreValue = StoreValue + FunctionLibrary.getActualData(CurrentValue);
						TestAttributes.Keyword = "STOREVALUE";
					}
				}
				break;
			}
			
			if (TestAttributes.Status.equalsIgnoreCase("Passed")) {
				FunctionLibrary.StoreValue(StoreVariable.trim(),StoreValue.trim());
				TestAttributes.ActualResult = "Text '" + StoreValue + "' is stored in the variable '" + StoreVariable + "'.";
			}
			
		} catch (Exception e) {
			TestAttributes.Keyword = "STOREVALUE";
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			
			switch (TestAttributes.ElementType.toUpperCase().trim()) {
			case "TEXTBOX":
			case "LABEL":
			case "ELEMENT":
			case "BUTTON":
			case "LINK":
				TestAttributes.ActualResult = "Error while retrieving the value from field '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "RADIOBUTTON":
			case "CHECKBOX":
				TestAttributes.ActualResult = "Error while retrieving the selection status of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
			}
			
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
				
	//========================================================================================================================================'
		
	//========================================================================================================================================'
	public static void CLOSEKEYBOARD() {
		TestAttributes.TakeScreenShotFlag= false;
				
		try {
			TestAttributes.mobiledriver.client.closeKeyboard();
			TestAttributes.ActualResult = "Keyboard is closed";

		} catch (Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while closing the Keyboard.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	
	//========================================================================================================================================'
	public static void SYNC() {
		TestAttributes.TakeScreenShotFlag= false;
				
		try {
			int TimeOut = Integer.parseInt(TestAttributes.Data);
			TimeOut = TimeOut * 1000;
			
			TestAttributes.mobiledriver.client.syncElements(0000, TimeOut);
			
			TestAttributes.ActualResult = "Application is synchronized";

		} catch (Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while synchronizing the application.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}		
}