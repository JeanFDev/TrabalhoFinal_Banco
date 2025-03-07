/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.usuarios;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.excecoes.ContaInexistenteException;
import com.mycompany.trabalhofinal_banco.excecoes.SaldoInsuficienteException;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.excecoes.ValorInvalidoException;
import javax.swing.JOptionPane;


public class Caixa extends Usuario {
    public Caixa(String nome, String cpf, String senha) {
        super(nome, cpf, senha);
    }

    @Override
    public void realizarLogin(String senha) throws SenhaIncorretaException {
        if (!this.senha.equals(senha)) {
            throw new SenhaIncorretaException("Senha incorreta!");
        }
        System.out.println("Login realizado com sucesso!");
    }

    public void realizarSaque(Cliente cliente, double valor, String senha) throws SaldoInsuficienteException, SenhaIncorretaException {
        cliente.realizarLogin(senha);
        if (cliente.conta.consultarSaldo() < valor) {
            throw new SaldoInsuficienteException("Saldo insuficiente.");
        }
        cliente.conta.setSaldo(cliente.conta.consultarSaldo() - valor);
        JOptionPane.showMessageDialog(null, "Saque de R$" + valor + " realizado com sucesso.");
    }

    public void realizarDeposito(Cliente cliente, double valor) {
        cliente.conta.setSaldo(cliente.conta.consultarSaldo() + valor);
        JOptionPane.showMessageDialog(null, "Depósito de R$" + valor + " realizado com sucesso.");
    }

    public void realizarTransferencia(Cliente cliente, Conta destino, double valor, String senha) throws SaldoInsuficienteException, SenhaIncorretaException, ContaInexistenteException, ValorInvalidoException {
        cliente.realizarLogin(senha);
        cliente.transferir(destino, valor);
        JOptionPane.showMessageDialog(null, "Transferência de R$" + valor + " realizada com sucesso.");
    }
}

