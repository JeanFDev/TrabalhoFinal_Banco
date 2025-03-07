/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.usuarios;

import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;


public abstract class Usuario {
    public String nome;
    public String cpf;
    public String senha;

    public Usuario(String nome, String cpf, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }
    
    public abstract void realizarLogin(String senha) throws SenhaIncorretaException;
}
