# README #
API criada para o desafio técnico da Texo IT

# raspberry-awards-api #
Essa API contém Restful services for Golden Raspberry Awards

## Como executar ##

### No terminal de sua escolha executar: ###
`mvn clean install`
`mvn spring-boot:run`

### Para executar os testes ###
`mvn test`

### Configurações ###
O arquivo application.yml contém as configurações da aplicação.
É importante ressaltar que o aplicação possui uma configuração especial:
config:
  breakNames: true

A configuração acima ativa a quebra de nomes de produtores dentro de um mesmo filme, quando existe mais de um produtor por filme, considerando ambos produtores como vencedores, caso o filme tenha sido vencedor.

### Collection do Postman
Na pasta docs existe uma coleção exportada de Postman para testar a aplicação usando o Postman.

# Referencias e links #
- [Java 17] (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Apache Maven] (https://maven.apache.org/install.html)
- [POSTMAN] (https://www.postman.com/downloads/)