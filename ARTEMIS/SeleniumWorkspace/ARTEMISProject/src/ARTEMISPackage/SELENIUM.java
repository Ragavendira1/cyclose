//
package ARTEMISPackage;

import java.io.IOException;
import java.sql.SQLException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.xpath.XPathResult;

import java.util.List;
import java.net.URISyntaxException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class SELENIUM {
	//========================================================================================================================================'
	public static void IF() throws SQLException {
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
	public static void STARTLOOP() throws InvalidFormatException, IOException, XmlException, SQLException, InterruptedException {
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
	public static void CALLTEST() throws InvalidFormatException, IOException, XmlException, SQLException, InterruptedException {
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
			FunctionLibrary.DriverSetup();
			TestAttributes.driver.get(TestAttributes.Data);	
			TestAttributes.ActualResult = "Application '" + TestAttributes.Data + "' is launched.";			
		} catch (Exception e) {			
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while launching the application '" + TestAttributes.Data + "'.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
		
	}				
	//========================================================================================================================================'	
	
	public static void VERIFYATTRIBUTE() {		
		TestAttributes.TakeScreenShotFlag= true;
		TestAttributes.KeywordType = "Verification";
		String Attribute = "";
		String AttributeExpectedValue = "";
		String AttributeActualValue = "";
		
		try{			
			String[] Data_Split = TestAttributes.Data.split("\\|\\|", -1);			
			Attribute = Data_Split[0].trim();			
			AttributeExpectedValue = Data_Split[1].trim();			
			AttributeActualValue = TestAttributes.element.getAttribute(Attribute).trim();
			
			if (AttributeActualValue.equalsIgnoreCase(AttributeExpectedValue)) {
				TestAttributes.Status = "Passed";
				TestAttributes.ActualResult = "Expected Value of the '" + Attribute + "' attribute of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' matches with the value in the application.";
			} else {
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "Expected Value of the '" + Attribute + "' attribute of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is '"  + AttributeExpectedValue + "'. But the actual value is '" + AttributeActualValue + "'."; 
			}		
		} catch (Exception e) {			
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while verifying the attribute '" + Attribute + "' of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'. ";
			if(TestAttributes.element==null)
				TestAttributes.ActualResult = TestAttributes.ActualResult + "Element not found. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}
	
	//========================================================================================================================================'
	
	public static void PAGEREFRESH() {
		TestAttributes.TakeScreenShotFlag= false;
		
		try {
			TestAttributes.driver.navigate().refresh();			
			TestAttributes.ActualResult = "Page is refreshed.";

		} catch(Exception e){	
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while refreshing the page.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	
	public static void MOUSEOVER() {
		TestAttributes.TakeScreenShotFlag= false;
		
		try {
			Actions action = new Actions(TestAttributes.driver);
			action.moveToElement(TestAttributes.element).build().perform();
			Thread.sleep(3000);
			TestAttributes.ActualResult = "Action mouse over is performed on the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'";

		} catch(Exception e){
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while performing the action mouse over on the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'. ";
			if(TestAttributes.element==null)
				TestAttributes.ActualResult = TestAttributes.ActualResult + "Element not found. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	
	public static void SWITCHTOLATESTWINDOW() {
		TestAttributes.TakeScreenShotFlag= true;
		
		try {
			Thread.sleep(5000);
			for(String winHandle : TestAttributes.driver.getWindowHandles()){
			    TestAttributes.driver.switchTo().window(winHandle);			    
			}			
			TestAttributes.ActualResult = "The script has switched to the latest window";
		} catch(Exception e){
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while switching to the latest window";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
		
	}
	//========================================================================================================================================'
	
	public static void SWITCHTOFRAME() {
		TestAttributes.TakeScreenShotFlag= false;
		
		try {
			    TestAttributes.driver.switchTo().frame(TestAttributes.element);
			    TestAttributes.ActualResult = "The script has switched to the frame";		

		} catch(Exception e){
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while switching to the frame. ";
			if(TestAttributes.element==null)
				TestAttributes.ActualResult = TestAttributes.ActualResult + "Element not found. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	
	public static void CLOSECURRENTWINDOW() {
		TestAttributes.TakeScreenShotFlag= false;
		
		try {			
			TestAttributes.driver.close();			
			TestAttributes.ActualResult = "The script has closed the current window";			
			for(String winHandle : TestAttributes.driver.getWindowHandles()){
			    TestAttributes.driver.switchTo().window(winHandle);
			}
		} catch(Exception e){
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while closing the current window";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	
	public static void WAITFORELEMENT() {
		
		TestAttributes.TakeScreenShotFlag= false;
		try {
			
			int counter = 0;
					
			while(TestAttributes.element==null && counter <= Integer.parseInt(TestAttributes.Data)){
				FunctionLibrary.getElement();
				counter = counter + 1;
			}
			
			if(TestAttributes.element==null){
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "The script has made '" + TestAttributes.Data + "' attempts to find the element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'. But the element is not found";
			}
			else
				TestAttributes.ActualResult = "The script has waited until the element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is found";
			

		} catch(Exception e){
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while waiting for the element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'. ";
			if(TestAttributes.element==null)
				TestAttributes.ActualResult = TestAttributes.ActualResult + "Element not found. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	
	public static void ISEXIST() {
		TestAttributes.TakeScreenShotFlag= true;
		TestAttributes.KeywordType = "Verification";

		try {
			if (TestAttributes.element != null && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is present in the screen '" + TestAttributes.Screen_Name + "'.";
			}
				
			if (TestAttributes.element == null && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not present in the screen '" + TestAttributes.Screen_Name + "'.";
			}
			
			if (TestAttributes.element != null && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
				TestAttributes.Status = "Passed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is present in the screen '" + TestAttributes.Screen_Name + "'.";
			}
				
			if (TestAttributes.element == null && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
				TestAttributes.Status = "Passed";
				TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not present in the screen '" + TestAttributes.Screen_Name + "'.";
			}					
		} catch (Exception e) {
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while verifying the existence of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
						
	//========================================================================================================================================'
	public static void ISENABLED() {
		TestAttributes.TakeScreenShotFlag= true;
		TestAttributes.KeywordType = "Verification";
		
			
		try {						
				if (TestAttributes.element.isEnabled() == true && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is enabled in the screen '" + TestAttributes.Screen_Name + "'.";
				}
					
				if (TestAttributes.element.isEnabled() == false && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not enabled in the screen '" + TestAttributes.Screen_Name + "'.";
				}
				
				if (TestAttributes.element.isEnabled() == true && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
					TestAttributes.Status = "Passed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is enabled in the screen '" + TestAttributes.Screen_Name + "'.";
				}
					
				if (TestAttributes.element.isEnabled() == false && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
					TestAttributes.Status = "Passed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not enabled in the screen '" + TestAttributes.Screen_Name + "'.";
				}			
			

		} catch (Exception e) {
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while verifying the enabled status of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'. ";
			if(TestAttributes.element==null)
				TestAttributes.ActualResult = TestAttributes.ActualResult + "Element not found. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	
	public static void ISDISPLAYED() {
		TestAttributes.TakeScreenShotFlag= true;
		TestAttributes.KeywordType = "Verification";
		
		try {
						
				if (TestAttributes.element.isDisplayed() == true && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is displayed in the screen '" + TestAttributes.Screen_Name + "'.";
				}
					
				if (TestAttributes.element.isDisplayed() == false && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not displayed in the screen '" + TestAttributes.Screen_Name + "'.";
				}
				
				if (TestAttributes.element.isDisplayed() == true && TestAttributes.Data.trim().equalsIgnoreCase("Yes")) {
					TestAttributes.Status = "Passed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is displayed in the screen '" + TestAttributes.Screen_Name + "'.";
				}
					
				if (TestAttributes.element.isDisplayed() == false && TestAttributes.Data.trim().equalsIgnoreCase("No")) {
					TestAttributes.Status = "Passed";
					TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not displayed in the screen '" + TestAttributes.Screen_Name + "'.";
				}			

			

		} catch (Exception e) {
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while verifying the visibility status of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'. ";
			if(TestAttributes.element==null)
				TestAttributes.ActualResult = TestAttributes.ActualResult + "Element not found. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'

	public static void INPUT() {
		
		TestAttributes.TakeScreenShotFlag = false;
					
		try {
			
			((JavascriptExecutor)TestAttributes.driver).executeScript("arguments[0].scrollIntoView();", TestAttributes.element);
			
			switch (TestAttributes.ElementType.toUpperCase().trim()) {
				
				case "TEXTBOX":
					TestAttributes.element.clear();
					TestAttributes.element.sendKeys(TestAttributes.Data);
					TestAttributes.ActualResult = "Value '" + TestAttributes.Data + "' is entered in the field '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
					break;
				
				case "LINK":	
				case "BUTTON":
				case "IMAGE":
				case "ELEMENT":
					TestAttributes.element.click();
					TestAttributes.ActualResult = TestAttributes.ElementType + " '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is clicked.";
					break;
					
				case "RADIOBUTTON":
					if (TestAttributes.element.isSelected() == false) {
						TestAttributes.element.click();
						TestAttributes.ActualResult = "Radio button '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is selected.";
					}
									
					if (TestAttributes.element.isSelected() == true) {
						TestAttributes.ActualResult = "Radio button '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already selected.";
					}
					break;					
						
				case "LIST":
					Select select = new Select(TestAttributes.element);
					String actualdata = "";
					
					boolean isNumeric = FunctionLibrary.isNumeric(TestAttributes.Data);
					
					if (isNumeric == true) {
						select.selectByIndex(Integer.parseInt(TestAttributes.Data)-1);
						TestAttributes.ActualResult = "Value in the index '" + TestAttributes.Data + "' is selected from the list '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";

					} else {
						if (TestAttributes.Data.contains("\""))
							 actualdata = TestAttributes.Data.substring(1,TestAttributes.Data.length()-1);
						else
							 actualdata = TestAttributes.Data;
								
						boolean found = false;
							
						List<WebElement> items = select.getOptions();
						int currentindex = 0;
							
						for (WebElement element: items) {
					        String currenttext = element.getText().trim();
					        if (currenttext.equalsIgnoreCase(actualdata)) {
					        	select.selectByIndex(currentindex);
					        	found = true;
					        	break;
					        }
						        
						    currentindex++;
						} 
							
						if (found == true)
							TestAttributes.ActualResult = "Value '" + actualdata + "' is selected from the list '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
						else {
							TestAttributes.ActualResult = "Value '" + actualdata + "' is not present in the list '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
							TestAttributes.Status = "Error";
							TestAttributes.TakeScreenShotFlag = true;
						}
					}
					break;
						
					case "CHECKBOX":
						if (TestAttributes.element.isSelected() == false && TestAttributes.Data.trim().equalsIgnoreCase("ON")) {
							TestAttributes.element.click();
							TestAttributes.ActualResult = "Checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is checked.";
							break;
						}
						
						if (TestAttributes.element.isSelected() == true && TestAttributes.Data.trim().equalsIgnoreCase("OFF")) {
							TestAttributes.element.click();
							TestAttributes.ActualResult = "Checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is unchecked.";
							break;
						}
											
						if (TestAttributes.element.isSelected() == true && TestAttributes.Data.trim().equalsIgnoreCase("ON")) {
							TestAttributes.ActualResult = "Checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already checked.";
							break;
						}
						
						if (TestAttributes.element.isSelected() == false && TestAttributes.Data.trim().equalsIgnoreCase("OFF")) {
							TestAttributes.ActualResult = "Checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already unchecked.";
							break;
						}
						
					case "TABLE":
						int row = 0;
						int InputColumn = 0;
						String InputObjectType = "";
						String InputValue = "";
						
						String[] DataSplit = TestAttributes.Data.split("\\|\\|");
						
						if (DataSplit.length == 2) {
							row = SearchTable();						
							String[] IPValuesCase1 = DataSplit[1].split("##");						
							InputColumn = Integer.parseInt(IPValuesCase1[0].trim());
							InputObjectType = IPValuesCase1[1].trim();
							InputValue = IPValuesCase1[2].trim();

						} else {
							String[] IPValuesCase2 = TestAttributes.Data.split("##");							
							row = Integer.parseInt(IPValuesCase2[0].trim());
							InputColumn = Integer.parseInt(IPValuesCase2[1].trim());
							InputObjectType = IPValuesCase2[2].trim();
							InputValue = IPValuesCase2[3].trim();						
						}
									
						if (row != 0) {
							String ChildElementLocation = TestAttributes.LocatorValue + "/tbody/tr[" + row + "]/td[" + InputColumn + "]";
							boolean done = ChildActions(ChildElementLocation,InputObjectType);
							
							if (done == true) {
								switch(InputObjectType.toUpperCase()) {
								
								case "LINK":	
								case "BUTTON":
								case "IMAGE":
								case "ELEMENT":
									TestAttributes.childelement.click();
									TestAttributes.ActualResult = TestAttributes.ElementType + " in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is clicked.";
									break;
								
								case ("RADIOBUTTON"):
									if (TestAttributes.childelement.isSelected() == false){
										TestAttributes.childelement.click();
										TestAttributes.ActualResult = "RadioButton in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is selected.";
										break;
									}
														
									if (TestAttributes.childelement.isSelected() == true){
										TestAttributes.ActualResult = "RadioButton in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already selected.";
										break;
									}
												
								case ("CHECKBOX"):
									if (TestAttributes.childelement.isSelected() == false && InputValue.trim().equalsIgnoreCase("ON")){
										TestAttributes.childelement.click();
										TestAttributes.ActualResult = "CheckBox in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is checked.";
										break;
									}
									
									if(TestAttributes.childelement.isSelected() == true && InputValue.trim().equalsIgnoreCase("OFF")){
										TestAttributes.childelement.click();
										TestAttributes.ActualResult = "CheckBox in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is unchecked.";
										break;
									}
														
									if(TestAttributes.childelement.isSelected() == true && InputValue.trim().equalsIgnoreCase("ON")){
										TestAttributes.ActualResult = "CheckBox in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already checked.";
										break;
									}
									
									if(TestAttributes.childelement.isSelected() == false && InputValue.trim().equalsIgnoreCase("OFF")){
										TestAttributes.ActualResult = "CheckBox in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is already unchecked.";
										break;
									}
																
								case ("TEXTBOX"):
									TestAttributes.childelement.clear();
									TestAttributes.childelement.sendKeys(InputValue);						
									TestAttributes.ActualResult = "Value '" + InputValue  + "' is entered in the TextBox present in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
									break;
									
								case ("LIST"):
									Select sel = new Select(TestAttributes.childelement);
									String actdata = "";
									
									boolean isNbr = FunctionLibrary.isNumeric(InputValue);
									
									if (isNbr == true) {
										sel.selectByIndex(Integer.parseInt(InputValue)-1);									
										TestAttributes.ActualResult = "Value in the index '" + InputValue + "' is selected from the List present in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
									} else {
										if (InputValue.contains("\""))
											actdata = InputValue.substring(1, InputValue.length() - 1);
										else
											actdata = InputValue;
												
										boolean found = false;
											
										List<WebElement> items = sel.getOptions();
										int currentindex = 0;
											
										for (WebElement element: items) {
									        String currenttext = element.getText().trim();
									        if (currenttext.equalsIgnoreCase(actdata)) {
									        	sel.selectByIndex(currentindex);
									        	found = true;
									        	break;
									        }
										        
										    currentindex++;
										} 
											
										if (found == true)										
											TestAttributes.ActualResult = "Value '" + actdata + "' is selected from the List present in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
										else {
											TestAttributes.ActualResult = "Value '" + actdata + "' is not present in the List present in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
											TestAttributes.Status = "Error";
											TestAttributes.TakeScreenShotFlag = true;
										}
									}
									break;
								}

							} else {
								TestAttributes.TakeScreenShotFlag= true;
								TestAttributes.Status = "Error";
								TestAttributes.ActualResult = "No " + InputObjectType + " is present in the column '" + InputColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
								TestAttributes.ActualResult = TestAttributes.ActualResult + " Or Error while finding the input object in the table.";
							}

						} else {
							TestAttributes.TakeScreenShotFlag= true;
							TestAttributes.Status = "Error";
							TestAttributes.ActualResult = "The search record is not found in the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
						}
						break;
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
				TestAttributes.ActualResult = "Error while selecting the value/index '" + TestAttributes.Data + "' from the list '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "CHECKBOX":
				TestAttributes.ActualResult = "Error while checking/unchecking the checkbox '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "TABLE":
				TestAttributes.ActualResult = "Error while searching / performing an action in the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
			}
			
			if(TestAttributes.element==null)
				TestAttributes.ActualResult = TestAttributes.ActualResult + "Element not found. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}
	
	//========================================================================================================================================'
	public static void VERIFYVALUE() {
		
		TestAttributes.TakeScreenShotFlag= true;
		TestAttributes.KeywordType = "Verification";
		String ActualValue = "";
		String WhatToVerify = "";
							
		try {
			
			switch(TestAttributes.ElementType.toUpperCase().trim()) {
				
				case "PAGE":
					
					String[] DataSpt = TestAttributes.Data.split("\\|\\|");
					WhatToVerify = DataSpt[0].trim().toUpperCase();					
					String Data = DataSpt[1].trim();
					Thread.sleep(2000);
					
					if(WhatToVerify.equalsIgnoreCase("URL") || WhatToVerify.equalsIgnoreCase("PARTIALURL"))
						ActualValue = TestAttributes.driver.getCurrentUrl().trim();
					if(WhatToVerify.equalsIgnoreCase("TITLE"))
						ActualValue = TestAttributes.driver.getTitle().trim();
					
					if(!WhatToVerify.equalsIgnoreCase("PARTIALURL"))
						if (ActualValue.equalsIgnoreCase(Data)) {
							TestAttributes.Status = "Passed";
							TestAttributes.ActualResult = "Expected " + WhatToVerify + " '" + Data + "' matches with the " + WhatToVerify + " of the application current page.";
						} else {
							TestAttributes.Status = "Failed";
							TestAttributes.ActualResult = "Expected " + WhatToVerify + " is '" + Data + "'. But the Actual " + WhatToVerify + " is '" + ActualValue + "'."; 
						}
					else
						
						if (ActualValue.contains(Data)) {
							TestAttributes.Status = "Passed";
							TestAttributes.ActualResult = "Expected " + WhatToVerify + " '" + Data + "' is present with the " + "URL '" +  ActualValue + "' of the application current page.";
						} else {
							TestAttributes.Status = "Failed";
							TestAttributes.ActualResult = "Expected " + WhatToVerify + " '" + Data + "' is not present with the " + "URL '" +  ActualValue + "' of the application current page."; 
						}
												
					break;
				
				case "TEXTBOX":
				case "ELEMENT":			
				case "LINK":
				case "BUTTON":
				case "LIST":
					if(TestAttributes.ElementType.trim().equalsIgnoreCase("TEXTBOX"))
						ActualValue = TestAttributes.element.getAttribute("value").trim();
					else{
						
						if(TestAttributes.ElementType.trim().equalsIgnoreCase("LIST")){
							Select select = new Select(TestAttributes.element);
							ActualValue = select.getFirstSelectedOption().getText().trim();						
						}	
						else
							Thread.sleep(1000);
							ActualValue = TestAttributes.element.getText().trim();
					}
					
					//---------------------------------------------------------------------------------------
					String ExpectedValue = null;
					if (TestAttributes.Data.contains("\""))
						ExpectedValue = TestAttributes.Data.substring(1,TestAttributes.Data.length()-1);
					else
						ExpectedValue = TestAttributes.Data;
					//---------------------------------------------------------------------------------------
															
					if (ActualValue.equalsIgnoreCase(ExpectedValue)) {
						TestAttributes.Status = "Passed";
						TestAttributes.ActualResult = "Expected Value '" + ExpectedValue + "' matches with the value in the application.";
					} else {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "Expected Value is '" + ExpectedValue + "'. But the Actual value is '" + ActualValue + "'."; 
					}
					break;
					
				case "RADIOBUTTON":
				case "CHECKBOX":
					if (TestAttributes.element.isSelected() == true && TestAttributes.Data.trim().equalsIgnoreCase("OFF")) {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is selected in the screen '" + TestAttributes.Screen_Name + "'.";
					}
						
					if (TestAttributes.element.isSelected() == false && TestAttributes.Data.trim().equalsIgnoreCase("ON")) {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not selected in the screen '" + TestAttributes.Screen_Name + "'.";
					}
					
					if (TestAttributes.element.isSelected() == true && TestAttributes.Data.trim().equalsIgnoreCase("ON")) {
						TestAttributes.Status = "Passed";
						TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is selected in the screen '" + TestAttributes.Screen_Name + "'.";
					}
						
					if(TestAttributes.element.isSelected() == false && TestAttributes.Data.trim().equalsIgnoreCase("OFF")){
						TestAttributes.Status = "Passed";
						TestAttributes.ActualResult = "The field '" + TestAttributes.Field_Name + "' is not selected in the screen '" + TestAttributes.Screen_Name + "'.";
					}	
					break;
					
				case "TABLE":
					int row = 0;
					int VerifyColumn = 0;
					String VerifyObjectType = "";
					String VerifyOperator = "";
					String VerifyValue = "";
					
					String[] DataSplit = TestAttributes.Data.split("\\|\\|");
					
					if (DataSplit.length == 2) {
						row = SearchTable();					
						String[] IPValuesCase1 = DataSplit[1].split("##");					
						VerifyColumn = Integer.parseInt(IPValuesCase1[0].trim());
						VerifyObjectType = IPValuesCase1[1].trim();
						VerifyOperator = IPValuesCase1[2].trim();
						VerifyValue = IPValuesCase1[3].trim();
				
					} else {
						String[] IPValuesCase2 = TestAttributes.Data.split("##");						
						row = Integer.parseInt(IPValuesCase2[0].trim());						
						VerifyColumn = Integer.parseInt(IPValuesCase2[1].trim());
						VerifyObjectType = IPValuesCase2[2].trim();
						VerifyOperator = IPValuesCase2[3].trim();
						VerifyValue = IPValuesCase2[4].trim();			
					}
								
					if (row != 0) {
						String ChildElementLocation = TestAttributes.LocatorValue + "/tbody/tr[" + row + "]/td[" + VerifyColumn + "]";
						boolean done = ChildActions(ChildElementLocation,VerifyObjectType);
						
						if (done == true) {
						
							///**********************************************************************\\\
							ActualValue = "";
						
							switch(VerifyObjectType.toUpperCase()){
							
								case "TEXTBOX":
								case "ELEMENT":			
								case "LINK":
								case "BUTTON":
								case "LIST":
									if(VerifyObjectType.trim().equalsIgnoreCase("TEXTBOX"))
										ActualValue = TestAttributes.childelement.getAttribute("value").trim();
									else{
										if(VerifyObjectType.trim().equalsIgnoreCase("LIST")){
											Select select = new Select(TestAttributes.childelement);
											ActualValue = select.getFirstSelectedOption().getText().trim();						
										}	
										else
											ActualValue = TestAttributes.childelement.getText().trim();
									}
								break;
								
								case "RADIOBUTTON":
								case "CHECKBOX":
									if (TestAttributes.childelement.isSelected() == true)
										ActualValue = "ON";
									else
										ActualValue = "OFF";
										
									break;
							
							}
							
						
							///**********************************************************************\\\
						
							///**********************************************************************\\\
						
							switch (VerifyOperator.toUpperCase().trim()) {
							case "=":
								if(ActualValue.trim().equalsIgnoreCase(VerifyValue))
									TestAttributes.Status = "Passed";
								else
									TestAttributes.Status = "Failed";
								break;
							
							case "CONTAINS":
								if(ActualValue.trim().toUpperCase().contains(VerifyValue.toUpperCase()))
									TestAttributes.Status = "Passed";
								else
									TestAttributes.Status = "Failed";
								break;
							}
						
							///**********************************************************************\\\
									
							if(TestAttributes.Status.equalsIgnoreCase("Failed"))
								TestAttributes.ActualResult = "Expected Value in the column '" + VerifyColumn + "' and row '" + row + "' of the table is '" + VerifyValue + "'. But the Actual value is '" + ActualValue + "'.";
							else
								TestAttributes.ActualResult = "Expected Value '" + VerifyValue + "' in the column '" + VerifyColumn + "' and row '" + row + "' of the table matches with the value in the applicaiton";
						
						} else {
							
							TestAttributes.TakeScreenShotFlag= true;
							TestAttributes.Status = "Error";
							TestAttributes.ActualResult = "No " + VerifyObjectType + " is present in the column '" + VerifyColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'. ";
							TestAttributes.ActualResult = TestAttributes.ActualResult + "Or Error while finding the verify object in the table.";							
						}
					
					} else {						
						TestAttributes.TakeScreenShotFlag= true;
						TestAttributes.Status = "Error";
						TestAttributes.ActualResult = "The search record is not found in the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
					}
					break;
				}
				
			
			
		} catch (Exception e) {			
			TestAttributes.Status = "Error";
			
			switch (TestAttributes.ElementType.toUpperCase().trim()) {
			case "LIST":
			case "TEXTBOX":			
			case "ELEMENT":
			case "BUTTON":
			case "LINK":
				TestAttributes.ActualResult = "Error while verifying the value '" + TestAttributes.Data + "' in the field '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "RADIOBUTTON":
			case "CHECKBOX":
				TestAttributes.ActualResult = "Error while verifying the selection status of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
				
			case "PAGE":
				//TestAttributes.ActualResult = "Error while verifying the selection status of the field '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				//WhatToVerify
				break;
				
			case "TABLE":
				TestAttributes.ActualResult = "Error while searching / verifying the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
								
			}
			
			if(TestAttributes.element==null)
				TestAttributes.ActualResult = TestAttributes.ActualResult + "Element not found. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}
	
	//========================================================================================================================================'
	public static void STOREVALUE() {
		TestAttributes.TakeScreenShotFlag = false;
			//element verification					
		try {
			String[] DataSplit;
			String StoreVariable = TestAttributes.Data;
			String StoreValue = "";
				
			switch (TestAttributes.ElementType.toUpperCase().trim()) {
			case "TEXTBOX":
			case "BUTTON":
				StoreValue = TestAttributes.element.getAttribute("value");
				break;
			
			case "ELEMENT":			
			case "LINK":
				StoreValue = TestAttributes.element.getText().trim();				
				break;
				
			case "RADIOBUTTON":
			case "CHECKBOX":
				if (TestAttributes.element.isSelected() == true)
					StoreValue = "ON";
				else
					StoreValue = "OFF";
				break;
				
			case "LIST":
				Select select = new Select(TestAttributes.element);
				StoreValue = select.getFirstSelectedOption().getText().trim();				
				break;
				
			case "TABLE":
				int row = 0;
				int StoreColumn = 0;
				String StoreObjectType = "";
				
				DataSplit = TestAttributes.Data.split("\\|\\|");
				
				if (DataSplit.length == 2) {
					TestAttributes.Keyword = "TEMP";
					row = SearchTable();
					TestAttributes.Keyword = "STOREVALUE";
				
					String[] StoreValuesCase1 = DataSplit[1].split("##");
				
					StoreColumn = Integer.parseInt(StoreValuesCase1[0].trim());
					StoreObjectType = StoreValuesCase1[1].trim();
					StoreVariable = StoreValuesCase1[2].trim();

				} else {
					String[] StoreValuesCase2 = TestAttributes.Data.split("##");
					
					row = Integer.parseInt(StoreValuesCase2[0].trim());
					StoreColumn = Integer.parseInt(StoreValuesCase2[1].trim());
					StoreObjectType = StoreValuesCase2[2].trim();
					StoreVariable = StoreValuesCase2[3].trim();
				}
							
				if (row != 0) {
					String ChildElementLocation = TestAttributes.LocatorValue + "/tbody/tr[" + row + "]/td[" + StoreColumn + "]";
					boolean done = ChildActions(ChildElementLocation,StoreObjectType);
					
					if (done == true) {
						switch(StoreObjectType.toUpperCase()) {
						case("LINK"):
						case("LABEL"):						
							StoreValue = TestAttributes.childelement.getText().trim();
							break;
						}
					
					} else {
						TestAttributes.TakeScreenShotFlag= true;
						TestAttributes.Status = "Error";
						
						switch(StoreObjectType.toUpperCase()) {
						case("LINK"):
							TestAttributes.ActualResult = "No Link in the column '" + StoreColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
							break;
							
						case("BUTTON"):
							TestAttributes.ActualResult = "No Button in the column '" + StoreColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
							break;
							
						case("RADIOBUTTON"):
							TestAttributes.ActualResult = "No RadioButton in the column '" + StoreColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
							break;
							
						case("CHECKBOX"):							
							TestAttributes.ActualResult = "No CheckBox in the column '" + StoreColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
							break;
							
						case("TEXTBOX"):
							TestAttributes.ActualResult = "No TextBox in the column '" + StoreColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
							break;
							
						case("LIST"):
							TestAttributes.ActualResult = "No List in the column '" + StoreColumn + "' and row '" + row + "' of the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";				 
							break;
						}
						
						TestAttributes.ActualResult = TestAttributes.ActualResult + " Or Error while finding the object in the table.";
					} 
				} else {
					TestAttributes.TakeScreenShotFlag= true;
					TestAttributes.Status = "Error";
					TestAttributes.ActualResult = "The search record is not found in the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";				 
				}
				break;
				
			case "":
				StoreValue = "";
				DataSplit = TestAttributes.Data.split("\\|\\|");
				StoreVariable = DataSplit[1];
				
				String ValuesSplit[] = DataSplit[0].split(";;");
				
				for(int i = 0; i < ValuesSplit.length; i++) {
					String CurrentValue = ValuesSplit[i].trim();
					
					if (CurrentValue.contains("\""))
						StoreValue = StoreValue + CurrentValue.substring(1,CurrentValue.length()-1);
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
			
			switch(TestAttributes.ElementType.toUpperCase().trim()){
			case "LIST":
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
				
			case "TABLE":
				TestAttributes.ActualResult = "Error while searching / accessing the table '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				break;
			}
			
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + "."; 
		}
	}
	
	//========================================================================================================================================'
	public static void ISDATAEXIST() {
		TestAttributes.TakeScreenShotFlag = true;
		TestAttributes.KeywordType = "Verification";
		
		try {
			if (TestAttributes.element != null) {
				switch(TestAttributes.ElementType.toUpperCase().trim()) {
				
				case "TABLE":
					int row = 0;
					row = SearchTable();
					
					String[] DataSplit = TestAttributes.Data.split("##");
					
					TestAttributes.ElementType = "TEMP";
					String ValueToSearch = FunctionLibrary.getActualData(DataSplit[3].trim());					
					TestAttributes.ElementType = "TABLE";
					
					String column = DataSplit[0].trim();
					
					if (row != 0) {
						TestAttributes.ActualResult = "Value '" + ValueToSearch + "' is present in the row '" + row + "' and column '" + column + "' of the tabe '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
					} else {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "Value '" + ValueToSearch + "' is not present in the column '" + column + "' of the tabe '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
					}
					break;
					
				case "LIST":
					Select select = new Select(TestAttributes.element);
					
					boolean found = false;
					
					List<WebElement> items = select.getOptions();
					
					for (WebElement element: items) {
				        String currenttext = element.getText().trim();
				        if (currenttext.equalsIgnoreCase(TestAttributes.Data)) {				        	
				        	found = true;
				        	break;
				        }
					}
										
					if (found == true) {
						TestAttributes.ActualResult = "Value '" + TestAttributes.Data + "' is present in the list '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
					} else {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "Value '" + TestAttributes.Data + "' is not present in the list '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
					}
					break;
				}

			} else {
				TestAttributes.TakeScreenShotFlag= true;
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "No such object exists";
			}

		} catch (Exception e) {
			TestAttributes.TakeScreenShotFlag= true;
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while exiting the loop.";
			TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
		}
	}

	//The below are not keywords
	//========================================================================================================================================'
	public static boolean ChildActions(String ChildElementLocation, String ObjectType) {
			
		try {
			
			TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(ChildElementLocation))));
			String ChildElementLocationToFind;
			boolean done = false;
			
			switch (ObjectType.trim().toUpperCase()) {
			
			case "ELEMENT":
				TestAttributes.childelement = TestAttributes.driver.findElement(By.xpath(ChildElementLocation));		
				done= true;					
				break;

			case "LINK":
				if (!done) {
					ChildElementLocationToFind = ChildElementLocation + "/*/a";
					TestAttributes.childelement = TestAttributes.driver.findElement(By.xpath(ChildElementLocationToFind));
					done= true;
				}
					
				if (!done) {
					ChildElementLocationToFind = ChildElementLocation + "/a";
					TestAttributes.childelement = TestAttributes.driver.findElement(By.xpath(ChildElementLocationToFind));
					done= true;
				}
				break;
				
			case "BUTTON":
			case "RADIOBUTTON":
			case "CHECKBOX":
			case "TEXTBOX":
				ChildElementLocationToFind = ChildElementLocation + "/input";
				TestAttributes.childelement = TestAttributes.driver.findElement(By.xpath(ChildElementLocationToFind));
				done= true;
				break;
			
			case "IMAGE":	
				
				ChildElementLocationToFind = ChildElementLocation + "/img";
				TestAttributes.childelement = TestAttributes.driver.findElement(By.xpath(ChildElementLocationToFind));
				done= true;
				break;
				
			case "LIST":	
				
				ChildElementLocationToFind = ChildElementLocation + "/select";
				TestAttributes.childelement = TestAttributes.driver.findElement(By.xpath(ChildElementLocationToFind));
				done= true;
				break;
			}
			
			return done;
		} 
		catch (Exception e) {
			return false;
		}
	}
	
	//========================================================================================================================================'
	public static int SearchTable() throws ClassNotFoundException, SQLException, URISyntaxException {
		
		String[] DataSplit = TestAttributes.Data.split("\\|\\|");
		String SearchValues = DataSplit[0];
		String[] SearchValuesSplit = SearchValues.split(";;");
		int RowCount = TestAttributes.driver.findElements(By.xpath(TestAttributes.LocatorValue + "/tbody/tr")).size();
		boolean RowFound = false;
		int i;
		
		for (i = 1; i <= RowCount; i++) {
			for (int j = 0; j < SearchValuesSplit.length; j++) {
				String[] CurrentSearch = SearchValuesSplit[j].split("##");
				
				int CurrentLookupColumn = Integer.parseInt(CurrentSearch[0].trim());
				String CurrentObjectType = CurrentSearch[1].trim();
				String CurrentOperator = CurrentSearch[2].trim();
				String CurrentLookupValue = CurrentSearch[3].trim();
				
				String ChildElementLocation = TestAttributes.LocatorValue + "/tbody/tr[" + i + "]/td[" + CurrentLookupColumn + "]";
				boolean done = ChildActions(ChildElementLocation,CurrentObjectType);
								
				///**********************************************************************\\\
				String ActualValue = "";	
				if (done == true) {
					switch (CurrentObjectType.toUpperCase()) {
					
					case "ELEMENT":
					case "LINK":					
						ActualValue = TestAttributes.childelement.getText().trim();						
						break;
						
					case "TEXTBOX":
					case "BUTTON":
						ActualValue = TestAttributes.childelement.getAttribute("value").trim();
						break;
						
					case "LIST":
						Select select = new Select(TestAttributes.childelement);
						ActualValue = select.getFirstSelectedOption().getText().trim();
						break;
						
					case "CHECKBOX":
					case "RADIOBUTTON":
						if(TestAttributes.childelement.isSelected() == true)
							ActualValue = "ON";
						else
							ActualValue = "OFF";
						break;
						
					//case "IMAGE":
						//break;
					}
				}
				else
					break;				
				///**********************************************************************\\\
				ActualValue = ActualValue.trim();
				
				switch (CurrentOperator.toUpperCase()) {
				case "=":						
					if(ActualValue.equalsIgnoreCase(CurrentLookupValue))
						RowFound = true;
					else
						RowFound = false;
					break;
					
				case "CONTAINS":						
					if (ActualValue.toUpperCase().contains(CurrentLookupValue.toUpperCase()))
						RowFound = true;
					else
						RowFound = false;
					break;
				}
				
				///**********************************************************************\\\
				if (RowFound == false)
					break;
			}
			
			if (RowFound == true)					
				break;					
		}
		
		if (RowFound == true)
			return i;
		else
			return 0;
	}
	
	
	//==================Alexander Forbes Functions =========================//
	
		public static void VERIFYARTICLES() throws InvalidFormatException, IOException, XmlException{
			
			TestAttributes.TakeScreenShotFlag = true;
			
			try{			
			
				List<WebElement> childs = TestAttributes.driver.findElements(By.xpath("html/body/div[1]/section/div[2]/div[2]/div[1]/div/div"));
				int numberofarticles = childs.size();
						
				for(int i = 2;i<=numberofarticles;i++){
					
					String currentarticlexpath = "html/body/div[1]/section/div[2]/div[2]/div[1]/div/div[" + i + "]/div";
					TestAttributes.childelement = null;
					
					try{
					
					TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentarticlexpath))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentarticlexpath))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentarticlexpath + "/div"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentarticlexpath + "/div"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentarticlexpath + "/div/h2"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentarticlexpath + "/div/h2"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentarticlexpath + "/div/span"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentarticlexpath + "/div/span"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentarticlexpath + "/a"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentarticlexpath + "/a"))));
		        	
		        	TestAttributes.ActualResult = "All articles found";
		        	
					}
					catch(Exception e){
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "Articles not found";	
						break;
					}
								
				}
			
			}
			
			catch(Exception e){			
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the articles";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
				
			}
		}
		
		
		public static void VERIFYSEARCHRESULTS() throws InvalidFormatException, IOException, XmlException{
			
			TestAttributes.TakeScreenShotFlag = true;
			
			try{			
			
				List<WebElement> childs = TestAttributes.driver.findElements(By.xpath("html/body/div[1]/section/div/ul/li"));
				int numberofresults = childs.size();
						
				for(int i = 1;i<=numberofresults;i++){
					
					String currentresultpath = "html/body/div[1]/section/div/ul/li[" + i + "]";
					TestAttributes.childelement = null;
					
					TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentresultpath))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentresultpath))));
						
		        	String currenttext = TestAttributes.childelement.getText().trim().toLowerCase();	        	
		        	if(currenttext.contains(TestAttributes.InputParameters[0].toLowerCase()))       	
		        		TestAttributes.ActualResult = "Results contain the search key";
		        	else{
		        		TestAttributes.Status = "Failed";		        	
		        		TestAttributes.ActualResult = "Result " + i + " does not contain the search key";
		        		break;
		        	}
								
				}
			
			}
			
			catch(Exception e){			
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the articles";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";			
			}
		}
		
		
		
		public static void VERIFYELEMENTUNDERLINECOLOR() throws InvalidFormatException, IOException, XmlException{
			
			TestAttributes.TakeScreenShotFlag = true;	
			
			try{
					
				String ExpectedUnderlineColor = TestAttributes.InputParameters[0].trim();
				Thread.sleep(2000);
				String ActualUnderlineColor = TestAttributes.element.getCssValue("border-bottom-color");
				
				if(ExpectedUnderlineColor.equalsIgnoreCase(ActualUnderlineColor))
					TestAttributes.ActualResult = "The element is underlined with the expected color '" + ExpectedUnderlineColor + "'.";
				else{
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The element is underlined with color '" + ActualUnderlineColor + "'. But the expected color is '" + ExpectedUnderlineColor + "'.";
				}
			
			}
			
			catch(Exception e){			
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the underline color of the element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";			
			}
		}
		
		
		public static void VERIFYELEMENTBACKGROUNDCOLOR() throws InterruptedException {
			
			TestAttributes.TakeScreenShotFlag= true;
			
			try {
				
				String ExpectedBackgroundColor = TestAttributes.InputParameters[0].trim();
				Thread.sleep(2000);
				String ActualBackgroundColor =  TestAttributes.element.getCssValue("color");
				
				if(ExpectedBackgroundColor.equalsIgnoreCase(ActualBackgroundColor))
					TestAttributes.ActualResult = "The background color of the element is same as the expected color '" + ExpectedBackgroundColor + "'.";
				else{
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The background color of the element is '" + ActualBackgroundColor + "'. But the expected color is '" + ExpectedBackgroundColor + "'.";
				}
							
				
			} catch(Exception e){			
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the background color of the element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
				
			}
			Thread.sleep(1000);
			
		}
		
		
		public static void PARTIALVERIFYVALUE() {
			TestAttributes.TakeScreenShotFlag= true;
			String ActualValue = "";
									
			try {
				if (TestAttributes.element != null) {
					switch(TestAttributes.ElementType.toUpperCase().trim()) {
									
					case "ELEMENT":			
					
						ActualValue = TestAttributes.element.getText().trim();
						
						if (ActualValue.contains(TestAttributes.InputParameters[0].trim())) {
							TestAttributes.Status = "Passed";
							TestAttributes.ActualResult = "Expected Value '" + TestAttributes.InputParameters[0].trim() + "' is present as part of the value '" + ActualValue + "' in the application.";
						} else {
							TestAttributes.Status = "Failed";
							TestAttributes.ActualResult = "Expected Value '" + TestAttributes.InputParameters[0].trim() + "' is not present as part of the value '" + ActualValue + "' in the application."; 
						}
						break;
						
					}
					
				} else {
					TestAttributes.TakeScreenShotFlag= true;
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "No such object exists";
				}
				
			} catch (Exception e) {
				TestAttributes.TakeScreenShotFlag= true;
				TestAttributes.Status = "Error";
				
				switch (TestAttributes.ElementType.toUpperCase().trim()) {
				
				case "ELEMENT":
					TestAttributes.ActualResult = "Error while verifying the value '" + TestAttributes.Data + "' in the field '" + TestAttributes.Field_Name + "' of the screen '" + TestAttributes.Screen_Name + "'.";
					break;
						
				}
				
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
			}
		}
		
		public static void VERIFYNEWS() throws InvalidFormatException, IOException, XmlException{
			
			TestAttributes.TakeScreenShotFlag = true;
			
			try{			
			
				List<WebElement> childs = TestAttributes.driver.findElements(By.xpath("html/body/div[1]/section/div[3]/div/div[3]/div/div"));
				int numberofnews = childs.size();
						
				for(int i = 2;i<=numberofnews;i++){
										  
					String currentnewsxpath = "html/body/div[1]/section/div[3]/div/div[3]/div/div[" + i + "]/div";
					TestAttributes.childelement = null;
					
					try{
					
					TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentnewsxpath))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentnewsxpath))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentnewsxpath + "/div"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentnewsxpath + "/div"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentnewsxpath + "/div/span"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentnewsxpath + "/div/span"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentnewsxpath + "/a"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentnewsxpath + "/a"))));
		        	
		        	TestAttributes.ActualResult = "All News found";
		        	
					}
					catch(Exception e){
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "News not found";	
						break;
					}
								
				}
			
			}
			
			catch(Exception e){			
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the News";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
				
			}
		}
		
		
		public static void VERIFYVIDEOS() throws InvalidFormatException, IOException, XmlException{
			
			TestAttributes.TakeScreenShotFlag = true;
			
			try{			
			
				List<WebElement> childs = TestAttributes.driver.findElements(By.xpath("html/body/div[1]/section/div[3]/div/div[3]/div/div"));
				int numberofvideos = childs.size();
						
				for(int i = 2;i<=numberofvideos;i++){
											  
					String currentvideoxpath = "html/body/div[1]/section/div[3]/div/div[3]/div/div[" + i + "]/div";
					TestAttributes.childelement = null;
					
					try{
					
					TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentvideoxpath))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentvideoxpath))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentvideoxpath + "/img"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentvideoxpath + "/img"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentvideoxpath + "/div[1]/h2"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentvideoxpath + "/div[1]/h2"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentvideoxpath + "/a"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentvideoxpath + "/a"))));
		        	
		        	TestAttributes.ActualResult = "All Videos found";
		        	
					}
					catch(Exception e){
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "Videos not found";	
						break;
					}
								
				}
			
			}
			
			catch(Exception e){			
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the Videos";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
				
			}
		}
		
		
		public static void VERIFYEVENTS() throws InvalidFormatException, IOException, XmlException{
			
			TestAttributes.TakeScreenShotFlag = true;
			
			try{			
			
				List<WebElement> childs = TestAttributes.driver.findElements(By.xpath("html/body/div[1]/section/div[3]/div/div[3]/div/div"));
				int numberofevents = childs.size();
						
				for(int i = 2;i<=numberofevents;i++){
										  
					String currenteventsxpath = "html/body/div[1]/section/div[3]/div/div[3]/div/div[" + i + "]/div";
					TestAttributes.childelement = null;
					
					try{
					
					TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currenteventsxpath))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currenteventsxpath))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currenteventsxpath + "/img"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currenteventsxpath + "/img"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currenteventsxpath + "/div[1]/h2"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currenteventsxpath + "/div[1]/h2"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currenteventsxpath + "/div[1]/span"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currenteventsxpath + "/div[1]/span"))));
		        	
		        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currenteventsxpath + "/div[2]"))));
		        	TestAttributes.childelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currenteventsxpath + "/div[2]"))));
		        	
		        	TestAttributes.ActualResult = "All Events found";
		        	
					}
					catch(Exception e){
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = "Events not found";	
						break;
					}
								
				}
			
			}
			
			catch(Exception e){			
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the Events";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
				
			}
		}
		

		public static void SCROLLUPANDCLICK() throws InterruptedException{
			
			TestAttributes.TakeScreenShotFlag = false;		
			boolean clicked = false;
			String emessage = "";
			
			for(int i = 1;i<=10;i++){
				
				try{				
					TestAttributes.element.click();
					clicked = true;
					break;			
				}
				
				catch(Exception e){	
					emessage = e.getMessage();
					JavascriptExecutor jse = (JavascriptExecutor)TestAttributes.driver;
					jse.executeScript("window.scrollBy(0,-250)", "");				
				}
			}
			
			if(clicked==true){
				TestAttributes.ActualResult = "Element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is clicked.";
			}
			else{
				TestAttributes.TakeScreenShotFlag = true;
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while clicking the element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + emessage + ".";
			}
					
			
			
		}
		
		public static void SCROLLDOWNANDCLICK() throws InterruptedException{
			
			TestAttributes.TakeScreenShotFlag = false;		
			boolean clicked = false;
			String emessage = "";
			
			for(int i = 1;i<=10;i++){
				
				try{				
					TestAttributes.element.click();
					clicked = true;
					break;			
				}
				
				catch(Exception e){	
					emessage = e.getMessage();
					JavascriptExecutor jse = (JavascriptExecutor)TestAttributes.driver;
					jse.executeScript("window.scrollBy(0,250)", "");				
				}
			}
			
			if(clicked==true){
				TestAttributes.ActualResult = "Element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "' is clicked.";
			}
			else{
				TestAttributes.TakeScreenShotFlag = true;
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while clicking the element '" + TestAttributes.Field_Name + "' in the screen '" + TestAttributes.Screen_Name + "'.";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + emessage + ".";
			}
			
		}
		
		public static void SCROLLDOWN() throws InterruptedException{
			try{
			
				TestAttributes.TakeScreenShotFlag = false;		
				Thread.sleep(8000);
				JavascriptExecutor jse = (JavascriptExecutor)TestAttributes.driver;
				jse.executeScript("window.scrollBy(0,250)", "");
				
				
				
				TestAttributes.ActualResult = "The screen is scrolled once.";
				
			}
			catch(Exception e){
				TestAttributes.TakeScreenShotFlag = true;
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while scrolling the screen.";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + e.getMessage() + ".";
			}
			
		}
		
		public static void SELECTCOUNTRY() throws InterruptedException{
			
			TestAttributes.TakeScreenShotFlag = true;		
			boolean clicked = false;
			int maximumscrolls = 25;
			int maxcountries = 10;
			String emessage = "";
			Actions action = new Actions(TestAttributes.driver);
			WebElement imageele = null;
			String scrollxpath;
			
			scrollxpath = "html/body/div[1]/footer/div[3]/div/div/section/div/div/div/div[3]/div/div[2]/div/div[2]";		
			
			TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(scrollxpath))));
	        WebElement scrollelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(scrollxpath))));
			
			int currentscroll = 1;
			int currentcountry = 1;
			
			while(currentscroll <= maximumscrolls && currentcountry <= maxcountries && !clicked){
				
			    try{
			    	
			    	String currentelementxpath = TestAttributes.LocatorValue + "/div[1]/div[" + currentcountry + "]";
			    	String currentelementimagexpath = TestAttributes.LocatorValue + "/div[1]/div[" + currentcountry + "]/img";
			    	
					TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentelementxpath))));
				    WebElement countryelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentelementxpath))));
				    
				    TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentelementimagexpath))));
				    imageele = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentelementimagexpath))));
				    
				    
			        if(countryelement.getText().equalsIgnoreCase(TestAttributes.InputParameters[0])){
			        	
			        	imageele = null;
			        	try{
				        	TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentelementimagexpath))));
						    imageele = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentelementimagexpath))));
			        	}
				    	catch(Exception e){
				    		imageele = null;
				    	}
			        	
			        	countryelement.click();
			        	clicked = true;
						break;
					}
			
			        
			        currentcountry = currentcountry +1;
				}
				catch(Exception e){
					emessage = e.getMessage();
					action.moveToElement(scrollelement).clickAndHold().moveByOffset(0, 20).release().build().perform();
					currentscroll = currentscroll + 1;
				}	
			   
			}
			
			if(clicked){
				TestAttributes.ActualResult = "The country '" + TestAttributes.InputParameters[0] + "' is selected from the field '" + TestAttributes.Field_Name + "'.";
				
				if(imageele!=null)
					TestAttributes.ActualResult = TestAttributes.ActualResult + "The corresponding flag is present";
				else{
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = TestAttributes.ActualResult + "The corresponding flag is not present";
				}
			}
			else{
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "Error while selecting the country '" + TestAttributes.InputParameters[0] + "' from the field '" + TestAttributes.Field_Name + "'. The given country may not be part of the list.";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + emessage + ".";

			}
		}
		
		
	public static void SELECTCOUNTRYBUSINESSDIRECTORY() throws InterruptedException{
			
			TestAttributes.TakeScreenShotFlag = true;		
			boolean clicked = false;
			int maximumscrolls = 50;
			int maxcountries = 16;
			String emessage = "";
			Actions action = new Actions(TestAttributes.driver);		
			String scrollxpath;
			
			scrollxpath = "html/body/div[1]/section/div[3]/div[1]/div[2]/div/div[3]/div/div[2]/div/div[2]";		
			
			TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(scrollxpath))));
	        WebElement scrollelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(scrollxpath))));
			
			int currentscroll = 1;
			int currentcountry = 1;
			
			while(currentscroll <= maximumscrolls && currentcountry <= maxcountries && !clicked){
				
			    try{
			    	
			    	String currentelementxpath = TestAttributes.LocatorValue + "/div[1]/div[" + currentcountry + "]";		    	
			    	
					TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentelementxpath))));
				    WebElement countryelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentelementxpath))));
				    			    	    	
			        if(countryelement.getText().equalsIgnoreCase(TestAttributes.InputParameters[0])){		        	
			        	countryelement.click();
			        	clicked = true;
						break;
					}
			        
			        currentcountry = currentcountry +1;
				}
				catch(Exception e){
					emessage = e.getMessage();
					action.moveToElement(scrollelement).clickAndHold().moveByOffset(0, 20).release().build().perform();
					currentscroll = currentscroll + 1;
				}	
			   
			}
			
			if(clicked){
				TestAttributes.ActualResult = "The country '" + TestAttributes.InputParameters[0] + "' is selected from the field '" + TestAttributes.Field_Name + "'.";
			}
			else{
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "Error while selecting the country '" + TestAttributes.InputParameters[0] + "' from the field '" + TestAttributes.Field_Name + "'. The given country may not be part of the list.";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + emessage + ".";

			}
		}

		
		public static void SELECTITEM() throws InterruptedException{
			
			TestAttributes.TakeScreenShotFlag = true;		
			boolean clicked = false;
			int maximumscrolls = 25;
			int maxitems = Integer.parseInt(TestAttributes.InputParameters[0].trim());
			String emessage = "";
			Actions action = new Actions(TestAttributes.driver);
					
			String scrollxpath = "html/body/div[1]/footer/div[1]/div/div[2]/div/div[3]/div/div[2]/div/div[2]";
			TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(scrollxpath))));
	        WebElement scrollelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(scrollxpath))));
			
			int currentscroll = 1;
			int currentitem = 1;
			
			while(currentscroll <= maximumscrolls && currentitem <= maxitems && !clicked){
				
			    try{
			    	
			    	String currentelementxpath = TestAttributes.LocatorValue + "/div[1]/div[" + currentitem + "]";
			    	
					TestAttributes.webdriverwait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath(currentelementxpath))));
				    WebElement itemelement = TestAttributes.webdriverwait.until(ExpectedConditions.elementToBeClickable((By.xpath(currentelementxpath))));
				    			    	    	
			        if(itemelement.getText().equalsIgnoreCase(TestAttributes.InputParameters[1])){
			        	
			        	itemelement.click();
			        	clicked = true;
						break;
					}
			        
			        currentitem = currentitem +1;
				}
				catch(Exception e){
					emessage = e.getMessage();
					action.moveToElement(scrollelement).clickAndHold().moveByOffset(0, 20).release().build().perform();
					currentscroll = currentscroll + 1;
				}	
			   
			}
			
			if(clicked){
				TestAttributes.ActualResult = "The item '" + TestAttributes.InputParameters[1] + "' is selected from the field '" + TestAttributes.Field_Name + "'.";
			}
			else{
				TestAttributes.Status = "Failed";
				TestAttributes.ActualResult = "Error while selecting the item'" + TestAttributes.InputParameters[1] + "' from the field '" + TestAttributes.Field_Name + "'. The given item may not be part of the list.";
				TestAttributes.ActualResult = TestAttributes.ActualResult + " " + emessage + ".";

			}
		}
		
		//==================End of Alexander Forbes Functions =========================//	
	}
	
