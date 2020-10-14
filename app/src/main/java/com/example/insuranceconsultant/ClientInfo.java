package com.example.insuranceconsultant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ClientInfo {
    @Getter private String numPolis;
    @Getter private String name;
    @Getter private String dateBirth;
    @Getter private String address;
    @Getter private String telephoneNum;
    @Getter private String consultantNum;

}
