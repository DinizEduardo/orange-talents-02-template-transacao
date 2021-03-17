package br.com.zup.transacao.repository;

import br.com.zup.transacao.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface TransacaoRepository extends CrudRepository<Transacao, String> {

    Page<Transacao> findAllByCartaoId(String cartaoId, Pageable paginacao);

}
