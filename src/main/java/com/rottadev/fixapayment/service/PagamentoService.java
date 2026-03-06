package com.rottadev.fixapayment.service;

import com.rottadev.fixapayment.entities.ConsumacaoEntity;
import com.rottadev.fixapayment.entities.PagamentoEntity;
import com.rottadev.fixapayment.repository.ConsumacaoRepository;
import com.rottadev.fixapayment.repository.PagamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ConsumacaoService consumacaoService;
    private final ConsumacaoRepository consumacaoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository,
                            ConsumacaoService consumacaoService,
                            ConsumacaoRepository consumacaoRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.consumacaoService = consumacaoService;
        this.consumacaoRepository = consumacaoRepository;
    }

    @Transactional
    public PagamentoEntity registrarPagamento(Long idConsumacao, PagamentoEntity pagamento){
        ConsumacaoEntity consumacao = consumacaoRepository.findById(idConsumacao)
                .orElseThrow(() -> new RuntimeException("Consumação não encontrada"));

        pagamento.setFuncionario(consumacao.getFuncionario());
        PagamentoEntity pagamentoRealizado = pagamentoRepository.save(pagamento);

        consumacaoService.registrarPagamento(idConsumacao, pagamentoRealizado.getId());

        return pagamentoRealizado;
    }




}
