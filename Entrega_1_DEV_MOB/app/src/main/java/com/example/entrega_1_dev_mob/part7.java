package com.example.entrega_1_dev_mob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.entrega_1_dev_mob.databinding.ActivityPart7Binding;

public class part7 extends AppCompatActivity {
    private  ActivityPart7Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part7);
        binding = ActivityPart7Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] flowerName = {"Rose","Lotus","Lily","Jasmine",
                "Tulip","Orchid","Levender","RoseMarry","Sunflower","Carnation"};
        int[] flowerImages = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,R.drawable.g,
                R.drawable.h,R.drawable.i,R.drawable.j};

        GridAdapter gridAdapter = new GridAdapter(part7.this,flowerName,flowerImages);
        binding.gridv.setAdapter(gridAdapter);
        binding.gridv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(part7.this,"You Clicked on "+ flowerName[position],Toast.LENGTH_SHORT).show();

            }
        });
    }
}