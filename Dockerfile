# Etapa de build
FROM openjdk:17-jdk-slim AS build

WORKDIR /workspace/app

# Copiar arquivos necessários para o build
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

# Tornar o Maven Wrapper executável e construir o projeto
RUN chmod +x ./mvnw
RUN ./mvnw install -DskipTests

# Extrair o conteúdo do JAR gerado
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# Etapa de runtime
FROM openjdk:17-jdk-slim

WORKDIR /app

# Variável ARG para o caminho dos arquivos extraídos
ARG DEPENDENCY=/workspace/app/target/dependency

# Copiar dependências e classes descompactadas
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app

# Expor a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-cp", "app:app/lib/*", "com.generation.blogpessoal.BlogpessoalApplication"]
