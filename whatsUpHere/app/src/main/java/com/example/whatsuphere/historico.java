package com.example.whatsuphere;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.whatsuphere.Entity.Chat;
import com.example.whatsuphere.Entity.Contato;
import com.example.whatsuphere.Entity.local;

import java.util.ArrayList;
import java.util.List;

public class historico extends AppCompatActivity {
    private ListView listViewH;
    private List<local> auxH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);
        listViewH = (ListView) findViewById(R.id.listHistorico);
        auxH = new ArrayList<local>();
        for (int i=0;i<10;i++){
            local lc = new local(Integer.toString(i),"local"+Integer.toString(i));
            auxH.add(lc);
        }
        listViewH.setAdapter(new ArrayAdapter<local>(historico.this,
                android.R.layout.simple_list_item_1,auxH));
    }
}