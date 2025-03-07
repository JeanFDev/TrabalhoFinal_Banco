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
import com.mycompany.trabalhofinal_banco.usuarios.Caixa;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import java.util.List;

public class TelaDeposito extends JFrame implements ActionListener {

    private JTextField textCliente, textValor;
    private JButton botaoDepositar;
    private Caixa caixa;
    private List<Cliente> clientes;
    private List<Conta> contas;

    public TelaDeposito(Caixa caixa, List<Cliente> clientes, List<Conta> contas) {
        this.caixa = caixa;
        this.clientes = clientes;
        this.contas = contas;
        setTitle("Depósito");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Cliente (CPF):"));
        textCliente = new JTextField();
        add(textCliente);

        add(new JLabel("Valor:"));
        textValor = new JTextField();
        add(textValor);

        botaoDepositar = new JButton("Depositar");
        botaoDepositar.addActionListener(this);
        add(new JLabel());
        add(botaoDepositar);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoDepositar) {
            String cpfCliente = textCliente.getText();
            double valor = Double.parseDouble(textValor.getText());

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

            caixa.realizarDeposito(cliente, valor);
            dispose();
        }
    }
}
