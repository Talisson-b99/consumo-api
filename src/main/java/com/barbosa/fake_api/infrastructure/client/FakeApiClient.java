package com.barbosa.fake_api.infrastructure.client;

import com.barbosa.fake_api.apiv1.dto.ProductsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "fake-api", url = "${fake-api.url:#{null}}")
public interface FakeApiClient {
    @GetMapping("/products")
    List<ProductsDTO> buscarListaPrdutos();
}
