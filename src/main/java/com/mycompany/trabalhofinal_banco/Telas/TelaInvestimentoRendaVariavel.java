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
import com.mycompany.trabalhofinal_banco.entidades.RendaVariavel;
import com.mycompany.trabalhofinal_banco.persistencia.Persistencia;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;


public class TelaInvestimentoRendaVariavel extends JFrame implements ActionListener {

    private JComboBox<RendaVariavel> comboRendaVariavel;
    private JTextField textQuantidade;
    private JPasswordField textSenha;
    private JButton botaoInvestir;
    private Cliente cliente;
    

    public TelaInvestimentoRendaVariavel(Cliente cliente) {
        this.cliente = cliente;
        setTitle("Compra de Ações");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        comboRendaVariavel = new JComboBox<>();
        // Adicionar opções de ações (simulação)
        comboRendaVariavel.addItem(new RendaVariavel("Ação A", 10.0, cliente));
        comboRendaVariavel.addItem(new RendaVariavel("Ação B", 20.0, cliente));
        add(comboRendaVariavel);

        add(new JLabel("Quantidade:")); // Modificado para quantidade
        textQuantidade = new JTextField();
        add(textQuantidade);

        add(new JLabel("Senha:"));
        textSenha = new JPasswordField();
        add(textSenha);

        botaoInvestir = new JButton("Comprar"); // Modificado para Comprar
        botaoInvestir.addActionListener(this);
        add(new JLabel());
        add(botaoInvestir);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoInvestir) {
            RendaVariavel acao = (RendaVariavel) comboRendaVariavel.getSelectedItem();
            double quantidade = Double.parseDouble(textQuantidade.getText()); // Obtém a quantidade
            String senha = new String(textSenha.getPassword());

            try {
                cliente.realizarLogin(senha);
                acao.investir(quantidade); // Chama o método investir da ação com a quantidade
                JOptionPane.showMessageDialog(this, "Compra de ações realizada com sucesso!");
                dispose();
            } catch (SenhaIncorretaException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}