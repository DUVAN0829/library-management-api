ARG MONOREPO=services
ARG BOOK_MSVC=book-msvc
ARG COMMONS=observability-commons

FROM alpine/java:22-jdk AS builder

ARG MONOREPO
ARG BOOK_MSVC
ARG COMMONS

WORKDIR /app

COPY pom.xml .

# instalar observability-commons en el .m2 local
COPY $MONOREPO/$COMMONS $MONOREPO/$COMMONS/
RUN mvn install -f $MONOREPO/$COMMONS/pom.xml -DskipTests

COPY $MONOREPO/pom.xml $MONOREPO/
COPY $MONOREPO/$BOOK_MSVC $MONOREPO/$BOOK_MSVC/

WORKDIR /app/$MONOREPO/$BOOK_MSVC

RUN ./mvnw clean package -DskipTests

FROM alpine/java:22-jdk

ARG MONOREPO
ARG BOOK_MSVC

WORKDIR /app

COPY --from=builder /app/$MONOREPO/$BOOK_MSVC/target/*.jar app.jar

CMD ["java" , "-jar", "app.jar"]