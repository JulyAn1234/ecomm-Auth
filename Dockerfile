FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/ecommerceAuth-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]
