ARG MONOREPO=platform
ARG GATEWAY_MSVC=gateway-msvc

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG GATEWAY_MSVC

WORKDIR /app

# NUEVO: copiar Maven wrapper
COPY $MONOREPO/$GATEWAY_MSVC/mvnw $MONOREPO/$GATEWAY_MSVC/mvnw
COPY $MONOREPO/$GATEWAY_MSVC/.mvn $MONOREPO/$GATEWAY_MSVC/.mvn

COPY pom.xml .
COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$GATEWAY_MSVC $MONOREPO/$GATEWAY_MSVC/

WORKDIR /app/$MONOREPO/$GATEWAY_MSVC

# NUEVO: asegurar permiso de ejecución
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG GATEWAY_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$GATEWAY_MSVC/target/*.jar app.jar

CMD ["java" , "-jar", "app.jar"]