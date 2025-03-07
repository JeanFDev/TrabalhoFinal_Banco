/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.trabalhofinal_banco;

import com.mycompany.trabalhofinal_banco.Telas.TelaLogin;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.persistencia.Persistencia;
import com.mycompany.trabalhofinal_banco.usuarios.Usuario;
import java.util.List;

public class main {

        public static void main(String[] args) {
        // Carregar dados dos arquivos CSV
        List<Conta> contas = Persistencia.carregarContas();
        List<Usuario> usuarios = Persistencia.carregarUsuarios(contas); // Passa a lista de contas

        // Exibir a tela de login
        TelaLogin telaLogin = new TelaLogin(usuarios, contas);
        telaLogin.setVisible(true);

        // Salvar dados ao final da execução
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Persistencia.salvarUsuarios(usuarios);
            Persistencia.salvarContas(contas);
        }));
    }
}
