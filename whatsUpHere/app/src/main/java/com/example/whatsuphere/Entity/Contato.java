package com.example.whatsuphere.Entity;

public class Contato {
    String iud;
    String nome;
    String email;

    public Contato(String iud,String email,String nome) {
        this.nome=nome;
        this.iud = iud;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Contato() {
    }

    public String getIud() {
        return iud;
    }

    public void setIud(String iud) {
        this.iud = iud;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "|_> "+nome+" |"+"\n|-->"+email+" |";
    }
}
