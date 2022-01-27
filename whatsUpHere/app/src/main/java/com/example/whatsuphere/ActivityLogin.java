package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityLogin extends AppCompatActivity {
    private TextView text_tela_cadastro;
    private Button bt_et;
    private EditText campEmailTxt,campSenhaTxt;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        IniciarComponents();
        progressBar.setVisibility(View.INVISIBLE);
        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,ActivityRegister.class);
                startActivity(intent);
            }
        });
        bt_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = campEmailTxt.getText().toString().trim();
                String senha = campSenhaTxt.getText().toString().trim();
                if(email.isEmpty()||senha.isEmpty()){
                    Toast.makeText(ActivityLogin.this,"campos obrigatorios vazios !",
                            Toast.LENGTH_LONG).show();
                }else if(senha.length()<6) {
                    Toast.makeText(ActivityLogin.this,"Senha são maiores que 6 caracteres !",
                            Toast.LENGTH_LONG).show();
                }else{
                    startActivity(new Intent(ActivityLogin.this,Activity_home.class));
                    EntrarComoUsuario();
                }
            }
        });

    }

    private void IniciarComponents(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        bt_et = findViewById(R.id.id_entrar);
        campEmailTxt = findViewById(R.id.edit_email);
        campSenhaTxt = findViewById(R.id.edit_senha);
        progressBar = (ProgressBar) findViewById(R.id.progres);
        mAuth = FirebaseAuth.getInstance();
    }

    private void EntrarComoUsuario() {
        String email = campEmailTxt.getText().toString().trim();
        String senha = campSenhaTxt.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);
        // Essa parte e com Firebase
        mAuth.signInWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ActivityLogin.this,"Login feito com sucesso !",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ActivityLogin.this,Activity_home.class));
                            limparCampos();
                        }else{
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(ActivityLogin.this,"Não foi possivel fazer Login !",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void limparCampos() {
        campEmailTxt.setText("");
        campSenhaTxt.setText("");
    }


}