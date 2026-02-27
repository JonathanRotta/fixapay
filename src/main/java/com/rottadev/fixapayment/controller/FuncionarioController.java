package com.rottadev.fixapayment.controller;

import com.rottadev.fixapayment.entities.FuncionarioEntity;
import com.rottadev.fixapayment.service.FuncionarioService;
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

    @PostMapping
    public ResponseEntity<FuncionarioEntity> criarFuncionario(@RequestBody FuncionarioEntity funcionario){
        FuncionarioEntity funcionarioCriado = funcionarioService.criarUsuario(funcionario);
        funcionarioService.criarUsuario(funcionario);
        return ResponseEntity.status(201).body(funcionarioCriado);
    }

}
