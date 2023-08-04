package br.com.bank.service;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.quality.Strictness;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BacenMockTest {

    @InjectMocks
    private SistemaBancario sistemaBancario;

    @Mock
    private Bacen bacen;

    @Test
    public void testRegistraBanco() {

        final String nomeBanco = "Banco do Serpro";
        Banco banco = new Banco(nomeBanco);

        final long id = 123L;

        when(bacen.cadastrarBanco(any(Banco.class))).thenReturn(id);

        final long idBancoRegistrado = bacen.cadastrarBanco(banco);

        assertEquals(id, idBancoRegistrado);
    }

    @Test
    public void testFalhaAoRegistrarBanco() {

        final String nomeBanco = "Banco do Serpro";
        Banco banco = new Banco(nomeBanco);

        final String exceptionMessage = "Banco nao cadastrado";

        when(bacen.cadastrarBanco(any(Banco.class)))
                .thenThrow(new BancoNaoCadastradoException(exceptionMessage));

        BancoNaoCadastradoException exception = assertThrows(BancoNaoCadastradoException.class, () -> {
            bacen.cadastrarBanco(banco);
        });

        assertEquals(exceptionMessage, exception.getMessage());
    }
}
