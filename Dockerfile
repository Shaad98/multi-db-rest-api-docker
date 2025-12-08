# FROM maven:3.9.11-eclipse-temurin-25-alpine AS builder

# WORKDIR /app

# COPY . .

# RUN mvn clean package -DskipTests


# FROM openjdk:25

# WORKDIR /app

# COPY --from=builder /app/target/*.jar app.jar

# ENTRYPOINT [ "java","-jar","app.jar" ]

# Build Stage
FROM maven:3.9.11-eclipse-temurin-25-alpine AS builder

WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


# Final Runtime Stage
FROM eclipse-temurin:25-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
