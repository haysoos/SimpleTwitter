package com.codepath.apps.mytwitterapp.models;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.activeandroid.annotation.Table;
import com.codepath.apps.mytwitterapp.TwitterClientApp;
import com.loopj.android.http.JsonHttpResponseHandler;

@Table(name = "Tweets")
public class Tweet extends BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3926017141139695871L;
	private User user;
	private String body;
	private long id;
	private boolean isFavorited;
	private boolean isRetweeted;


	public User getUser() {
		return user;
	}

	public String getBody() {
		return body;
	}

	public long getId() {
		return id;
	}

	public boolean isFavorited() {
		return isFavorited;
	}

	public boolean isRetweeted() {
		return isRetweeted;
	}

	public static Tweet fromJson(JSONObject jsonObject) {
		Tweet tweet = new Tweet();
		try {
			Log.d("DEBUG", "Trying to load tweet from jsonObject");
			tweet.jsonObject = jsonObject;
			tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
			tweet.body = tweet.getString("text");
			tweet.id = tweet.getLong("id");
			tweet.isFavorited = tweet.getBoolean("favorited");
			tweet.isRetweeted = tweet.getBoolean("retweeted");            

		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return tweet;
	}

	public static ArrayList<Tweet> fromJson(JSONArray jsonArray) {
		ArrayList<Tweet> tweets = new ArrayList<Tweet>(jsonArray.length());

		for (int i=0; i < jsonArray.length(); i++) {
			JSONObject tweetJson = null;
			try {
				tweetJson = jsonArray.getJSONObject(i);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}

			Tweet tweet = Tweet.fromJson(tweetJson);
			if (tweet != null) {
				tweets.add(tweet);
			}
		}

		return tweets;
	}

	public String toString(){
		return new StringBuilder().
				append("Tweet [id:").
				append(id).
				append(",favorited:").
				append(isFavorited).
				append(",retweeted:").
				append(isRetweeted).toString();

	}

	public void setBody(String body) {
		this.body = body;
	}

	public void postTweet() {
		TwitterClientApp.getRestClient().postTweet(this, new JsonHttpResponseHandler(){
			@Override
			public void onSuccess(JSONArray jsonTweets){
				Log.d("Debug", jsonTweets.toString());
			}
		});
		
	}
}