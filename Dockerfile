# Bruker gradle 8 og jdk 21 som builder image
FROM gradle:8-jdk21 AS builder

# Setter arbeidskatalog
WORKDIR /home/gradle

# Kopierer nødvendige filer for byggeprosessen
COPY settings.gradle.kts gradlew build.gradle.kts .
COPY src src
COPY gradle gradle

# Kompilerer prosjektet
RUN ./gradlew bootJar

# Flytter JAR-filen til app.jar
RUN mv build/libs/*.jar app.jar


# Bruker et lettvekts-alpine image for å kjøre applikasjonen
FROM eclipse-temurin:21-alpine

# Opprett bruker og gruppe for sikkerhet
RUN addgroup -S app && adduser -S -G app -h /app app

# Setter arbeidskatalog til /app
USER app
WORKDIR /app

# Kopierer JAR-filen fra builder image til /app
COPY --from=builder --chown=app:app /home/gradle/app.jar .

# Lager lagringskatalog og kopierer databasefilen om det er nødvendig
RUN mkdir -p /app/storage
# COPY --chown=app:app app.mv.db /app/storage/

# Starter applikasjonen
CMD ["java", "-jar", "app.jar"]
