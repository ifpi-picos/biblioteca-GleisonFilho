package main;

import dominio.*;
import service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        int opcao;

        do {
            System.out.println("\n=== Sistema de Gerenciamento de Biblioteca ===");
            System.out.println("1. Adicionar Livro");
            System.out.println("2. Adicionar Usuário");
            System.out.println("3. Listar Livros");
            System.out.println("4. Listar Usuários");
            System.out.println("5. Realizar Empréstimo");
            System.out.println("6. Listar Empréstimos");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título do livro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Digite o autor do livro: ");
                    String autor = scanner.nextLine();
                    biblioteca.adicionarLivro(new Livro(titulo, autor));
                    break;

                case 2:
                    System.out.print("Digite o nome do usuário: ");
                    String nome = scanner.nextLine();
                    biblioteca.adicionarUsuario(new Usuario(nome));
                    break;

                case 3:
                    biblioteca.listarLivros();
                    break;

                case 4:
                    biblioteca.listarUsuarios();
                    break;

                case 5:
                    System.out.print("Digite o ID do usuário: ");
                    int idUsuario = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o título do livro: ");
                    String tituloLivro = scanner.nextLine();
                    biblioteca.realizarEmprestimo(idUsuario, tituloLivro);
                    break;

                case 6:
                    biblioteca.listarEmprestimos();
                    break;

                case 7:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 7);

        scanner.close();
    }
}