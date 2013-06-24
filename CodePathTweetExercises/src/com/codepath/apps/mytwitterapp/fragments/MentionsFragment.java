package com.codepath.apps.mytwitterapp.fragments;

import java.util.List;

import org.json.JSONArray;

import com.codepath.apps.mytwitterapp.TwitterClientApp;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;

public class MentionsFragment extends TweetsListFragment {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterClientApp.getRestClient().getMentionsTimeLine(new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets) {
				
				List<Tweet> tweets = Tweet.fromJson(jsonTweets);
				getAdapter().addAll(tweets);
				
			}
		});
	}

}
