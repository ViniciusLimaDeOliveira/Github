package com.example.atvservidor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.atvservidor.model.Livro;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    EditText editNome,editEditora;
    ListView listView_livros;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ArrayList<Livro> acervo = new ArrayList<Livro>();
    private ArrayAdapter<Livro> arrayAdapterLivro;
    Livro livroSelecionado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IniciarComponents();
        IniciarFirebase();
        eventoDatabase();

        listView_livros.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                livroSelecionado = (Livro)adapterView.getItemAtPosition(i);
                System.out.println(livroSelecionado);
                editNome.setText(livroSelecionado.getTitulo());
                editEditora.setText(livroSelecionado.getEditora());
                return false;
            }
        });

//        listView_livros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                livroSelecionado = (Livro)parent.getItemAtPosition(position);
//                System.out.println(livroSelecionado);
//                editNome.setText(livroSelecionado.getTitulo());
//                editEditora.setText(livroSelecionado.getEditora());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    private void eventoDatabase() {
        databaseReference.child("livros").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                acervo.clear();
                for(DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Livro l = objSnapshot.getValue(Livro.class);
                    acervo.add(l);
                }
                arrayAdapterLivro = new ArrayAdapter<Livro>(MainActivity.this,
                        android.R.layout.simple_list_item_1,acervo);
                listView_livros.setAdapter(arrayAdapterLivro);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void IniciarComponents(){
        editNome = (EditText) findViewById(R.id.campo_nome);
        editEditora = (EditText) findViewById(R.id.campo_editora);
        listView_livros = (ListView) findViewById(R.id.list_view_livros);
    }
    private void IniciarFirebase(){
        FirebaseApp.initializeApp(MainActivity.this);
        firebaseDatabase  = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.add_menu){
            Livro l = new Livro();
            System.out.println(l);
            l.setUid(UUID.randomUUID().toString());
            l.setTitulo(editNome.getText().toString());
            l.setEditora(editEditora.getText().toString());
            databaseReference.child("livros").child(l.getUid()).setValue(l);
            limparcampos();
        }
        else if(id == R.id.atualiza_menu){
            Livro l = new Livro();
            l.setUid(livroSelecionado.getUid());
            l.setTitulo(editNome.getText().toString().trim());
            l.setEditora(editEditora.getText().toString().trim());
            databaseReference.child("livros").child(l.getUid()).setValue(l);
            limparcampos();
        } else if(id == R.id.remove_menu){
            Livro l = new Livro();
            l.setUid(livroSelecionado.getUid());
            databaseReference.child("livros").child(l.getUid()).removeValue();
            limparcampos();
        }
        return true;
    }
     private void limparcampos(){
        editNome.setText("");
        editEditora.setText("");
     }
}