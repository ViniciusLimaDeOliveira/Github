package com.example.trab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText edit_titulo,edit_editora;
    private static final String TAG = "MainActivity";
    private ArrayList<Livro> acervo = new ArrayList<>();
    private Button bnt2;
    private LivroListAdapter adapter;
    int id = 0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: Started.");
        IniciarComponents();
        ListView mListView = (ListView) findViewById(R.id.listview);
        adapter = new LivroListAdapter(this,R.layout.adapter_layout,acervo);
        mListView.setAdapter(adapter);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,descricao.class);
                intent.putExtra("obj",acervo.get(i));
                startActivityForResult(intent,101);
                return false;
            }
        });
        /*
        Livro a = new Livro(1,"teste","teste editora");
        Livro b = new Livro(2,"teste2","teste editora2");
        Livro c = new Livro(2,"teste","teste editora");
        Livro d = new Livro(3,"teste2","teste editora2");
        Livro e = new Livro(4,"teste","teste editora");
        Livro f = new Livro(5,"teste2","teste editora2");

        acervo.add(a);
        acervo.add(b);
        acervo.add(c);
        acervo.add(d);
        acervo.add(e);
        acervo.add(f);
         */
        bnt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo  = edit_titulo.getText().toString();
                String editora = edit_editora.getText().toString();

                if(titulo.isEmpty() || editora.isEmpty()){
                    Snackbar snac = Snackbar.make(v,"Preencha todos os campos de texto.",Snackbar.LENGTH_SHORT);
                    snac.setBackgroundTint(Color.WHITE);
                    snac.setTextColor(Color.BLACK);
                    snac.show();
                }else{
                    cadastrar();
                   //mListView.requestLayout();
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
    private void realoadData(){
        adapter.notifyDataSetChanged();
    }
    private void IniciarComponents(){
        edit_titulo  = findViewById(R.id.editText2);
        edit_editora = findViewById(R.id.editText);
        bnt2 = findViewById(R.id.button2);
    }
    private void cadastrar(){
        String titulo = edit_titulo.getText().toString();
        String editora = edit_editora.getText().toString();
        Livro a = new Livro(id,titulo,editora);
        acervo.add(a);
        id=id+1;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101) {
            if (resultCode == descricao.RESULT_OK) {
                Livro L = (Livro) data.getSerializableExtra("obj");
                acervo.set(L.getIsbn(), L);
                realoadData();
            }
        }
    }
}