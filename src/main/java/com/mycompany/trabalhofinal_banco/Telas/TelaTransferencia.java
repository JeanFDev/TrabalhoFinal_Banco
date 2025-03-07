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
import com.mycompany.trabalhofinal_banco.persistencia.Persistencia;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import java.util.List;

public class TelaTransferencia extends JFrame implements ActionListener {

    private JTextField textContaDestino, textValor;
    private JPasswordField textSenha; 
    private JButton botaoTransferir;
    private Cliente cliente;
    private List<Conta> contas;

    public TelaTransferencia(Cliente cliente, List<Conta> contas) {
        this.cliente = cliente;
        this.contas = contas;
        setTitle("Transferência");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Conta Destino:"));
        textContaDestino = new JTextField();
        add(textContaDestino);

        add(new JLabel("Valor:"));
        textValor = new JTextField();
        add(textValor);

        add(new JLabel("Senha:"));
        textSenha = new JPasswordField();
        add(textSenha);

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
            int contaDestinoNumero = Integer.parseInt(textContaDestino.getText());
            double valor = Double.parseDouble(textValor.getText());
            String senha = new String(textSenha.getPassword());

            try {
                cliente.realizarLogin(senha);
                Conta contaDestino = null;
                for (Conta conta : contas) {
                    if (conta.getNumero() == contaDestinoNumero) {
                        contaDestino = conta;
                        break;
                    }
                }
                if (contaDestino == null) {
                    throw new ContaInexistenteException("Conta de destino não encontrada.");
                }
                cliente.transferir(contaDestino, valor);
                JOptionPane.showMessageDialog(this, "Transferência realizada com sucesso!");
                dispose();
                Persistencia.salvarContas(contas);
            } catch (SenhaIncorretaException | SaldoInsuficienteException | ContaInexistenteException | ValorInvalidoException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}
