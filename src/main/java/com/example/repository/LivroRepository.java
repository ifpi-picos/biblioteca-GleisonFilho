package com.example.repository;

import com.example.dominio.Livro;
import com.example.service.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroRepository {
    
    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, disponivel) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setBoolean(3, livro.isDisponivel());
            stmt.executeUpdate();

            System.out.println("✅ Livro salvo com sucesso!");

        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Livro> listarTodos() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT titulo, autor, disponivel FROM livro";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Livro livro = new Livro(rs.getString("titulo"), rs.getString("autor"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
                livros.add(livro);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar livros: " + e.getMessage());
            e.printStackTrace();
        }

        return livros;
    }

    public Livro buscarPorTitulo(String tituloLivro) {
        String sql = "SELECT titulo, autor, disponivel FROM livro WHERE titulo = ?";
        
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tituloLivro);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Livro livro = new Livro(rs.getString("titulo"), rs.getString("autor"));
                livro.setDisponivel(rs.getBoolean("disponivel"));
                return livro;
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar livro: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    public void atualizar(Livro livro) {
        String sql = "UPDATE livro SET autor = ?, disponivel = ? WHERE titulo = ?";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getAutor());
            stmt.setBoolean(2, livro.isDisponivel());
            stmt.setString(3, livro.getTitulo());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("✅ Livro atualizado com sucesso!");
            } else {
                System.out.println("⚠️ Nenhum livro encontrado para atualização.");
            }
        } catch (SQLException e) {
            System.err.println("❌ Erro ao atualizar livro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean removerPorTitulo(String titulo) {
        String sql = "DELETE FROM livro WHERE titulo = ?";
    
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, titulo);
            int rowsDeleted = stmt.executeUpdate();
            
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erro ao remover livro: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}    