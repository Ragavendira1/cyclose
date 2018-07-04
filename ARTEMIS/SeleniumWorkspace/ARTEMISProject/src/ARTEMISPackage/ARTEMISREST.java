package ARTEMISPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.xmlbeans.XmlException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ARTEMISREST {
	
	public static boolean stopflag = false, verifyflag = false;
	
	public static void CALLTEST() throws InvalidFormatException, IOException, XmlException, SQLException, InterruptedException {
		CommonKeywordLibrary.CALLTEST();		
	}
	
	public static void STOREVALUE() {
								
		try {
			
			String[] DataSplit;
			String StoreVariable = TestAttributes.Data;
			String StoreValue = "";
				
			DataSplit = TestAttributes.Data.split("\\|\\|",-1);
			StoreVariable = DataSplit[1].trim();
			
			String ValuesSplit[] = DataSplit[0].trim().split(";;",-1);
			
			for(int i = 0; i < ValuesSplit.length; i++) {
				String CurrentValue = ValuesSplit[i].trim();
				
				if (CurrentValue.contains("\""))
					StoreValue = StoreValue + CurrentValue.substring(1,CurrentValue.length()-1);
				else 					
					StoreValue = StoreValue + CurrentValue;				
			}
			
			FunctionLibrary.StoreValue(StoreVariable.trim(),StoreValue.trim());
			TestAttributes.ActualResult = "Text '" + StoreValue + "' is stored in the variable '" + StoreVariable + "'. ";			

		} catch (Exception e) {
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while storing the value. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + e.getMessage() + ". ";			
		}
	}
	
	public static void GET() {		
		GETANDPOST("GET");
	}
	
	public static void POST() {		
		GETANDPOST("POST");
	}
	
	//handle @@ inserver
	public static void GETANDPOST(String Keyword) {
		stopflag = false;
		String server = TestAttributes.Screen_Name.trim();
		String endpoint = TestAttributes.Field_Name.trim();
		String[] getdatasplit = TestAttributes.Data.trim().split("\\|\\|",-1);		
		String paramdata =  null, headerdata = null, postdata = null, verifydata = null, actualdata = null;			
					
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
				case "ACTUALDATA":
					actualdata = currentdatasplit[1].trim();
			}				
		}		
		
		if(paramdata!=null) {		
			Map<String, String> parametershm = getMap(paramdata);			
			server = replaceparameterswithvalues(server,parametershm );
			endpoint = replaceparameterswithvalues(endpoint,parametershm );
		}		
		
		server = server + "/" + endpoint;		
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpGet getRequest = null;
		HttpPost postRequest = null;
		
		if(Keyword.trim().equalsIgnoreCase("GET"))
			getRequest = new HttpGet(server);
		else
			postRequest = new HttpPost(server);
		
		if(headerdata!=null) { 	
			String key,value;
			Map<String, String> headershm = getMap(headerdata);
			for (Map.Entry<String, String> entry : headershm.entrySet()){		
			   key = entry.getKey();
			   value = entry.getValue();
			   
			   if(Keyword.trim().equalsIgnoreCase("GET"))
				   getRequest.addHeader(key, value);
				else 
				   postRequest.addHeader(key, value);				
			 }
		}
		 
	  
	    String actualcode = null,actualjsonstring = null;
	   
	    try {	
		   
		   if(Keyword.trim().equalsIgnoreCase("POST")) {
				StringEntity input;
				
				if(!isJson(postdata)) {
				   if(postdata!=null)	
					   input =new StringEntity(getJsonstring(TestAttributes.ProjectLocation + "TestData/" + postdata));
				   else
					   input = new StringEntity("");
				}
				else
				   input = new StringEntity(postdata);
				   
				postRequest.setEntity(input);
			}
		   
		    HttpResponse response;
		    if(Keyword.trim().equalsIgnoreCase("GET"))
			   response = httpClient.execute(getRequest);
			else
			   response = httpClient.execute(postRequest);
			
			actualcode = "" + response.getStatusLine().getStatusCode() + "";
			actualjsonstring = getJSONResponse(response);
			
			if(actualdata != null) {
				File responseFile = new File(TestAttributes.ProjectLocation + "TestData/" + actualdata);    
		    	responseFile.createNewFile();    
		    	OutputStreamWriter oSW = new OutputStreamWriter(new FileOutputStream(responseFile),"UTF-8"); 
		    	oSW.write(actualjsonstring);        
		    	oSW.flush();    
		    	oSW.close();
			}
			
		}		
		catch(Exception e) { // how will it behave if the server is not responding or server id is wrong			
			TestAttributes.Status = "Error";
			TestAttributes.ActualResult = "Error while posting the request. Please check if the / request data and its format is valid / server is up. ";
			TestAttributes.ActualResult = TestAttributes.ActualResult + e.getMessage() + ". "; 
		}
		
		if(!TestAttributes.Status.equalsIgnoreCase("Error")) {
		
			try {
				
				if(verifydata!=null) {
					if(!isJson(verifydata))
						verifydata = getJsonstring(TestAttributes.ProjectLocation + "TestData/" + verifydata);
						
					JSONObject expectedjsontemp = new JSONObject(verifydata);
					String expectedcode = FunctionLibrary.RetrieveData(expectedjsontemp.get("code").toString());
					String expectedjsonstring = expectedjsontemp.get("content").toString();
					//make code not mandatory and also content
					if(expectedcode.contains("ARTEMISSTORE-")) {
						String[] expectedcodesplit = expectedcode.split("ARTEMISSTORE-",-1);
						FunctionLibrary.StoreValue(expectedcodesplit[1], actualcode);
					}
					else {	
						if(!actualcode.equals(expectedcode)) {
							TestAttributes.Status = "Failed";
							TestAttributes.ActualResult = "The expected response code is '" + expectedcode + "'. But the actual response code is '" + actualcode + "'. ";
						}else
							TestAttributes.ActualResult = "The expected response code '" + expectedcode + "' matches with the actual response code. ";
					}
										 		
					JSONObject expectedjson = new JSONObject("{\"content\":" + expectedjsonstring + "}");
					JSONObject actualjson = new JSONObject("{\"content\":" + actualjsonstring + "}");
					
					if(!comparemaps(stopflag,toMap(expectedjson), toMap(actualjson))) {
						TestAttributes.Status = "Failed";
						TestAttributes.ActualResult = TestAttributes.ActualResult + "Expected response content does not match with the actual reponse content. ";
					}else
						TestAttributes.ActualResult = TestAttributes.ActualResult + "Expected response content matches with the actual reponse content. ";
				}
				
			}
			catch(Exception e) {
				TestAttributes.Status = "Error";
				TestAttributes.ActualResult = "Error while verifying the response. Please check if the verify data and its format is valid. ";
				TestAttributes.ActualResult = TestAttributes.ActualResult + e.getMessage() + ". "; 
			}
		
		}
		
	}	
	
	
	public static boolean comparemaps(boolean stopflaglocal, Map<String, Object> expectedmap, Map<String, Object> actualmap) throws Exception{ 
		//only one that too store means fail
		if(stopflaglocal)
			return false;
		
		boolean returnvalue = false;
		
		for (Map.Entry<String, Object> entry : expectedmap.entrySet()){
			
			if(stopflag)
				return false;						
						
		    String currentexpectedkey = entry.getKey();
		    Object currentexpectedvalue = entry.getValue();
		    
		    if(actualmap.containsKey(currentexpectedkey)){
		   
			    Object currentactualvalue = actualmap.get(currentexpectedkey);
			   
			    if(currentexpectedvalue.getClass().equals(currentactualvalue.getClass())){
			   
				    switch(currentexpectedvalue.getClass().toString()){
					   
					    case "class java.util.ArrayList":
						    ArrayList currentexpectedarraylist = (java.util.ArrayList)currentexpectedvalue;
						    ArrayList currentactualarraylist = (java.util.ArrayList)currentactualvalue;
						   
						    for(int i =0;i<currentexpectedarraylist.size();i++){						    	
							    for(int j =0;j<currentactualarraylist.size();j++){
							    	stopflag = false;
							    	returnvalue = comparemaps(false,(java.util.HashMap) currentexpectedarraylist.get(i),(java.util.HashMap) currentactualarraylist.get(j));
							    	if(returnvalue)
							    		break;
							    }
							    							    							    
							    if(returnvalue==false){						    	
							    	stopflag = true;
							    	break;
							    }
						    }						    
						   
						    break;    
					   
					    case "class java.util.HashMap":
						    returnvalue = comparemaps(stopflag,(java.util.HashMap) currentexpectedvalue,(java.util.HashMap) currentactualvalue);
						    if(returnvalue==false)						    	
						    	stopflag = true;
						    break; 
						    
					    default:
					    	String strcurrentexpectedvalue = FunctionLibrary.RetrieveData("" + currentexpectedvalue + "");					    	
					    	
					    	if(strcurrentexpectedvalue.contains("ARTEMISSTORE-")) {
								String[] strcurrentexpectedvaluesplit = strcurrentexpectedvalue.split("ARTEMISSTORE-",-1);
								if(toStore(expectedmap,actualmap))								
									FunctionLibrary.StoreValue(strcurrentexpectedvaluesplit[1], "" + currentactualvalue + "");
							}else {					    	
						    	if(strcurrentexpectedvalue.equals("" + currentactualvalue + ""))
							    	returnvalue = true;
							    else{
								    returnvalue = false;
								    stopflag = true;
							    }					    
							}
						    break;
				    }    
			   
			    }
			    else{
				    returnvalue = false;				    
				    stopflag = true;
			    }
		    }
		    else{
			    returnvalue =  false;		    
			    stopflag = true;
	    }
	    
	}
	
	return returnvalue;
}
	
	public static String getJsonstring(String filename) throws Exception{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);            
            line = br.readLine();
        }        
		return sb.toString();
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
	
	public static String getJSONResponse(HttpResponse response) throws UnsupportedOperationException, IOException {
		String json="";		
		json = IOUtils.toString(response.getEntity().getContent());		
		return json;
	}
	
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();
	
	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);
	
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }
	
	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
	
	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }
	
	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}
	
	public static boolean isJson(String str) {		
		try {
			new JSONObject(str);
			return true;
	    } catch (Exception e) {
	    	return false;
	    }
	}

	public static boolean toStore(Map<String, Object> expectedmap, Map<String, Object> actualmap) {
		boolean toreturn = true;
		
		for (Map.Entry<String, Object> entry : expectedmap.entrySet()){		
		   String expectedkey = entry.getKey();
		   String expectedvalue = "" + entry.getValue() + "";
		   
		   if(!expectedvalue.contains("ARTEMISSTORE-")) {
			   if(actualmap.containsKey(expectedkey)){
				   String actualvalue = "" + actualmap.get(expectedkey) + "";
				   if(!actualvalue.equalsIgnoreCase(expectedvalue))
					   toreturn = false;								
			   }else {
				   toreturn = false;
			   }
		   }
		}
		
		return toreturn;
		
	}
	
}

