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

        sql  = "INSERT INTO contato (nome, data_nasc, email, telefone, ";
        sql += "data_cadastro) VALUES (?, ?, ?, ?, ?)";

        executarSql(sql, contato, "insert");

        return true;
    }

    public static boolean alterar(Contato contato) throws SQLException, Exception {
        String sql;

        sql  = "UPDATE contato SET nome = ?, data_nasc = ?, email = ?, ";
        sql += "telefone = ? WHERE id = ?";

        executarSql(sql, contato, "update");

        return true;
    }

    public static boolean excluir(int id) throws SQLException, Exception {
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

        sql = "SELECT * FROM contato WHERE id = ?";

        listaContato = executarConsulta(sql, id);

        if (!listaContato.isEmpty()) {
            return listaContato.get(0);
        }

        return null;
    }

    public static List<Contato> consultarContatoPorNome(String nome)
            throws SQLException, Exception {
        List<Contato> listaContato;
        String sql;

        sql = "SELECT * FROM contato WHERE UPPER(nome) LIKE ?";

        listaContato = executarConsulta(sql, nome);

        if (!listaContato.isEmpty()) {
            return listaContato;
        }

        return null;
    }

    private static void executarSql(String sql, Contato contato, 
            String operacao) throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, contato.getNome());
            stmt.setDate(2, new Date(contato.getDataNasc().getTime()));
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getTelefone());
            
            if (operacao.equalsIgnoreCase("insert")) {
                stmt.setDate(5, new Date(contato.getDataCadastro().getTime()));
            } else {
                stmt.setInt(5, contato.getId());
            }

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

    private static List<Contato> executarConsulta(String sql, String valor) 
            throws SQLException, Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet result;
        List<Contato> listaContato = new ArrayList<>();

        try {
            conn = Conexao.getConexao();
            stmt = conn.prepareStatement(sql);

            stmt.setString(1, '%' + valor.toUpperCase() + '%');

            result = stmt.executeQuery();

            while (result.next()) {
                Contato contato = new Contato(
                        result.getString("nome"),
                        result.getDate("data_nasc"),
                        result.getString("telefone"),
                        result.getString("email"));
                contato.setId(result.getInt("id"));

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

    private static List<Contato> executarConsulta(String sql, int valor)
            throws SQLException, Exception {
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
                        result.getString("telefone"),
                        result.getString("email"));
                contato.setId(result.getInt("id"));

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
