package com.example.salesmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class KalathiAgorwn extends Fragment implements View.OnClickListener {
    //creating variables
    TextView kalathiAgorwn;
    Button buyItems;
    Button eraseItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kalathi_agorwn, container, false);

        //antistoixizw metavlhtes me stoixeia toy ui
        kalathiAgorwn = view.findViewById(R.id.kalathiAgorwnTextView);
        buyItems = view.findViewById(R.id.buyItemsCart);
        eraseItems = view.findViewById(R.id.deleteCartButton);

        showKalathi();
        buyItems.setOnClickListener(this);
        eraseItems.setOnClickListener(this);
        return view;
    }
    //method to delete to kalathi agorwn
    public void deleteKalathi(){
        MainActivity.salesManagerDatabase.myDao().deleteCart();
        Toast.makeText(getActivity(), "to kalathi agorwn ananewthike", Toast.LENGTH_SHORT).show();
        showKalathi();
    }
    //method gia na steilw ola ta items apo to kalathi agorwn kai na ta kataxwrysw san pwlhseis
    public void buyKalathi(){
        List<Kalathi> kalathi = MainActivity.salesManagerDatabase.myDao().getKalathi();
        Pwlhseis pwlhsh = new Pwlhseis();
        int pelatisId=0;
        int proionId=0;
        String date = "";
        int posothta=0;
        int stock;
        int newstock;
        //gia kathe item sto kalathi , to pernaw san mia pwlhsh sto table twn pwlhsewn
        for(Kalathi k:kalathi){

            pelatisId=k.getKalathiPelatisID();
            proionId=k.getKalathiProionID();
            date=k.getKalathiDate();
            posothta=k.getKalathiPosothta();

            pwlhsh.setPelatisID(pelatisId);
            pwlhsh.setProionID(proionId);
            pwlhsh.setDate(date);
            pwlhsh.setPosothta(posothta);

            MainActivity.salesManagerDatabase.myDao().addPwlhsh(pwlhsh);

            //ananewnw to stock toy kathe proiontos
            stock=MainActivity.salesManagerDatabase.myDao().getStockFromProion(proionId);
            newstock = stock - posothta;
            MainActivity.salesManagerDatabase.myDao().setNewStock(newstock,proionId);
        }
        //afou h paraggelia perastike , svhnw to cart
        MainActivity.salesManagerDatabase.myDao().deleteCart();
    }
    //method to display to kalathi se ena textview
    public void showKalathi(){
        List<Kalathi> kalathi = MainActivity.salesManagerDatabase.myDao().getKalathi();
        String result = "";
        int idPelati = 0;
        int counter=0;
        for(Kalathi k: kalathi){
            int idProiontos = k.getKalathiProionID();
            idPelati = k.getKalathiPelatisID();
            String date = k.getKalathiDate();
            int pososthta = k.getKalathiPosothta();
            //gia kathe item sto kalathi , an to id toy pelath einai diaforetiko apo ton selected pelath apo to home fragment , anevase ton counter
            //auto ginetai gia na eleg3w an yparxoun panw apo 2 pelates sto idio kalathi gegonos poy de to thelw.
            if(k.getKalathiPelatisID()!=MainActivity.selected_customer_ID){
                counter++;
            }
            else{
                result = result + "\nIdProiontos: "+ idProiontos + "\nIdPelati: "+idPelati+ "\nDate: "+date+ "\nQuantity: "+pososthta+"\n";
            }
        }
        //an o counter einai megalyteros toy 0 , dhladh an yparxoun panw apo 1 pelates sto kalathi
        if(counter>0){
            manyUsersOnKalathi();
            Toast.makeText(getActivity(), "Δεν γίνεται να καταχωρήσετε διαφορετικούς πελάτες στο ίδιο καλάθι αγορών", Toast.LENGTH_LONG).show();
        }
        else{
            kalathiAgorwn.setText(result);
        }
    }
    //methodos gia na svhnw to kalathi agorwn otan yparxoun panw apo 1 pelates se auto
    public void manyUsersOnKalathi(){
        MainActivity.salesManagerDatabase.myDao().deleteCart();
    }
    //methodos gia na antistoixhsw koumpi me function onclick
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buyItemsCart:
                buyKalathi();
            case R.id.deleteCartButton:
                deleteKalathi();
        }
    }
}
