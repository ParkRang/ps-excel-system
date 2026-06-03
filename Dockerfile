FROM gradle:8.14.3-jdk24 AS builder

WORKDIR /app

COPY . .

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:24-jre

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar backend.jar

ENTRYPOINT ["java", "-jar", "backend.jar"]
