package com.study.kafka.kafkatwitterproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.kafka.kafkatwitterproducer.utils.TwitterFunctionalities;

@RestController
@RequestMapping("kafka")
class UserResourceController {

//	TwitterFunctionalities funcObj = new TwitterFunctionalities();
	
	@Autowired
	TwitterFunctionalities funcObj;

	@GetMapping("/publish/stream")
	public String printStream() {
		funcObj.printStream();
		return "streaming";
	}

	@GetMapping("/publish/searchStream/{keywordsArray}")
	public String searchAndPrintStream(@PathVariable("keywordsArray") String[] keywordsArray) {
		funcObj.searchAndPrintStream(keywordsArray);
		return "streaming searchAndPrintStream";
	}

	@GetMapping("/publish/streamFilter")
	public String streamFilter() {
		funcObj.streamFilter();
		return "streaming streamFilter";
	}

	@GetMapping("/publish/stopStream")
	public void stopStream() {
		funcObj.stopStream();
	}

}

//@GetMapping("/publish/message/{message}")
//public String post(@PathVariable("message") final String message) {
//	kafkaTemplate.send(TOPIC, message);
//	return "publishing message : " + message;
//}

//@GetMapping("/publish/message/{message}")
//public String produceMessage(@PathVariable("message") String message) {
//	for (int i = 0; i < 10; i++) {
//		Util.produceMessage(	message);
//	}
//	return "Produced Successfully - " + message;
//}
