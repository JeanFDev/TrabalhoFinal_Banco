/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco;


import com.mycompany.trabalhofinal_banco.entidades.ContaCorrente;
import com.mycompany.trabalhofinal_banco.excecoes.ContaInexistenteException;
import com.mycompany.trabalhofinal_banco.excecoes.SaldoInsuficienteException;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.excecoes.ValorInvalidoException;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    private Cliente clienteOrigem;
    private Cliente clienteDestino;
    private ContaCorrente contaOrigem;
    private ContaCorrente contaDestino;

    @BeforeEach
    void setUp() {
        contaOrigem = new ContaCorrente(1234,1000);
        contaDestino = new ContaCorrente(5678,500);
        clienteOrigem = new Cliente("João", "12345678900", "123", contaOrigem);
        clienteDestino = new Cliente("Maria", "98765432100", "456", contaDestino);
    }

    @Test
    void realizarTransferenciaComSaldoSuficiente() throws SaldoInsuficienteException, ContaInexistenteException, SenhaIncorretaException, ValorInvalidoException {
        clienteOrigem.transferir(clienteDestino.conta, 500.0);
        assertEquals(500.0, clienteOrigem.conta.consultarSaldo());
        assertEquals(1000.0, clienteDestino.conta.consultarSaldo());
    }

    @Test
    void realizarTransferenciaComSaldoInsuficiente() {
        assertThrows(SaldoInsuficienteException.class, () -> clienteOrigem.transferir(clienteDestino.conta, 1500.0));
    }

    @Test
    void realizarTransferenciaComValorInvalido() {
        assertThrows(ValorInvalidoException.class, () -> clienteOrigem.transferir(clienteDestino.conta, -500.0));
    }

    @Test
    void realizarTransferenciaParaMesmaConta() {
        assertThrows(ValorInvalidoException.class, () -> clienteOrigem.transferir(clienteOrigem.conta, 500.0));
    }
}