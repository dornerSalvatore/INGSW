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
    public RicercaActivity frame1;

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
    public SpinnerController(RicercaActivity view){
        frame1=view;
        ArrayList<String> tipologia = frame1.struttura.getListTipologia();
        for (String s : tipologia) {
            frame1.spinnerList.add(s);
        }
        frame1.adapter.notifyDataSetChanged();
        int c = 500;
        frame1.spinnerList1.add("");
        for (int i = 0; i <= 10; i++) {
            if (i == 0)
                frame1.spinnerList1.add(String.valueOf(i * 10) + "£" + "-" + String.valueOf(i + 5 * 10) + "£");
            else if (i > 0 && i < 5)
                frame1.spinnerList1.add(String.valueOf(i * 100) + "£" + "-" + ((i + 1) * 100) + "£");

            else if (i > 5 && i < 10) {
                int b = c + 500;
                frame1.spinnerList1.add(String.valueOf((c) + "£" + "-" + (b)) + "£");
                c = b;
            } else if (i == 10) frame1.spinnerList1.add(">2500£");
        }
        frame1.adapter1.notifyDataSetChanged();

        frame1.spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) frame1);
        frame1.prezzo.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) frame1);
    }
    public void onItemSelected1() {
        boolean flag = true;
        if (frame1.prezzo.getSelectedItem().toString() != "") {
            frame1.testo = "";
            frame1.testo1 = "";
            String stringa = frame1.prezzo.getSelectedItem().toString();
            for (int i = 0; i < stringa.length(); i++) {
                if (Character.isDigit(stringa.charAt(i)) && flag)
                    frame1.testo += stringa.charAt(i);
                else {
                    if (Character.isDigit(stringa.charAt(i)))
                        frame1.testo1 += stringa.charAt(i);
                    flag = false;
                }


            }
        }
    }


}
