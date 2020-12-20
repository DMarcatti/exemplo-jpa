package br.com.marcatti.service;

import br.com.marcatti.dto.PessoaDto;
import br.com.marcatti.entity.Pessoa;
import br.com.marcatti.vo.PessoaVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.UUID;

public interface ApiService {

    void deleteById(UUID id);
    void update(UUID id, PessoaDto pessoaDto);
    void save(PessoaDto pessoaDto);
    void save(UUID uuid, Pessoa pessoa);
    Page<PessoaVo> findAll(Pageable paginacao);
    PessoaVo findByUuid(UUID id);

}
