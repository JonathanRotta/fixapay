package com.rottadev.fixapayment.repository;

import com.rottadev.fixapayment.entities.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, UUID> {

}
