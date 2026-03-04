package com.rottadev.fixapayment.repository;

import com.rottadev.fixapayment.entities.ConsumacaoEntity;
import com.rottadev.fixapayment.entities.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ConsumacaoRepository extends JpaRepository<ConsumacaoEntity, Long> {


    List<ConsumacaoEntity> findByFuncionario(FuncionarioEntity funcionario);
}
