package com.example.whatsuphere.Entity;

import java.io.Serializable;
import java.util.List;

public class Chat implements Serializable {
    private String nome;
    private Double latitude;
    private Double longitude;

    public Chat(){}
    public Chat(String nome,Double latitude, Double longitude) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public Chat(String nome){
        this.nome = nome;
    }
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
