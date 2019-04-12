# [Tem mesa livre?](http://167.99.228.14:8080/workspace/)

## Descrição

Este projeto tem como objetivo fornecer um meio de disponibilizar para aluguel salas comerciais que estiverem livre. Assim como também realiza o aluguel das respectivas salas comerciais. Gerencia salas livres e as reservas feitas por usuários interessados nas salas. 

## Tecnologias

- Servidor linux ubuntu 18.10
- Java 8
- Java EE 7
- EJB 3
- CDI
- JAX-RS
- JPA
- JTA
- Hibernate
- Maven
- PostgreSql 10
- PgAdmin 3 (client para postgres)
- Jboss EAP 7
- AngularJs
- MaterializeCss

# Instalação

A seguir, um passo a passo de como instalar em uma máquina com sistema operacinal ubuntu 18.10 zerada.


```
# Instalação do Java 8

sudo add-apt-repository ppa:webupd8team/java

sudo apt-get update

sudo apt-get install oracle-java8-installer

# Instalação do PostgreSql 10

sudo apt-get install postgresql-10

sudo -u postgres psql postgres

postgres=# alter user postgres with password 'senha';

postgres=# \q

# Instalação do Jboss eap 7. Na raiz do repositório tem o arquivo jboss-eap-7.0.0-installer.jar. O instalador irá realizar algumas perguntas com o intuito de criar um usuario e senha para acessar o admin do jboss.

java -jar jboss-eap-7.0.0-installer.jar

# Instalação do maven

sudo apt-get install maven

# Instalação do client para acessar o banco de dados postgres
sudo apt-get install pgadmin3

```

#Configuração

## PostgresSql

- Abra o pgadmin3 e vamos criar uma nova conexão.

- Em nova conexão, devemos informar um nome para a conexão, o host (se for local, então localhost), usuário postgres e a senha que foi definida durante a instalação do postgresql anteriormente.

- Crie uma base de dados chamada workspace.

- Em src/test/resources existe o arquivo ddl.sql

- Rode o conteúdo do arquivo ddl.sql para criar a base de dados

## Jboss EAP

- Na raiz do repositório git, existe um arquivo chamado standalone.xml, como exemplo de configuração completa. Substitua o seu arquivo standalone.xml que se encontra em JBOSS-HOME/standalone/configuration por este ou então copia apenas a tag datasources deste arquivo, que é a configuração do banco de dados no jboss.

- Startar o jboss através do comando:

```
# Navegue atá a pasta da instalação do JBOSS-HOME/bin e execute:

./standalone.sh


```

- Acesse o endereço http://localhost:9990 e informe o usuario e senha criado anteriormente na instalação do jboss eap 7.

- Na aba deployment, devemos adicionar o arquivo postgresql-9.4-1200-jdbc41.jar que se encontra na raiz do repositório git.

- Na aba Configuration - SubSytems - Datasources - Non-XA. Se você optou por copiar do standalone.xml que se encontra no repositório basta selecionar o banco de dados workspace e inserir a senha do seu banco de dados. Caso contrario, você pode criar um novo banco de dados clicando em add. Testa se a conexão esta ok selecionando a opção Test Connection.

## Instalação da aplicação

Faça o clone do projeto, navegue até a pasta raiz do projeto, aonde se encontra o arquivo pom.xml e execute:

```
# Execute o maven para instalar a aplicação

mvn clean

mvn install

```

Com o jboss startado, vá até a aba deployment, e adicione o arquivo workspace.war que se encontra na pasta target que foi gerada pelo comando maven no seu projeto.

Para acessar a aplicação basta digitar no navegador: http://localhost:8080/workspace 


# Padrões de Projeto

## Front End

Na parte da visualização, temos [MaterializeCss](https://materializecss.com/) para fazer a parte do CSS. Framework que segue os padrões do [Material Design](https://material.io/design/) da Google.   

Para tornar as páginas dinâmica. Foi utilizado [AngularJs](https://angularjs.org/) também da Google. Programação feita em Javascript. Temos os seguintes conceitos:

- Controller: Realiza a comunicação entre o arquivo html e o arquivo Javascript, é aonde são programadas os eventos gerados pelo html.

- Service: Encapsula o objeto $http. Esta camada é utilizada para enviar e obter dados do servidor back end. São utilizados pelo Controller.

- Constant: São objetos que não mudam seu valor, a fim de manter organizado o projeto sem valores fixos espalhados pelo código. são usados pelo Controller.

- Directive: São bibliotecas usadas para encapsular algum comportamento que pode ser repetitivo e que vai ser usado em vários ponto na aplicação no html. No caso, estão sendo utilizados para encapsular e integrar os componentes do framework css MaterilizeCs com o AngularJs.

- View: São as partes html separadas em cada módulo de acesso que serão incluídas via html, dependendo do tipo de requisição feita.

## Back End

No back end, aplicação é dividia nos seguintes padrões de design:

- Api: Camada que representa a tecnologia jax-rs. Serve para receber e devolver as requisições do fron end.

- Service: Camada que representa a tecnologia EJB. Na camada service, o EJB garante que as transações são finalizadas com sucesso (commit) ou em caso de ocorrer exceção é feito roolback da transação, tudo de forma automática, pois é gerenciado pelo servidor Jboss (JTA). Nesta camada é onde ficam as regras de negócio e as chamadas ao banco de dados.

- Repository: Camada que representa os acessos ao banco de dados, utilizado o EntityManager do JPA em conjunto com Hibernate para obter e gravar dados no banco de dados.

- Entity: representa a entidade do banco de dados e o mapeamento Hibernate feito via JPA.

- Durante todo o ciclo no back end, é usado a tecnologia CDI, que serve para injetar instancias das classes que serão utilizados em outras classes. 

- Producer: Utilizado sempre que o CDI Injector não conseguir instanciar um objeto apenas com a palavra chave reservada new. Então criamos producers para realizar a operação de instanciar o objeto e definimos o escopo em que deve ser instanciado. 

- Util: Classes utilitárias que apenas processam alguma coisa, geralmente são classes do tipo static para utilização. 

- Exception: Criação de exceções específicas. 

- persistence.xml: na pasta resources em main temos o arquivo persistence.xml onde apontamos a integração entre o jboss e a aplicação através do JTA.

- O arquivo que representa o modelo de base de dados se encontra em src/test/resources e tem o nome de ddl.sql.

## Testes Unitários

Os testes ficam em src/test/java. Lá podemos testar os serviços da API criando um client para fazer os acessos.

## Pontos importantes

- O Login foi simulado para poder desenvolver outras telas.
- Falta definir melhor os tipos de dados da tabela referente aos locais, após melhor entendimento da regra de negócio.
- Melhorar o conteúdo existente no sistema
- Na tela de pesquisar local as fotos que aparecem são simulações apenas.

#Entrega final

## Avaliação do escopo

O escopo ficou bem claro. Foi o suficiente para poder começar o desenvolvimento. Alguns itens ainda carecem de um pouco mais de informação para melhor entendimento. Foi identificado também que poderia ser incluído 2 novas telas para gerenciamento de reservas. Conforme explicado na seção Descrição detalhada de cada item do escopo do projeto logo abaixo.

## Estimativa em DIAS do prazo de entrega

30 dias úteis

Início Desenvolvimento: 08/03/2019
Fim Desenvolvimento: 22/04/2019, dependendo da complexidade no máximo dia 26/04/2019

## Aponte as horas e atividade que utilizou para fazer o teste

A planilha esta anexado na raíz do projeto no repositório git pelo nome horas.xlsx.

## Estimativa em horas do desenvolvimento de TODO o projeto/atividades descritas no escopo

De 150 horas até 200 horas para todo o projeto. Dependendo do nível de complexidade após melhor entendimento.

Um melhor detalhamento por item do escopo do projeto está logo abaixo.

## Descrição detalhada de cada item do escopo do projeto

Segue uma descrição detalhada de cada item de escopo:

- TELA INICIAL

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- CADASTRO DE USUÁRIO ADMINISTRADOR

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- CADASTRO DE USUÁRIO RH

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- CADASTRO DE USUÁRIO PROFISSIONAL

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- CADASTRO DE LUGARES

Quase pronto. Faltou upload de fotos. Faltou melhor entendimento sobre a diponibilidade da locação e sobre os demais campos, bem como definir seus tipos de dados e validações. Dependendo da complexidade da regra para definição da disponibilidade pode variar em 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- MODIFICAÇÕES NOS LOCAIS CADASTRADOS

Está pronto.

- EXCLUSÃO DE LOCAIS CADASTRADOS

Está pronto.

- CADASTRO DE PROFISSIONAIS

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- MODIFICAÇÃO NOS DADOS DE PROFISSIONAIS

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- EXCLUSÃO DE PROFISSIONAIS

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- PESQUISA DE PROFISSIONAIS

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 24 horas de desenvolvimento. 

- PESQUISA DE LOCAIS

Desenvolvimento já iniciado. Pode variar de 6 e a 18 horas de desenvolvimento.

- RESERVA DE LOCAIS

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 18 horas de desenvolvimento. 

Identifiquei 2 novos itens:

- GERENCIAMENTO DE RESERVAS ADMIN

Uma tela onde mostra todas as reservas de suas salas. 

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 18 horas de desenvolvimento. 

- GERENCIAMENTO DE RESERVAS PROFISSIONAL

Uma tela onde mostra todas as reservas feitas e em andamento, possibilidade de cancelar uma reserva. 

Não desenvolvido. Pode variar de 6 horas de desenvolvimento até 18 horas de desenvolvimento. 
