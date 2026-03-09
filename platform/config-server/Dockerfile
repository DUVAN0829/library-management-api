ARG MONOREPO=platform
ARG CONFIG_SERVER=config

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG CONFIG_SERVER

WORKDIR /app

COPY pom.xml .
COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$CONFIG_SERVER $MONOREPO/$CONFIG_SERVER/

WORKDIR /app/$MONOREPO/$CONFIG_SERVER

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG CONFIG_SERVER

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$CONFIG_SERVER/target/*.jar app.jar

CMD ["java" , "-jar", "app.jar"]