package com.example.salesmanager;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
//evala ws primary keys to id pelath , id proiontos kai to date , giati mporei kapoios pelaths na thelei na 3anagorasei kati apo to magazi sto mellon
@Entity(tableName = "pwlhseis",
        primaryKeys = {"pwlhseis_pelatisID","pwlhseis_proionID","pwlhseis_date"},
        foreignKeys = {
        @ForeignKey(entity = Pelates.class,
                    parentColumns = "pelatisID",
                    childColumns = "pwlhseis_pelatisID",
                    onDelete = ForeignKey.CASCADE,
                    onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity = Proionta.class,
                    parentColumns = "proionID",
                    childColumns = "pwlhseis_proionID",
                    onDelete = ForeignKey.CASCADE,
                    onUpdate = ForeignKey.CASCADE)})
public class Pwlhseis {
        @ColumnInfo (name = "pwlhseis_pelatisID")@NonNull
        private int pelatisID;

        @ColumnInfo (name = "pwlhseis_proionID")@NonNull
        private int proionID;

        @ColumnInfo(name = "pwlhseis_date")@NonNull
        private String date;

        @ColumnInfo(name = "pwlhseis_posothta")
        private int posothta;

    public int getPelatisID() {
        return pelatisID;
    }

    public void setPelatisID(int pelatisID) {
        this.pelatisID = pelatisID;
    }

    public int getProionID() {
        return proionID;
    }

    public void setProionID(int proionID) {
        this.proionID = proionID;
    }

    @NonNull
    public String getDate() {
        return date;
    }

    public void setDate(@NonNull String date) {
        this.date = date;
    }

    public int getPosothta() {
        return posothta;
    }

    public void setPosothta(int posothta) {
        this.posothta = posothta;
    }
}
