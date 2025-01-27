
<div align="center">

# Blog Pessoal - Java/Spring

</div>

<div align="center">
    <img src="https://i.imgur.com/w8tTOuT.png" title="source: imgur.com" />
</div>

## Descrição do Projeto

O **Blog Pessoal** é um projeto open-source desenvolvido em Java utilizando o framework Spring. Ele funciona como uma plataforma completa onde os usuários podem criar, editar e compartilhar postagens de blog. O projeto segue o padrão de arquitetura MVC (Model-View-Controller) e implementa funcionalidades robustas de segurança com o uso do Spring Security.

Este projeto foi desenvolvido durante o bootcamp da Generation Brasil, proporcionando experiência prática no desenvolvimento de uma aplicação full-stack com ênfase nas tecnologias de back-end.

## 💻 Tecnologias Utilizadas

- **Frameworks:** Spring, Hibernate, JPA, Spring Security e JUnit.
- **Persistência de Dados:** MySQL para desenvolvimento e PostgreSQL para produção (RDS).
- **Transmissão de Dados:** Formato JSON para comunicação com a API.
- **Arquitetura:** Padrão MVC.
- **Segurança:** Implementada com Spring Security.
- **Testes:** Testes unitários com JUnit na classe `Usuario`.
- **Principais Dependências:** Spring Web, Spring Boot Devtools, Validation, Spring Data JPA, MySQL Driver, JSON Web Token, JUnit, H2 Database, Spring Doc, PostgreSQL.
- **Documentação da API:** Swagger UI.

## 📋 Explore a Documentação Swagger

A documentação Swagger fornece detalhes abrangentes sobre os endpoints da API, modelos de dados e como interagir com a aplicação.

Para acessar a documentação completa, confira o arquivo Swagger disponível [aqui](https://github.com/growthfolio/spring-blog-platform/blob/main/blogpessoal_swagger_docs/Projeto%20Blog%20Pessoal.pdf).

## 🔎 Diagrama de Classes

![Diagrama de Classes](https://www.planttext.com/api/plantuml/svg/jLD1JiCm4Bpd5JuQeNoW1rHGBqWz8D4-O6KlmSAnaRsMA8Y-7TknawP4HKXmIRAJyUpiPBFs18v2hnkXmNksmnxOHyDOovHaAxQrfikLH2-S4c0Z-4XE5VZLHe4E-qHLdjZneG37-FgQTKYtMlEhMjmTxYLFcb7z1DnKSJv8Jzq6KkgIdF5iZ-Abu64HbgYHCByPUtqsPS9gS75AKXJqsOY4RsHdY0I2T4Y0ta1if7eip6XZbJFCTV01d4fo--7fDvdiifKQo61iIgDEmSTnwfSOa_c9SZ6bOdWn96Dxd4Sq3Ne2UZJFC8UUBFtr_a0WD1HNnHNBlDotjbEcEil5S42014_k6xzIYom5qEhz0kiygjpYl-CY-u9cWgA7lFfjUpFkTLETpi0r6fd-yYy0)

## 🌐 Demonstração ao Vivo

Você pode acessar a versão publicada do projeto [aqui](https://main.d3tf7gxlu2utwn.amplifyapp.com/).

## 🚀 Deploy na AWS

A aplicação foi implantada em uma instância EC2 da AWS, garantindo um ambiente seguro e escalável para execução em produção. O deploy utilizou os seguintes recursos:

- **Instância EC2:** Configurada para hospedar o back-end.
- **Banco de Dados PostgreSQL:** Hospedado no Amazon RDS para persistência em produção.
- **Upload de Fotos:** Gerenciado com Amazon S3 para armazenamento e AWS Lambda para geração de URLs assinadas para upload seguro.
- **Segurança:** Configuração de regras de firewall via grupos de segurança para acesso restrito à aplicação.
- **Porta de Acesso:** O back-end está configurado para operar na porta 8080.

## 🙏 Agradecimentos

Este projeto foi desenvolvido durante o bootcamp da Generation Brasil. Meu agradecimento a todos os colegas e instrutores que foram parte essencial desta jornada de aprendizado. A colaboração e o conhecimento compartilhado foram inestimáveis.
