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
import com.mycompany.trabalhofinal_banco.excecoes.ContaInexistenteException;
import com.mycompany.trabalhofinal_banco.excecoes.SaldoInsuficienteException;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.excecoes.ValorInvalidoException;
import com.mycompany.trabalhofinal_banco.usuarios.Caixa;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import java.util.List;



public class TelaTransferenciaCaixa extends JFrame implements ActionListener {

    private JTextField textCliente, textValor, textContaDestino;
    private JPasswordField textSenha;
    private JButton botaoTransferir;
    private Caixa caixa;
    private List<Cliente> clientes;
    private List<Conta> contas;
    

    public TelaTransferenciaCaixa(Caixa caixa, List<Cliente> clientes, List<Conta> contas) {
        this.caixa = caixa;
        this.clientes = clientes;
        this.contas = contas;
        setTitle("Transferência");
        setSize(300, 250); // Aumenta a altura para acomodar os campos
        setLayout(new GridLayout(8, 1)); // Layout com 8 linhas e 1 coluna

        add(new JLabel("Cliente (CPF):"));
        textCliente = new JTextField();
        add(textCliente);

        add(new JLabel("Valor:"));
        textValor = new JTextField();
        add(textValor);

        add(new JLabel("Senha:"));
        textSenha = new JPasswordField();
        add(textSenha);

        add(new JLabel("Conta Destino (Número):"));
        textContaDestino = new JTextField();
        add(textContaDestino);

        botaoTransferir = new JButton("Transferir");
        botaoTransferir.addActionListener(this);
        add(new JLabel()); // Espaço vazio
        add(botaoTransferir);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoTransferir) {
            String cpfCliente = textCliente.getText();
            double valor = Double.parseDouble(textValor.getText());
            String senha = new String(textSenha.getPassword());
            int numeroContaDestino = Integer.parseInt(textContaDestino.getText());

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

            Conta contaDestino = null;
            for (Conta c : contas) {
                if (c.getNumero() == numeroContaDestino) {
                    contaDestino = c;
                    break;
                }
            }

            if (contaDestino == null) {
                JOptionPane.showMessageDialog(this, "Conta de destino não encontrada.");
                return;
            }

            try {
                caixa.realizarTransferencia(cliente, contaDestino, valor, senha);
                dispose();
            } catch (SaldoInsuficienteException | SenhaIncorretaException | ContaInexistenteException | ValorInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}
