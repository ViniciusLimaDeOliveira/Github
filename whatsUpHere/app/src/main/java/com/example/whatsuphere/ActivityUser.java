package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsuphere.Entity.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityUser extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextView nome,email;
    private Button btDeslogar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Whats Up!");
        setContentView(R.layout.activity_user);
        getSupportActionBar().hide();
        iniciarComponents();
        IniciarFirebase();
        btDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                startActivity(new Intent(ActivityUser.this,ActivityLogin.class));
            }
        });
    }

    private void iniciarComponents() {
        nome = findViewById(R.id.nome_camp_id);
        email = findViewById(R.id.email_camp_id);
        btDeslogar = findViewById(R.id.deslogar_id);
    }
    private void IniciarFirebase(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(ActivityUser.this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser fireUser = mAuth.getCurrentUser();
        if(fireUser==null){
            Toast.makeText(ActivityUser.this, "Sem user", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(ActivityUser.this,ActivityLogin.class));
        }else {

            databaseReference.child("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User user = snapshot.getValue(User.class);
                            nome.setText(user.getNome());
                            email.setText(user.getEmail());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }
    }

}