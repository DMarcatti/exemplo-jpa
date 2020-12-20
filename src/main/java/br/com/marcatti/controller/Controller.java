package br.com.marcatti.controller;

import br.com.marcatti.dto.PessoaDto;
import br.com.marcatti.service.impl.ApiServiceImpl;
import br.com.marcatti.vo.PessoaVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/v1")
public class Controller {

    @Autowired
    ApiServiceImpl service;

    @PostMapping()
    @ApiOperation(value = "Serviço para incluir novos registros",
            notes = "Servico exemplo para incluir novos registros")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody PessoaDto pessoaDto){
        log.debug("Controller::v1:: dto {}" , pessoaDto);
        service.save(pessoaDto);
    }

    @GetMapping
    @ApiOperation(value = "Serviço para listar todos os registros",
            response = PessoaVo.class,
            responseContainer = "List",
            notes = "Servico exemplo para listar todos os registros")
    public ResponseEntity<Page<PessoaVo>> all(@PageableDefault(sort = "uuid", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        log.debug("Controller::v1::find");
        Page<PessoaVo> lst = service.findAll(paginacao);
        return new ResponseEntity<>(lst, HttpStatus.OK);
    }

    @GetMapping("/{uuid}")
    @ApiOperation(value = "Serviço de buscar por uuid",
            response = PessoaVo.class,
            notes = "Servico exemplo para buscar registros por uuid")
    public ResponseEntity<PessoaVo> findById(
            @ApiParam(value = "uuid do registro para busca", required = true , example = "3fa85f64-5717-4562-b3fc-2c963f66afa")
            @PathVariable("uuid") UUID uuid){
        log.debug("Controller::findById::v1 uuid {}" , uuid );
        PessoaVo m = service.findByUuid(uuid);
        if (m == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation(value = "Serviço de remover por uuid",
            notes = "Servico exemplo para remover registros por uuid")
    public ResponseEntity<PessoaDto> deleteById(
            @ApiParam(value = "uuid excluir", required = true , example = "3fa85f64-5717-4562-b3fc-2c963f66afa")
            @PathVariable("uuid") UUID uuid){
        log.debug("Controller::deleteById::v1 uuid {}" , uuid );
        service.deleteById(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void put(
            @ApiParam(value = "Id para update", required = true , example = "3fa85f64-5717-4562-b3fc-2c963f66afa")
            @PathVariable("uuid") UUID uuid, @RequestBody PessoaDto pessoaDto){
        log.debug("Controller::put::v1 pathvariable {} objeto {}", uuid , pessoaDto);
        service.update(uuid, pessoaDto);
    }

}
