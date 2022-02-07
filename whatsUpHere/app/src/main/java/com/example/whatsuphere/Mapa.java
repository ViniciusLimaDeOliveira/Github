package com.example.whatsuphere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.whatsuphere.Entity.Chat;
import com.example.whatsuphere.Entity.Ponto;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
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

public class Mapa extends AppCompatActivity implements LocationListener,OnMapReadyCallback {
    GoogleMap map;
    Geocoder geocoder;
    LocationManager locationManager;
    LocationListener locationListener;
    private ArrayList<Ponto> pontos;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private LatLng atual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        geocoder = new Geocoder(this);
        IniciarFirebase();
        //Mapa
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.Mapa);
        mapFragment.getMapAsync(this);
        // permições
        if (ContextCompat.checkSelfPermission(Mapa.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Mapa.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        if (ContextCompat.checkSelfPermission(Mapa.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Mapa.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
        }
        //pegar localização
        getLocation();

    }
    private void IniciarFirebase() {
        FirebaseApp.initializeApp(Mapa.this);
        mAuth = FirebaseAuth.getInstance();
        RootRef = FirebaseDatabase.getInstance().getReference();
    }
    private void getLocation() {
        if (ContextCompat.checkSelfPermission(Mapa.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Mapa.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        if (ContextCompat.checkSelfPermission(Mapa.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Mapa.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
        }
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    5000,
                    5,
                    Mapa.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void eventoDatabase(String cidadeName) {
        RootRef.child("Chats").child(cidadeName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pontos.clear();
                for(DataSnapshot objSnapshot:snapshot.getChildren()){
                    Chat c = objSnapshot.getValue(Chat.class);
                    String name = c.getNome();
                    Double lt = c.getLatitude();
                    Double lo = c.getLongitude();
                    // Toast.makeText(Ambientes.this, name, Toast.LENGTH_SHORT).show();
                    LatLng ltlo = new LatLng(lt,lo);
                    Ponto cn = new Ponto(name,ltlo);
                    pontos.add(cn);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        //Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(Mapa.this, Locale.getDefault());
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String addres = addressList.get(0).getSubAdminArea();
            eventoDatabase(addres);
            atual = new LatLng(location.getLatitude(),location.getLongitude());
            // agora buscar no firebase os pontos
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        for (Ponto p:
             pontos) {
            map.addMarker(new MarkerOptions().position(p.getCordenadas()).title(p.getNome()));
            map.animateCamera(CameraUpdateFactory.zoomTo(15.0f));
            map.moveCamera(CameraUpdateFactory.newLatLng(atual));
        }
    }
}
