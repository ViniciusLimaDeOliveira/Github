package com.example.whatsuphere.Entity;

import java.util.List;

public class Contatos {
    private int id;
    private int id_cliente;
    private List<Contato> contatos;

    public Contatos(int id, int id_cliente) {
        this.id = id;
        this.id_cliente = id_cliente;
    }
    public Contatos(int id, int id_cliente, List<Contato> contatos) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.contatos = contatos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
}
