ARG MONOREPO=services
ARG LOAN_MSVC=loan-msvc

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG BOOK_MSVC

WORKDIR /app

COPY pom.xml .
COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$LOAN_MSVC $MONOREPO/$LOAN_MSVC/

WORKDIR /app/$MONOREPO/$LOAN_MSVC

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG BOOK_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$LOAN_MSVC/target/*.jar app.jar

CMD ["java" , "-jar", "app.jar"]