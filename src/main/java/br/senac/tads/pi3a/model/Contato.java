/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.tads.pi3a.model;

import java.util.Date;

/**
 *
 * @author fabiano.bfcarvalho
 */
public class Contato {
    private String nome;
    private Date dataNasc;
    private int telefone;
    private String email;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataNasc
     */
    public Date getDataNasc() {
        return dataNasc;
    }

    /**
     * @param dataNasc the dataNasc to set
     */
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    /**
     * @return the telefone
     */
    public int getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /*
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public boolean addContato(){
        
        return false;
    }
    public boolean removerContato(){
        
        return false;
    }
    
}
