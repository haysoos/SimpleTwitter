package com.codepath.apps.mytwitterapp;

import java.util.List;

import org.json.JSONArray;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.codepath.apps.mytwitterapp.fragments.TweetsListFragment;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class TimelineActivity extends FragmentActivity {

	TweetsListFragment fragmentTweets;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeline);
		fragmentTweets = 
				(TweetsListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentTweets);
				
		Tweet tweet = null;
		if(savedInstanceState != null && savedInstanceState.containsKey("new_tweet")){
			tweet = (Tweet)savedInstanceState.get("new_tweet");
		}
		loadHomeTimeLine(tweet);
		
	}

	private void loadHomeTimeLine(final Tweet tweet) {
		TwitterClientApp.getRestClient().getHomeTimeLine(new JsonHttpResponseHandler(){
			
			@Override
			public void onSuccess(JSONArray jsonTweets){
				Log.d("DEBUG", jsonTweets.toString());
				List<Tweet> tweets = Tweet.fromJson(jsonTweets);
				if(tweet != null){
					tweets.add(0, tweet);
				}
				fragmentTweets.getAdapter().addAll(tweets);
			}			
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.action_create:
	        tweetActivity();
	    	return true;
	    case R.id.action_refresh:
	    	loadHomeTimeLine(null);
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	private void tweetActivity() {
		Intent i = new Intent(this, TweetActivity.class);
		startActivity(i);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.timeline, menu);
		return true;
	}

}
