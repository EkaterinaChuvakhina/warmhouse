FROM bellsoft/liberica-runtime-container:jdk-17-slim-musl
COPY target/*.jar /app/app.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/app.jar"]
