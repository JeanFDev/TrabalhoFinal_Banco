/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.trabalhofinal_banco;


import com.mycompany.trabalhofinal_banco.entidades.ContaCorrente;
import com.mycompany.trabalhofinal_banco.excecoes.SaldoInsuficienteException;
import com.mycompany.trabalhofinal_banco.excecoes.SenhaIncorretaException;
import com.mycompany.trabalhofinal_banco.usuarios.Caixa;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CaixaTest {

    private Caixa caixa;
    private Cliente cliente;
    private ContaCorrente conta;

    @BeforeEach
    void setUp() {
        caixa = new Caixa("José", "555.666.777-88", "senha1213");
        conta = new ContaCorrente(1234,1000);
        cliente = new Cliente("Pedro", "444.555.666-77", "senha1415", conta);
    }

    @Test
    void realizarSaqueComSucesso() throws SaldoInsuficienteException, SenhaIncorretaException {
        caixa.realizarSaque(cliente, 500, "senha1415");
        assertEquals(500.0, cliente.conta.consultarSaldo());
    }

    @Test
    void realizarSaqueComSaldoInsuficiente() {
        assertThrows(SaldoInsuficienteException.class, () -> caixa.realizarSaque(cliente, 1500, "senha1415"));
    }

    @Test
    void realizarDeposito() {
        caixa.realizarDeposito(cliente, 500.0);
        assertEquals(1500.0, cliente.conta.consultarSaldo());
    }
}
