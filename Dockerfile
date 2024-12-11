FROM openjdk:17-jdk-alpine

COPY ./target/ecommerceAuth-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
