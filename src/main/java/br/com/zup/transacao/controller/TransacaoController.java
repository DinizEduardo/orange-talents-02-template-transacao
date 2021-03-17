package br.com.zup.transacao.controller;

import br.com.zup.transacao.model.Cartao;
import br.com.zup.transacao.model.Transacao;
import br.com.zup.transacao.repository.CartaoRepository;
import br.com.zup.transacao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/cartoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository transacaoRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @GetMapping("/{id}/transacoes")
    public ResponseEntity<Page<Transacao>> listar(@PathVariable String id,
                                                  @PageableDefault(size = 10) Pageable paginacao) {

        Optional<Cartao> cartao = cartaoRepository.findById(id);

        if(!cartao.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Page<Transacao> transacoes = transacaoRepository.findAllByCartaoId(id, paginacao);

        return ResponseEntity.ok().body(transacoes);
    }
}
