package com.rottadev.fixapayment.controller;

import com.rottadev.fixapayment.entities.FuncionarioEntity;
import com.rottadev.fixapayment.service.FuncionarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/funcionario")
@CrossOrigin(origins = "http://localhost:5173")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService){
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public ResponseEntity<Page<FuncionarioEntity>> listar(Pageable pageable) {
        return ResponseEntity.ok(funcionarioService.buscarFuncionario(pageable));
    }

    @PostMapping
    public ResponseEntity<FuncionarioEntity> criarFuncionario(@RequestBody FuncionarioEntity funcionario){
        FuncionarioEntity funcionarioCriado = funcionarioService.criarFuncionario(funcionario);
        return ResponseEntity.status(201).body(funcionarioCriado);
    }

    @PutMapping
    public ResponseEntity<FuncionarioEntity> editarFuncionario(@RequestParam String nome,
                                                               @RequestBody FuncionarioEntity funcionario){
        FuncionarioEntity funcionarioAtualizado = funcionarioService.alterarFuncionario(nome,funcionario);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping
    public ResponseEntity deletarFuncionario(@RequestParam String nome){
        funcionarioService.deletarFuncionario(nome);
        return ResponseEntity.noContent().build();
    }


}
