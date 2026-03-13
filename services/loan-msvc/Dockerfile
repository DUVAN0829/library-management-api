ARG MONOREPO=services
ARG LOAN_MSVC=loan-msvc
ARG COMMONS=observability-commons

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG LOAN_MSVC
ARG COMMONS

WORKDIR /app

COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn/

# instalar observability-commons
COPY $MONOREPO/$COMMONS $MONOREPO/$COMMONS/
RUN ./mvnw install -f $MONOREPO/$COMMONS/pom.xml -DskipTests

COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$LOAN_MSVC $MONOREPO/$LOAN_MSVC/

WORKDIR /app/$MONOREPO/$LOAN_MSVC

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG LOAN_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$LOAN_MSVC/target/*.jar app.jar

CMD ["java" , "-jar", "app.jar"]