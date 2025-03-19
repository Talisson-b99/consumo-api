package com.barbosa.fake_api.business.converter;

import com.barbosa.fake_api.apiv1.dto.ProductsDTO;
import com.barbosa.fake_api.infrastructure.entities.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Component
public class ProdutoConverter {
    public ProdutoEntity toEntity(ProductsDTO dto){
        return ProdutoEntity.builder()
                .nome(dto.getNome())
                .categoria(dto.getCategoria())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .imagem(dto.getImagem())
                .dataInclusao(LocalDateTime.now())
                .build();
    }

    public ProductsDTO toDTO(ProdutoEntity entity){
        return ProductsDTO.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .categoria(entity.getCategoria())
                .descricao(entity.getDescricao())
                .preco(entity.getPreco())
                .imagem(entity.getImagem())
                .build();
    }

    public List<ProductsDTO> toListDTO(List<ProdutoEntity> entityList){
        return entityList.stream().map(this::toDTO).toList();
    }

    public ProdutoEntity toEntityUpdate(ProductsDTO dto, ProdutoEntity entity){
        return ProdutoEntity.builder()
                .id(entity.getId())
                .nome(dto.getNome() != null ? dto.getNome() : entity.getNome())
                .categoria(dto.getCategoria() != null ? dto.getCategoria() : entity.getCategoria())
                .descricao(dto.getDescricao() != null ? dto.getDescricao() : entity.getDescricao())
                .preco(dto.getPreco() != null ? dto.getPreco() :entity.getPreco())
                .imagem(dto.getImagem() != null ? dto.getImagem() : entity.getImagem())
                .dataInclusao(entity.getDataInclusao())
                .dataAtualizacao(LocalDateTime.now())
                .build();
    }

}
