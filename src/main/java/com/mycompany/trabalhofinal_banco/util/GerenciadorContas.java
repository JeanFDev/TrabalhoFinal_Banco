/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.util;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.trabalhofinal_banco.entidades.Conta;

public class GerenciadorContas {

    private static List<Conta> contas = new ArrayList<>();
    private static int proximoNumeroConta = 1000;

    public static int gerarNumeroContaUnico() {
        while (true) {
            boolean numeroExiste = false;
            for (Conta conta : contas) {
                if (conta.getNumero() == proximoNumeroConta) {
                    numeroExiste = true;
                    break;
                }
            }
            if (!numeroExiste) {
                return proximoNumeroConta++;
            }
            proximoNumeroConta++;
        }
    }

    public static void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public static List<Conta> getContas() {
        return contas;
    }
}
