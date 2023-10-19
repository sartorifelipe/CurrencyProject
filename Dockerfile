FROM openjdk:17-jre-slim
WORKDIR /app
COPY target/my-application.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
