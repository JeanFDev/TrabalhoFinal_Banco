/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.entidades;
import com.mycompany.trabalhofinal_banco.interfaces.Investimento;

public class RendaFixa implements Investimento {

    private String nome;
    private double taxa;

    public RendaFixa(String nome, double taxa) {
        this.nome = nome;
        this.taxa = taxa;
    }

    public String getNome() {
        return nome;
    }

    public double getTaxa() {
        return taxa;
    }


    @Override
    public void investir(double valor) {
        System.out.println("Investimento em renda fixa " + nome + " no valor de R$" + valor);
    }

    @Override
    public void resgatar(double valor) {
        System.out.println("Resgate de renda fixa " + nome + " no valor de R$" + valor);
    }
    
    @Override
    public String toString() {
        return nome + " (" + (taxa * 100) + "%)";
    }
}
