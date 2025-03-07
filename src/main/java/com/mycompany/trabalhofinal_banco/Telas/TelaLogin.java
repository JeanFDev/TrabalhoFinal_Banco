/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.trabalhofinal_banco.usuarios.Usuario;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import com.mycompany.trabalhofinal_banco.usuarios.Caixa;
import com.mycompany.trabalhofinal_banco.usuarios.Gerente;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.entidades.Conta;
import java.util.List;
import java.util.ArrayList;


public class TelaLogin extends JFrame implements ActionListener {

    private JTextField textCPF;
    private JPasswordField textSenha;
    private JButton botaoLogin;
    private List<Usuario> usuarios;
    private List<Conta> contas;
    private Usuario usuario;

    public TelaLogin(List<Usuario> usuarios, List<Conta> contas) {
        this.usuarios = usuarios;
        this.contas = contas;
        setTitle("Login");
        setSize(300, 150);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("CPF:"));
        textCPF = new JTextField();
        add(textCPF);

        add(new JLabel("Senha:"));
        textSenha = new JPasswordField();
        add(textSenha);

        botaoLogin = new JButton("Login");
        botaoLogin.addActionListener(this);
        add(new JLabel()); // Espaço vazio
        add(botaoLogin);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoLogin) {
            String cpf = textCPF.getText();
            String senha = new String(textSenha.getPassword());

            try {
                usuario = null;
                for (Usuario u : usuarios) {
                    if (u.cpf.equals(cpf) && u.senha.equals(senha)) {
                        usuario = u;
                        break;
                    }
                }

                if (usuario == null) {
                    throw new SenhaIncorretaException("Usuário ou senha inválidos.");
                } else {
                    if (usuario instanceof Caixa) {
                        // Criar lista de clientes
                        List<Cliente> clientes = new ArrayList<>();
                        for (Usuario u : usuarios) {
                            if (u instanceof Cliente) {
                                clientes.add((Cliente) u);
                            }
                        }
                        new TelaCaixa((Caixa) usuario, clientes, contas);
                    } else if (usuario instanceof Cliente) {
                        new TelaCliente((Cliente) usuario, usuarios, contas);
                    } else if (usuario instanceof Gerente) {
                        new TelaGerente((Gerente) usuario, usuarios, contas);
                    }
                    dispose();
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro durante o login: " + ex.getMessage());
                ex.printStackTrace(); // Imprime o rastreamento da pilha para depuração
            }
        }
    }
}