package com.example.whatsuphere;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.whatsuphere.Entity.Chat;
import com.example.whatsuphere.Entity.Contato;
import com.example.whatsuphere.Entity.local;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class historico extends AppCompatActivity implements LocationListener {
    //private ListView listViewH;
    //private List<local> auxH;
    private WebView webView;
    LocationManager locationManager;
    LatLng cordendas;
    LocationListener locationListener;
    private GeolocationPermissions.Callback mCallBack;
    private String mOrigin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Whats Up!");
        setContentView(R.layout.activity_historico);
        webView = (WebView) findViewById(R.id.webmapa);
        getLocation();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setGeolocationEnabled(true);

        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                if(Build.VERSION.SDK_INT>=23){
                    if(hasPermission()){
                        callback.invoke(origin,true,false);
                    }else {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},362);
                        mCallBack = callback;
                        mOrigin = origin;
                    }
                }else{
                    callback.invoke(origin,true,false);
                }

            }
        });


    }
    private void iniciarSite(){
        webView.loadUrl( "https://www.google.com/maps/search/?api=1&query="+cordendas.latitude+","+cordendas.longitude);
    }
    private void getLocation() {
        if (ContextCompat.checkSelfPermission(historico.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(historico.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }
        if (ContextCompat.checkSelfPermission(historico.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(historico.this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 100);
        }
        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    1000,
                    5,
                    historico.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  boolean hasPermission(){
        return ContextCompat.checkSelfPermission(historico.this, Manifest.permission.ACCESS_FINE_LOCATION)== PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==362){
            if(grantResults.length>0&&grantResults[0]== PERMISSION_GRANTED){
                mCallBack.invoke(mOrigin,true,false);
            }
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        try {
            Geocoder geocoder = new Geocoder(historico.this, Locale.getDefault());
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String addres = addressList.get(0).getSubAdminArea();
            cordendas = new LatLng(location.getLatitude(),location.getLongitude());
            iniciarSite();
            // agora buscar no firebase os pontos
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("Whats Up!");
//        setContentView(R.layout.activity_historico);
//        listViewH = (ListView) findViewById(R.id.listHistorico);
//        auxH = new ArrayList<local>();
//        for (int i=0;i<10;i++){
//            local lc = new local(Integer.toString(i),"local"+Integer.toString(i));
//            auxH.add(lc);
//        }
//        listViewH.setAdapter(new ArrayAdapter<local>(historico.this,
//                android.R.layout.simple_list_item_1,auxH));
//    }