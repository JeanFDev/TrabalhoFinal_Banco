/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.excecoes.SaldoInsuficienteException;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.usuarios.Caixa;

import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import java.util.List;

public class TelaSaque extends JFrame implements ActionListener {

    private JTextField textCliente, textValor;
    private JPasswordField textSenha;
    private JButton botaoSacar;
    private Caixa caixa;
    private List<Cliente> clientes;
    private List<Conta> contas;

    public TelaSaque(Caixa caixa, List<Cliente> clientes, List<Conta> contas) {
        this.caixa = caixa;
        this.clientes = clientes;
        this.contas = contas;
        setTitle("Saque");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Cliente (CPF):"));
        textCliente = new JTextField();
        add(textCliente);

        add(new JLabel("Valor:"));
        textValor = new JTextField();
        add(textValor);

        add(new JLabel("Senha:"));
        textSenha = new JPasswordField();
        add(textSenha);

        botaoSacar = new JButton("Sacar");
        botaoSacar.addActionListener(this);
        add(new JLabel());
        add(botaoSacar);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoSacar) {
            String cpfCliente = textCliente.getText();
            double valor = Double.parseDouble(textValor.getText());
            String senha = new String(textSenha.getPassword());

            Cliente cliente = null;
            for (Cliente c : clientes) {
                if (c.cpf.equals(cpfCliente)) {
                    cliente = c;
                    break;
                }
            }

            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
                return;
            }

            try {
                caixa.realizarSaque(cliente, valor, senha);
                dispose();
            } catch (SaldoInsuficienteException | SenhaIncorretaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}
