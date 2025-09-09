# Use a robust OpenJDK image for the build process
FROM openjdk:17-jdk-slim as builder

# Define the working directory inside the container
WORKDIR /app

# Install Maven
RUN apt-get update && apt-get install -y maven

# Copy the pom.xml file to the container
COPY pom.xml .

# Download Maven dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the source code
COPY . .

# Compile the project and generate the JAR file
RUN mvn clean install -DskipTests

# Use a lightweight and reliable JRE image for execution
FROM eclipse-temurin:17-jre-alpine

# Copy the compiled JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]