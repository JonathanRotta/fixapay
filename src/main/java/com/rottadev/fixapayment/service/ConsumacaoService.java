package com.rottadev.fixapayment.service;

import com.rottadev.fixapayment.entities.ConsumacaoEntity;
import com.rottadev.fixapayment.entities.FuncionarioEntity;
import com.rottadev.fixapayment.exception.NegocioException;
import com.rottadev.fixapayment.repository.ConsumacaoRepository;
import com.rottadev.fixapayment.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class ConsumacaoService {

    private final ConsumacaoRepository consumacaoRepository;
    private final FuncionarioRepository funcionarioRepository;

    public ConsumacaoService(ConsumacaoRepository consumacaoRepository, FuncionarioRepository funcionarioRepository){
        this.consumacaoRepository = consumacaoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<ConsumacaoEntity> buscarConsumacoes(){
        return consumacaoRepository.findAll();
    }

    public List<ConsumacaoEntity> buscarPorFuncionario(String nome){
        FuncionarioEntity funcionario = funcionarioRepository.findByNome(nome)
                .orElseThrow(() -> new NegocioException("Não existe funcionário com este nome")) ;
        List<ConsumacaoEntity> listaConsumacao = consumacaoRepository.findByFuncionario(funcionario);


        return listaConsumacao;
    }

    @Transactional
    public ConsumacaoEntity criarConsumacao(ConsumacaoEntity consumacao){
        UUID idFuncionario = consumacao.getFuncionario().getId();
        FuncionarioEntity funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new NegocioException("Funcionário não encontrado com o ID informado."));
        consumacao.setFuncionario(funcionario);

        return consumacaoRepository.save(consumacao);

    }

    public ConsumacaoEntity alterarStatus(Long id, String status){
        ConsumacaoEntity consumacao = consumacaoRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Essa consumação não existe"));

        if ("pago".equalsIgnoreCase(status)) {
            throw new NegocioException("Essa consumação ja foi paga!");
        }
        consumacao.setStatus(status);

        return consumacaoRepository.save(consumacao);
    }
}
