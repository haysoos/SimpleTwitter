package com.codepath.apps.mytwitterapp.fragments;

import java.util.List;

import org.json.JSONArray;

import com.codepath.apps.mytwitterapp.TwitterActivity;
import com.codepath.apps.mytwitterapp.TwitterClientApp;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.util.Log;

public class HomeTimelineFragment extends TweetsListFragment {
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TwitterClientApp.getRestClient().getHomeTimeLine(new JsonHttpResponseHandler(){
			
			@Override
			public void onSuccess(JSONArray jsonTweets){
				Log.d("DEBUG", jsonTweets.toString());
				List<Tweet> tweets = Tweet.fromJson(jsonTweets);
				
				Tweet tweet = ((TwitterActivity) getActivity()).getTweet();
				if(tweet != null){
					tweets.add(0, tweet);
				}
				getAdapter().addAll(tweets);
			}			
		});

	}
}
