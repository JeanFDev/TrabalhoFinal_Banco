/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// JEAN FELIPE MARTINHO - 201835022
package com.mycompany.trabalhofinal_banco;


import com.mycompany.trabalhofinal_banco.entidades.Conta;
import com.mycompany.trabalhofinal_banco.usuarios.Cliente;
import com.mycompany.trabalhofinal_banco.usuarios.Gerente;
import com.mycompany.trabalhofinal_banco.usuarios.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GerenteTest {

    private Gerente gerente;
    private List<Usuario> usuarios;
    private List<Conta> contas;

    @BeforeEach
    void setUp() {
        usuarios = new ArrayList<>();
        contas = new ArrayList<>();
        gerente = new Gerente("Maria", "12323300000", "senha456");
        usuarios.add(gerente);
    }

   @Test
    void cadastrarClienteComSucesso() {
        Cliente cliente = gerente.cadastrarCliente("Carlos", "111.222.333-44", "senha789", 1000.0, "Corrente", usuarios);
        assertNotNull(cliente);
        assertEquals("Carlos", cliente.getNome());
        assertEquals(1000.0, cliente.conta.consultarSaldo());
    }
}