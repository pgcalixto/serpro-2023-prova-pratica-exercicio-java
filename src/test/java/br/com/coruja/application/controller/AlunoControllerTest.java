package br.com.coruja.application.controller;


import br.com.coruja.domain.model.Aluno;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AlunoControllerTest {
    @Autowired
    protected WebTestClient web;

    @BeforeEach
    public void setUp() {
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
    }

    @Test
    public void deve_criar_aluno() {

        Aluno aluno = new Aluno();
        aluno.setNome("José da Silva");
        aluno.setEmail("jose.silva@serpro.gov.br");

        web.post().uri("/api/alunos")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectStatus().isCreated();
    }

}
