# Conversor de Moedas

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b29daafb79be474bb1f348f07bc8b55b)](https://app.codacy.com/gh/lukastteles/conversor-de-moedas?utm_source=github.com&utm_medium=referral&utm_content=lukastteles/conversor-de-moedas&utm_campaign=Badge_Grade)
[![Build Status](https://travis-ci.com/lukastteles/conversor-de-moedas.svg?branch=main)](https://travis-ci.com/lukastteles/conversor-de-moedas)

<p align="center">
  <a href="#-projeto">Projeto</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#-como-executar-a-aplicacao">Como executar a aplicação</a>&nbsp;&nbsp;|&nbsp;&nbsp
  <a href="#-recursos">Recursos</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#-tecnologias">Tecnologias</a>&nbsp;&nbsp;|&nbsp;&nbsp;
  <a href="#-camadas">Camadas</a>
</p>

## 💻 Projeto
O [Conversor de Moedas](https://github.com/lukastteles/conversor-de-moedas) é uma API Rest para conversão de valores
em algumas moedas diferentes como Real (BRL), Dolar (USD), Euro (EUR) e Iene (JPY) utilizando
a API [Exchange rates](https://exchangeratesapi.io/).

O projeto está disponível em: https://conversor-de-moedas-lukas.herokuapp.com/

## 🔨 Como executar a aplicação

#### Requisitos
    - Maven
    - Java 1.8

```bash
# Clone o repositório
$ git clone https://github.com/lukastteles/conversor-de-moedas.git

# Vá para a pasta do repositório
$ cd conversor-de-moedas

# Instale as dependências
$ mvn clean install

# Rode o projeto
$ mvn spring-boot:run

# Agora está rodando na porta 8080 
```

## 📃 Recursos
-  Primeiro é preciso adicionar um usuário ao sistema, utilizando a rota a seguir como POST:
```
/users
```

fornecendo no corpo da requisição apenas o nome:
```
{
    "name": "Jéssica"
}
```
essa requisição retornará os dados do usuário adicionado:
```
{
    "id": 1,
    "name": "Jéssica"
}
```
-  Para utilizar a conversão de valores é preciso fazer uma requisição do tipo POST no caminho:
```
/currency-converter
```

passando os seguintes parâmetros:
```
{
  "baseCurrency": "string",
  "baseValue": 0,
  "destinationCurrency": "string",
  "userId": 0
}
```
obtendo o resultado no formato a seguir:
```
{
  "baseCurrency": "string",
  "baseValue": 0,
  "conversionTax": 0,
  "date": "string",
  "destinationCurrency": "string",
  "destinationValue": 0,
  "id": 0,
  "userId": 0
}
```


-  É possível consultar o histórico de transações feitas por um usuário específico
acessando a mesma rota, num requisição GET, acrescentando o ID do usuário:
```
/currency-converter/{idUser}
```
-  Também é possível consultar a lista de usuários adicionados no sistema e seus respectivos IDs com uma requisição GET
na mesma rota:
```
/users
```

## 🧩 Tecnologias
Esse projeto foi desenvolvido utilizando as seguintes tecnologias:

-  **Java 1.8**

Linguagem utilizada no projeto com grande comunidade e suporte.

-  **Spring Boot**

Facilita o desenvolvimento e configuração de uma aplicação web como uma API Rest,
contendo classes e anotações para acessar os recursos das requisições HTTP.
Além disso, facilita a manipulação dos dados e a conexão com banco de dados e utiliza um servidor de aplicação embarcado.  

-  **JUnit**

Utilizado para configuração e definição dos testes unitários e testes de integração.

-  **Swagger2**

Biblioteca para configuração e criação da documentação da API que está disponível
[aqui](https://conversor-de-moedas-lukas.herokuapp.com/swagger-ui.html)


##📚 Camadas
A API está dividida nas seguintes camadas:

-  **Controller**

Lida com as requisições dos usuários, fica responsável pelos endpoints e por retornar a resposta aos usuários
com a ajuda das outras camadas.

-  **Service**
  
Responsável pela lógica da aplicação, utiliza os dados recebidos para construir a resposta com os modelos de dados
definidos no sistema.

-  **Repository**

Camada responsável pela conexão com o banco de dados e realização das consultas para obter os objetos armazenados. 

---
**Feito por Lukas Teles**