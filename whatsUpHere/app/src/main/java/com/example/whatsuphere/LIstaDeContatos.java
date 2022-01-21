package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.whatsuphere.Entity.Contato;
import com.example.whatsuphere.Entity.User;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class LIstaDeContatos extends AppCompatActivity {
    private ListView listViewContatos;
    private ArrayAdapter<Contato> arrayAdapterUser;
    private List<Contato> auxUser;
    private Button btAdd,brRmv;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_contatos);
        iniciarComponents();
        IniciarFirebase();
        eventoDatabase();

    }
    private void eventoDatabase() {
        databaseReference.child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                auxUser.clear();
                int id =0;
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    User l = objSnapshot.getValue(User.class);
                    String nome = l.getNome();
                    String email = l.getEmail();
                    auxUser.add(new Contato(id,nome,email));
                    id+=1;
                }
                arrayAdapterUser = new ArrayAdapter<Contato>(LIstaDeContatos.this,
                        android.R.layout.simple_list_item_1,auxUser);
                listViewContatos.setAdapter(arrayAdapterUser);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void IniciarFirebase(){
        FirebaseApp.initializeApp(LIstaDeContatos.this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }
    private void iniciarComponents() {
        listViewContatos = (ListView) findViewById(R.id.list_contatos);
        auxUser = new ArrayList<Contato>();
        btAdd = findViewById(R.id.add_contato);
        brRmv = findViewById(R.id.rmv_contato);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.perfil_menu){
            Intent intent = new Intent(LIstaDeContatos.this,ActivityUser.class);
            startActivity(intent);
        } else if(id == R.id.home_menu){
            Intent intent = new Intent(LIstaDeContatos.this,Activity_home.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}