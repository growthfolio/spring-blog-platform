# 📝 Spring Blog Platform - Plataforma de Blog Completa

## 🎯 Objetivo de Aprendizado
Projeto desenvolvido para estudar **Spring Boot** e **arquitetura MVC**, implementando uma plataforma completa de blog com autenticação, CRUD de postagens, segurança com Spring Security e deploy na AWS.

## 🛠️ Tecnologias Utilizadas
- **Framework:** Spring Boot, Spring Security, Spring Data JPA
- **Linguagem:** Java 17+
- **Banco de Dados:** MySQL (dev), PostgreSQL (prod)
- **Autenticação:** JWT (JSON Web Tokens)
- **Documentação:** Swagger/OpenAPI
- **Testes:** JUnit 5
- **Deploy:** AWS (EC2, RDS, S3)
- **Conceitos estudados:**
  - Arquitetura MVC
  - Spring Security e JWT
  - JPA/Hibernate e relacionamentos
  - Testes unitários
  - Deploy em cloud (AWS)
  - API RESTful

## 🚀 Demonstração
```java
// Controller REST com Spring Boot
@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
    
    @GetMapping
    public ResponseEntity<List<Postagem>> getAll() {
        return ResponseEntity.ok(postagemRepository.findAll());
    }
    
    @PostMapping
    public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(postagemRepository.save(postagem));
    }
}
```

## 💡 Principais Aprendizados

### 🏗️ Arquitetura Spring Boot
- **Controllers:** Gerenciamento de endpoints REST
- **Services:** Lógica de negócio e regras
- **Repositories:** Acesso a dados com JPA
- **Entities:** Mapeamento objeto-relacional

### 🔐 Segurança e Autenticação
- **Spring Security:** Configuração de segurança
- **JWT:** Tokens para autenticação stateless
- **BCrypt:** Hash de senhas
- **CORS:** Configuração para frontend

### 📊 Banco de Dados e JPA
- **Relacionamentos:** @OneToMany, @ManyToOne
- **Validações:** Bean Validation (@Valid, @NotNull)
- **Queries:** JPQL e métodos derivados
- **Migrations:** Controle de schema

## 🧠 Conceitos Técnicos Estudados

### 1. **Entidades JPA**
```java
@Entity
@Table(name = "tb_postagens")
public class Postagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "O atributo título é obrigatório")
    @Size(min = 5, max = 100)
    private String titulo;
    
    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Tema tema;
    
    @ManyToOne
    @JsonIgnoreProperties("postagem")
    private Usuario usuario;
}
```

### 2. **Spring Security Configuration**
```java
@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .sessionManagement(management -> management
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/usuarios/logar").permitAll()
                .requestMatchers("/usuarios/cadastrar").permitAll()
                .anyRequest().authenticated())
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
```

### 3. **Testes Unitários**
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Testes da Classe UsuarioController")
class UsuarioControllerTest {
    
    @Test
    @DisplayName("Deve criar um novo usuário")
    public void deveCriarNovoUsuario() {
        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuario);
        
        ResponseEntity<Usuario> corpoResposta = testRestTemplate
            .exchange("/usuarios/cadastrar", HttpMethod.POST, 
                     corpoRequisicao, Usuario.class);
        
        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }
}
```

## 📁 Estrutura do Projeto
```
spring-blog-platform/
├── src/main/java/
│   └── com/generation/blogpessoal/
│       ├── controller/          # Controllers REST
│       ├── model/              # Entidades JPA
│       ├── repository/         # Repositórios de dados
│       ├── security/           # Configurações de segurança
│       └── service/            # Serviços de negócio
├── src/test/java/              # Testes unitários
├── blogpessoal_swagger_docs/   # Documentação Swagger
└── target/                     # Build artifacts
```

## 🔧 Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- MySQL (desenvolvimento)

### Passos
1. Clone o repositório:
```bash
git clone <repo-url>
cd spring-blog-platform
```

2. Configure o banco de dados no `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_blogpessoal
spring.datasource.username=root
spring.datasource.password=root
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

4. Acesse a documentação Swagger:
```
http://localhost:8080/swagger-ui.html
```

## 🌐 Deploy AWS
- **EC2:** Instância para hospedar a aplicação
- **RDS PostgreSQL:** Banco de dados em produção
- **S3:** Armazenamento de imagens
- **Lambda:** URLs assinadas para upload seguro

## 🚧 Desafios Enfrentados
1. **Spring Security:** Configuração de JWT e CORS
2. **Relacionamentos JPA:** Mapeamento correto entre entidades
3. **Testes:** Configuração de ambiente de teste
4. **Deploy AWS:** Configuração de infraestrutura cloud
5. **CORS:** Integração com frontend React

## 📚 Recursos Utilizados
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Reference](https://spring.io/projects/spring-security)
- [JPA/Hibernate Guide](https://hibernate.org/orm/documentation/)
- [AWS Documentation](https://docs.aws.amazon.com/)
- [Generation Brasil Bootcamp](https://brazil.generation.org/) - Bootcamp onde o projeto foi desenvolvido

## 📈 Próximos Passos
- [ ] Implementar sistema de comentários
- [ ] Adicionar categorias e tags
- [ ] Criar dashboard administrativo
- [ ] Implementar notificações por email
- [ ] Adicionar sistema de likes/dislikes
- [ ] Melhorar cobertura de testes

## 🔗 Projetos Relacionados
- [React E-commerce](../react-ecommerce-tt/) - Frontend integrado
- [Sistema Bancário](../contabancaria/) - Base de POO
- [Java Generation Notes](../java-generation-notes/) - Fundamentos estudados

---

**Desenvolvido por:** Felipe Macedo  
**Contato:** contato.dev.macedo@gmail.com  
**GitHub:** [FelipeMacedo](https://github.com/felipemacedo1)  
**LinkedIn:** [felipemacedo1](https://linkedin.com/in/felipemacedo1)

> 💡 **Reflexão:** Este projeto consolidou meus conhecimentos em Spring Boot e arquiteturas enterprise. A experiência com Spring Security, JPA e deploy na AWS foi fundamental para entender desenvolvimento backend profissional.