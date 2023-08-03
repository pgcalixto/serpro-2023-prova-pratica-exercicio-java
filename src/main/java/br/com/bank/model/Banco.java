package br.com.bank.model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Banco {

    private String nome;

    public Banco(String nome) {
        this.nome = nome;
    }

    private Map<String, Conta> contas = new HashMap<>();

    public void adicionarConta(Conta conta) {
        final String cpf = conta.getCpf();

        if (contas.containsKey(cpf)) {
            throw new RuntimeException("Conta para o CPF desejado já existe");
        }

        contas.put(cpf, conta);
    }

    public Conta pesquisarContaDoCliente(String cpf) {
        if (!contas.containsKey(cpf)) {
            return null;
        }

        return contas.get(cpf);
    }

    public List<Conta> listarContasAltaRenda() {
        return filtrarContas(c -> c.getSaldo() >= 10000);
    }

    private List<Conta> filtrarContas(Predicate<Conta> filtro) {
        return contas.values()
                .stream()
                .filter(filtro)
                .collect(Collectors.toList());
    }
}
