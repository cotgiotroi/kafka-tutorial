package com.gaurav.kafka.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaurav.kafka.pojo.CustomObject;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;

public class CustomDeserializer implements Deserializer<CustomObject> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public CustomObject deserialize(String topic, byte[] data) {
        ObjectMapper mapper = new ObjectMapper();
        CustomObject object = null;
//        try {
//            object = mapper.readValue(data, CustomObject.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return object;
    }

    @Override
    public void close() {
    }
}