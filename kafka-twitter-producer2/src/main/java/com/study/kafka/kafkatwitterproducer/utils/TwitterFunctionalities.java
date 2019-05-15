package com.study.kafka.kafkatwitterproducer.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.User;

@Service
public class TwitterFunctionalities {

	Twitter twitter = null;
	StatusListener listener = null;
	TwitterStream twitterStream = null;

	public TwitterFunctionalities() {
		twitter = FactoryClass.getTwitterInstance();
		listener = new FactoryClass().getListener();
		twitterStream = FactoryClass.getTwitterStreamInstance();
		twitterStream.addListener(listener);
	}

	public void printMyTimeline() {
		try {
			User user = twitter.verifyCredentials();
			List<Status> statuses = twitter.getHomeTimeline();
			System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
			for (Status status : statuses) {
				System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
			}
		} catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to get timeline: " + te.getMessage());
			System.exit(-1);
		}
	}

	public void postTweet() {
		String latestStatus = "Hello";
		Status status = null;
		try {
			status = twitter.updateStatus(latestStatus);
			System.out.println("Successfully updated the status to [" + status.getText() + "].");
		} catch (TwitterException e) {
			e.printStackTrace();
		}
	}

	public void printStream() {
		twitterStream.sample();
	}

	public void searchTweets() {
		try {
			Query query = new Query("India");
			QueryResult result = twitter.search(query);
			for (Status status : result.getTweets()) {
				System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchAndPrintStream(String[] keywordsArray) {
		try {
			// Filter
			FilterQuery filter = new FilterQuery();
//			String[] keywordsArray = { "BTS", "JIN" };
			filter.track(keywordsArray);
			twitterStream.filter(filter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchAndWriteInFile() {
		OutputStream os = null;
		try {
			Query query = new Query("BTS");
			QueryResult result = twitter.search(query);

			os = new FileOutputStream(new File("/home/css/twitterFile/dataFiles/file1.txt"));

			for (Status status : result.getTweets()) {
				System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
				os.write(status.getText().getBytes(), 0, status.getText().length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void streamFilter() {

		// filtering is OR
		// AND is not available

		double locations[][] = { { 68.116667, 8.066667, }, { 97.416667, 37.100000, } };
		String[] keywordsArray = { "india", "tamil" };
		String[] languages = { "ta" };

		try {
			FilterQuery filter = new FilterQuery();

			filter.track(keywordsArray); // search for keywords
			filter.locations(locations); // search for location
			filter.language(languages); // search for language

			twitterStream.filter(filter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopStream() {
		twitterStream.cleanUp(); // shutdown internal stream consuming thread
		twitterStream.shutdown(); // Shuts down internal dispatcher thread shared by all TwitterStream instances.
		System.out.println("Stream ended");
	}
}
