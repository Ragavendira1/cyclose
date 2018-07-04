package ARTEMISPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.experitest.selenium.MobileWebDriver;

public class TestAttributes {
		
	public static String Run_Name = "";	
	public static String Global_DataBase = "";	
	public static String AutomationTool = "";
	public static int Instance_ID = 0;;
	public static int Run_ID = 0;
	public static String BatchName ="";
	public static int StartStepId = 0;
	public static boolean TestInstanceControl = true;
	
	
	public static String InitialSetUpErrorMessage = "";
	public static String ProjectLocation = "";
	public static String LogLocation= "";
	//See if problem comes as previous value is taken for next instance id
	public static Connection conn = null;
	public static Statement stmtTests = null;	
	public static Statement stmtSteps = null;
	public static Statement stmtSteps1 = null;
	public static Statement stmt = null;
	public static Statement batchstmt = null;
	public static Statement stmtsid = null;
	public static Statement stmtSDS = null;
	public static Statement stmtTDS = null;
	
	
	public static String TestLog,ExecutionLog
						 ,Global_CDT,FinalStatus,Global_Test_Id,Global_Workflow_Name,Global_SharedDataSet,Global_SharedDataSetList	
						 ,Global_TestDataSet,Global_TestDataSetList,Test_Id,Workflow_Name,SharedDataSet
	                     ,TestDataSet,Global_Test_ShortName,Status,ActualResult,ScreenShotsPaths,Step_Description	
	                     ,Expected_Result,Run_Type,Screen_Name,Field_Name,Keyword,Data,Locator,LocatorValue
	                     ,ElementType,ObjectType,MobileLocator,KeywordType,DataNotApplicableString;
	
	public static int iflevel,iflevelinaction,calltestcounter,looplevel,Step_Id,Step_Id_Results;
	public static boolean TakeScreenShotFlag,ExitLoop,WriteResults,breakflag;
	
	public static String[] calltesttestid,calltestworkflowname,calltestshareddataset,calltesttestdataset,ELSE,InputParameters,OutputParameters;
		
	public static int[] LoopCounters,StepsIds,calltestteststepid;
	public static boolean[] IfEvaluation;
	
	public static WebElement element,childelement;		
	public static WebDriver driver;
	public static WebDriverWait webdriverwait;	
	public static MobileWebDriver mobiledriver;
		
	public static void Initialization() {
		
		InitialSetUpErrorMessage = "";
		TestLog = "";
		ExecutionLog = "";
		Global_CDT = "";
		FinalStatus= "Not Completed";
		Global_Test_Id = "";
		Global_Workflow_Name = "";
		Global_SharedDataSet = "";
		Global_SharedDataSetList = "";;
		Global_TestDataSet = "";
		Global_TestDataSetList ="";
		Test_Id = "";
		Workflow_Name = "";
		SharedDataSet = ""; 
		TestDataSet = "";
		Global_Test_ShortName = "";
		Status= "";
		ActualResult= "";
		ScreenShotsPaths = "";
		Step_Description = "";
		Expected_Result = "";
		Run_Type = "";
		Screen_Name = "";
		Field_Name = "";
		Keyword = "";	
		Data = "";
		Locator = "";
		LocatorValue = "";
		ElementType= "";
		ObjectType= "";
		MobileLocator= "";
		KeywordType = "Navigation";
		DataNotApplicableString = "NOTAPPLICABLE";
		
		iflevel = 0; iflevelinaction = 0; calltestcounter = 0; looplevel = 0; Step_Id = 0; Step_Id_Results = 0;		 
		
		TakeScreenShotFlag = false; ExitLoop = false; WriteResults = false; breakflag = false; 
		
		calltesttestid = new String[5];
		calltestworkflowname = new String[5];
		calltestshareddataset = new String[5];
		calltesttestdataset = new String[5];
		calltestteststepid = new int[5];
			
		ELSE = new String[5];	
		InputParameters = new String[5];
		OutputParameters = new String[5];
		
		LoopCounters = new int[5];
		StepsIds = new int[5];
		
		IfEvaluation = new boolean[5];
			
		element = null;
		childelement = null;	
		driver = null;
		webdriverwait = null;	
		mobiledriver = null;
		
		for(int i = 0; i < 5; i++) {
			calltesttestid[i] = "";
			calltestworkflowname[i] = "";
			calltestshareddataset[i] = "";
			calltesttestdataset[i] = "";
			calltestteststepid[i] = 0;
			
			ELSE[i] = "";
			
			LoopCounters[i] = 0;
			StepsIds[i] = 0;
			
			IfEvaluation[i] = false;
		}		
		setInOutParameters();
	}
	
	public static void setInOutParameters() {
		InputParameters = new String[5];
		OutputParameters = new String[5];
		
		for (int i = 0; i < 5; i++){
			InputParameters[i] = "";
			OutputParameters[i] = "";
		}
	}
}