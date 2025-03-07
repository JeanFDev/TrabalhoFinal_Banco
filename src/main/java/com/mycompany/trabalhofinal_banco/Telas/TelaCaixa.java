/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.trabalhofinal_banco.usuarios.Caixa;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import java.util.List;


public class TelaCaixa extends JFrame implements ActionListener {

    private JButton botaoSaque, botaoDeposito, botaoTransferencia;
    private Caixa caixa;
    private List<Cliente> clientes;
    private List<Conta> contas;

    public TelaCaixa(Caixa caixa, List<Cliente> clientes, List<Conta> contas) {
        this.caixa = caixa;
        this.clientes = clientes;
        this.contas = contas;
        setTitle("Tela do Caixa");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));

        botaoSaque = new JButton("Saque Cliente");
        botaoDeposito = new JButton("Depósito Cliente");
        botaoTransferencia = new JButton("Transferência de Valores");

        botaoSaque.addActionListener(this);
        botaoDeposito.addActionListener(this);
        botaoTransferencia.addActionListener(this);

        add(botaoSaque);
        add(botaoDeposito);
        add(botaoTransferencia);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoSaque) {
            new TelaSaque(caixa, clientes, contas);
        } else if (e.getSource() == botaoDeposito) {
            new TelaDeposito(caixa, clientes, contas);
        } else if (e.getSource() == botaoTransferencia) {
            new TelaTransferenciaCaixa(caixa, clientes, contas);
        }
    }
}