package com.example.ds2022_30242_stecko_daiana_2_sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Controller implements CommandLineRunner {

    private final Sender sender;

    @Autowired
    public Controller(Sender sender) {
        this.sender = sender;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sent!");
        sender.send();
    }
}
