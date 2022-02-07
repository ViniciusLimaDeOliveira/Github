package com.example.whatsuphere.Entity;

public class MenssagemOBJ {
    private String uid;
    private String autor;
    private String conteudo;

    public MenssagemOBJ(){}
    public MenssagemOBJ(String uid,String autor, String conteudo) {
        this.uid = uid;
        this.autor = autor;
        this.conteudo = conteudo;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public String toString() {
        return  "== \n"+
                "autor='" + autor + ':'+ "\n" + conteudo + "\n =="  ;
    }
}
