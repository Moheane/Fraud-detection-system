LABEL authors="Ramahadi"
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copying Maven files for better caching
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn

# Giving execute permission to mvnw
RUN chmod +x mvnw

# Downloading dependencies
RUN ./mvnw dependency:go-offline -B

# Copying source code
COPY src src

# Building the application
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

# Creating non-root user for security
RUN useradd -r -s /bin/false springuser

# Copying the built jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Changing ownership
RUN chown springuser:springuser app.jar

# Run as non-root user
USER springuser

EXPOSE 8080

# JVM optimizations for container
ENTRYPOINT ["java", \
            "-XX:+UseContainerSupport", \
            "-XX:MaxRAMPercentage=75.0", \
            "-Djava.security.egd=file:/dev/./urandom", \
            "-jar", \
            "app.jar"]