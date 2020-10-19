package com.example.insuranceconsultant;

import android.os.Parcel;
import android.os.Parcelable;

import lombok.AllArgsConstructor;
import lombok.Getter;

//@AllArgsConstructor
public class ClientInfo implements Parcelable {
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

    protected ClientInfo(Parcel in) {
        numPolis = in.readString();
        name = in.readString();
        dateBirth = in.readString();
        telephoneNum = in.readString();
        address = in.readString();
        consultantNum = in.readString();
    }

    public static final Creator<ClientInfo> CREATOR = new Creator<ClientInfo>() {
        @Override
        public ClientInfo createFromParcel(Parcel in) {
            return new ClientInfo(in);
        }

        @Override
        public ClientInfo[] newArray(int size) {
            return new ClientInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numPolis);
        dest.writeString(name);
        dest.writeString(dateBirth);
        dest.writeString(telephoneNum);
        dest.writeString(address);
        dest.writeString(consultantNum);
    }
}
