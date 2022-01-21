package com.example.whatsuphere.Entity;

public class local {
    private String Uid;
    private String localName;

    public local(String uid, String localName) {
        this.Uid = uid;
        this.localName = localName;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        this.Uid = uid;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }
}
