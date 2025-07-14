FROM ghcr.io/graalvm/graalvm-ce AS builder
WORKDIR /workspace

RUN gu install native-image

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

RUN ./gradlew nativeCompile


# Build image
FROM alpine:latest
WORKDIR /app

RUN apk add libstdc++ libc6-compat

COPY --from=builder /workspace/app/build/native/nativeCompile/app /app

EXPOSE 8080
CMD ["/app/app"]