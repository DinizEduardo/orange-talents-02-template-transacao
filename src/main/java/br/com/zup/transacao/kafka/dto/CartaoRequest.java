package br.com.zup.transacao.kafka.dto;

import br.com.zup.transacao.model.Cartao;

public class CartaoRequest {

    private String id;

    private String email;

    public Cartao toModel() {

        return new Cartao(id, email);

    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
