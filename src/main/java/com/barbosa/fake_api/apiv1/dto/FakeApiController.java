package com.barbosa.fake_api.apiv1.dto;

import com.barbosa.fake_api.business.service.FakeApiService;
import com.barbosa.fake_api.business.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Tag(name = "fake-api")
public class FakeApiController {
    private final FakeApiService service;
    private final ProdutoService produtoService;

    @Operation(summary = "Busca todos os produtos da api fake e salva no banco de dados", method = "POST",
    responses =  {
            @ApiResponse(responseCode = "200", description = "Produtos salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar os produtos")
    })
    @PostMapping("/api")
    public ResponseEntity<List<ProductsDTO>> buscarProdutos(){
        return ResponseEntity.ok(service.buscaProdutos());
    }

    @Operation(summary = "Busca todos os produtos do banco de dados", method = "GET",
        responses = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de dados")
        }
    )
    @GetMapping()
    public ResponseEntity<List<ProductsDTO>>buscarTodosProdutos() {
        return ResponseEntity.ok(produtoService.buscaTodosProdutos());
    }

    @Operation(summary = "Busca produto pelo nome", method = "GET")
    @GetMapping("/{nome}")
    public ResponseEntity<ProductsDTO>buscarProdutoPorNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoService.buscaProdutoPorNome(nome));
    }

    @Operation(summary = "Fazer update de novos produtos", method = "PUT",
    responses = {
            @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao salvar produtos")
    })
    @PutMapping()
    public ResponseEntity<ProductsDTO> updateProdutos(@RequestParam String id, @RequestBody ProductsDTO dto) {
        return ResponseEntity.ok(produtoService.updateProduto(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@RequestParam String id){
        produtoService.deletaProduto(id);
        return ResponseEntity.ok().build();
    }




}
