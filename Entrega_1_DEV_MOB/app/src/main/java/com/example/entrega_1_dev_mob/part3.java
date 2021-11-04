package com.example.entrega_1_dev_mob;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.BlendMode;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class part3 extends AppCompatActivity {
    private Button bt;
    private ConstraintLayout a;
    private View back;
    private MediaPlayer mymusic;
    private Button hbt,bt2,bt3,bt4,bt5;
    private String[] mensages = {"String vazia, digite algo!","Cafe selecionado","Cafe expressso",
            "Cafe preto","Cafe com leite","Cafe sem açucar","Pão","Pão frances","Pão Sovado","Sanduiche",
            "Misto quente","Pão com ovo","cachoro quente"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part3);
        IniciarComponents();
        mymusic = MediaPlayer.create(this,R.raw.never);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(part3.this, bt);
                //Inflating the Popup using xml file
                popup.getMenuInflater()
                        .inflate(R.menu.poupup_menu, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.Q)
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getTitle().equals("Red")){
                            a.setBackgroundColor(Color.RED);
                        }else if (item.getTitle().equals("Blue")){
                            a.setBackgroundColor(Color.BLUE);
                        }else if (item.getTitle().equals("White")){
                            a.setBackgroundColor(Color.WHITE);
                        }
                        return true;
                    }
                });

                popup.show(); //showing popup menu
            }
        }); //closing the setOnClickListener method

        hbt.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(part3.this,part4.class);
                startActivity(intent);
                return false;
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(part3.this,part5.class);
                startActivity(intent);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(part3.this,part6.class);
                startActivity(intent);
            }
        });
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(part3.this,part7.class);
                startActivity(intent);
            }
        });
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mymusic.start();
            }
        });
    }
    private void IniciarComponents(){
        bt = findViewById(R.id.button1);
        a = findViewById(R.id.part3);
        hbt = findViewById(R.id.holdbtn);
        bt2 = findViewById(R.id.btn2);
        bt3 = findViewById(R.id.btn3);
        bt4 = findViewById(R.id.btn4);
        bt5 = findViewById(R.id.btn5);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.overflow_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.item_a_1:
                Toast.makeText(this,mensages[2],Toast.LENGTH_LONG).show();
                break;
            case R.id.item_a_2:
                Toast.makeText(this,mensages[3],Toast.LENGTH_LONG).show();
                break;
            case R.id.item_a_3:
                Toast.makeText(this,mensages[4],Toast.LENGTH_LONG).show();
                break;
            case R.id.item_a_4:
                Toast.makeText(this,mensages[5],Toast.LENGTH_LONG).show();
                break;
            case R.id.item_b_1:
                Toast.makeText(this,mensages[7],Toast.LENGTH_LONG).show();
                break;
            case R.id.item_b_2:
                Toast.makeText(this,mensages[8],Toast.LENGTH_LONG).show();
                break;

            case R.id.item_c_1:
                Toast.makeText(this,mensages[10],Toast.LENGTH_LONG).show();
                break;
            case R.id.item_c_2:
                Toast.makeText(this,mensages[11],Toast.LENGTH_LONG).show();
                break;
            case R.id.item_c_3:
                Toast.makeText(this,mensages[12],Toast.LENGTH_LONG).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}