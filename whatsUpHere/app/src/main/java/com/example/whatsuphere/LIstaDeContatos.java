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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.whatsuphere.Entity.Contato;
import com.example.whatsuphere.Entity.MenssagemOBJ;
import com.example.whatsuphere.Entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LIstaDeContatos extends AppCompatActivity {
    private ListView listViewContatos;
    private ArrayAdapter<Contato> arrayAdapterUser;
    private List<Contato> auxUser;
    private Button btAdd,brRmv;
    private EditText editText;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser fireUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Whats Up!");
        setContentView(R.layout.activity_lista_de_contatos);
        iniciarComponents();
        IniciarFirebase();
        eventoDatabase();
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adicionarContato();
            }
        });
        brRmv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removerContato();
            }
        });
    }

    private void removerContato() {
        String men = editText.getText().toString().trim();
        if(men.isEmpty()){
            Toast.makeText(this, "Campo vazio", Toast.LENGTH_SHORT).show();
        }if(auxUser.isEmpty()){
            Toast.makeText(this, "Nenhum contato na lista", Toast.LENGTH_SHORT).show();
        }else{
            String id="";
            for (Contato a:
                 auxUser) {
                if(a.getEmail().equals(men)){
                    id=a.getIud();
                }
            }
            if(id==""){
                Toast.makeText(this, "Ninguem com esse email", Toast.LENGTH_SHORT).show();
            }else{
                databaseReference.child("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("contatos")
                        .child(id)
                        .removeValue();
            }
            editText.setText("");
        }
    }

    private void adicionarContato() {
        String men = editText.getText().toString().trim();
        if(men.isEmpty()){
            Toast.makeText(this, "Campo vazio", Toast.LENGTH_SHORT).show();
        }else{
            Contato m = new Contato();
            m.setEmail(men);
            m.setIud(UUID.randomUUID().toString());
            databaseReference.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("contatos")
                    .child(m.getIud())
                    .setValue(m)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                editText.setText("");
                            }else{
                                Toast.makeText(LIstaDeContatos.this, "Ocorreu erro no envio", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void eventoDatabase() {
        databaseReference.child("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("contatos")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                auxUser.clear();
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Contato l = objSnapshot.getValue(Contato.class);
                    String email = l.getEmail();
                    String iud = l.getIud();
                    auxUser.add(new Contato(iud,email));
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
        mAuth = FirebaseAuth.getInstance();
        fireUser = mAuth.getCurrentUser();
    }
    private void iniciarComponents() {
        listViewContatos = (ListView) findViewById(R.id.list_contatos);
        auxUser = new ArrayList<Contato>();
        btAdd = findViewById(R.id.add_contato);
        brRmv = findViewById(R.id.rmv_contato);
        editText = findViewById(R.id.editTextTextEmailAddress);
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