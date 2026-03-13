ARG MONOREPO=services
ARG COPY_MSVC=copy-msvc

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG COPY_MSVC

WORKDIR /app

# NUEVO: copiar Maven wrapper
COPY $MONOREPO/$COPY_MSVC/mvnw $MONOREPO/$COPY_MSVC/mvnw
COPY $MONOREPO/$COPY_MSVC/.mvn $MONOREPO/$COPY_MSVC/.mvn

COPY pom.xml .
COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$COPY_MSVC $MONOREPO/$COPY_MSVC/

WORKDIR /app/$MONOREPO/$COPY_MSVC

# NUEVO: asegurar permiso de ejecución
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG COPY_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$COPY_MSVC/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]