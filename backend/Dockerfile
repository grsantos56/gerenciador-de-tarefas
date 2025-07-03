# Dockerfile para construção e execução da aplicação Gerenciador de Tarefas
# Este Dockerfile usa uma construção de vários estágios para criar uma imagem final leve
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

COPY gerenciador-de-tarefas ./gerenciador-de-tarefas

WORKDIR /app/gerenciador-de-tarefas

RUN mvn dependency:go-offline -B

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre-focal AS runner

WORKDIR /app

COPY --from=builder /app/gerenciador-de-tarefas/target/gerenciador-de-tarefas-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]