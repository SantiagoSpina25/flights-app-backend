FROM eclipse-temurin:17-jre

RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

WORKDIR /app

COPY target/flights-app-1.0.0.jar app.jar

EXPOSE 8080

USER appuser

ENTRYPOINT ["java", "-jar", "app.jar"]