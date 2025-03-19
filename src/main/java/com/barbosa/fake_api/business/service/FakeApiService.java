package com.barbosa.fake_api.business.service;

import com.barbosa.fake_api.apiv1.dto.ProductsDTO;
import com.barbosa.fake_api.business.converter.ProdutoConverter;
import com.barbosa.fake_api.infrastructure.client.FakeApiClient;

import com.barbosa.fake_api.infrastructure.entities.ProdutoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class FakeApiService {

    private final FakeApiClient client;
    private final ProdutoConverter converter;
    private final ProdutoService produtoService;

    public List<ProductsDTO> buscaProdutos() {
        try {

        List<ProductsDTO> dto = client.buscarListaPrdutos();

        dto.forEach(
                produto -> {
                    Boolean retorno = produtoService.existsPorNome(produto.getNome());

                    if(retorno.equals(false)) {
                        produtoService.salvaProdutos(produto);
                        }
                    }
        );

        return produtoService.buscaTodosProdutos();

        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar gravar produtos no banco de dados " + e);
        }
    }


}
