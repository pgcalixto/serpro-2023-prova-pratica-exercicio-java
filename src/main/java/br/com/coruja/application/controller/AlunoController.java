package br.com.coruja.application.controller;


import br.com.coruja.application.exceptions.ResourceNotFoundException;
import br.com.coruja.domain.model.Aluno;
import br.com.coruja.domain.repository.IAlunoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private IAlunoRepository alunoRepository;

    @GetMapping
    public ResponseEntity<List<Aluno>> list() {
        return new ResponseEntity<>(alunoRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> find(@PathVariable long id) {

        Aluno aluno = alunoRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        return new ResponseEntity<Aluno>(aluno, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno novoAluno) {

        Aluno alunoCriado = alunoRepository.save(novoAluno);

        return new ResponseEntity<Aluno>(alunoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable long id, @RequestBody Aluno dadosAtualizacaoAluno) {

        return alunoRepository
                .findById(id)
                .map(aluno -> {
                    aluno.setNome(dadosAtualizacaoAluno.getNome());
                    aluno.setEmail(dadosAtualizacaoAluno.getEmail());

                    Aluno alunoAtualizado = alunoRepository.save(aluno);

                    return new ResponseEntity<Aluno>(alunoAtualizado, HttpStatus.OK);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {

        alunoRepository.deleteById(id);
    }
}
