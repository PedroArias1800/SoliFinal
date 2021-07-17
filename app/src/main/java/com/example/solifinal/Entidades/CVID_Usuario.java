package com.example.solifinal.Entidades;

public class CVID_Usuario {

    private String firstname;
    private String lastname;
    private String email;

    public CVID_Usuario(String a, String b, String c){
        setFirstname(a);
        setLastname(b);
        setEmail(c);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}