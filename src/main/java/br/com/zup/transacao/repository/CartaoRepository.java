package br.com.zup.transacao.repository;

import br.com.zup.transacao.model.Cartao;
import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends CrudRepository<Cartao, String> {
}
