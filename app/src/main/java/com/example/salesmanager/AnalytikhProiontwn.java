package com.example.salesmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AnalytikhProiontwn extends Fragment implements View.OnClickListener {
    //create variables
    Button addButton;
    Button delButton;
    Button editButton;

    EditText editTextID;
    EditText editTextPerigrafh;
    EditText editTextTimh;
    EditText editTextStock;

    TextView AnalytikhProiontwnTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_analytikh_proiontwn, container, false);
        //vriskw stoixeia toy ui kai ta antistoixizw se metavlhtes
        addButton=view.findViewById(R.id.addProionButton);
        delButton=view.findViewById(R.id.deleteProionButton);
        editButton=view.findViewById(R.id.updateProionButton);

        editTextID=view.findViewById(R.id.iDproiontosEditText);
        editTextPerigrafh=view.findViewById(R.id.perigrafhProiontosEditText);
        editTextTimh=view.findViewById(R.id.timhProiontosEditText);
        editTextStock=view.findViewById(R.id.stockProiontosEditText);

        AnalytikhProiontwnTextView=view.findViewById(R.id.analytikhProiontwnTextView);
        showProionta();


        addButton.setOnClickListener(this);
        delButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        return view;
    }
    //method to insert proion sth vash
    public void insertProion(){
        int proionID = 0;
        try {
            proionID = Integer.parseInt(editTextID.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String perigrafh = editTextPerigrafh.getText().toString();
        double timh = 0;
        try{
            timh = Double.parseDouble(editTextTimh.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int stock = 0;
        try{
            stock = Integer.parseInt(editTextStock.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        Proionta proion = new Proionta();
        proion.setPerigrafh(perigrafh);
        proion.setProionID(proionID);
        proion.setStock(stock);
        proion.setTimh(timh);

        MainActivity.salesManagerDatabase.myDao().addProion(proion);

        editTextID.setText("");
        editTextStock.setText("");
        editTextTimh.setText("");
        editTextPerigrafh.setText("");
        showProionta();
    }

    //method to delete proion apo th vash
    public void deleteProion(){
        int proionID = 0;
        try {
            proionID = Integer.parseInt(editTextID.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Proionta proion = new Proionta();
        proion.setProionID(proionID);
        MainActivity.salesManagerDatabase.myDao().deleteProion(proion);
        editTextID.setText("");
        showProionta();
    }

    //method to update proion sth vash
    public void updateProion(){
        int proionID = 0;
        try {
            proionID = Integer.parseInt(editTextID.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String perigrafh = editTextPerigrafh.getText().toString();
        double timh = 0;
        try{
            timh = Double.parseDouble(editTextTimh.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        int stock = 0;
        try{
            stock = Integer.parseInt(editTextStock.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Proionta proion = new Proionta();
        proion.setPerigrafh(perigrafh);
        proion.setProionID(proionID);
        proion.setStock(stock);
        proion.setTimh(timh);

        MainActivity.salesManagerDatabase.myDao().updateProion(proion);

        editTextID.setText("");
        editTextStock.setText("");
        editTextTimh.setText("");
        editTextPerigrafh.setText("");
        showProionta();
    }

    //method gia na kanw display ta proionta sto ui mesa se ena textview
    public void showProionta(){
        List<Proionta> proion = MainActivity.salesManagerDatabase.myDao().getProionta();
        String result = "";
        for(Proionta p: proion){
            int id = p.getProionID();
            String perigrafh = p.getPerigrafh();
            double timh = p.getTimh();
            int stock = p.getStock();
            result = result + "\nId: "+ id + "\nPerigrafh: "+perigrafh+ "\nTimh: "+timh+ "\nStock: "+stock+"\n";
        }
        AnalytikhProiontwnTextView.setText(result);
    }

    //onclick gegonota analoga me to tti koumpi pataw
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addProionButton:
                insertProion();
            case R.id.deleteProionButton:
                deleteProion();
            case R.id.updateProionButton:
                updateProion();
        }
    }
}
