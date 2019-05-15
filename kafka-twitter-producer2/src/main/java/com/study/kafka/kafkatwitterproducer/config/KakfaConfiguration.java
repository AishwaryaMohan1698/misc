//package com.study.kafka.kafkatwitterproducer.config;
//
////@Configuration
//public class KakfaConfiguration {
//
////	@Value(value = "${kafka.bootstrapAddress}")
////	private String bootstrapAddress;
////
////	@Bean
////	public ProducerFactory<String, String> producerFactory() {
////		Map<String, Object> config = new HashMap<>();
////		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
////		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////		return new DefaultKafkaProducerFactory<String, String>(config);
////	}
////
////	@Bean
////	public KafkaTemplate<String, String> kafkaTemplate() {
////		return new KafkaTemplate<>(producerFactory());
////	}
//
//}
