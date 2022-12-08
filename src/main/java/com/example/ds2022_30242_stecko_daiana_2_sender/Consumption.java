package com.example.ds2022_30242_stecko_daiana_2_sender;

import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter

public class Consumption {
    private UUID consumptionId;
    private UUID deviceId;
    private Timestamp timestamp;
    private Float energyconsum;

    public Consumption (UUID deviceId, Timestamp timestamp, Float energyconsum) {
         this.deviceId = deviceId;
         this.timestamp = timestamp;
         this.energyconsum = energyconsum;
    }
}


