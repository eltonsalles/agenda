package br.senac.tads.pi3a.model;

import java.util.Date;

/**
 *
 * @author fabiano.bfcarvalho
 */
public class Contato {

    private int id;
    private String nome;
    private Date dataNasc;
    private String telefone;
    private String email;
    private Date dataCadastro;

    /**
     *
     * @param nome
     * @param dataNasc
     * @param telefone
     * @param email
     */
    public Contato(String nome, Date dataNasc, String telefone, String email) {
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
