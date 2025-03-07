/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.usuarios.Usuario;

import java.util.List;

public class TelaCliente extends JFrame implements ActionListener {

    private JButton botaoTransferencia, botaoSaldo, botaoExtrato, botaoInvestimento;
    private Cliente cliente;
    private List<Usuario> usuarios;
    private List<Conta> contas;

    public TelaCliente(Cliente cliente, List<Usuario> usuarios, List<Conta> contas) {
        this.cliente = cliente;
        this.usuarios = usuarios;
        this.contas = contas;
        setTitle("Tela do Cliente");
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));

        botaoTransferencia = new JButton("Transferência");
        botaoSaldo = new JButton("Saldo");
        botaoExtrato = new JButton("Extrato");
        botaoInvestimento = new JButton("Investimentos");

        botaoTransferencia.addActionListener(this);
        botaoSaldo.addActionListener(this);
        botaoExtrato.addActionListener(this);
        botaoInvestimento.addActionListener(this);

        add(botaoTransferencia);
        add(botaoSaldo);
        add(botaoExtrato);
        add(botaoInvestimento);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoTransferencia) {
            new TelaTransferencia(cliente, contas); // Abre a tela de transferência
        } else if (e.getSource() == botaoSaldo) {
            JOptionPane.showMessageDialog(this, "Saldo: R$" + cliente.conta.consultarSaldo());
        } else if (e.getSource() == botaoExtrato) {
            // Implementar tela de extrato
        } else if (e.getSource() == botaoInvestimento) {
            TelaInvestimentos telaInvestimentos = new TelaInvestimentos(cliente);
            telaInvestimentos.setVisible(true);
        }
    }
}
