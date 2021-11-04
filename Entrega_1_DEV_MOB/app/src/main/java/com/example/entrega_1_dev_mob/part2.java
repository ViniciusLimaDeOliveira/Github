package com.example.entrega_1_dev_mob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class part2 extends AppCompatActivity {
    private ToggleButton togle;
    private ConstraintLayout a;
    private Spinner  spnr;
    private AutoCompleteTextView t1;
    private Button btn;
    private RadioGroup radioG;
    private RadioButton rb;
    private String[] mensages = {"Metodo de entrega não selecionado :","Metodo de entrega é"," Para pais "};
    private String[] paises = {
            "Brasil",
            "Russia",
            "Abecásia",
            "Afeganistão",
            "África do Sul",
            "Albânia",
            "Alemanha",
            "Andorra",
            "Angola",
            "Antígua e Barbuda",
            "Arábia Saudita",
            "Argélia",
            "Argentina",
            "Armênia",
            "Austrália",
            "Áustria",
            "Azerbaijão"};
    private  String[] op = {"PAC","Sedex","FeedEx","UPC"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);
        IniciarComponents();
        getSupportActionBar().hide();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(part2.this,part3.class);
                startActivity(intent);
            }
        });

        togle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    a.setBackgroundColor(Color.BLACK);
                } else {
                    a.setBackgroundColor(Color.WHITE);
                }
            }
        });


        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,paises);

        t1.setThreshold(1);
        t1.setAdapter(adp);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item,op);

        spnr.setAdapter(adapter);
        spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int position = spnr.getSelectedItemPosition();
                    String srt = mensages[1]+ op[+position];
                    Snackbar snac = Snackbar.make(adapterView,srt, Snackbar.LENGTH_LONG);
                    snac.setBackgroundTint(Color.WHITE);
                    snac.setTextColor(Color.BLACK);
                    snac.show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Snackbar snac = Snackbar.make(adapterView, mensages[0], Snackbar.LENGTH_LONG);
                snac.setBackgroundTint(Color.WHITE);
                snac.setTextColor(Color.BLACK);
                snac.show();
            }
        });


    } private void IniciarComponents(){
        togle = (ToggleButton) findViewById(R.id.toggleButton2);
        a = findViewById(R.id.tela2);
        spnr = (Spinner) findViewById(R.id.spinner);
        t1 = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
        btn = findViewById(R.id.cadbuton);
        radioG = findViewById(R.id.radiogroup1);
    }

    public void checkButton(View v){
        int radid = radioG.getCheckedRadioButtonId();
        rb = findViewById(radid);
        Snackbar snac = Snackbar.make(v, rb.getText(), Snackbar.LENGTH_LONG);
        snac.setBackgroundTint(Color.WHITE);
        snac.setTextColor(Color.BLACK);
        snac.show();
    }
}