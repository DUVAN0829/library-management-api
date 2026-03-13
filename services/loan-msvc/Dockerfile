ARG MONOREPO=services
ARG LOAN_MSVC=loan-msvc

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG LOAN_MSVC

WORKDIR /app

# NUEVO: copiar Maven wrapper
COPY $MONOREPO/$LOAN_MSVC/mvnw $MONOREPO/$LOAN_MSVC/mvnw
COPY $MONOREPO/$LOAN_MSVC/.mvn $MONOREPO/$LOAN_MSVC/.mvn

COPY pom.xml .
COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$LOAN_MSVC $MONOREPO/$LOAN_MSVC/

WORKDIR /app/$MONOREPO/$LOAN_MSVC

# NUEVO: asegurar permiso de ejecución
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG LOAN_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$LOAN_MSVC/target/*.jar app.jar

CMD ["java" , "-jar", "app.jar"]