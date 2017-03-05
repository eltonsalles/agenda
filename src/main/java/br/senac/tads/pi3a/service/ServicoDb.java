package br.senac.tads.pi3a.service;

import br.senac.tads.pi3a.utils.Conexao;
import java.sql.Connection;

public class ServicoDb {
    @SuppressWarnings("CallToPrintStackTrace")
    public static boolean verificarConexao() {
        Connection conexao;
        
        try {
            conexao = Conexao.getConexao();
            
            if (conexao != null && conexao.isValid(1000)) {
                return true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
