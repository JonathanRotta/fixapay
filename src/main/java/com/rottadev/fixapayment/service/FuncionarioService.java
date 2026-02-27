package com.rottadev.fixapayment.service;

import com.rottadev.fixapayment.entities.FuncionarioEntity;
import com.rottadev.fixapayment.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository){
        this.funcionarioRepository = funcionarioRepository;
    }


    public FuncionarioEntity criarUsuario(FuncionarioEntity funcionario){
        return funcionarioRepository.save(funcionario);
    }
}
