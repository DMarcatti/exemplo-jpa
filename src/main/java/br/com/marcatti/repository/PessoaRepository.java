package br.com.marcatti.repository;

import br.com.marcatti.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa , UUID>{
    Pessoa findByUuid(UUID uuid);
    void deleteByUuid(UUID uuid);
}

