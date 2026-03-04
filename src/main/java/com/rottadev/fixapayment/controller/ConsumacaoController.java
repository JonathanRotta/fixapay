package com.rottadev.fixapayment.controller;


import com.rottadev.fixapayment.entities.ConsumacaoEntity;
import com.rottadev.fixapayment.entities.FuncionarioEntity;
import com.rottadev.fixapayment.service.ConsumacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumacao")
public class ConsumacaoController {

    private final ConsumacaoService consumacaoService;

    public ConsumacaoController(ConsumacaoService consumacaoService){
        this.consumacaoService = consumacaoService;
    }

    @GetMapping
    public ResponseEntity<List<ConsumacaoEntity>> buscarConsumacoes(){
        List<ConsumacaoEntity> lista = consumacaoService.buscarConsumacoes();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{nome}")
    public ResponseEntity<List<ConsumacaoEntity>> buscarConsumacaoPorFuncionario(@PathVariable String nome){
        List<ConsumacaoEntity> lista = consumacaoService.buscarPorFuncionario(nome);

        return ResponseEntity.ok(lista);

    }

    @PostMapping
    public ResponseEntity<ConsumacaoEntity> criarConsumacao(@RequestBody ConsumacaoEntity consumacao){
        ConsumacaoEntity novaConsumacao = consumacaoService.criarConsumacao(consumacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsumacao);
    }


}
