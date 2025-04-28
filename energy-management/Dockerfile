# Folosește imaginea oficială JDK 17 slim
FROM openjdk:17-jdk-slim

# Setează directorul de lucru în container
WORKDIR /app

# Copiază fișierul JAR în container
COPY target/energy-management-0.0.1-SNAPSHOT.jar app.jar

# Expune portul pe care rulează aplicația
EXPOSE 8081

# Comanda de start
ENTRYPOINT ["java", "-jar", "app.jar"]
