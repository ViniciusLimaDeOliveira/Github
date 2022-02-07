package com.example.whatsuphere.Entity;

import com.google.android.gms.maps.model.LatLng;

public class Ponto {
    private String nome;
    private LatLng cordenadas;

    public Ponto(){};

    public Ponto(String nome, LatLng cordenadas) {
        this.nome = nome;
        this.cordenadas = cordenadas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LatLng getCordenadas() {
        return cordenadas;
    }

    public void setCordenadas(LatLng cordenadas) {
        this.cordenadas = cordenadas;
    }

}
