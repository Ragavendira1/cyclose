package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ARTEMIS {

String javapath = "C:/Program Files/Java/jre7/bin/java.exe";
String jarpath = "C:/Users/tg2944/Downloads/artemis/ARTEMIS/SeleniumDriver.jar";
String runid = "0";
String runname = "testng";
String database = "SQLSERVER";
String tool = "SELENIUM";

public int runTest(String testname) throws Exception{

String command = "\"" + javapath + "\"" + " -jar " + "\"" + jarpath + "\"" + " \"" + runid + "\"" + " \"" + runname + "\"" + " \"" + testname + "\"" + " \"" + database + "\"" + " \"" + tool + "\"";
Process process = Runtime.getRuntime().exec(command);
process.waitFor();
return process.exitValue();
}

@Test
public void CyclosDemo001_441() throws Exception{
Assert.assertEquals(runTest("441"), 1);
}


@Test
public void CyclosDemo003_445() throws Exception{
Assert.assertEquals(runTest("445"), 1);
}

}
