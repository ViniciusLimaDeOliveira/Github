package com.example.trab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class descricao extends AppCompatActivity {
    private Button bt;
    private String nTitulo,nEditora;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descricao);
        Intent intent = getIntent();
        IniciarComponents();
        Livro L = (Livro) intent.getSerializableExtra("obj");
        EditText titulo = (EditText) findViewById(R.id.titulo);
        EditText editora = (EditText) findViewById(R.id.editora);
        TextView isbn = (TextView) findViewById(R.id.isbn);
        //TextView titulo = (TextView) findViewById(R.id.titulo);
        //TextView editora = (TextView) findViewById(R.id.editora);
        isbn.setText(Integer.toString(L.getIsbn()));
        titulo.setText(L.getTitulo());
        editora.setText(L.getEditora());

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nEditora = ((EditText) findViewById(R.id.titulo)).getText().toString();
                nTitulo  = ((EditText) findViewById(R.id.editora)).getText().toString();
                L.setEditora(nEditora);
                L.setTitulo(nTitulo);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("obj",L);
                setResult(descricao.RESULT_OK,returnIntent);
                finish();
            }
        });
    }
    private void IniciarComponents(){
        bt = findViewById(R.id.button);
    }
}