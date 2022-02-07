package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsuphere.Entity.Chat;
import com.example.whatsuphere.Entity.Contato;
import com.example.whatsuphere.Entity.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Ambientes extends AppCompatActivity implements LocationListener {
    List<Chat> chats;
    Button bt;
    ArrayAdapter<Chat> arrayAdapterChats ;
    ListView listViewChat;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    Geocoder geocoder;
    LocationManager locationManager;
    LocationListener locationListener;
    TextView textView;
    String nomeCidade;
    List<Address> addressList;
    Double lati,longui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Whats Up!");
        setContentView(R.layout.activity_ambientes);
        getLocation();
        iniciarComponents();
        IniciarFirebase();


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequisitarNovoChat();
            }
        });
        listViewChat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Ambientes.this,group.class);
                intent.putExtra("obj",chats.get(i));
                intent.putExtra("city",nomeCidade);
                startActivityForResult(intent,101);
            }
        });
    }

    private void IniciarFirebase() {
        FirebaseApp.initializeApp(Ambientes.this);
        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
    }

    private void iniciarComponents() {
        listViewChat = (ListView) findViewById(R.id.listaChats);
        bt = findViewById(R.id.button_criar_chat);
        chats = new ArrayList<Chat>();
        textView = findViewById(R.id.textView_ambientes);

    }

    private void eventoDatabase(String cidadeName) {
        RootRef.child("Chats").child(cidadeName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chats.clear();
                    for(DataSnapshot objSnapshot:snapshot.getChildren()){
                        Chat c = objSnapshot.getValue(Chat.class);
                        String name = c.getNome();
                        Double lt = c.getLatitude();
                        Double lo = c.getLongitude();
                        // Toast.makeText(Ambientes.this, name, Toast.LENGTH_SHORT).show();
                        Chat cn = new Chat(name,lt,lo);
                        chats.add(cn);
                    }

                arrayAdapterChats = new ArrayAdapter<Chat>(Ambientes.this,
                        android.R.layout.simple_list_item_1,chats){

                    @SuppressLint("ResourceAsColor")
                    @Override
                    public View getView (int position, View convertView, ViewGroup parent) {

                        View view = super.getView(position, convertView, parent);

                        // Como o simple_list_item_1 retorna um TextView, esse cast pode ser feito sem problemas
                        ((TextView) view).setTextColor(R.color.black);

                        return view;
                    }

                };
                listViewChat.setAdapter(arrayAdapterChats);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void RequisitarNovoChat() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Ambientes.this);
        builder.setTitle("De um nome para o Chat:");
        final EditText nomeDoChat = new EditText(Ambientes.this);
        builder.setView(nomeDoChat);
        nomeDoChat.setHint("e.x.: Classe de progamação");
        builder.setPositiveButton("Criar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String nomeChat = nomeDoChat.getText().toString();
                if(nomeChat.isEmpty()){
                    Toast.makeText(Ambientes.this, "Entrada vazia", Toast.LENGTH_SHORT).show();
                }else {
                    getLocation();
                    CriarNovoChat(nomeChat);
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private void CriarNovoChat(String nome) {
        Chat c = new Chat(nome,lati,longui);
        RootRef.child("Chats").child(nomeCidade).child(c.getNome()).setValue(c)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Ambientes.this, "Chat criado com sucesso", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Ambientes.this, "Ocorreu erro na criação", Toast.LENGTH_SHORT).show();
                        }

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
        }
        return super.onOptionsItemSelected(item);
    }
    private void getLocation() {
        if (ContextCompat.checkSelfPermission(Ambientes.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Ambientes.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        if (ContextCompat.checkSelfPermission(Ambientes.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Ambientes.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
        }
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    5000,
                    5,
                    Ambientes.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        //Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            geocoder = new Geocoder(Ambientes.this, Locale.getDefault());
            addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String addres = addressList.get(0).getAddressLine(0);
            nomeCidade = addressList.get(0).getSubAdminArea();
            lati   = location.getLatitude();
            longui = location.getLongitude();
            Toast.makeText(this, nomeCidade, Toast.LENGTH_LONG).show();
            textView.setText(addres);
            eventoDatabase(nomeCidade);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}