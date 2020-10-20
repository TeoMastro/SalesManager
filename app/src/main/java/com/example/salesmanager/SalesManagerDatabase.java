package com.example.salesmanager;

import androidx.room.Database;
import androidx.room.RoomDatabase;
//dhlwnw poies klaseis einai oi pinakes ths vashs moy
@Database(entities = {Pelates.class , Proionta.class , Pwlhseis.class , Kalathi.class },version = 1)
public abstract class SalesManagerDatabase extends RoomDatabase {
    public abstract MyDao myDao();
}
