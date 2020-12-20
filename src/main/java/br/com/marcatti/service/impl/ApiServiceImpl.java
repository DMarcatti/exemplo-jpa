package br.com.marcatti.service.impl;

import br.com.marcatti.dto.PessoaDto;
import br.com.marcatti.entity.Pessoa;
import br.com.marcatti.repository.PessoaRepository;
import br.com.marcatti.service.ApiService;
import br.com.marcatti.vo.PessoaVo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@Transactional
public class ApiServiceImpl implements ApiService {

    @Autowired
    PessoaRepository pessoaRepository;


    private Pessoa builder(UUID uuid, PessoaDto pessoaDto){
        return  Pessoa.builder()
                                .uuid(uuid)
                                .nome(pessoaDto.getNome())
                                .email(pessoaDto.getEmail())
                                .nascimento(pessoaDto.getNascimento())
                                .tipoDocumento(pessoaDto.getTipoDocumento())
                                .documento(pessoaDto.getDocumento())
                      .build();
    }

    public void save(PessoaDto pessoaDto){
      UUID uuid = UUID.randomUUID();
      this.save(uuid, builder(uuid, pessoaDto));
    }
    public void save(UUID uuid, Pessoa p){
        log.debug("post UUID {} Pessoa {}", uuid , p);
        this.pessoaRepository.save(p);
    }
    public Page<PessoaVo> findAll(Pageable paginacao) {
        return this.pessoaRepository.findAll(paginacao).map( p -> {
            return PessoaVo.builder()
                   .uuid(p.getUuid())
                   .nome(p.getNome())
                   .email(p.getEmail())
                   .nascimento(p.getNascimento())
                   .tipoDocumento(p.getTipoDocumento())
                   .documento(p.getDocumento())
                   .build();
        });
    }
    public PessoaVo findByUuid(UUID uuid) {
        return new ModelMapper().map(
                this.pessoaRepository.findByUuid(uuid),
                PessoaVo.class);
    }
    public void deleteById(UUID uuid) {
        this.pessoaRepository.deleteByUuid(uuid);
    }
    public void update(UUID uuid, PessoaDto pessoaDto) {
        PessoaVo pessoaVo = this.findByUuid(uuid);
        if (pessoaVo == null){
            log.info("update::Pessoa nao encontrada para UUID {}" , uuid );
        }else{
            this.save(uuid, builder(uuid, pessoaDto));
        }
    }
}
