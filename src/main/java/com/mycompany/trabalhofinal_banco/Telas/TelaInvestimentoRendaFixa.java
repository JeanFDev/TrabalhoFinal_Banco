/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.Telas;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import com.mycompany.trabalhofinal_banco.entidades.RendaFixa;
import com.mycompany.trabalhofinal_banco.persistencia.Persistencia;
import javax.swing.JFrame;


public class TelaInvestimentoRendaFixa extends JFrame implements ActionListener {

    private JComboBox<RendaFixa> comboRendaFixa;
    private JTextField textValor;
    private JPasswordField textSenha;
    private JButton botaoInvestir;
    private Cliente cliente;

    public TelaInvestimentoRendaFixa(Cliente cliente) {
        this.cliente = cliente;
        setTitle("Investimento Renda Fixa");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        comboRendaFixa = new JComboBox<>();
        // Adicionar opções de renda fixa (simulação) ---- depois remover
        comboRendaFixa.addItem(new RendaFixa("CDB", 0.10));
        comboRendaFixa.addItem(new RendaFixa("Tesouro Direto", 0.08));
        add(comboRendaFixa);

        add(new JLabel("Valor:"));
        textValor = new JTextField();
        add(textValor);

        add(new JLabel("Senha:"));
        textSenha = new JPasswordField();
        add(textSenha);

        botaoInvestir = new JButton("Investir");
        botaoInvestir.addActionListener(this);
        add(new JLabel()); // Espaço vazio
        add(botaoInvestir);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoInvestir) {
            RendaFixa rendaFixa = (RendaFixa) comboRendaFixa.getSelectedItem();
            double valor = Double.parseDouble(textValor.getText());
            String senha = new String(textSenha.getPassword());

            try {
                cliente.realizarLogin(senha);
                cliente.investirRendaFixa(rendaFixa, valor);
                JOptionPane.showMessageDialog(this, "Investimento realizado com sucesso!");
                dispose();
            } catch (SenhaIncorretaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}