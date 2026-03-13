ARG MONOREPO=services
ARG USER_MSVC=user-msvc

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG USER_MSVC

WORKDIR /app

COPY pom.xml .
COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$USER_MSVC $MONOREPO/$USER_MSVC/

WORKDIR /app/$MONOREPO/$USER_MSVC

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG USER_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$USER_MSVC/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]