package com.example.dominio;

public class Livro {
    private int id;
    private String titulo;
    private String autor;
    private boolean disponivel;

    public Livro() {} // Construtor vazio para o JPA

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    public void add(Livro Livro) {
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}
