package com.rottadev.fixapayment.controller;


import com.rottadev.fixapayment.entities.ConsumacaoEntity;
import com.rottadev.fixapayment.service.ConsumacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumacao")
public class ConsumacaoController {

    private final ConsumacaoService consumacaoService;

    public ConsumacaoController(ConsumacaoService consumacaoService){
        this.consumacaoService = consumacaoService;
    }

    @PostMapping
    public ResponseEntity<ConsumacaoEntity> criarConsumacao(@RequestBody ConsumacaoEntity consumacao){
        ConsumacaoEntity novaConsumacao = consumacaoService.criarConsumacao(consumacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConsumacao);
    }

}
