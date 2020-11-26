package com.gaurav.kafka.constants;

public interface IKafkaConstants {
//	public static String KAFKA_BROKERS = "10.60.129.62:8092";
	public static String KAFKA_BROKERS = "vbiqueue62:8092,vbiqueue64:8092,vbiqueue65:8092,vbiqueue63:8092,vbiqueue66:8092,vbiqueue67:8092";
//	public static String KAFKA_BROKERS = "10.30.132.62:8092";

	public static Integer MESSAGE_COUNT=1000;
	
	public static String CLIENT_ID="client1";
	
//	public static String TOPIC_NAME="cdr_daily_fee";
	public static String TOPIC_NAME="cdr_ocs_g33";

	public static String GROUP_ID_CONFIG="consumerGroup10";
	
	public static Integer MAX_NO_MESSAGE_FOUND_COUNT=100;
	
	public static String OFFSET_RESET_LATEST="latest";
	
	public static String OFFSET_RESET_EARLIER="earliest";
	
	public static Integer MAX_POLL_RECORDS=1;
}
