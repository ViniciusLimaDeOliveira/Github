package com.example.whatsuphere.Entity;

import java.io.Serializable;

public class Chat implements Serializable {
    private String Uid;
    private String nome;
    //private String uidDono;
    //local para saber onde ele deve ser mostrado
    //pode ter mais coisa

    public Chat(String uid, String nome)   {
        Uid = uid;
        this.nome = nome;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "Uid='" + Uid + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
