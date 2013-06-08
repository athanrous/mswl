package com.example.final_exer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class TwitterSearchActivity extends Activity {

	//field to update with retrieved tweets
	private TextView tweetDisplay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter_search);
		//reference throughout class
		tweetDisplay = (TextView)findViewById(R.id.tweet_txt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.twitter_search, menu);
		return true;
	}

	/*
	 * Called when user presses button
	 */
	public void searchTwitter(View view){
		//get user entered search term
		EditText searchTxt = (EditText)findViewById(R.id.search_edit);
		String searchTerm = "#oSC13";
		if(searchTerm.length()>0){
			try{
				//encode in case user has included symbols such as spaces etc
				String encodedSearch = URLEncoder.encode(searchTerm, "UTF-8");
				//append encoded user search term to search URL
				String searchURL = "http://search.twitter.com/search.json?q="+encodedSearch;
				//instantiate and execute AsyncTask
				new GetTweets().execute(searchURL);

			}
			catch(Exception e){ 
				tweetDisplay.setText("Whoops - something went wrong!");
				e.printStackTrace(); 
			}
		}
		else 
			tweetDisplay.setText("Enter a search query!");
	}

	/*
	 * AsyncTask class to fetch tweets in background thread
	 *  - receives URL search string and updates UI on calling execute
	 *  - specify parameter and return types as strings
	 *  - parameter is twitter URL search string
	 *  - result is retrieved tweets
	 */
	private class GetTweets extends AsyncTask<String, Void, String> {
		/*
		 * Carry out fetching task in background
		 * - receives search URL via execute method
		 */
		@Override
		protected String doInBackground(String... twitterURL) {
			//start building result which will be json string
			StringBuilder tweetFeedBuilder = new StringBuilder();
			//should only be one URL, receives array
			for (String searchURL : twitterURL) {
				HttpClient tweetClient = new DefaultHttpClient();
				try {
					//pass search URL string to fetch
					HttpGet tweetGet = new HttpGet(searchURL);
					//execute request
					HttpResponse tweetResponse = tweetClient.execute(tweetGet);
					//check status, only proceed if ok
					StatusLine searchStatus = tweetResponse.getStatusLine();
					if (searchStatus.getStatusCode() == 200) {
						//get the response
						HttpEntity tweetEntity = tweetResponse.getEntity();
						InputStream tweetContent = tweetEntity.getContent();
						//process the results
						InputStreamReader tweetInput = new InputStreamReader(tweetContent);
						BufferedReader tweetReader = new BufferedReader(tweetInput);
						String lineIn;
						while ((lineIn = tweetReader.readLine()) != null) {
							tweetFeedBuilder.append(lineIn);
						}
					}
					else
						tweetDisplay.setText("Whoops - something went wrong!");
				}
				catch(Exception e){ 
					tweetDisplay.setText("Whoops - something went wrong!");
					e.printStackTrace(); 
				}
			}
			//return result string
			return tweetFeedBuilder.toString();
		}
		/*
		 * Process result of search query
		 * - this receives JSON string representing tweets with search term included
		 */
		protected void onPostExecute(String result) {
			//start preparing result string for display
			StringBuilder tweetResultBuilder = new StringBuilder();
			try {
				//get JSONObject from result
				JSONObject resultObject = new JSONObject(result);
				//get JSONArray contained within the JSONObject retrieved - "results"
				JSONArray tweetArray = resultObject.getJSONArray("results");
				//loop through each item in the tweet array
				for (int t=0; t<tweetArray.length(); t++) {
					//each item is a JSONObject
					JSONObject tweetObject = tweetArray.getJSONObject(t);
					//get the username and text content for each tweet
					tweetResultBuilder.append(tweetObject.getString("from_user")+": ");
					tweetResultBuilder.append(tweetObject.get("text")+"\n\n");
				}
			}
			catch (Exception e) {
				tweetDisplay.setText("Something went wrong!");
				e.printStackTrace();
			}
			//check result exists
			if(tweetResultBuilder.length()>0)
				tweetDisplay.setText(tweetResultBuilder.toString());
			else
				tweetDisplay.setText("No tweets found for your search!");
		}
	}


}

