package com.rottadev.fixapayment.service;


import com.rottadev.fixapayment.entities.ConsumacaoEntity;
import com.rottadev.fixapayment.entities.FuncionarioEntity;
import com.rottadev.fixapayment.exception.NegocioException;
import com.rottadev.fixapayment.repository.ConsumacaoRepository;
import com.rottadev.fixapayment.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.UUID;

@Service
public class ConsumacaoService {

    private final ConsumacaoRepository consumacaoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ConsumacaoService(ConsumacaoRepository consumacaoRepository, FuncionarioRepository funcionarioRepository){
        this.consumacaoRepository = consumacaoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public ConsumacaoEntity criarConsumacao(ConsumacaoEntity consumacao){
        UUID idFuncionario = consumacao.getFuncionario().getId();
        FuncionarioEntity funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new NegocioException("Funcionário não encontrado com o ID informado."));
        consumacao.setFuncionario(funcionario);

        return consumacaoRepository.save(consumacao);

    }
}
