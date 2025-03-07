/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.usuarios;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.entidades.RendaFixa;
import com.mycompany.trabalhofinal_banco.entidades.RendaVariavel;
import com.mycompany.trabalhofinal_banco.excecoes.ContaInexistenteException;
import com.mycompany.trabalhofinal_banco.excecoes.SaldoInsuficienteException;
import com.mycompany.trabalhofinal_banco.excecoes.ValorInvalidoException;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.interfaces.Investimento;


public class Cliente extends Usuario implements Investimento {
    public Conta conta;

    public Cliente(String nome, String cpf, String senha, Conta conta) {
        super(nome, cpf, senha);
        this.conta = conta;
    }

    @Override
    public void realizarLogin(String senha) throws SenhaIncorretaException {
        if (!this.senha.equals(senha)) {
            throw new SenhaIncorretaException("Senha incorreta!");
        }
        System.out.println("Login realizado com sucesso!");
    }

    @Override
    public void investir(double valor) {
        if (conta.consultarSaldo() < valor) {
            throw new RuntimeException("Saldo insuficiente.");
        }

        conta.setSaldo(conta.consultarSaldo() - valor);
        System.out.println("Investimento realizado com sucesso, Valor: R$" + valor);
    }

    @Override
    public void resgatar(double valor) {
        conta.setSaldo(conta.consultarSaldo() + valor);
        System.out.println("Resgate realizado com sucesso, Valor: R$" + valor);
    }

    public void investirRendaFixa(RendaFixa investimento, double valor) {
        investir(valor);
        System.out.println("Investimento em renda fixa realizado com sucesso: " + investimento.getNome() + ", Valor: R$" + valor);
    }

    public void investirRendaVariavel(RendaVariavel investimento, double valor) {
        investir(valor);
        System.out.println("Investimento em renda variável realizado com sucesso: " + investimento.getNome() + ", Valor: R$" + valor);
    }

    public void solicitarCredito(double valor) {
        //
    }
    
    public void transferir(Conta destino, double valor) throws SaldoInsuficienteException, ContaInexistenteException, ValorInvalidoException {
    System.out.println("Iniciando transferência: Origem=" + conta.getNumero() + ", Destino=" + destino.getNumero() + ", Valor=" + valor);

    if (destino == null) {
        System.out.println("Erro: Conta de destino não encontrada.");
        throw new ContaInexistenteException("Conta de destino não encontrada.");
    }
    if (valor <= 0) {
        System.out.println("Erro: Valor inválido para transferência.");
        throw new ValorInvalidoException("Valor inválido para transferência.");
    }
    if (conta.consultarSaldo() < valor) {
        System.out.println("Erro: Saldo insuficiente.");
        throw new SaldoInsuficienteException("Saldo insuficiente!");
    }
    if (conta.getNumero() == destino.getNumero()) {
        System.out.println("Erro: Transferência para a mesma conta.");
        throw new ValorInvalidoException("Transferência para a mesma conta não permitida.");
    }
    
    double saldoOrigemAntes = conta.consultarSaldo();
    double saldoDestinoAntes = destino.consultarSaldo();

    conta.setSaldo(conta.consultarSaldo() - valor); 
    destino.setSaldo(destino.consultarSaldo() + valor); 

    double saldoOrigemDepois = conta.consultarSaldo();
    double saldoDestinoDepois = destino.consultarSaldo();

    System.out.println("Transferência realizada com sucesso!");
    System.out.println("Saldo Origem: Antes=" + saldoOrigemAntes + ", Depois=" + saldoOrigemDepois);
    System.out.println("Saldo Destino: Antes=" + saldoDestinoAntes + ", Depois=" + saldoDestinoDepois);
    System.out.println("Fim da transferência.");
    
    }
}