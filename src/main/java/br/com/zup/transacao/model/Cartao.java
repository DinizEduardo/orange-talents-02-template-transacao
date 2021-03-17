package br.com.zup.transacao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cartoes")
public class Cartao {

    @Id
    private String id;

    @Column(nullable = false)
    private String email;

    public Cartao(String id, String email) {
        this.id = id;
        this.email = email;
    }

    @Deprecated
    public Cartao() {
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
