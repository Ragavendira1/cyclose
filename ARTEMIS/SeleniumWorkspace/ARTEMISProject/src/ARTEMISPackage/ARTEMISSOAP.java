package ARTEMISPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;


public class ARTEMISSOAP {
	
	public static String getXMLstring(String xmlpath) throws Exception{
		
		StringBuffer requestfilecontents = new StringBuffer();      
	    BufferedReader bufferedReader = new BufferedReader(new FileReader(xmlpath));	    
	    String line = null;
	    while((line = bufferedReader.readLine()) != null)      
	    	requestfilecontents.append(line);
	    
	    return requestfilecontents.toString();
		
	}
	
	public static Map<String, String> getMap(String parameters){		
		Map<String, String> hm = new HashMap();
		String key,value;
		String[] parameterssplit = parameters.split(";;",-1);
		
		for(int i=0;i<parameterssplit.length;i++){
			String currentkeyvaluepair = parameterssplit[i].trim();			
			String[] currentkeyvaluepairsplit = currentkeyvaluepair.split("##",-1);
			key = currentkeyvaluepairsplit[0].trim();
			value = currentkeyvaluepairsplit[1].trim();			
			hm.put(key, value);			
		}		
		return hm;
	}
	
	public static String replaceparameterswithvalues(String str, Map<String,String> hm){
		
		String key,value;		
		for (Map.Entry<String, String> entry : hm.entrySet()){		
		   key = entry.getKey();
		   value = entry.getValue();		   
		   str = str.replace("{" + key + "}", value);		   
		}
		return str;		
	}
	
	
	public static void POST() {
		
		String server = TestAttributes.Screen_Name.trim();
		String endpoint = TestAttributes.Field_Name.trim();
		String[] getdatasplit = TestAttributes.Data.trim().split("\\|\\|",-1);		
		String paramdata =  null, headerdata = null, postdata = null, verifydata = null, actualdata = null, verifycode = null;			
					
		for(int i=0;i<getdatasplit.length;i++) {				
			String currentdata = getdatasplit[i].trim();			
			String[] currentdatasplit = currentdata.split("::",-1);
			
			switch(currentdatasplit[0].trim()) {			
				case "PARAMDATA":
					paramdata = currentdatasplit[1].trim();
					break;
				case "VERIFYDATA":
					verifydata = currentdatasplit[1].trim();
					break;
				case "HEADERDATA":
					headerdata = currentdatasplit[1].trim();
					break;
				case "POSTDATA":
					postdata = currentdatasplit[1].trim();
					break;
				case "VERIFYCODE":
					verifycode = currentdatasplit[1].trim();
					break;
				case "ACTUALDATA":
					actualdata = currentdatasplit[1].trim();
			}				
		}		
		
		if(paramdata!=null) {		
			Map<String, String> parametershm = getMap(paramdata);			
			server = replaceparameterswithvalues(server,parametershm );
			endpoint = replaceparameterswithvalues(endpoint,parametershm );
		}		
		
		if(!endpoint.equalsIgnoreCase(""))				
			if(server.charAt(server.length()-1)=='/' || endpoint.charAt(0)=='/')
				server = server + endpoint;
			else
				server = server + "/" + endpoint;
		else
			if(server.charAt(server.length()-1)=='/')
				server = server + endpoint;
			else
				server = server + "/" + endpoint;
		
		
		HttpClient client = new HttpClient(); 
		PostMethod post = new PostMethod(server);
		
		if(headerdata!=null) { 	
			String key,value;
			Map<String, String> headershm = getMap(headerdata);
			for (Map.Entry<String, String> entry : headershm.entrySet()){		
			   key = entry.getKey();
			   value = entry.getValue();
			   post.setRequestHeader(key,value);
			 }
		}
		
		String actualcode = null;
		
	    try {		    	
	    	RequestEntity entity = new StringRequestEntity(getXMLstring(TestAttributes.ProjectLocation + "TestData/" + postdata), "text/xml",  "utf-8");
	    	post.setRequestEntity(entity);
	    	int result = client.executeMethod(post);
	    	actualcode = "" + result + "";
	    	
	    	File responseFile = new File(TestAttributes.ProjectLocation + "TestData/" + actualdata);    
	    	responseFile.createNewFile();    
	    	OutputStreamWriter oSW = new OutputStreamWriter(new FileOutputStream(responseFile),"UTF-8"); 
	    	oSW.write(post.getResponseBodyAsString());        
	    	oSW.flush();    
	    	oSW.close();
	    	
		}		
		catch(Exception e) { // how will it behave if the server is not responding or server id is wrong			
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while posting the request. Please check if the / request data and its format is valid / server is up. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + e.getMessage() + ". "; 
		}
		
		if(!TestAttributes.Status.equalsIgnoreCase("Error")) {
		
			try {
				
				String expectedcode = verifycode;
				
				if(!actualcode.equals(expectedcode)) {
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = "The expected response code is '" + expectedcode + "'. But the actual response code is '" + actualcode + "'. ";
				}else
					TestAttributes.ActualResult = "The expected response code '" + expectedcode + "' matches with the actual response code. "; 
									 		
				Reader expectedXMLReader = new InputStreamReader(new FileInputStream(TestAttributes.ProjectLocation + "TestData/" + verifydata), "UTF-8");    
			    Reader actualXMLReader = new InputStreamReader(new FileInputStream(TestAttributes.ProjectLocation + "TestData/" + actualdata), "UTF-8");
				
			    XMLUnit.setIgnoreWhitespace(true); 
			    Diff xmlDiff = new Diff(expectedXMLReader, actualXMLReader);
			    
				if(!xmlDiff.similar()) {
					TestAttributes.Status = "Failed";
					TestAttributes.ActualResult = TestAttributes.ActualResult + "Expected response content does not match with the actual reponse content. ";
				}else
					TestAttributes.ActualResult = TestAttributes.ActualResult + "Expected response content matches with the actual reponse content. ";				
				
			}
			catch(Exception e) {
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the response. Please check if the verify data and its format is valid. ";
				TestAttributes.ActualResult = TestAttributes.ActualResult + e.getMessage() + ". "; 
			}
		
		}
		
	}	

}
