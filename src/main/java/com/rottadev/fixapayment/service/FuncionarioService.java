package com.rottadev.fixapayment.service;

import com.rottadev.fixapayment.entities.FuncionarioEntity;
import com.rottadev.fixapayment.exception.NegocioException;
import com.rottadev.fixapayment.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }

    public Page<FuncionarioEntity> buscarFuncionario(Pageable pageable){
        return funcionarioRepository.findAll(pageable);
    }

    public FuncionarioEntity criarFuncionario(FuncionarioEntity funcionario){

        if(funcionario.getNome() == null ||
           funcionario.getFuncao() == null ||
           funcionario.getTelefone() == null ||
           funcionario.getValorDiaria() == null){

            throw new NegocioException("Está faltando campos obrigatórios");
        }

        if(funcionarioRepository.existsByNomeIgnoreCase(funcionario.getNome())){
            throw new NegocioException("Já existe um funcionário cadastrado com esse nome!");
        }

        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public FuncionarioEntity alterarFuncionario(String nome, FuncionarioEntity funcionario){
        FuncionarioEntity funcionarioExistente = funcionarioRepository.findByNome(nome)
                        .orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));

        funcionarioExistente.setNome(funcionario.getNome());
        funcionarioExistente.setFuncao(funcionario.getFuncao());
        funcionarioExistente.setTelefone(funcionario.getTelefone());
        funcionarioExistente.setValorDiaria(funcionario.getValorDiaria());

        return funcionarioRepository.save(funcionarioExistente);

    }

    public FuncionarioEntity deletarFuncionario(String nome){

        if(funcionarioRepository.existsByNomeIgnoreCase(nome)){
            return funcionarioRepository.deleteByNome(nome);
        }

        throw new NegocioException("Esse funcionário não existe");

    }
}
