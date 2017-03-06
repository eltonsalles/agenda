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

    /**
     * Faz insert na tabela contato
     *
     * @param contato
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean inserir(Contato contato) throws SQLException, Exception {
        String sql;

        sql  = "INSERT INTO contato (nome, data_nasc, email, telefone, ";
        sql += "data_cadastro) VALUES (?, ?, ?, ?, ?)";

        executarSql(sql, contato, "insert");

        return true;
    }

    /**
     * Faz update na tabela contato
     *
     * @param contato
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean alterar(Contato contato) throws SQLException, Exception {
        String sql;

        sql  = "UPDATE contato SET nome = ?, data_nasc = ?, email = ?, ";
        sql += "telefone = ? WHERE id = ?";

        executarSql(sql, contato, "update");

        return true;
    }

    /**
     * Exclui um registro pelo id na tabela contato
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws Exception
     */
    public static boolean excluir(int id) throws SQLException, Exception {
        String sql;

        sql = "DELETE FROM contato WHERE id = ?";

        executarSql(sql, id);

        return true;
    }
    
    /**
     * Traz todos os contatos da tabela contato
     * 
     * @return
     * @throws SQLException
     * @throws Exception 
     */
    public static List<Contato> consultarContatos() 
            throws SQLException, Exception {
        List<Contato> listaContato;
        String sql;

        sql = "SELECT * FROM contato";

        listaContato = executarConsulta(sql);

        if (!listaContato.isEmpty()) {
            return listaContato;
        }

        return null;
    }

    /**
     * Faz uma consulta na tabela contato pelo id
     *
     * @param id
     * @return
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * Faz uma consulta na tabela contato pelo nome
     *
     * @param nome
     * @return
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * Executa operações de insert ou update
     *
     * @param sql
     * @param contato
     * @param operacao
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * Executa a operação delete no banco de dados
     *
     * @param sql
     * @param id
     * @throws SQLException
     * @throws Exception
     */
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

    /**
     * Faz a operação select no banco de dados sem paramêtros
     *
     * @param sql
     * @return
     * @throws SQLException
     * @throws Exception
     */
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
                contato.setId(result.getInt("id"));
                contato.setDataCadastro(result.getDate("data_cadastro"));

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
    
    /**
     * Faz a operação select no banco de dados usando como paramêtro uma string
     *
     * @param sql
     * @param valor
     * @return
     * @throws SQLException
     * @throws Exception
     */
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
                contato.setDataCadastro(result.getDate("data_cadastro"));

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

    /**
     * Faz a operação select no banco de dados usando como paramêtro um int
     *
     * @param sql
     * @param valor
     * @return
     * @throws SQLException
     * @throws Exception
     */
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
                contato.setDataCadastro(result.getDate("data_cadastro"));

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
