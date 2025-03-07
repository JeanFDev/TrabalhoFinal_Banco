/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco.persistencia;

import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.entidades.ContaCorrente;
import com.mycompany.trabalhofinal_banco.entidades.ContaPoupanca;
import com.mycompany.trabalhofinal_banco.usuarios.Caixa;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import com.mycompany.trabalhofinal_banco.usuarios.Gerente;
import com.mycompany.trabalhofinal_banco.usuarios.Usuario;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Persistencia {

    private static final String USUARIOS_CSV = "usuarios.csv";
    private static final String CONTAS_CSV = "contas.csv";
    private static Map<Integer, Conta> contasCarregadas = new HashMap<>();

    // Salvar usuários no arquivo CSV
    public static void salvarUsuarios(List<Usuario> usuarios) {
        System.out.println("Persistencia: Iniciando salvamento de usuários.");
        try (PrintWriter writer = new PrintWriter(new FileWriter(USUARIOS_CSV))) {
            writer.println("tipo,nome,cpf,senha,numeroConta"); // Cabeçalho
            for (Usuario usuario : usuarios) {
                if (usuario instanceof Cliente) {
                    Cliente cliente = (Cliente) usuario;
                    writer.println("C," + cliente.getNome() + "," + cliente.getCpf() + "," + cliente.getSenha() + "," + cliente.conta.getNumero());
                } else if (usuario instanceof Caixa) {
                    Caixa caixa = (Caixa) usuario;
                    writer.println("X," + caixa.nome + "," + caixa.cpf + "," + caixa.senha + ",");
                } else if (usuario instanceof Gerente) {
                    Gerente gerente = (Gerente) usuario;
                    writer.println("G," + gerente.nome + "," + gerente.cpf + "," + gerente.senha + ",");
                }
            }
            System.out.println("Persistencia: Salvamento de usuários concluído.");
        } catch (IOException e) {
            System.out.println("Persistencia: Erro ao salvar usuários: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Carregar usuários do arquivo CSV
    public static List<Usuario> carregarUsuarios(List<Conta> contas) {
        System.out.println("Persistencia: Iniciando carregamento de usuários.");
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USUARIOS_CSV))) {
            String linha;
            reader.readLine(); // Ignorar cabeçalho
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String tipo = dados[0];
                String nome = dados[1];
                String cpf = dados[2];
                String senha = dados[3];
                if (tipo.equals("C")) {
                    int numeroConta = Integer.parseInt(dados[4]);
                    Conta conta = null;
                    for (Conta c : contas) {
                        if (c.getNumero() == numeroConta) {
                            conta = c;
                            break;
                        }
                    }
                    if (conta != null) {
                        usuarios.add(new Cliente(nome, cpf, senha, conta));
                    }
                } else if (tipo.equals("X")) {
                    usuarios.add(new Caixa(nome, cpf, senha));
                } else if (tipo.equals("G")) {
                    usuarios.add(new Gerente(nome, cpf, senha));
                }
            }
            System.out.println("Persistencia: Carregamento de usuários concluído.");
        } catch (IOException e) {
            System.out.println("Persistencia: Erro ao carregar usuários: " + e.getMessage());
            e.printStackTrace();
        }
        return usuarios;
    }

    // Salvar contas no arquivo CSV
    public static void salvarContas(List<Conta> contas) {
        System.out.println("Persistencia: Iniciando salvamento de contas.");
        try (PrintWriter writer = new PrintWriter(new FileWriter(CONTAS_CSV))) {
            writer.println("tipo,numero,saldo"); // Cabeçalho
            for (Conta conta : contas) {
                if (conta instanceof ContaCorrente) {
                    writer.println("CC," + conta.getNumero() + "," + conta.consultarSaldo());
                } else if (conta instanceof ContaPoupanca) {
                    writer.println("CP," + conta.getNumero() + "," + conta.consultarSaldo());
                }
            }
            System.out.println("Persistencia: Salvamento de contas concluído.");
        } catch (IOException e) {
            System.out.println("Persistencia: Erro ao salvar contas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Carregar conta especifica
    public static Conta carregarConta(int numeroConta) {
        System.out.println("Persistencia: Iniciando carregamento da conta " + numeroConta);
        if (contasCarregadas.containsKey(numeroConta)) {
            System.out.println("Persistencia: Conta " + numeroConta + " já carregada.");
            return contasCarregadas.get(numeroConta);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(CONTAS_CSV))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                int numero = Integer.parseInt(dados[1]);
                if (numero == numeroConta) {
                    String tipo = dados[0];
                    double saldo = Double.parseDouble(dados[2]);
                    if (tipo.equals("CC")) {
                        Conta conta = new ContaCorrente(numero, saldo);
                        contasCarregadas.put(numeroConta, conta);
                        System.out.println("Persistencia: Conta " + numeroConta + " carregada.");
                        return conta;
                    } else if (tipo.equals("CP")) {
                        Conta conta = new ContaPoupanca(numero, saldo);
                        contasCarregadas.put(numeroConta, conta);
                        System.out.println("Persistencia: Conta " + numeroConta + " carregada.");
                        return conta;
                    }
                }
            }
            System.out.println("Persistencia: Conta " + numeroConta + " não encontrada.");
        } catch (IOException e) {
            System.out.println("Persistencia: Erro ao carregar conta " + numeroConta + ": " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //Carregar todas as contas
    public static List<Conta> carregarContas() {
        System.out.println("Persistencia: Iniciando carregamento de todas as contas.");
        List<Conta> contas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONTAS_CSV))) {
            String linha;
            reader.readLine();
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                String tipo = dados[0];
                int numero = Integer.parseInt(dados[1]);
                double saldo = Double.parseDouble(dados[2]);
                if (tipo.equals("CC")) {
                    Conta conta = carregarConta(numero);
                    contas.add(conta);
                } else if (tipo.equals("CP")) {
                    Conta conta = carregarConta(numero);
                    contas.add(conta);
                }
            }
            System.out.println("Persistencia: Carregamento de todas as contas concluído.");
        } catch (IOException e) {
            System.out.println("Persistencia: Erro ao carregar todas as contas: " + e.getMessage());
            e.printStackTrace();
        }
        return contas;
    }
}
