package com.example.repository;

import com.example.dominio.Emprestimo;
import com.example.dominio.Livro;
import com.example.dominio.Usuario;
import com.example.service.Conexao;
import com.example.service.Notificar; // Importando a classe de notificação
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmprestimoRepository {
    
    public void salvar(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (usuario_id, livro_id, data_emprestimo) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, emprestimo.getUsuario().getId());
            stmt.setString(2, emprestimo.getLivro().getTitulo());  
            stmt.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
            stmt.executeUpdate();

            // Notificar o usuário após o empréstimo ser registrado com sucesso
            System.out.println("📢 Notificando usuário sobre o empréstimo...");
            Notificar.notificarEmprestimo(emprestimo.getUsuario().getEmail(), emprestimo.getLivro().getTitulo());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Emprestimo> listarTodos() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT e.id, u.id AS usuario_id, u.nome, u.email, l.titulo, e.data_emprestimo " +
                     "FROM emprestimo e " +
                     "JOIN usuario u ON e.usuario_id = u.id " +
                     "JOIN livro l ON e.livro_id = l.id";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(rs.getString("nome"), rs.getString("email"));  
                usuario.setId(rs.getInt("usuario_id"));
                
                Livro livro = new Livro(rs.getString("titulo"), "Autor Desconhecido");  
                
                Emprestimo emprestimo = new Emprestimo(livro, usuario);
                emprestimos.add(emprestimo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emprestimos;
    }
}
