package com.barbosa.fake_api.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity()
@Table(name = "tb_produtos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nome;
    private BigDecimal preco;

    private String categoria;
    @Column( length = 1000)
    private String descricao;
    private String imagem;

    @Column(name="data_inclusao")
    private LocalDateTime dataInclusao;

    @Column(name="data_atualizacao")
    private LocalDateTime dataAtualizacao;

}
