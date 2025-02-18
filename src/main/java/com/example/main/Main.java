package com.example.main;

import com.example.dominio.*;
import com.example.repository.*;
import com.example.service.Notificar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UsuarioRepository usuarioRepo = new UsuarioRepository();
        LivroRepository livroRepo = new LivroRepository();

        int opcao;
        do {
            System.out.println("\n📌 Menu:");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Adicionar Usuário");
            System.out.println("3. Listar Livros");
            System.out.println("4. Listar Usuários");
            System.out.println("5. Realizar Empréstimo");
            System.out.println("6. Remover Livro");
            System.out.println("7. Remover Usuário");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1 -> {
                    System.out.print("Título: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    Livro livro = new Livro(titulo, autor);
                    livroRepo.salvar(livro);
                    System.out.println("✅ Livro adicionado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    Usuario usuario = new Usuario(nome, email);
                    usuarioRepo.salvar(usuario);
                    System.out.println("✅ Usuário cadastrado com sucesso!");
                }
                case 3 -> {
                    System.out.println("\n📚 Lista de Livros:");
                    for (Livro livro : livroRepo.listarTodos()) {
                        System.out.println("- " + livro.getTitulo() + " | " + livro.getAutor());
                    }
                }
                case 4 -> {
                    System.out.println("\n👤 Lista de Usuários:");
                    for (Usuario usuario : usuarioRepo.listarTodos()) {
                        System.out.println("- " + usuario.getNome() + " | " + usuario.getEmail());
                    }
                }
                case 5 -> {
                    System.out.print("Nome do usuário: ");
                    String nomeUsuario = scanner.nextLine();
                    Usuario usuario = usuarioRepo.buscarPorNome(nomeUsuario);

                    if (usuario == null) {
                        System.out.println("❌ Usuário não encontrado.");
                        break;
                    }

                    System.out.print("Título do livro: ");
                    String tituloLivro = scanner.nextLine();
                    Livro livro = livroRepo.buscarPorTitulo(tituloLivro);

                    if (livro == null) {
                        System.out.println("❌ Livro não encontrado.");
                        break;
                    }

                    if (!livro.isDisponivel()) {
                        System.out.println("❌ O livro já está emprestado.");
                        break;
                    }

                    livro.setDisponivel(false);
                    livroRepo.atualizar(livro);

                    Notificar.notificarEmprestimo(usuario.getEmail(), livro.getTitulo());

                    System.out.println("✅ Empréstimo realizado com sucesso!");
                }
                case 6 -> {
                    System.out.print("Título do livro a remover: ");
                    String tituloLivro = scanner.nextLine();

                    if (livroRepo.removerPorTitulo(tituloLivro)) {
                        System.out.println("✅ Livro removido com sucesso!");
                    } else {
                        System.out.println("❌ Livro não encontrado.");
                    }
                }
                case 7 -> {
                    System.out.print("Nome do usuário a remover: ");
                    String nomeUsuario = scanner.nextLine();
                    Usuario usuario = usuarioRepo.buscarPorNome(nomeUsuario);

                    if (usuario == null) {
                        System.out.println("❌ Usuário não encontrado.");
                        break;
                    }

                    if (usuarioRepo.removerPorNome(nomeUsuario)) {
                        Notificar.enviarEmail(usuario.getEmail(), "Conta Removida", "Sua conta foi removida do sistema.");
                        System.out.println("✅ Usuário removido com sucesso!");
                    } else {
                        System.out.println("❌ Erro ao remover usuário.");
                    }
                }
                case 8 -> System.out.println("Saindo...");
                default -> System.out.println("❌ Opção inválida!");
            }
        } while (opcao != 8);

        scanner.close();
    }
}
