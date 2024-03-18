# Desafio

[](https://github.com/backend-br/desafios/blob/master/cryptography/PROBLEM.md)

# Solução

- [x]  Criar projeto Spring
    - **~~Flyway DB~~**
    - **~~PostgreSQL Driver SQL**A~~
    - **~~Spring Data JPA~~**
    - **~~Spring Web WEB~~**
    - **~~Spring Boot DevTools~~**
    - **~~Lombok~~**
- [x]  Configurar BD
    
    ```bash
    brew install postgresql@14
    
    psql NOME_DO_DB // para logar no database
    
    brew services start postgresql // para iniciar o server do psql na maquina
    ```
    
    ```bash
    spring.datasource.url=jdbc:postgresql://localhost:5432/fernandakipper
    spring.datasource.username=fernandakipper
    spring.datasource.password=password
    ```
    
- [x]  Criar Migration com SQL Puro
- [x]  Criar Entidade
- [x]  Criar Repository
- [x]  Criar Service de Criptografia
    
    Duas opções
    
    - Bouncy Castle
    - Jasypt
        
        ```java
        		<dependency>
        			<groupId>com.github.ulisesbocchio</groupId>
        			<artifactId>jasypt-spring-boot-starter</artifactId>
        			<version>3.0.5</version>
        		</dependency>
        ```
        
        - [x]  Criar classe de Config
            
            ```java
                @Bean
                public AES256TextEncryptor textEncryptor() {
                    AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
                    textEncryptor.setPassword(NOSSA_CHAVE);
                    return textEncryptor;
                }
            ```
            
        - [x]  Criar classe de criptografia
            
            ```java
                public String encryptData(String data) {
                    return textEncryptor.encrypt(data);
                }
            
                public String decryptData(String encryptedData) {
                    return textEncryptor.decrypt(encryptedData);
                }
            ```
            
- [x]  Criar DTO
- [x]  Criar Service que manipula a Entidade
    - Não esquecer da anotação `@Transactional` para os métodos que alterarem o banco de dados
- [x]  Criar Controller
    - Dica, receber o `UriComponentsBuilder` por parâmetro e usar para montar as URIs
        
        ```java
                var uri = uriBuilder.path("/{id}")
                        .buildAndExpand(data.getId()).toUri();
        ```
        
- [x]  Bônus: Criar e Gerenciar Exceções Customizadas
- [ ]  Bônus: Configurar VARs de AMBIENTE
