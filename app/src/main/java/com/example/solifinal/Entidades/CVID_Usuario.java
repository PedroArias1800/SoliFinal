package com.example.solifinal.Entidades;

public class CVID_Usuario {

    private int id;
    private String user;
    private String password;
    private String nombre;

    public CVID_Usuario(int i, String u, String p, String n){
        id = i;
        user = u;
        password = p;
        nombre = n;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}