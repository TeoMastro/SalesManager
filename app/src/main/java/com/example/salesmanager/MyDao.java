package com.example.salesmanager;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addPelaths(Pelates pelates);

    @Insert
    public void addProion(Proionta proionta);

    @Insert
    public void addPwlhsh(Pwlhseis pwlhseis);

    @Insert
    public void addToCart(Kalathi kalathi);

    @Query("select * from kalathi")
    public List<Kalathi> getKalathi();

    @Query("select * from pwlhseis")
    public List<Pwlhseis> getPwlhseis();

    @Query("delete from pwlhseis where pwlhseis_pelatisID= :id")
    public abstract void deleteAgoresPelath(int id);

    @Query("select * from pelates")
    public List<Pelates> getPelates();

    @Query("select pelatisID from pelates")
    public List<Integer> getIdsPelates();

    @Query("select * from proionta")
    public List<Proionta> getProionta();

    @Query("select proionID from proionta")
    public List<Integer> getIdsProionta();

    @Query("select proionID,proionta_stock,proionta_timh from proionta")
    public List<Proionta> getProionStock();

    //methodos poy xrhsimopoiw otan thelw na ananewsw to stock enos proiontos
    @Query("UPDATE proionta SET proionta_stock= :newStock WHERE proionID = :id")
    public abstract void setNewStock(int newStock,int id);

    //methodos gia na parw to stock enos proiontos me sygkekrimeno id
    @Query("SELECT proionta_stock FROM proionta WHERE proionID = :id")
    public abstract int getStockFromProion(int id);

    @Query("delete from kalathi")
    public void deleteCart();

    @Delete
    public void deletePelath(Pelates pelaths);

    @Delete
    public void deleteProion(Proionta proionta);

    @Update
    public void updatePelath(Pelates pelaths);

    @Update
    public void updateProion(Proionta proionta);
}
