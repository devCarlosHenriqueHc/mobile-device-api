# Usa uma imagem robusta do OpenJDK para o processo de build
FROM openjdk:17-jdk-slim as builder

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml para o container
COPY pom.xml .

# Baixa as dependências do Maven (acelera o build)
RUN mvn dependency:go-offline -B

# Copia todo o código-fonte restante
COPY . .

# Compila o projeto e gera o arquivo JAR
RUN mvn clean install -DskipTests

# Usa uma imagem JRE diferente e confiável para a execução
FROM eclipse-temurin:17-jre-alpine

# Copia o arquivo JAR compilado da etapa 'builder'
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta que a aplicação vai rodar
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]