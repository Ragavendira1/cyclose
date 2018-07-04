package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC_001 {
	
	String javapath = "C:/Program Files/Java/jre7/bin/java.exe";
	String jarpath = "C:/Users/tg2944/Downloads/artemis/ARTEMIS/SeleniumDriver.jar";
	String runid = "0";
	String runname= "test";
	String database = "ACCESS";
	String tool = "ARTEMISREST";
	
	public int runTest(String testname) throws Exception{
		String command = "\"" + javapath + "\"" + " -jar " + "\"" + jarpath + "\"" + " \"" + runid + "\"" + " \"" + runname + "\"" + " \"" + testname + "\"" + " \"" + database + "\"" + " \"" + tool + "\"";
		Process process = Runtime.getRuntime().exec(command);
		process.waitFor();
		return process.exitValue();
	}

	//@Test
	public void TC001() throws Exception{
		//Assert.assertEquals(runTest("442"), 1);
		
	    Assert.assertEquals(runTest(new Exception().getStackTrace()[0].getMethodName()), 0);
	}
	
	
	//@Test
	public void TC002() throws Exception{
		//Assert.assertEquals(runTest("442"), 1);
		
	    Assert.assertEquals(runTest(new Exception().getStackTrace()[0].getMethodName()), 0);
	}
	
	
	//@Test
	public void TC003() throws Exception{
		//Assert.assertEquals(runTest("442"), 1);
		
	    Assert.assertEquals(runTest(new Exception().getStackTrace()[0].getMethodName()), 0);
	}
	
	
	//@Test
	public void TC004() throws Exception{
		//Assert.assertEquals(runTest("442"), 1);
		
	    Assert.assertEquals(runTest(new Exception().getStackTrace()[0].getMethodName()), 0);
	}
	
	
	
	
	
}
