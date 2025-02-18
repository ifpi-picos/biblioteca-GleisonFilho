package com.example.service;

import com.example.dominio.Livro;
import com.example.dominio.Usuario;
import com.example.dominio.Emprestimo;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        System.out.println("📚 Livro adicionado com sucesso!");
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("👤 Usuário adicionado com sucesso!");
    }

    public void listarLivros() {
        if (livros.isEmpty()) {
            System.out.println("⚠️ Nenhum livro cadastrado.");
        } else {
            System.out.println("📚 Livros cadastrados:");
            for (Livro livro : livros) {
                System.out.println("- " + livro.getTitulo() + " (Autor: " + livro.getAutor() + ")");
            }
        }
    }

    public Usuario buscarUsuario(String nome) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equalsIgnoreCase(nome)) {
                return usuario;
            }
        }
        return null; 
    }
    
    public Livro buscarLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro;
            }
        }
        return null;
    }

    public void listarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("⚠️ Nenhum usuário cadastrado.");
        } else {
            System.out.println("👤 Usuários cadastrados:");
            for (Usuario usuario : usuarios) {
                System.out.println("- " + usuario.getNome());
            }
        }
    }

    public void realizarEmprestimo(Usuario usuario, Livro livro) {
        if (!livros.contains(livro)) {
            System.out.println("❌ Livro não encontrado.");
            return;
        }
        if (!usuarios.contains(usuario)) {
            System.out.println("❌ Usuário não encontrado.");
            return;
        }
        if (!livro.isDisponivel()) {
            System.out.println("❌ O livro já está emprestado.");
            return;
        }

        Emprestimo emprestimo = new Emprestimo(livro, usuario);
        emprestimos.add(emprestimo);
        livro.setDisponivel(false);

        System.out.println("✅ Empréstimo realizado com sucesso!");
        System.out.println("📅 Data de devolução: " + emprestimo.getDataDevolucao());
    }
}
