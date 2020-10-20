package com.example.salesmanager.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.salesmanager.Kalathi;
import com.example.salesmanager.KalathiAgorwn;
import com.example.salesmanager.MainActivity;
import com.example.salesmanager.Pelates;
import com.example.salesmanager.Proionta;
import com.example.salesmanager.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {
    //creating variables
    Spinner pelatisSpinner;
    Spinner proionSpinner;
    EditText dateEditText;
    EditText posothtaEditText;
    Button addToCartButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //antistoixizw metavlhtes me stoixeia toy ui
        pelatisSpinner = view.findViewById(R.id.mainIdPelathSpinner);
        proionSpinner = view.findViewById(R.id.mainIdProiontosSpinner);

        dateEditText = view.findViewById(R.id.mainDateEditText);
        posothtaEditText = view.findViewById(R.id.mainPosothtaEditText);

        addToCartButton = view.findViewById(R.id.mainAddToCartButton);


        fillSpinners();

        addToCartButton.setOnClickListener(this);
        return view;
    }
    //methodos gia na gemisw ta ids sta spinners twn pelatwn kai twn proiontwn
    public void fillSpinners(){
        List<Integer> pelatesIds = MainActivity.salesManagerDatabase.myDao().getIdsPelates();
        List<Integer> proiontaIds = MainActivity.salesManagerDatabase.myDao().getIdsProionta();

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(getContext(),android.R.layout.simple_spinner_item,pelatesIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pelatisSpinner.setAdapter(adapter);

        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(getContext(),android.R.layout.simple_spinner_item,proiontaIds);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proionSpinner.setAdapter(adapter1);

        //xrhsimopoiw adapters oi opoioi morfwpoioun to pws tha fainontai mesa sto spinner kathw kai pws tha fainontai ta items otan to spinner anoi3ei , dhladh otan to epile3w
    }

    //methodos gia na steilw mia ypopsifia paraggelia sto cart
    public void sendToCart(){
        List<Proionta> proionta = MainActivity.salesManagerDatabase.myDao().getProionStock();
        int posothta=0;
        int proionID = 0;
        int pelatisID=0;
        int checkStock=0;
        try {
            proionID = Integer.parseInt(proionSpinner.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try{
            posothta=Integer.parseInt(posothtaEditText.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        try{
            pelatisID = Integer.parseInt(pelatisSpinner.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        String date = dateEditText.getText().toString();
        //pairnw to stock gia to proion poy epilexthike sto ui
        checkStock = MainActivity.salesManagerDatabase.myDao().getStockFromProion(proionID);
        //elegxos an yparxei h posothta poy zhtaei o xrhsths
        if(posothta<=checkStock) {
            //tote o pelaths tha ginei o selected pelaths
            MainActivity.selected_customer_ID=pelatisID;
            //h paraggelia tha perastei sto kalathi
            for (Proionta p : proionta) {
                if (proionID == p.getProionID()) {
                    Kalathi kalathi = new Kalathi();
                    kalathi.setKalathiPelatisID(pelatisID);
                    kalathi.setKalathiProionID(proionID);
                    kalathi.setKalathiDate(date);
                    kalathi.setKalathiPosothta(posothta);

                    MainActivity.salesManagerDatabase.myDao().addToCart(kalathi);
                }

            }
            Toast.makeText(getActivity(), "Το προιόν στάλθηκε στο καλάθι", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getActivity(), "Δεν υπάρχει τόσο stock", Toast.LENGTH_SHORT).show();
        }
    }
    //h methodos poy tha ektelestei otan tha pathsw to koumpi toy ui
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mainAddToCartButton:
                sendToCart();
        }
    }
}
