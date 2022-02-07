package com.example.whatsuphere.Entity;

public class Contato {
    String iud;
    String email;

    public Contato(String iud,String email) {
        this.iud = iud;
        this.email = email;
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
        return "|"+email+"|";
    }
}
