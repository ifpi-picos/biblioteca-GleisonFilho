package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.service.Conexao;

public class App {
    public static void main(String[] args) {
        Connection conexao = Conexao.getConnection();

        if (conexao != null) {
            System.out.println("Banco de dados conectado com sucesso!");

            // Exemplo de consulta para listar livros
            listarLivros(conexao);
        } else {
            System.out.println("Conexão falhou!");
        }
    }

    public static void listarLivros(Connection conexao) {
        String sql = "SELECT id, titulo, autor FROM Livro"; // Altere conforme o nome da tabela no BD

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Lista de Livros:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                System.out.println(id + " - " + titulo + " (Autor: " + autor + ")");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        }
    }
}
