package com.example.salesmanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "pelates")
public class Pelates {
    @PrimaryKey
    private int pelatisID;

    @ColumnInfo (name = "pelates_name")
    private String name;

    @ColumnInfo (name = "pelates_surname")
    private String surname;

    @ColumnInfo (name = "pelates_address")
    private String address;

    public int getPelatisID() {
        return pelatisID;
    }

    public void setPelatisID(int pelatisID) {
        this.pelatisID = pelatisID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
