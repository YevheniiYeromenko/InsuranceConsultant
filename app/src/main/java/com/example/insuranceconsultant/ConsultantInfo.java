package com.example.insuranceconsultant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ConsultantInfo {
    public ConsultantInfo() {
    }

    @Getter private String number;
    @Getter private String name;
    @Getter private String score;
    @Getter private String trainerNumber;
    @Getter private String password;
    @Getter private int level;
}
