package com.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:postgresql://localhost:5432/Biblioteca";
    private static final String USER = System.getenv("DB_USER"); // Pegando usuário do ambiente
    private static final String PASSWORD = System.getenv("DB_PASSWORD"); // Pegando senha do ambiente

    static {
        try {
            Class.forName("org.postgresql.Driver"); // Garante que o driver está carregado
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ Driver do PostgreSQL não encontrado!", e);
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("❌ Erro ao conectar ao banco de dados: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
