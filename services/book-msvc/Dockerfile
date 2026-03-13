ARG MONOREPO=services
ARG BOOK_MSVC=book-msvc

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG BOOK_MSVC

WORKDIR /app

# NUEVO: copiar Maven wrapper
COPY $MONOREPO/$BOOK_MSVC/mvnw $MONOREPO/$BOOK_MSVC/mvnw
COPY $MONOREPO/$BOOK_MSVC/.mvn $MONOREPO/$BOOK_MSVC/.mvn

COPY pom.xml .
COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$BOOK_MSVC $MONOREPO/$BOOK_MSVC/

WORKDIR /app/$MONOREPO/$BOOK_MSVC

# NUEVO: asegurar permiso de ejecución
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG BOOK_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$BOOK_MSVC/target/*.jar app.jar

CMD ["java" , "-jar", "app.jar"]