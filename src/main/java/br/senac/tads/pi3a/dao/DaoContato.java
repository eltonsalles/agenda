/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3a.dao;

import java.sql.SQLException;
import java.util.List;
import br.senac.tads.pi3a.model.Contato;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.senac.tads.pi3a.utils.Conexao;

/**
 *
 * @author Fabiano
 */
public class DaoContato {
     public static boolean inserir(Contato contato) throws SQLException, Exception {
        String sql;

        sql  = "INSERT INTO cliente (nome,data_nasc, email,telefone ";
        sql += "VALUES (?, ?, ?, ?)";
        
        executarSql(sql, contato);
        
        return true;
    }
     
      public static boolean alterar(Contato contato) throws SQLException, Exception {
        String sql;
        
        sql  = "UPDATE contato SET nome = ?, data_nasc = ?, email = ?,telefone = ? ";
    
        sql += "WHERE id = ?";
        
        executarSql(sql, contato);
        
        return true;
    }
      
       public static boolean excluir( int id) throws SQLException, Exception {
        String sql;
        
        sql = "DELETE FROM contato WHERE id = ?";
        
        executarSql(sql, id);
        
        return true;
               
    }
       
       public static List<Contato> consultarContatos() 
               throws SQLException, Exception {
           
           
        List<Contato> listaContato;
        String sql;
        
        sql = "SELECT * FROM contato";
        
        listaContato = executarConsulta(sql);
        
        return listaContato;
    }
         public static Contato consultarContatoPorId(int id)
            throws SQLException, Exception {
        List<Contato> listaContato;
        String sql;
        
        sql = "SELECT * FROM cliente WHERE id = ?";
        
        listaContato = executarConsulta(sql, id, false);
        
        if (!listaContato.isEmpty()) {
            return listaContato.get(0);
        }
        
        return null;
    }
          public static List<Contato> consultarContatoPorNome(String nome)
            throws SQLException, Exception {
        List<Contato> listaContato;
        String sql;
        
        sql = "SELECT * FROM cliente WHERE nome = ?";
        
        listaContato = executarConsulta(sql, nome, false);
        
        if (!listaContato.isEmpty()) {
            
            return (List<Contato>) listaContato.get(0);
        }
        
        return null;
    }
       
         private static void executarSql(String sql, Contato contato)
            throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, contato.getNome());
            stmt.setDate(2, new Date(contato.getDataNasc().getTime()));
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getTelefone());
          

            stmt.execute();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            
            if (conn != null && !conn.isClosed()) {
                conn.isClosed();
            }
        }
    }
       
        private static void executarSql(String sql, int id) 
            throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            
            stmt.setInt(1, id);

            stmt.execute();
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            
            if (conn != null && !conn.isClosed()) {
                conn.isClosed();
            }
        }
    }
        
         private static List<Contato> executarConsulta(String sql) 
            throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result;
        List<Contato> listaContato = new ArrayList<>();
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            
            result = stmt.executeQuery();

            while (result.next()) {                
                Contato contato = new Contato(
                        result.getString("nome"), 
                        result.getDate("data_nasc"),
                         result.getString("telefone"),
                        result.getString("email"));
                        
                    
                
                
                listaContato.add(contato);
            }
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            
            if (conn != null && !conn.isClosed()) {
                conn.isClosed();
            }
        }
        
        return listaContato;
    }
       private static List<Contato> executarConsulta(String sql, String valor, 
            boolean like) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result;
        List<Contato> listaContato = new ArrayList<>();
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            
            if (like) {
                stmt.setString(1, '%' + valor.toUpperCase() + '%');
            } else {
                stmt.setString(1, valor.toUpperCase());
            }
            
            result = stmt.executeQuery();

            while (result.next()) {                
                Contato contato = new Contato(
                        result.getString("nome"), 
                        result.getDate("data_nasc"), 
                        result.getString("tel"), 
                        result.getString("email"));
                        
               
                
                listaContato.add(contato);
            }
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            
            if (conn != null && !conn.isClosed()) {
                conn.isClosed();
            }
        }
        
        return listaContato;
    }   
       private static List<Contato> executarConsulta(String sql, int valor, 
            boolean like) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result;
        List<Contato> listaContato = new ArrayList<>();
        
        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);
            
           
                stmt.setInt(1, valor);
             
            
            result = stmt.executeQuery();

            while (result.next()) {                
                Contato contato = new Contato(
                        result.getString("nome"), 
                        result.getDate("data_nasc"), 
                        result.getString("tel"), 
                        result.getString("email"));
                        
               
                
                listaContato.add(contato);
            }
        } finally {
            if (stmt != null && !stmt.isClosed()) {
                stmt.close();
            }
            
            if (conn != null && !conn.isClosed()) {
                conn.isClosed();
            }
        }
        
        return listaContato;
    }   
    
    
}
