package com.example.salesmanager;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
//o pinakas kalathi einai idios me ton pinaka pwlhseis , alla xrhsimopoieitai gia th diaxeirish toy kalathiou agorwn
@Entity (tableName = "kalathi",
        primaryKeys = {"kalathi_pelatisID","kalathi_proionID","kalathi_date"},
        foreignKeys = {
                @ForeignKey(entity = Pelates.class,
                        parentColumns = "pelatisID",
                        childColumns = "kalathi_pelatisID",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE),
                @ForeignKey(entity = Proionta.class,
                        parentColumns = "proionID",
                        childColumns = "kalathi_proionID",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE)})
public class Kalathi {
    @ColumnInfo(name = "kalathi_pelatisID")@NonNull
    private int kalathiPelatisID;

    @ColumnInfo (name = "kalathi_proionID")@NonNull
    private int kalathiProionID;

    @ColumnInfo(name = "kalathi_date")@NonNull
    private String kalathiDate;

    @ColumnInfo(name = "kalathi_posothta")
    private int kalathiPosothta;

    public int getKalathiPelatisID() {
        return kalathiPelatisID;
    }

    public void setKalathiPelatisID(int kalathiPelatisID) {
        this.kalathiPelatisID = kalathiPelatisID;
    }

    public int getKalathiProionID() {
        return kalathiProionID;
    }

    public void setKalathiProionID(int kalathiProionID) {
        this.kalathiProionID = kalathiProionID;
    }

    @NonNull
    public String getKalathiDate() {
        return kalathiDate;
    }

    public void setKalathiDate(@NonNull String kalathiDate) {
        this.kalathiDate = kalathiDate;
    }

    public int getKalathiPosothta() {
        return kalathiPosothta;
    }

    public void setKalathiPosothta(int kalathiPosothta) {
        this.kalathiPosothta = kalathiPosothta;
    }
}
