package com.study.kafka.kafkatwitterproducer.utils;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

@Service
public class FactoryClass {

	@Autowired
	NewTopic zilkerTopic;

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	public static Twitter getTwitterInstance() {
		return TwitterFactory.getSingleton();
	}

	public static TwitterStream getTwitterStreamInstance() {
		return new TwitterStreamFactory().getInstance();
	}

	public StatusListener getListener() {
		return new StatusListener() {
			@Override
			public void onStatus(Status status) {
				try {
//					System.out.println("............................");
					System.out.println(kafkaTemplate);
//					System.out.println("............................");
//					System.out.println(status.getUser().getName() + " : " + status.getText());
//					kafkaTemplate.send(zilkerTopic.name(), status.getUser().getName() + " : " + status.getText());
//					Util.kafkaSend(status.getText());

//					System.out.println(status.getUser().getName());
//					HashtagEntity[] hashtagsEntities = status.getHashtagEntities();
//					System.out.println("hashtags used: ");
//					for (HashtagEntity hashtag : hashtagsEntities) {
//						System.out.println("#" + hashtag.getText());
//					}

				} catch (Exception e) {
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
}
