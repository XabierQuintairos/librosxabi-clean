FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY . /app

# Añadimos los certificados necesarios para SSL de MongoDB
RUN apt-get update && apt-get install -y ca-certificates && ./mvnw clean install

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
