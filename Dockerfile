# Use a common and robust JDK image for the build stage
FROM openjdk:17-jdk-slim as builder

# Define the working directory inside the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download Maven dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the source code
COPY . .

# Compile the project and generate the JAR file
RUN mvn clean install -DskipTests

# Use the same lightweight JRE image for the final runtime stage
FROM openjdk:17-jre-slim

# Copy the compiled JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]