ARG MONOREPO=services
ARG COPY_MSVC=copy-msvc
ARG COMMONS=observability-commons

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG COPY_MSVC
ARG COMMONS

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn/

# instalar observability-commons
COPY $MONOREPO/$COMMONS $MONOREPO/$COMMONS/
RUN ./mvnw install -f $MONOREPO/$COMMONS/pom.xml -DskipTests

COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$COPY_MSVC $MONOREPO/$COPY_MSVC/

WORKDIR /app/$MONOREPO/$COPY_MSVC

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG COPY_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$COPY_MSVC/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]