package com.barbosa.fake_api.business.service;

import com.barbosa.fake_api.apiv1.dto.ProductsDTO;
import com.barbosa.fake_api.business.converter.ProdutoConverter;
import com.barbosa.fake_api.infrastructure.entities.ProdutoEntity;
import com.barbosa.fake_api.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;
    private final ProdutoConverter converter;

    public ProductsDTO salvaProdutos(ProductsDTO dto){
        try {
            ProdutoEntity entity = converter.toEntity(dto);
            return converter.toDTO( repository.save(entity));
        } catch (Exception e){
            throw  new RuntimeException("Erro ao salvar Produtos" + e);
        }
    }

    public List<ProductsDTO> buscaTodosProdutos(){
        try{
            return converter.toListDTO(repository.findAll());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar por todos os produtos" + e);
        }
    }

    public Boolean existsPorNome(String nome) {
        try{
            return repository.existsBynome(nome);
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar por nome" + e + nome);
        }
    }

    public ProductsDTO buscaProdutoPorNome(String nome) {
        try{
            return converter.toDTO(repository.findByNome(nome));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar produto por nome" + e);
        }
    }

    public void deletaProduto(String id){
        try{
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar por nome" + e);
        }
    }

    public ProductsDTO updateProduto(String id, ProductsDTO dto){
        try{
            ProdutoEntity entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Id não existe no banco de dados"));
            ProdutoEntity entityUpdate = converter.toEntityUpdate( dto, entity);
            repository.save(entityUpdate);
            return converter.toDTO(repository.findByNome(entityUpdate.getNome()));
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar produto" + e);
        }
    }
}
