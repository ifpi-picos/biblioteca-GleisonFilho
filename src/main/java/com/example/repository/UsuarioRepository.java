package com.example.repository;

import com.example.dominio.Usuario;
import com.example.service.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {
    
    public void salvar(Usuario usuario) {
        String sql = "INSERT INTO usuario (nome, email) VALUES (?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.executeUpdate();
            
            System.out.println("✅ Usuário salvo com sucesso!");
            
        } catch (SQLException e) {
            System.err.println("❌ Erro ao salvar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public Usuario buscarPorNome(String nome) {
        String sql = "SELECT * FROM usuario WHERE nome = ?";
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                return new Usuario(rs.getString("nome"), rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT nome, email FROM usuario";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                usuarios.add(new Usuario(rs.getString("nome"), rs.getString("email")));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }

        return usuarios;
    }

    public boolean removerPorNome(String nome) {
        String sql = "DELETE FROM usuario WHERE nome = ?";
    
        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, nome);
            int rowsDeleted = stmt.executeUpdate();
            
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("❌ Erro ao remover usuário: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }    
}
