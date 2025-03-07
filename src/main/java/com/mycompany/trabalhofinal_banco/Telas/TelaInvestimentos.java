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

public class TelaInvestimentos extends JFrame implements ActionListener {

    private JButton botaoRendaFixa, botaoRendaVariavel;
    private Cliente cliente;

    public TelaInvestimentos(Cliente cliente) {
        this.cliente = cliente;
        setTitle("Tela de Investimentos");
        setSize(300, 150);
        setLayout(new GridLayout(2, 1));

        botaoRendaFixa = new JButton("Renda Fixa");
        botaoRendaVariavel = new JButton("Renda Variável");

        botaoRendaFixa.addActionListener(this);
        botaoRendaVariavel.addActionListener(this);

        add(botaoRendaFixa);
        add(botaoRendaVariavel);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoRendaFixa) {
            new TelaInvestimentoRendaFixa(cliente);
        } else if (e.getSource() == botaoRendaVariavel) {
            new TelaInvestimentoRendaVariavel(cliente);
        }
    }
}
