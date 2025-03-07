/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.entidades;
import com.mycompany.trabalhofinal_banco.interfaces.Investimento;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;


public class RendaVariavel implements Investimento {

    private String nome;
    private double precoAcao; // Preço por ação
    private int quantidade; // Quantidade de ações compradas
    private Cliente cliente;

    public RendaVariavel(String nome, double precoAcao, Cliente cliente) {
        this.nome = nome;
        this.precoAcao = precoAcao;
        this.cliente = cliente;
    }

    public String getNome() {
        return nome;
    }

    public double getPrecoAcao() {
        return precoAcao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoAcao(double precoAcao) {
        this.precoAcao = precoAcao;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    @Override
    public void investir(double quantidade) { // Modificado para receber quantidade
        int qtd = (int) quantidade;
        double valorTotal = qtd * precoAcao; // Calcula o valor total
        cliente.investir(valorTotal); // Chama o método investir do cliente com o valor total
        this.quantidade += qtd; // Atualiza a quantidade de ações
        System.out.println("Compra de " + qtd + " ações de " + nome + " realizada com sucesso. Valor total: R$" + valorTotal);
        // Adicione aqui a lógica específica para a compra de ações
    }

    @Override
    public void resgatar(double quantidade) {
        // Implementação do resgate de ações
        int qtd = (int) quantidade;
        if(qtd > this.quantidade){
            System.out.println("Quantidade de ações insuficiente para resgate.");
            return;
        }
        double valorTotal = qtd * precoAcao;
        cliente.resgatar(valorTotal);
        this.quantidade -= qtd;
        System.out.println("Resgate de " + qtd + " ações de " + nome + " realizado com sucesso. Valor total: R$" + valorTotal);
        // Adicione aqui a lógica específica para o resgate de ações
    }
    
    @Override
    public String toString() {
        return nome + " (R$" + precoAcao + ")"; // Formato correto
    }
}
    
    

