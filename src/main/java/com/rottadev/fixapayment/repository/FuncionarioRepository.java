package com.rottadev.fixapayment.repository;

import com.rottadev.fixapayment.entities.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, UUID> {

    Optional<FuncionarioEntity> findByNome(String nome);
    FuncionarioEntity deleteByNome(String nome);
    boolean existsByNomeIgnoreCase(String nome);
}
