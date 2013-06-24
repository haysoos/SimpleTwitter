package com.codepath.apps.mytwitterapp.fragments;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.mytwitterapp.R;
import com.codepath.apps.mytwitterapp.models.Tweet;
import com.codepath.apps.mytwitterapp.models.TweetsAdapter;


public class TweetsListFragment extends Fragment {

	private TweetsAdapter adapter;
	private ListView lvTweets;
	private List<Tweet> tweets;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadTimeline();
	}

	private void loadTimeline() {
		
		lvTweets = (ListView) getActivity().findViewById(R.id.lvTweets);
		tweets = new ArrayList<Tweet>();
		adapter = new TweetsAdapter(getActivity(), tweets);
		lvTweets.setAdapter(adapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_tweets_list, container, false);
	}

	public TweetsAdapter getAdapter() {
		return adapter;
	}
	
	
}
