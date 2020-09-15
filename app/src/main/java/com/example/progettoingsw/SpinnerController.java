package com.example.progettoingsw;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.Struttura;

import java.util.ArrayList;

import static com.example.progettoingsw.Connection.ConnectionClass.getTopRecensione;

public class SpinnerController {
    public RecensioneActivity frame;

    public SpinnerController(RecensioneActivity view)
    {
        frame=view;
        for(int i=0;i<=5;i++)
        {
            frame.spinnerList1.add(String.valueOf(i));
        } frame.adapter1.notifyDataSetChanged();

        if (ConnectionClass.con == null) {
            frame.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(frame, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                }
            });}
        else{
            ArrayList<Struttura> r= frame.struttura.getListaStrutture();
            for(Struttura s: r)
            {


                frame.spinnerList.add(s.getNome());
            }
            frame.adapter.notifyDataSetChanged();
            frame.spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) frame);
            frame.id=getTopRecensione();

        }
    }

    public void onItemSelected() {
        Struttura struttura1=frame.struttura.getStrutturaByNome(frame.spin.getSelectedItem().toString());
        if(struttura1!=null)
        {
            String nome = struttura1.getCitta();
            frame.citta.setText(nome);
            String tipologia=struttura1.getTipologia();
            frame.tipologiaStruttura.setText(tipologia);
            frame.latitudine=struttura1.getLatitudine();
            frame.longitudine=struttura1.getLongitudine();
            frame.indirizzo=struttura1.getIndirizzo();
        }


    }

}
