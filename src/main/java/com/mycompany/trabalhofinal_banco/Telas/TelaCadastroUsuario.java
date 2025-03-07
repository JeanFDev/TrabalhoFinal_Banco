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
import com.mycompany.trabalhofinal_banco.usuarios.Gerente;
import com.mycompany.trabalhofinal_banco.usuarios.Usuario;
import java.util.List;

public class TelaCadastroUsuario extends JFrame implements ActionListener {
    
    // Componentes da interface gráfica
    private JTextField textNome, textCPF, textSaldoInicial;
    private JPasswordField textSenha;
    private JComboBox<String> comboTipoUsuario, comboTipoConta;
    private JButton botaoCadastrar;
    
    private Gerente gerente; // Gerente responsável pelo cadastro
    private List<Usuario> usuarios; // Lista de usuários do sistema
    private List<Conta> contas; // Lista de contas do sistema

    public TelaCadastroUsuario(Gerente gerente, List<Usuario> usuarios, List<Conta> contas) {
        this.gerente = gerente;
        this.usuarios = usuarios;
        this.contas = contas;
        setTitle("Cadastro de Usuário");
        setSize(300, 300);
        setLayout(new GridLayout(7, 2)); //grid

        add(new JLabel("Nome:"));
        textNome = new JTextField();
        add(textNome);

        add(new JLabel("CPF:"));
        textCPF = new JTextField();
        add(textCPF);

        add(new JLabel("Senha:"));
        textSenha = new JPasswordField(); // Cria um campo de texto para senha
        add(textSenha);

        add(new JLabel("Saldo Inicial:"));
        textSaldoInicial = new JTextField();
        add(textSaldoInicial);

        comboTipoUsuario = new JComboBox<>(new String[]{"Cliente", "Caixa", "Gerente"});
        add(new JLabel("Tipo Usuário:"));
        add(comboTipoUsuario);

        comboTipoConta = new JComboBox<>(new String[]{"Corrente", "Poupança"});
        add(new JLabel("Tipo Conta:"));
        add(comboTipoConta);

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.addActionListener(this);
        add(new JLabel());
        add(botaoCadastrar);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Define o comportamento ao fechar a janela pra voltar a anterior
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == botaoCadastrar) {
            String nome = textNome.getText();
            String cpf = textCPF.getText();
            String senha = new String(textSenha.getPassword());
            double saldoInicial = Double.parseDouble(textSaldoInicial.getText());
            String tipoUsuario = (String) comboTipoUsuario.getSelectedItem();
            String tipoConta = (String) comboTipoConta.getSelectedItem();

            try {
                // Cadastra o usuário de acordo com o tipo selecionado
                if (tipoUsuario.equals("Cliente")) {
                    Cliente cliente = gerente.cadastrarCliente(nome, cpf, senha, saldoInicial, tipoConta, usuarios);
                    usuarios.add(cliente);
                } else if (tipoUsuario.equals("Caixa")) {
                    Caixa caixa = gerente.cadastrarCaixa(nome, cpf, senha, usuarios);
                    usuarios.add(caixa);
                } else if (tipoUsuario.equals("Gerente")) {
                    Gerente novoGerente = gerente.cadastrarGerente(nome, cpf, senha, usuarios);
                    usuarios.add(novoGerente);
                }
                JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
                dispose();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
    }
}