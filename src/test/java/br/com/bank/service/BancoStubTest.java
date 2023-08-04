package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BacenStubTest {

    @Test
    public void testRegistraBanco() {
        final Banco banco = new Banco("Banco do Serpro");

        final Bacen bacen = new BacenStub();
        final SistemaBancario sistemaBancario = new SistemaBancario(bacen);

        final long id = sistemaBancario.registrarBanco(banco);

        assertEquals(0L, id);
    }
}
