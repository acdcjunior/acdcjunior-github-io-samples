spring-mvc-jpa-mockito-piloto
=============================

Aplicacao "Piloto" mostrando o uso de Spring MVC + JPA + Mockito

##Como usar:

- Baixe a aplicacao (pegue o **[ZIP aqui](https://github.com/acdcjunior/spring-mvc-jpa-mockito-piloto/archive/master.zip)**, ou via `git clone`)
- Vah na pasta e digite `mvn tomcat7:run`
- Abra a URL **http://127.0.0.1:8080/piloto**

Pronto!

###Detalhes pequenos, mas importantes:
- O `mvn tomcat7:run` utiliza o banco de testes conforme scripts em `src/test/resources/sql`. O banco criado eh o `bancoH2tomcatRun.h2.db` na pasta do projeto -- note que ele eh apagado/recriado a cada execucao.
- Os testes tambem utilizam os mesmos scripts SQL acima como dados, mas eles criam um banco em memoria (que eh recriado a cada execucao de metodo de teste).
- `mvn cobertura:cobertura` gera o relatorio de cobertura de testes em `/target/site/cobertura/index.html`.
- Edicoes em paginas `.jsp` vao ser refletidas imediatamente na aplicacao sem necessidade de reiniciar.
- Edicoes em classes `.java` ainda requerem restart! (Maven nao recompila os `.java` assim que eles sao editados -- para conseguir que as edicoes em classes repercutam automaticamente, execute o projeto via um tomcat do eclipse, como sempre se fez.)

##Overview de tecnologias usadas:

- Maven
- Spring e Spring MVC 3.2.5
- JSP + Tiles 3.0.3 como View Technology
- Persistencia
  - JPA 2.0
  - Hibernate 4.2.7.SP1 como implementacao JPA
  - H2 como banco de exemplo e de testes
- Testes
  - JUnit 4.11
  - Mockito 1.9.5 + Hamcrest matchers 1.3
  - Spring-Test
  - Relatorio de cobertura de testes com Cobertura 2.6

##Objetivos/filosofia

O objetivo da aplicacao eh fornecer infraestrutura de exemplo para:

- Controladores Spring MVC
- Entidades e queries do JPA 2.0
- Testes de controladores e de repositorios (com banco embarcado)

##Outros/detalhes do uso

- Como nao eh possivel ter `<link rel="stylesheet">` no `<body>`, os nomes dos arquivos css sao definidos no `tiles.xml`, junto com a view.
