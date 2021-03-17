# PROJETO - TRANSAÇÃO

## 00-SETUP-DO-PROJETO

### Objetivo

Sabemos que está ansioso(a) para começar a codificar, porém antes precisamos preparar nosso ambiente, portanto esse será nosso objetivo nessa tarefa.

### Descrição

Nessa tarefa precisamos criar um projeto para atender as funcionalidades da **Transação**, para tal, temos alguns pré requisitos de linguagem de programação e tecnologia, pois precisamos que esse projeto seja evoluído e mantido por anos, portanto é extremamente importante a escolha das mesmas.

Nosso mais experiência membro do time, sugeriu os seguintes itens:

Linguagem de programação

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Kotlin 1.3](https://kotlinlang.org/)

Tecnologia

- [Spring Boot 2.3.*](https://spring.io/projects/spring-boot)

Gerenciador de dependência

- [Maven](https://maven.apache.org/)

### Resultado Esperado

Projeto gerado com as tecnologias sugeridas:

- [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) ou [Kotlin 1.3](https://kotlinlang.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Maven](https://maven.apache.org/)

## 05-TRANSAÇÃO-DO-CARTÃO

### Objetivo

Não temos controle das transações de um usuário. Mas devemos fornecer uma experiência de consulta de transações para o
usuário final.

As transações são realizadas por um conjunto de sistemas legados, esses sistemas não possuem APIs REST modeladas,
necessidade proveniente da equipe de mobile, os sistemas também não suportam o volume de operações de consultas esperado.

### Descrição

É bastante comum que sistemas legados, não sejam implementados usando padrões de comunicação dos dias atuais, afinal
eles foram construídos a muito anos atrás.

A equipe de desenvolvimento dos sistemas legados nos avisou que suportar APIs REST não é um opção viável no momento,
devido às restrições tecnológicas, porém, eles conseguiriam enviar todas as transações em um tópico do Kafka. Atuando
como um "streaming" de pagamentos.

A equipe de desenvolvimento está enviando as transações no tópico do Kafka denominado `transacoes` com o seguinte formato:

```json
{
   "id":"c63fd0e0-eccb-4af3-9d49-39cde0ffdaf1",
   "valor":1.4874248222626738,
   "estabelecimento":{
      "nome":"B. A. Ware",
      "cidade":"North Winstonbury",
      "endereco":"18327 Mills Groves, West Marquita, SD 31244"
   },
   "cartao":{
      "id":"b0012b90-42c8-40e6-903b-64acb3aa649b",
      "email":"eduardo.diniz@zup.com.br"
   },
   "efetivadaEm":"2020-08-10T13:16:56"
}
```

Para estimular a geração das transações é preciso efetuar uma requisição para o sistema bancário, conforme exemplo abaixo:

**Requisição**

```shell script
curl --location --request POST 'http://localhost:7777/api/cartoes' \
--header 'Content-Type: application/json' \
--data-raw '{
  "id": "<NÚMERO DO CARTÃO>",
  "email": "<EMAIL DO USUÁRIO LOGADO>"
}'
```

**Exemplo**

```shell script
curl --location --request POST 'http://localhost:7777/api/cartoes' \
--header 'Content-Type: application/json' \
--data-raw '{
  "id": "5541da9c-79c5-44fb-b701-cc50ed07b45d",
  "email": "eduardo.diniz@zup.com.br"
}'
```

A partir do momento do estimulo, será gerado uma transação a cada 5 minutos, caso deseja parar a geração de transação,
devemos remover o mesmo do sistema bancário, conforme exemplo abaixo:

**Requisição**

```shell script
curl --location --request DELETE 'http://localhost:7777/api/cartoes/<NÚMERO DO CARTÃO>'
```

**Exemplo**

```shell script
curl --location --request DELETE 'http://localhost:7777/api/cartoes/5541da9c-79c5-44fb-b701-cc50ed07b45d'
```

### Necessidades

Implementar o consumo das mensagens do tópico de transações.

### Resultado Esperado

Novo serviço recebendo informações de transações de cartões.