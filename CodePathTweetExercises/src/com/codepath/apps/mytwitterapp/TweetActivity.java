package com.codepath.apps.mytwitterapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.mytwitterapp.models.Tweet;

public class TweetActivity extends Activity {
	
	EditText etTweetBody;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tweet);
		
		etTweetBody = (EditText) findViewById(R.id.etTweetBody);
		
		etTweetBody.addTextChangedListener(new TextWatcher(){

			@Override
			public void afterTextChanged(Editable s) {
				if(s.length() >= 140){
					Toast.makeText(getApplicationContext(), "Tweets can only be 140 charcters", Toast.LENGTH_SHORT).show();
				}
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tweet, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.action_tweet:
	    	pushTweetToServer();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}

	private void pushTweetToServer() {
		Toast.makeText(this, "Tweet Posted to Server", Toast.LENGTH_SHORT).show();
		Intent i = new Intent(this, TimelineActivity.class);
		Tweet tweet = new Tweet();
		tweet.setBody(etTweetBody.getText().toString());
		tweet.postTweet();
		i.putExtra("new_tweet", tweet);
		startActivity(i);
	}

}
