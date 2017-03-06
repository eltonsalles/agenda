package br.senac.tads.pi3a.service;

import br.senac.tads.pi3a.utils.Conexao;
import java.sql.Connection;
import java.sql.SQLException;


public class ServicoDb {
    /**
     * Método para garantir que a tela abra apenas se houver 
     * conexão com o banco de dados
     * 
     * @return 
     */
    @SuppressWarnings("CallToPrintStackTrace")
    public static boolean verificarConexao() {
        Connection conexao;
        
        try {
            conexao = Conexao.getConexao();
            
            if (conexao != null && conexao.isValid(1000)) {
                return true;
            }
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
