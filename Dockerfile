# Usa uma imagem base com o OpenJDK para o processo de build
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

# Usa uma imagem mais leve (somente com o Java Runtime) para a execução
FROM openjdk:17-jre-slim

# Copia o arquivo JAR compilado da etapa 'builder' para a imagem final
COPY --from=builder /app/target/*.jar app.jar

# Expõe a porta que a aplicação vai rodar (8080 por padrão)
EXPOSE 8080

# Comando para rodar a aplicação quando o container for iniciado
ENTRYPOINT ["java", "-jar", "app.jar"]