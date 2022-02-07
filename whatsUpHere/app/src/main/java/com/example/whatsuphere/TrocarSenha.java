package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TrocarSenha extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    Button bt,btc;
    private EditText edit_senha;
    private  FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Whats Up!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_senha);
        IniciarFirebase();
        bt = findViewById(R.id.btts799);
        edit_senha = findViewById(R.id.senha_mudar);
        btc = findViewById(R.id.button123);
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(TrocarSenha.this, "Operação cancelada !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TrocarSenha.this,config.class);
                startActivity(intent);
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String senha = edit_senha.getText().toString().trim();
                if(senha.isEmpty()){
                    Toast.makeText(TrocarSenha.this,"Campo vazio",
                            Toast.LENGTH_LONG).show();
                }
                else if(senha.length()<6) {
                    Toast.makeText(TrocarSenha.this,"Senha deve ter no minimo 6 digitos",
                            Toast.LENGTH_LONG).show();
                }else{
                    mudaSenha(senha);
                }
            }
        });

    }

    private void mudaSenha(String senha) {

        user.updatePassword(senha).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(TrocarSenha.this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("senha").setValue(senha);
                edit_senha.setText("");
                Intent intent = new Intent(TrocarSenha.this,Activity_home.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TrocarSenha.this, "Senha não foi alterada", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TrocarSenha.this,config.class);
                startActivity(intent);
            }
        });

    }
    private void IniciarFirebase(){
        FirebaseApp.initializeApp(TrocarSenha.this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }
}