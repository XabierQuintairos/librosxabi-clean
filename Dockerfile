FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . /app


EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
