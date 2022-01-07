package com.example.atvservidor.model;

import java.io.Serializable;

public class Livro implements Serializable {
    private String uid;
    private String titulo;
    private String editora;

    public Livro(){}
    public Livro(String uid,  String titulo, String editora) {
        this.uid = uid;
        this.titulo = titulo;
        this.editora = editora;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        String a="";
        a+="| Titulo : "+titulo+" |";
        a+=" \n| Editora : "+editora+" |";
        return a;
    }
}
