package SeleniumPackage;

public class Settings{
	public static String DateFormat = "MM-dd-yyyy h-mm-ss a";
	
	public static String Generate_Execution_Log = "";	
	public static int Maximum_Error_Recovery_Attempts = 1;	
	public static String Browser = "";	
	public static String DriverLocation = "";
	public static int implicitlyWait = 0;	
	public static int pageLoadTimeout = 0;	
	public static int setScriptTimeout = 0;	
	public static int webdriverwaittime = 0;	
	public static String Device = "";	
	public static String Host = "";	
	public static int Port = 0;
	
	public static void Settings() {
		try {
			Generate_Execution_Log = FunctionLibrary.getConfigValue("Generate Execution Log").trim();
			Maximum_Error_Recovery_Attempts = Integer.parseInt(FunctionLibrary.getConfigValue("Maximum Error Recovery Attempts").trim());
			Browser = FunctionLibrary.getConfigValue("Browser").trim();
			DriverLocation = FunctionLibrary.getConfigValue("DriverLocation").trim();
			implicitlyWait  = Integer.parseInt(FunctionLibrary.getConfigValue("implicitlyWait").trim());
			pageLoadTimeout = Integer.parseInt(FunctionLibrary.getConfigValue("pageLoadTimeout").trim());
			setScriptTimeout = Integer.parseInt(FunctionLibrary.getConfigValue("setScriptTimeout").trim());
			webdriverwaittime = Integer.parseInt(FunctionLibrary.getConfigValue("webdriverwaittime").trim());
			Device= FunctionLibrary.getConfigValue("Device").trim();
			Host= FunctionLibrary.getConfigValue("Host").trim();
			Port = Integer.parseInt(FunctionLibrary.getConfigValue("Port").trim());

		} catch (Exception e) {
			TestAttributes.InitialSetUpErrorMessage = TestAttributes.InitialSetUpErrorMessage + "Error while retrieving the configuration values. ";
		}
	}
}