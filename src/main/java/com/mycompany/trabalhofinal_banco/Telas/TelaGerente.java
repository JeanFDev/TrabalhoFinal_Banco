/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.trabalhofinal_banco.usuarios.Gerente;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.usuarios.Usuario;
import java.util.List;

public class TelaGerente extends JFrame implements ActionListener {

    private JButton botaoRendaFixa, botaoRendaVariavel, botaoCredito, botaoUsuarios;
    private Gerente gerente;
    private List<Usuario> usuarios;
    private List<Conta> contas;

    public TelaGerente(Gerente gerente, List<Usuario> usuarios, List<Conta> contas) {
        this.gerente = gerente;
        this.usuarios = usuarios;
        this.contas = contas;
        setTitle("Tela do Gerente");
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));

        botaoRendaFixa = new JButton("Renda Fixa");
        botaoRendaVariavel = new JButton("Renda Variável");
        botaoCredito = new JButton("Crédito");
        botaoUsuarios = new JButton("Usuários");

        botaoRendaFixa.addActionListener(this);
        botaoRendaVariavel.addActionListener(this);
        botaoCredito.addActionListener(this);
        botaoUsuarios.addActionListener(this);

        add(botaoRendaFixa);
        add(botaoRendaVariavel);
        add(botaoCredito);
        add(botaoUsuarios);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoRendaFixa) {
            // Implementar tela de renda fixa
        } else if (e.getSource() == botaoRendaVariavel) {
            // Implementar tela de renda variável
        } else if (e.getSource() == botaoCredito) {
            // Implementar tela de crédito
        } else if (e.getSource() == botaoUsuarios) {
            new TelaCadastroUsuario(gerente, usuarios, contas).setVisible(true);
        }
    }
}
