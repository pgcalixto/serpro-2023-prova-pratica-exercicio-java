package br.com.bank.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class BancoTest {

    @Test
    public void testContaAdicionadaEhRetornadaNaPesquisa() {
        final String cpf = "123.456.789-0";
        final String nomeBanco = "Banco do Serpro";

        final Banco banco = new Banco(nomeBanco);
        final Conta conta = new Conta(cpf);

        banco.adicionarConta(conta);

        final Conta contaPesquisada = banco.pesquisarContaDoCliente(cpf);

        assertEquals(cpf, contaPesquisada.getCpf());
    }

    @Test
    public void testPesquisaDeContaInexistenteRetornaNada() {
        final String cpfInexistente = "987.654.321-0";
        final String nomeBanco = "Banco do Serpro";

        final Banco banco = new Banco(nomeBanco);

        Conta contaPesquisada = banco.pesquisarContaDoCliente(cpfInexistente);

        assertNull(contaPesquisada);
    }

    @Test
    public void testListarContasAltaRenda() {
        final String nomeBanco = "Banco do Serpro";
        final Banco banco = new Banco(nomeBanco);

        final double saldoBaixaRenda = 9999;
        final double saldoAltaRenda = 10000;

        final String cpfContaBaixaRenda1 = "111.111.111-0";
        final String cpfContaBaixaRenda2 = "222.222.222-0";
        final String cpfContaAltaRenda1 = "333.333.333-0";
        final String cpfContaAltaRenda2 = "444.444.444-0";
        final String cpfContaAltaRenda3 = "555.555.555-0";

        final Conta contaBaixaRenda1 = new Conta(cpfContaBaixaRenda1, saldoBaixaRenda);
        final Conta contaBaixaRenda2 = new Conta(cpfContaBaixaRenda2, saldoBaixaRenda);
        final Conta contaAltaRenda1 = new Conta(cpfContaAltaRenda1, saldoAltaRenda);
        final Conta contaAltaRenda2 = new Conta(cpfContaAltaRenda2, saldoAltaRenda);
        final Conta contaAltaRenda3 = new Conta(cpfContaAltaRenda3, saldoAltaRenda);

        banco.adicionarConta(contaBaixaRenda1);
        banco.adicionarConta(contaBaixaRenda2);
        banco.adicionarConta(contaAltaRenda1);
        banco.adicionarConta(contaAltaRenda2);
        banco.adicionarConta(contaAltaRenda3);

        final List<Conta> contasAltaRenda = banco.listarContasAltaRenda();

        final List<Conta> contasAltaRendaDesejada =
                List.of(contaAltaRenda1, contaAltaRenda2, contaAltaRenda3);

        assertEquals(contasAltaRendaDesejada, contasAltaRenda);
    }
}
