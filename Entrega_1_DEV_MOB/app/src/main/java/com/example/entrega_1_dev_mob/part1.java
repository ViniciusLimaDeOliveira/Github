package com.example.entrega_1_dev_mob;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class part1 extends AppCompatActivity {
    private ToggleButton togle;
    private ConstraintLayout a;
    private EditText eText;
    private Button btn;
    private String[] mensages = {"String vazia, digite algo!","Cafe selecionado","Cafe expressso",
            "Cafe preto","Cafe com leite","Cafe sem açucar","Pão","Pão frances","Pão Sovado","Sanduiche",
            "Misto quente","Pão com ovo","cachoro quente"};
    private Button next_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);
        IniciarComponents();
        getSupportActionBar().hide();
        togle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    a.setBackgroundColor(Color.BLACK);
                } else {
                    a.setBackgroundColor(Color.WHITE);
                }
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(part1.this,part2.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = eText.getText().toString();
                if(str.isEmpty()) {
                    Snackbar snac = Snackbar.make(view, mensages[0], Snackbar.LENGTH_LONG);
                    snac.setBackgroundTint(Color.WHITE);
                    snac.setTextColor(Color.BLACK);
                    snac.show();
                }else{
                    Snackbar snac = Snackbar.make(view, str, Snackbar.LENGTH_LONG);
                    snac.setBackgroundTint(Color.WHITE);
                    snac.setTextColor(Color.BLACK);
                    snac.show();
                }
            }
        });

    }
    private void IniciarComponents(){
        togle = (ToggleButton) findViewById(R.id.toggleButton2);
        a = findViewById(R.id.lineralay1);
        eText = (EditText) findViewById(R.id.text_edit);
        btn = (Button) findViewById(R.id.cadbuton);
        next_btn = findViewById(R.id.prox);
    }


}