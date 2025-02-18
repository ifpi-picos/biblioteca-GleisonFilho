package com.example.service;

public class Notificar {

    public static void notificarEmprestimo(String destinatario, String livro) {
        System.out.println("📧 Enviando e-mail para: " + destinatario);
        System.out.println("📌 Assunto: Confirmação de Empréstimo");
        System.out.println("📖 Mensagem: O livro '" + livro + "' foi emprestado com sucesso!");
        System.out.println("-------------------------------------------------");
    }

    public static void enviarEmail(String email, String assunto, String mensagem) {
        System.out.println("📧 Enviando e-mail para: " + email);
        System.out.println("📌 Assunto: " + assunto);
        System.out.println("📖 Mensagem: " + mensagem);
        System.out.println("-------------------------------------------------");
    }

    public static void notificarDevolucao(String destinatario, String livro, int diasRestantes) {
        System.out.println("📧 Enviando e-mail para: " + destinatario);
        System.out.println("📌 Assunto: Lembrete de Devolução");
        System.out.println("📖 Mensagem: O livro '" + livro + "' deve ser devolvido em " + diasRestantes + " dias.");
        System.out.println("-------------------------------------------------");
    }
}
