package com.example.salesmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;


public class YpoloipoPwlhseis extends Fragment implements View.OnClickListener {
    //create variables
    Button searchButton;
    TextView sold;
    TextView stock;
    EditText id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ypoloipo_pwlhseis, container, false);
        //antistoixizw metavlhtes me stoixeia toy ui
        searchButton = view.findViewById(R.id.searchYpoloipoPwlhseisButton);
        sold = view.findViewById(R.id.poulhthikaTextView);
        stock = view.findViewById(R.id.stockTextView);
        id = view.findViewById(R.id.idProiontosYpoloipoPolhseisEditText);

        searchButton.setOnClickListener(this);
        return view;
    }
    //methodos gia na psa3w to proion gia to opoio thelw leptomeries
    public void searchProion(){
        List<Pwlhseis> pwlhseis = MainActivity.salesManagerDatabase.myDao().getPwlhseis();
        int proionID=0;
        try {
            proionID = Integer.parseInt(id.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        //pairnw to stock toy ekastote proiontos kai to kanw display sto ui
        int stockSelected = MainActivity.salesManagerDatabase.myDao().getStockFromProion(proionID);
        stock.setText(String.valueOf(stockSelected));

        //psaxnw stis pwlhseis kai vgazw to sum apo ta proionta idiou id poy poulhthikan
        int posothta = 0;
        for(Pwlhseis p: pwlhseis){
            if(proionID==p.getProionID()){
                posothta = posothta+p.getPosothta();
            }

        }
        sold.setText(String.valueOf(posothta));

    }
    //onclick to koumpi ektelei th methodo poy toy lew
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.searchYpoloipoPwlhseisButton:
                searchProion();
        }
    }
}
