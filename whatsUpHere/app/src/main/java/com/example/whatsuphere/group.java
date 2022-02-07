package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsuphere.Entity.Chat;
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

import java.util.ArrayList;
import java.util.UUID;

public class group extends AppCompatActivity {
    private Button bt;
    private EditText mensagem;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private String nomeCidade,nomeChat;
    private String nomeUser;
    private ListView listView_mensagems;
    private ArrayAdapter arrayAdapterMensagems;
    private ArrayList<MenssagemOBJ> mensagems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Whats Up!");
        setContentView(R.layout.activity_group);
        inciarComponentes();
        IniciarFirebase();
        Intent intent = getIntent();
        Chat chat = (Chat) intent.getSerializableExtra("obj");
        nomeChat = chat.getNome();
        nomeCidade = intent.getStringExtra("city");
        eventoDatabase();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviarMensagem();
            }
        });
    }
    private void eventoDatabase() {
        RootRef.child("Chats").child(nomeCidade).child(nomeChat).child("menssagems")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mensagems.clear();
                for(DataSnapshot objSnapshot:snapshot.getChildren()){
                    MenssagemOBJ c = objSnapshot.getValue(MenssagemOBJ.class);
                    // Toast.makeText(Ambientes.this, name, Toast.LENGTH_SHORT).show();
                    mensagems.add(c);
                }

                arrayAdapterMensagems = new ArrayAdapter<MenssagemOBJ>(group.this,
                        android.R.layout.simple_list_item_1,mensagems);
                listView_mensagems.setAdapter(arrayAdapterMensagems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void IniciarFirebase() {
        FirebaseApp.initializeApp(group.this);
        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
    }

    private void enviarMensagem() {
        String men = mensagem.getText().toString().trim();
        if(men.isEmpty()){
            Toast.makeText(this, "Menssagem Vazia", Toast.LENGTH_SHORT).show();
        }else{
            MenssagemOBJ m = new MenssagemOBJ();
            m.setAutor(nomeUser);
            m.setConteudo(men);
            RootRef.child("Chats").child(nomeCidade).child(nomeChat).child("menssagems")
                    .push().setValue(m)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                mensagem.setText("");
                            }else{
                                Toast.makeText(group.this, "Ocorreu erro no envio", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    private void inciarComponentes() {
        bt = findViewById(R.id.button_send);
        mensagem = findViewById(R.id.menssagem_campo);
        listView_mensagems = findViewById(R.id.listMensagens);
        mensagems = new ArrayList<MenssagemOBJ>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser fireUser = mAuth.getCurrentUser();
        if(fireUser==null){
            startActivity(new Intent(group.this,ActivityLogin.class));
        }else {
            //Toast.makeText(Activity_home.this, fireUser.toString(), Toast.LENGTH_LONG).show();
            RootRef.child("Users")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            User user =snapshot.getValue(User.class);
                            nomeUser = user.getNome();
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

        }
    }
}