FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/EcommerceAuthApplication.jar /app/app.jar

CMD ["java", "-jar", "app.jar"]
