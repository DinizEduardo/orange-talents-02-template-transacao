package br.com.zup.transacao.kafka;

import br.com.zup.transacao.kafka.dto.TransacaoRequest;
import br.com.zup.transacao.model.Transacao;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class TransacaoConsumidor {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoRequest request) {
        System.out.println("-------------------------------------------------");
        System.out.println("Mensagem chegou");
        Transacao transacao = request.toModel();
        System.out.println(transacao.toString());
        manager.merge(transacao);

//        System.out.println(transacao.toString());
        System.out.println("Mensagem terminou");
        System.out.println("-------------------------------------------------");

    }


}
