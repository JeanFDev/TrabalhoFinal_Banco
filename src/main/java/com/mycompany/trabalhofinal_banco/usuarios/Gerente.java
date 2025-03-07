/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.usuarios;

import com.mycompany.trabalhofinal_banco.entidades.RendaFixa;
import com.mycompany.trabalhofinal_banco.entidades.RendaVariavel;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.entidades.ContaCorrente;
import com.mycompany.trabalhofinal_banco.entidades.ContaPoupanca;
import com.mycompany.trabalhofinal_banco.excecoes.CPFDuplicadoException;
import com.mycompany.trabalhofinal_banco.util.GerenciadorContas;
import java.util.List;

public class Gerente extends Usuario {

    public Gerente(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }
    
    @Override
    public void realizarLogin(String senha) throws SenhaIncorretaException {
        if (!this.senha.equals(senha)) {
            throw new SenhaIncorretaException("Senha incorreta!");
        }
        System.out.println("Login realizado com sucesso!");
    }
    
    public RendaFixa cadastrarRendaFixa(String nome, double taxa) {
        return new RendaFixa(nome, taxa);
    }

    public RendaVariavel cadastrarRendaVariavel(String nome, double precoAcao) {
        return new RendaVariavel(nome, precoAcao, null);
    }

    public Cliente cadastrarCliente(String nome, String cpf, String senha, double saldoInicial, String tipoConta, List<Usuario> usuarios) throws IllegalArgumentException {
        // Verifica se já existe um usuário com o mesmo CPF
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                throw new CPFDuplicadoException("Já existe um usuário com este CPF.");
            }
        }

        int numeroConta = GerenciadorContas.gerarNumeroContaUnico();
        Conta conta;
        if (tipoConta.equals("Corrente")) {
            conta = new ContaCorrente( numeroConta,saldoInicial);
        } else {
            conta = new ContaPoupanca(numeroConta,saldoInicial);
        }
        GerenciadorContas.adicionarConta(conta);
        return new Cliente(nome, cpf, senha, conta);
    }

    public Caixa cadastrarCaixa(String nome, String cpf, String senha, List<Usuario> usuarios) throws IllegalArgumentException {
        // Verifica se já existe um usuário com o mesmo CPF
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                throw new CPFDuplicadoException("Já existe um usuário com este CPF.");
            }
        }
        return new Caixa(nome, cpf, senha);
    }

    public Gerente cadastrarGerente(String nome, String cpf, String senha, List<Usuario> usuarios) throws IllegalArgumentException {
        // Verifica se já existe um usuário com o mesmo CPF
        for (Usuario usuario : usuarios) {
            if (usuario.getCpf().equals(cpf)) {
                throw new CPFDuplicadoException("Já existe um usuário com este CPF.");
            }
        }
        return new Gerente(nome, cpf, senha);
    }
}