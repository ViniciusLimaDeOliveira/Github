package com.example.entrega_1_dev_mob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class part5 extends AppCompatActivity {
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part5);
        IniciarComponents();
        String items[] = new String[]{"1 item","2 item","3 item","4 item","5 item"};
        ArrayAdapter<String> meuAdapter = new ArrayAdapter<String>(part5.this,
                android.R.layout.simple_list_item_1,items);
        lv.setAdapter(meuAdapter);
    }
    private void IniciarComponents(){
        lv = findViewById(R.id.listv);
    }
}