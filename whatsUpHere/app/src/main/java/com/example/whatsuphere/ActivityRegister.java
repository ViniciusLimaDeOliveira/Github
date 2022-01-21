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
import android.widget.Toast;

import com.example.whatsuphere.Entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class ActivityRegister extends AppCompatActivity {

    private EditText edit_nome,edit_email,edit_senha;
    private Button bt_cad;
    String[] mensagens = {"Prencha todos os campos.","Cadastro realizado com sucesso.","Não foi possivel realizar cadastro","Senha curta, 6 ou mais caracteres !"};
    ProgressBar progressBar;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        iniciarComponents();
        bt_cad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome  = edit_nome.getText().toString().trim();
                String email = edit_email.getText().toString().trim();
                String senha = edit_senha.getText().toString().trim();
                if(nome.isEmpty() || email.isEmpty()||senha.isEmpty()){
                    Toast.makeText(ActivityRegister.this,mensagens[0],
                            Toast.LENGTH_LONG).show();
                }else if(senha.length()<6) {
                    Toast.makeText(ActivityRegister.this,mensagens[3],
                            Toast.LENGTH_LONG).show();
                }else{
                    CadastraUsuario();
                }
            }
        });
    }
    private void iniciarComponents(){
        edit_nome  = findViewById(R.id.tnome);
        edit_email = findViewById(R.id.temail);
        edit_senha = findViewById(R.id.tsenha);
        bt_cad = findViewById(R.id.cadbuton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
    }
    private void CadastraUsuario(){
        String nome = edit_nome.getText().toString().trim();
        String email = edit_email.getText().toString().trim();
        String senha = edit_senha.getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);
        // essa parte e com Firebase
        mAuth.createUserWithEmailAndPassword(email,senha)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                     if(task.isSuccessful()){
                         User user = new User(nome,email,senha);
                         FirebaseDatabase.getInstance().getReference("Users")
                                 .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                 .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                             @Override
                             public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ActivityRegister.this,
                                            mensagens[1],
                                            Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(ActivityRegister.this,ActivityLogin.class);
                                    startActivity(intent);
                                }else{
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(ActivityRegister.this,
                                            mensagens[2],
                                            Toast.LENGTH_LONG).show();
                                }
                             }
                         });


                     } else{
                         Toast.makeText(ActivityRegister.this,
                                 mensagens[2],
                                 Toast.LENGTH_LONG).show();
                         progressBar.setVisibility(View.GONE);
                     }
                    }
                });
    }
}