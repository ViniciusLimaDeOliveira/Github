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

public class trocarEmail extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    Button bt,btc;
    private EditText edit_email;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Whats Up!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trocar_email);
        IniciarFirebase();
        btc = findViewById(R.id.buttonCance);
        bt = findViewById(R.id.button_mudar);
        edit_email = findViewById(R.id.emailTrocarid);
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(trocarEmail.this, "Operação cancelada !", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(trocarEmail.this,config.class);
                startActivity(intent);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_email.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(trocarEmail.this,"Campo vazio",
                            Toast.LENGTH_LONG).show();
                }else {
                    mudaEmail(email);
                }
            }
        });
    }
    private void mudaEmail(String email) {

        user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(trocarEmail.this, "Email alterado com sucesso", Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("email").setValue(email);
                edit_email.setText("");
                Intent intent = new Intent(trocarEmail.this,Activity_home.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(trocarEmail.this, "Email não foi alterado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(trocarEmail.this,config.class);
                startActivity(intent);
            }
        });

    }
    private void IniciarFirebase(){
        FirebaseApp.initializeApp(trocarEmail.this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
    }
}