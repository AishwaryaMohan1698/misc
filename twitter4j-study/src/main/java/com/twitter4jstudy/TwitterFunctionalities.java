package com.twitter4jstudy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.User;

public class TwitterFunctionalities {

	Twitter twitter = TwitterFactory.getSingleton();
	StatusListener listener = null;

	public TwitterFunctionalities() {
//		ConfigurationBuilder cb = new ConfigurationBuilder();
//		cb.setDebugEnabled(true).setOAuthConsumerKey("Bb5HYTs87vXcTTQnPRv1jfKD4")
//				.setOAuthConsumerSecret("dKlRffNiDE0BBigPm8ApGZNjXkwAv6LNl7FpEYSwR2zBvOCKL8")
//				.setOAuthAccessToken("1126046545512525827-Rvo9n80Lc9K0kLGTA4kpWb5UtgAVhN")
//				.setOAuthAccessTokenSecret("aLUBaorntKhhygSS2RYnSMNNkgFBfihJf6UEnydQgPS1Q");
//		TwitterFactory tf = new TwitterFactory(cb.build());
//		Twitter twitter = tf.getInstance();

		listener = new StatusListener() {
			@Override
			public void onStatus(Status status) {
				// prints every status in the stream
				System.out.println(status.getUser().getName() + " : " + status.getText());

				// write to text file
				try {
					Util.writeStringToFile("/home/css/twitterFile/dataFiles/file1.txt",
							status.getCreatedAt() + "|" + status.getText() + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
			}

			@Override
			public void onException(Exception ex) {
				ex.printStackTrace();
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
			}

			@Override
			public void onStallWarning(StallWarning warning) {
			}
		};
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

		TwitterStream twitterStream = new TwitterStreamFactory().createInstance();
		twitterStream.addListener(listener);
		// sample() method internally creates a thread which manipulates TwitterStream
		// and calls these adequate listener methods continuously.
		twitterStream.sample();
	}

	public void searchTweets() {
		try {
			Query query = new Query("BTS");
			QueryResult result = twitter.search(query);
			for (Status status : result.getTweets()) {
				System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchTweetsAndStream() {
		try {
			TwitterStream twitterStream = new TwitterStreamFactory().createInstance();
			twitterStream.addListener(listener);

			// Filter
			FilterQuery filter = new FilterQuery();
			String[] keywordsArray = { "BTS", "JIN" };
			filter.track(keywordsArray);
			twitterStream.filter(filter);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchWriteUsingOutputStream() {
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
}
