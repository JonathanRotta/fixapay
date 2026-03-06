package com.rottadev.fixapayment.controller;


import com.rottadev.fixapayment.entities.PagamentoEntity;
import com.rottadev.fixapayment.service.PagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService){
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoEntity> registrarPagamento(@RequestParam Long idConsumacao,
                                                              @RequestBody PagamentoEntity pagamento){
        PagamentoEntity pagamentoRealizado = pagamentoService.registrarPagamento(idConsumacao, pagamento);
        return ResponseEntity.ok(pagamentoRealizado);
    }
}
