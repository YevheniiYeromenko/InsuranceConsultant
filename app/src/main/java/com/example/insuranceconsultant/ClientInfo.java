package com.example.insuranceconsultant;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
public class ClientInfo {
    public ClientInfo() {
    }

    public ClientInfo(String numPolis, String name, String dateBirth, String telephoneNum, String address, String consultantNum) {
        this.numPolis = numPolis;
        this.name = name;
        this.dateBirth = dateBirth;
        this.telephoneNum = telephoneNum;
        this.address = address;
        this.consultantNum = consultantNum;
    }

    @Getter private String numPolis;
    @Getter private String name;
    @Getter private String dateBirth;
    @Getter private String telephoneNum;
    @Getter private String address;
    @Getter private String consultantNum;

}
