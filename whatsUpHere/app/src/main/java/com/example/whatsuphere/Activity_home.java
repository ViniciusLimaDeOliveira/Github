package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.whatsuphere.Entity.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_home extends AppCompatActivity {
    private Button btConfig,btListaContatos,btAmbientes,btHLocais;
    private TextView nomeUser;
    private FirebaseAuth mAuth;
    private  FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        iniciarComponents();
        IniciarFirebase();
        btConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_home.this,config.class);
                startActivity(intent);
            }
        });
        btListaContatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_home.this,LIstaDeContatos.class);
                startActivity(intent);
            }
        });
        btHLocais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_home.this,historico.class);
                startActivity(intent);
            }
        });
        btAmbientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_home.this,Ambientes.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser fireUser = mAuth.getCurrentUser();
        if(fireUser==null){
            startActivity(new Intent(Activity_home.this,ActivityLogin.class));
        }else {
            //Toast.makeText(Activity_home.this, fireUser.toString(), Toast.LENGTH_LONG).show();
            databaseReference.child("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User user =snapshot.getValue(User.class);
                            nomeUser.setText(user.getNome());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }
    }

    private void iniciarComponents(){
        btListaContatos = findViewById(R.id.Contatos_id);
        btConfig =  findViewById(R.id.Config_id);
        btAmbientes = findViewById(R.id.Ambientes_id);
        btHLocais = findViewById(R.id.Historico_id);
        nomeUser = findViewById(R.id.NomeUser);
    }
    private void IniciarFirebase(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(Activity_home.this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
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
            Intent intent = new Intent(Activity_home.this,ActivityUser.class);
            startActivity(intent);
        } else if(id == R.id.home_menu){
            Intent intent = new Intent(Activity_home.this,Activity_home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}