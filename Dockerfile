FROM eclipse-temurin:21-alpine AS builder
WORKDIR /workspace

#  Cache gradle
COPY gradlew ./
COPY gradle/wrapper ./gradle/wrapper
RUN chmod +x ./gradlew && ./gradlew -v

# Cache dependencies
COPY settings.gradle.kts .
COPY app/build.gradle.kts ./app/
COPY gradle/libs.versions.toml ./gradle/
RUN ./gradlew dependencies

# Build app
COPY app ./app
RUN ./gradlew installDist


# Build image
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=builder /workspace/app/build/install/app /app

EXPOSE 8080
ENTRYPOINT exec /app/bin/app