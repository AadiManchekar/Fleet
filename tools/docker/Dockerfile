# ---------- Stage 1: Build ----------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

ARG MODULE_NAME
ARG SKIP_TESTS=false

WORKDIR /build

COPY license-header /build/license-header
COPY pom.xml /build/pom.xml
COPY ${MODULE_NAME} /build/${MODULE_NAME}
COPY proto /build/proto

# Download all dependencies (including plugins and their dependencies)
# So that subsequent Maven commands can run in "offline" mode (without needing to download anything from the internet).
# -B is batch mode. It disables interactive prompts and progress bars etc
RUN mvn dependency:go-offline -B

# -pl stands for projects list. Instructs Maven to build only the specified module
# -am stands for also make. Instructs Maven Maven to also build any modules that the specified module depends on
RUN if [ "${SKIP_TESTS}" = "true" ]; then \
      mvn -pl ${MODULE_NAME} -am clean package -DskipTests -B; \
    else \
      mvn -pl ${MODULE_NAME} -am clean package -B; \
    fi

# ---------- Stage 2: Runtime ----------
FROM gcr.io/distroless/java21-debian12@sha256:7c9a9a362eadadb308d29b9c7fec2b39e5d5aa21d58837176a2cca50bdd06609

ARG MODULE_NAME
ARG PORT

WORKDIR /app
EXPOSE ${PORT}

# Copy the built jar from the builder image
COPY --from=builder /build/${MODULE_NAME}/target/*.jar /app/app.jar

# The entrypoint of this image is set to the equivalent of "java -jar", so this image expects users to supply a path to a JAR file in the CMD.
CMD ["/app/app.jar"]
