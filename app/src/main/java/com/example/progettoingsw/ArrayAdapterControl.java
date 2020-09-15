package com.example.progettoingsw;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

public class ArrayAdapterControl {
    ListaStrutture frame;
    public ArrayAdapterControl(ListaStrutture view){frame=view;}
    public  void onItemClick(AdapterView<?> av, View v, int pos, long id)
    {
        Intent openPage1 = new Intent(frame,StrutturaActivity.class);
        openPage1.putExtra("string",av.getItemAtPosition(pos).toString());
        openPage1.putExtra("nickname",frame.nickname);
        // passo all'attivazione dell'activity Pagina.java
        frame.startActivity(openPage1);
        frame.finish();
    }
}
