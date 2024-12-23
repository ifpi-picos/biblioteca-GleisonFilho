package dominio;

public class Usuario {
    private int id;
    private String nome;
    private static int contador = 1;

    public Usuario(String nome) {
        this.nome = nome;
        this.id = contador++;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}