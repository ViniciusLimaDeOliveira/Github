package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.whatsuphere.Entity.Chat;
import com.example.whatsuphere.Entity.Contato;

import java.util.ArrayList;
import java.util.List;
//Implementação provisoria
public class Ambientes extends AppCompatActivity {
    List<Chat> chats;
    ArrayAdapter<Chat> arrayAdapterChats ;
    ListView listViewChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambientes);

        chats =  new ArrayList<Chat>();
        for (int i=0;i<5;i++){
            Chat c = new Chat(Integer.toString(i),"Chat teste");
            chats.add(c);
        }
        listViewChat = (ListView) findViewById(R.id.listaChats);
        arrayAdapterChats = new ArrayAdapter<Chat>(Ambientes.this,
                android.R.layout.simple_list_item_1,chats);
        listViewChat.setAdapter(arrayAdapterChats);

        listViewChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Ambientes.this,group.class);
                intent.putExtra("obj",chats.get(i));
                startActivityForResult(intent,101);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.perfil_menu){
            Intent intent = new Intent(Ambientes.this,ActivityUser.class);
            startActivity(intent);
        } else if(id == R.id.home_menu){
            Intent intent = new Intent(Ambientes.this,Activity_home.class);
            startActivity(intent);
        } else if(id == R.id.novoGrupo){
            //Criar novo Grupo
        }
        return super.onOptionsItemSelected(item);
    }
}