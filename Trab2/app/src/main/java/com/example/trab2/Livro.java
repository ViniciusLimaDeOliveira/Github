package com.example.trab2;

import java.io.Serializable;

public class Livro implements Serializable {
    private int isbn;
    private String titulo;
    private String editora;

    public Livro(int isbn, String titulo, String editora) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.editora = editora;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
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
        return "Livro" +
                "=| isbn='" + isbn +
                " | titulo='" + titulo +
                " | editora='" + editora + '|';
    }
}
