package br.com.coruja.application.controller;


import br.com.coruja.domain.model.Aluno;
import br.com.coruja.domain.repository.IAlunoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AlunoController.class)
class AlunoControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAlunoRepository alunoRepository;

    @Test
    public void test() throws Exception {

        Aluno aluno = new Aluno();
        aluno.setNome("José da Silva");
        aluno.setEmail("jose.silva@serpro.gov.br");

        String alunoJson = new ObjectMapper().writeValueAsString(aluno);

        mockMvc
                .perform(post("/api/alunos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(alunoJson)
                )
                .andExpect(status().isCreated());
    }
}
