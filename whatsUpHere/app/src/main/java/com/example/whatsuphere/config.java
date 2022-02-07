package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class config extends AppCompatActivity {
    private Button tma,tsen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        iniciarComponents();
        getSupportActionBar().setTitle("Whats Up!");
        tma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(config.this,trocarEmail.class);
                startActivity(intent);
            }
        });
        tsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(config.this,TrocarSenha.class);
                startActivity(intent);
            }
        });
    }
    private void iniciarComponents(){
        tma = findViewById(R.id.Trocar_Email_id);
        tsen = findViewById(R.id.Trocar_Senha_id);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.perfil_menu){
            Intent intent = new Intent(config.this,ActivityUser.class);
            startActivity(intent);
        } else if(id == R.id.home_menu){
            Intent intent = new Intent(config.this,Activity_home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}