package br.com.zup.transacao.kafka.dto;

import br.com.zup.transacao.model.Estabelecimento;

public class EstabelecimentoRequest {

    private Long id;

    private String nome;

    private String cidade;

    private String endereco;

    public Estabelecimento toModel() {

        return new Estabelecimento(id, nome, cidade, endereco);

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEndereco() {
        return endereco;
    }
}
