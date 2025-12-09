# ---------- build stage ----------
FROM maven:3.9.0-eclipse-temurin-17 AS build

WORKDIR /app

# primero copiamos solo pom.xml para aprovechar cache de Maven
COPY pom.xml .

# copiamos el código fuente
COPY src ./src

# construimos el jar (sin tests para acelerar)
RUN mvn -DskipTests package

# ---------- runtime stage ----------
FROM eclipse-temurin:17-jre

# crear usuario no-root
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser

WORKDIR /app

# copiamos el jar producido en la etapa de build
COPY --from=build /app/target/*.jar app.jar

# puerto por defecto (tu app usará el PORT del entorno si lo configurás)
EXPOSE 8080

# usuario no root
USER appuser

# comando principal
ENTRYPOINT ["sh", "-c", "java -jar /app/app.jar"]
