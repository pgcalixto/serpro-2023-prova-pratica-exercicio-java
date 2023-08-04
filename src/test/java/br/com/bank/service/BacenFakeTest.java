package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BacenFakeTest {

    @Test
    public void testRegistraBanco() {
        final Banco banco = new Banco("Banco do Serpro");

        final Bacen bacen = new BacenFake();
        final SistemaBancario sistemaBancario = new SistemaBancario(bacen);

        final long id = sistemaBancario.registrarBanco(banco);

        assertEquals(0L, id);
    }

    @Test
    public void testRegistraMultiplosBancos() {
        final Banco banco1 = new Banco("Banco do Serpro 1");
        final Banco banco2 = new Banco("Banco do Serpro 2");
        final Banco banco3 = new Banco("Banco do Serpro 3");

        final Bacen bacen = new BacenFake();
        final SistemaBancario sistemaBancario = new SistemaBancario(bacen);

        final long id1 = sistemaBancario.registrarBanco(banco1);
        final long id2 = sistemaBancario.registrarBanco(banco2);
        final long id3 = sistemaBancario.registrarBanco(banco3);

        assertEquals(0L, id1);
        assertEquals(1L, id2);
        assertEquals(2L, id3);
    }

    @Test
    public void testFalhaAoRegistrarBancoJaExistente() {
        final String nomeBanco = "Banco do Serpro";

        final Banco banco1 = new Banco(nomeBanco);
        final Banco banco2 = new Banco(nomeBanco);

        final Bacen bacen = new BacenFake();
        final SistemaBancario sistemaBancario = new SistemaBancario(bacen);

        final long id1 = sistemaBancario.registrarBanco(banco1);
        assertEquals(0L, id1);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            sistemaBancario.registrarBanco(banco2);
        });

        assertEquals("Banco com o nome desejado já existe", exception.getMessage());
    }
}
