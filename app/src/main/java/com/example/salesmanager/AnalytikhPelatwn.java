package com.example.salesmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class AnalytikhPelatwn extends Fragment implements View.OnClickListener {
    //create variables
    Button addButton;
    Button delButton;
    Button editButton;

    EditText editTextID;
    EditText editTextName;
    EditText editTextSurname;
    EditText editTextAdress;

    TextView AnalytikhPelatwnTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_analytikh_pelatwn,container,false);
        //vriskw stoixeia toy ui kai ta antistoixizw se metavlhtes
        addButton = view.findViewById(R.id.addPelathButton);
        delButton = view.findViewById(R.id.deletePelathButton);
        editButton = view.findViewById(R.id.editPelathButton);

        editTextID = view.findViewById(R.id.idPelathEditText);
        editTextName = view.findViewById(R.id.NamePelathEditText);
        editTextSurname = view.findViewById(R.id.surnamePelathEditText);
        editTextAdress = view.findViewById(R.id.addressPelathEditText);

        AnalytikhPelatwnTextView = view.findViewById(R.id.AnalytikhPelatwnTextView);
        showPelates();

        addButton.setOnClickListener(this);
        delButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
        return view;
    }
    //method to insert pelath sth vash
    public void insertPelath(){
        int pelatisId = 0;
        try {
            pelatisId = Integer.parseInt(editTextID.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        String address = editTextAdress.getText().toString();

        Pelates pelaths = new Pelates();
        pelaths.setName(name);
        pelaths.setSurname(surname);
        pelaths.setAddress(address);
        pelaths.setPelatisID(pelatisId);

        MainActivity.salesManagerDatabase.myDao().addPelaths(pelaths);

        editTextID.setText("");
        editTextName.setText("");
        editTextSurname.setText("");
        editTextAdress.setText("");
        showPelates();
    }

    //method to delete pelath apo th vash
    public void deletePelath(){
        int pelatisId = 0;
        try {
            pelatisId = Integer.parseInt(editTextID.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Pelates pelaths = new Pelates();
        pelaths.setPelatisID(pelatisId);
        MainActivity.salesManagerDatabase.myDao().deletePelath(pelaths);
        editTextID.setText("");
        showPelates();
    }

    //method to update pelath sth vash
    public void updatePelath(){
        int pelatisId = 0;
        try {
            pelatisId = Integer.parseInt(editTextID.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String name = editTextName.getText().toString();
        String surname = editTextSurname.getText().toString();
        String address = editTextAdress.getText().toString();

        Pelates pelaths = new Pelates();
        pelaths.setName(name);
        pelaths.setSurname(surname);
        pelaths.setAddress(address);
        pelaths.setPelatisID(pelatisId);

        MainActivity.salesManagerDatabase.myDao().updatePelath(pelaths);

        editTextID.setText("");
        editTextName.setText("");
        editTextSurname.setText("");
        editTextAdress.setText("");
        showPelates();
    }

    //method gia na kanw display tous pelates sto ui mesa se ena textview
    public void showPelates(){
        List<Pelates> pelates = MainActivity.salesManagerDatabase.myDao().getPelates();
        String result = "";
        for(Pelates p: pelates){
            int id = p.getPelatisID();
            String name = p.getName();
            String surname = p.getSurname();
            String address = p.getAddress();
            result = result + "\nId: "+ id + "\nName: "+name+ "\nSurname: "+surname+ "\nAddress: "+address+"\n";
        }
        AnalytikhPelatwnTextView.setText(result);
    }

    //onclick gegonota analoga me to tti koumpi pataw
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addPelathButton:
                insertPelath();
            case R.id.deletePelathButton:
                deletePelath();
            case R.id.editPelathButton:
                updatePelath();
        }
    }
}
