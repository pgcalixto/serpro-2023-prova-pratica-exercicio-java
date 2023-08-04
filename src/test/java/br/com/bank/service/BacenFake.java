package br.com.bank.service;


import java.util.HashMap;
import java.util.Map;

import br.com.bank.gateway.Bacen;
import br.com.bank.model.Banco;

public class BacenFake extends Bacen {

    private long id = 0L;
    private Map<String, Banco> bancos = new HashMap<>();

    @Override
    public long cadastrarBanco(Banco banco) {

        final String nome = banco.getNome();

        if (bancos.containsKey(nome)) {
            throw new RuntimeException("Banco com o nome desejado já existe");
        }

        bancos.put(nome, banco);

        return id++;
    }
}
