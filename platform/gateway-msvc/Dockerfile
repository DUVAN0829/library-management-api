ARG MONOREPO=services
ARG GATEWAY_MSVC=gateway-msvc

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG GATEWAY_MSVC

WORKDIR /app

COPY pom.xml .
COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$GATEWAY_MSVC $MONOREPO/$GATEWAY_MSVC/

WORKDIR /app/$MONOREPO/$GATEWAY_MSVC

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG GATEWAY_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$GATEWAY_MSVC/target/*.jar app.jar

CMD ["java" , "-jar", "app.jar"]