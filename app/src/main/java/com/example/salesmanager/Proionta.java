package com.example.salesmanager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "proionta")
public class Proionta {
    @PrimaryKey
    private int proionID;

    @ColumnInfo (name = "proionta_perigrafh")
    private String perigrafh;

    @ColumnInfo (name = "proionta_timh")
    private double timh;

    @ColumnInfo (name = "proionta_stock")
    private int stock;

    public int getProionID() {
        return proionID;
    }

    public void setProionID(int proionID) {
        this.proionID = proionID;
    }

    public String getPerigrafh() {
        return perigrafh;
    }

    public void setPerigrafh(String perigrafh) {
        this.perigrafh = perigrafh;
    }

    public double getTimh() {
        return timh;
    }

    public void setTimh(double timh) {
        this.timh = timh;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
