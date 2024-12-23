package service;

import dominio.*;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;
    private List<Usuario> usuarios;
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("Livro adicionado: " + livro.getTitulo());
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuário adicionado: " + usuario.getNome());
    }

    public void listarLivros() {
        System.out.println("Livros na biblioteca:");
        for (Livro livro : livros) {
            String status = livro.isDisponivel() ? "Disponível" : "Emprestado";
            System.out.println("Título: " + livro.getTitulo() + " | Autor: " + livro.getAutor() + " | Status: " + status);
        }
    }

    public void listarUsuarios() {
        System.out.println("Usuários cadastrados:");
        for (Usuario usuario : usuarios) {
            System.out.println("ID: " + usuario.getId() + " | Nome: " + usuario.getNome());
        }
    }

    public void realizarEmprestimo(int idUsuario, String tituloLivro) {
        Usuario usuario = usuarios.stream()
                .filter(u -> u.getId() == idUsuario)
                .findFirst()
                .orElse(null);

        Livro livro = livros.stream()
                .filter(l -> l.getTitulo().equalsIgnoreCase(tituloLivro) && l.isDisponivel())
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        if (livro == null) {
            System.out.println("Livro não encontrado ou indisponível.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        emprestimos.add(emprestimo);
        System.out.println("Empréstimo realizado com sucesso!");
    }

    public void listarEmprestimos() {
        System.out.println("Empréstimos realizados:");
        for (Emprestimo emprestimo : emprestimos) {
            System.out.println("Usuário: " + emprestimo.getUsuario().getNome() +
                    " | Livro: " + emprestimo.getLivro().getTitulo() +
                    " | Data: " + emprestimo.getDataEmprestimo());
        }
    }
}