package com.example.exercise_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;

public class JSONParser {
	
	 static InputStream is = null;
	 static JSONObject jObj = null;
	 static String json = "";
	 
	 public JSONParser() {
		 
		 
	 }
	 
	 public JSONObject getJSONFromUrl(String url) {
		    try {
		    	
		        HttpParams params = new BasicHttpParams();
		        HttpConnectionParams.setConnectionTimeout(params, 10000);
		        HttpConnectionParams.setSoTimeout(params, 10000);
		        
		        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		        HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);
		        HttpProtocolParams.setUseExpectContinue(params, true);
		       
		        DefaultHttpClient httpClient = new DefaultHttpClient(params);
		        
		        HttpGet httpPost = new HttpGet(url);
		        
		        HttpResponse httpResponse = httpClient.execute(httpPost);
		        
		        HttpEntity httpEntity = httpResponse.getEntity();
		        is = httpEntity.getContent();           
		    } catch (UnsupportedEncodingException ee) {
		        Log.i("UnsupportedEncodingException...", is.toString());
		    } catch (ClientProtocolException e) {
		        Log.i("ClientProtocolException...", is.toString());
		    } catch (IOException e) {
		        Log.i("IOException...", is.toString());
		    }

		    try {
		        BufferedReader reader = new BufferedReader(new InputStreamReader(
		                is, "utf-8"), 8); //We remove the old charset iso-8859-1 and define utf-8
		        StringBuilder stBuilder = new StringBuilder();
		        String line = null;
		        while ((line = reader.readLine()) != null) {
		            stBuilder.append(line + "\n");
		        }
		        is.close();
		        reader.close();
		        json = stBuilder.toString();
		        Log.i("StringBuilder...", json);
		       
		    } catch (Exception e) {
		        Log.e("Buffer Error", "Error converting result " + e.toString());
		    }
		    // try parse the string to a JSON object . Exceptions add from similar case [stackoverflow]
		    try {
		        jObj = new JSONObject(json);
		    } catch (Exception e) {
		        Log.e("JSON Parser", "Error parsing data " + e.toString());
		        try {
		            jObj = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
		        } catch (Exception e0) {
		            Log.e("JSON Parser0", "Error parsing data [" + e0.getMessage()+"] "+json);
		            Log.e("JSON Parser0", "Error parsing data " + e0.toString());
		            try {
		                jObj = new JSONObject(json.substring(1));
		            } catch (Exception e1) {
		                Log.e("JSON Parser1", "Error parsing data [" + e1.getMessage()+"] "+json);
		                Log.e("JSON Parser1", "Error parsing data " + e1.toString());
		                try {
		                    jObj = new JSONObject(json.substring(2));
		                } catch (Exception e2) {
		                    Log.e("JSON Parser2", "Error parsing data [" + e2.getMessage()+"] "+json);
		                    Log.e("JSON Parser2", "Error parsing data " + e2.toString());
		                    try {
		                        jObj = new JSONObject(json.substring(3));
		                    } catch (Exception e3) {
		                        Log.e("JSON Parser3", "Error parsing data [" + e3.getMessage()+"] "+json);
		                        Log.e("JSON Parser3", "Error parsing data " + e3.toString());
		                    }
		                }
		            }
		        }
		    }

		    // return JSON Object so as to handle it afterwards
		    return jObj;
			
			
		}

}
