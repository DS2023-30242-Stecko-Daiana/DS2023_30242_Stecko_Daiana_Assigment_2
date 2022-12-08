package com.example.ds2022_30242_stecko_daiana_2_sender;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileNotFoundException;

import java.time.Instant;

import java.sql.Timestamp;
import java.util.Scanner;

import java.util.UUID;

@Service
public class Sender{

    private RabbitTemplate rabbitTemplate;
    private final File file = new File("sensor.csv");

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;
    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Autowired
    public Sender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
     public void send() {
         try {
             Scanner scan = new Scanner(file);
             int x = 0;
             Timestamp timestamp = Timestamp.from(Instant.now());
             while (scan.hasNextLine()){
                 Float value = scan.nextFloat();
                 timestamp.setMinutes(timestamp.getMinutes() + x*10);
                 Consumption consumption =
                         new Consumption(UUID.fromString("603ae687-9fd4-4728-b0bf-eb529c372c98"), timestamp, value);
                 ObjectMapper objectMapper = new ObjectMapper();
                 objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

                 String data = objectMapper.writeValueAsString(consumption);
                 rabbitTemplate.convertAndSend(exchange,routingkey,data);
                 x++;
                 Thread.sleep(1000); // 600 000 milliseconds for 10 minutes
             }
         } catch (FileNotFoundException | JsonProcessingException | InterruptedException e) {
             throw new RuntimeException(e);
         }
     }
}
