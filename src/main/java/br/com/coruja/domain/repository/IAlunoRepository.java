package br.com.coruja.domain.repository;


import br.com.coruja.domain.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAlunoRepository extends JpaRepository<Aluno, Long> {

}
