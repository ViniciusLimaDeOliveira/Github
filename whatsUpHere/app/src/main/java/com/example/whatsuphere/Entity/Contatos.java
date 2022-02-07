package com.example.whatsuphere.Entity;

import java.util.List;

public class Contatos {
    private String nome;
    private String Email;

    public Contatos(String nome, String email) {
        this.nome = nome;
        Email = email;
    }

    public Contatos() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
