package com.example.dominio;

public class Usuario {
    private int id;
    private String nome;
    private String email;

    public Usuario() {}

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int int1) {
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}
