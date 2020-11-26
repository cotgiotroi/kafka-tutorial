package com.gaurav.kafka;

import com.gaurav.kafka.constants.IKafkaConstants;
import com.gaurav.kafka.consumer.ConsumerCreator;
import com.gaurav.kafka.producer.ProducerCreator;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.concurrent.ExecutionException;

public class App {
    public static void main(String[] args) {
//		runProducer();
        runConsumer();
    }

    static void runConsumer() {
        Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
        consumer.seek(new TopicPartition("cdr_ocs_g33", 0), 11882565605l);

        int noMessageToFetch = 0;

        while (true) {
            final ConsumerRecords<Long, String> consumerRecords = consumer.poll(100);
            if (consumerRecords.count() == 0) {
                noMessageToFetch++;
                if (noMessageToFetch > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                    break;
                else
                    continue;
            }

            consumerRecords.forEach(record -> {
                int l = record.value().length();
                System.out.println("=======start========= ");
                System.out.println("Record Key " + record.key());
                System.out.println("Record value = " + record.value());
                System.out.println("Record length = " + l);
                System.out.println("Record partition " + record.partition());
                System.out.println("Record offset " + record.offset());
                System.out.println("=======end========= ");

//					try {
//						FileWriter myWriter = null;
//						myWriter = new FileWriter("filename.txt");
//						myWriter.write(record.value());
//						myWriter.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
            });
            consumer.commitAsync();
        }

        consumer.close();
    }

    static void runProducer() {
        Producer<Long, String> producer = ProducerCreator.createProducer();

        for (int index = 0; index < IKafkaConstants.MESSAGE_COUNT; index++) {
            final ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(IKafkaConstants.TOPIC_NAME,
                    "This is record " + index);
            try {
                RecordMetadata metadata = producer.send(record).get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            } catch (ExecutionException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            } catch (InterruptedException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
    }
}
