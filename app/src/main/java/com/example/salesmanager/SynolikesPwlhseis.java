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


public class SynolikesPwlhseis extends Fragment implements View.OnClickListener {
    //create variables
    TextView pwlhseisTextView;
    Button deleteAgores;
    EditText idPelath;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_synolikes_pwlhseis, container, false);
        //vriskw stoixeia toy ui kai ta antistoixizw se metavlhtes
        pwlhseisTextView = view.findViewById(R.id.showPwlhseisTextView);
        deleteAgores = view.findViewById(R.id.deleteAgoresButton);
        idPelath = view.findViewById(R.id.idPelathSynolikePwlhseisEditText);

        showPwlhseis();
        deleteAgores.setOnClickListener(this);
        return view;
    }
    //method gia na kanw delete agores enos sugkekrimenou pelath apo to sunolo twn agorwn
    public void deleteAgores(){
        int pelatisId=0;
        try {
            pelatisId = Integer.parseInt(idPelath.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        MainActivity.salesManagerDatabase.myDao().deleteAgoresPelath(pelatisId);
        showPwlhseis();
    }
    //method gia na kanw display ton pinaka twn polhsewn se ena textview
    public void showPwlhseis(){
        List<Pwlhseis> pwlhseis = MainActivity.salesManagerDatabase.myDao().getPwlhseis();
        String result = "";
        for(Pwlhseis p: pwlhseis){
            int idPelath = p.getPelatisID();
            int idProiontos = p.getProionID();
            String date = p.getDate();
            int posothta = p.getPosothta();
            result = result + "\nId pelath: "+ idPelath + "\nId proiontos: "+idProiontos+ "\ndate: "+date+ "\nPosothta: "+posothta+"\n";
        }
        pwlhseisTextView.setText(result);
    }
    //methodos poy ekteleitai otan pataw to koumpi toy ui
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.deleteAgoresButton:
                deleteAgores();
        }
    }
}
