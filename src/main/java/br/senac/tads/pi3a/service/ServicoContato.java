package br.senac.tads.pi3a.service;

import br.senac.tads.pi3a.model.Contato;
import br.senac.tads.pi3a.dao.DaoContato;
import java.util.List;

/**
 *
 * @author Elton
 */
public class ServicoContato {
    
    /**
     * Recebe um objeto Contato e chama a DaoContato para inserir o 
     * registro no banco de dados
     * 
     * @param contato
     * @return
     * @throws Exception 
     */
    public static boolean cadastrar(Contato contato) throws Exception {
        try {
            return DaoContato.inserir(contato);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Recebe um objeto Contato e chama a DaoContato para alterar o 
     * registro no banco de dados
     * 
     * @param contato
     * @return
     * @throws Exception 
     */
    public static boolean alterar(Contato contato) throws Exception {
        try {
            return DaoContato.alterar(contato);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Recebe o id do contato e chama o DaoContato para excluir o registro
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public static boolean excluir(int id) throws Exception {
        try {
            if (id <= 0) {
                throw new Exception("Informe um número válido de Id.");
            }
            
            return DaoContato.excluir(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Retorna todos os contatos do banco de dados
     * 
     * @return
     * @throws Exception 
     */
    public static List<Contato> consultarContatos() throws Exception {
        try {
            return DaoContato.consultarContatos();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Retorna um objeto Contato conforme o id pesquisado
     * 
     * @param id
     * @return
     * @throws Exception 
     */
    public static Contato consultarContatoPorId(int id) throws Exception {
        try {
            if (id <= 0) {
                throw new Exception("Informe um número válido de Id.");
            }
            
            return DaoContato.consultarContatoPorId(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    /**
     * Retorna todos os contatos do banco de dados que tenham parte do 
     * nome igual a string
     * 
     * @param nome
     * @return
     * @throws Exception 
     */
    public static List<Contato> consultarContatosPorNome(String nome) 
            throws Exception {
        try {
            if (nome.isEmpty()) {
                throw new Exception("Informe um nome para a pesquisa.");
            }
            
            return DaoContato.consultarContatoPorNome(nome);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
