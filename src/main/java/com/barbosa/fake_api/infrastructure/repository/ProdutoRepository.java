package com.barbosa.fake_api.infrastructure.repository;

import com.barbosa.fake_api.infrastructure.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String> {
    Boolean existsBynome(String nome);

    ProdutoEntity findByNome(String nome);

}
