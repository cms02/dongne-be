FROM rsunix/yourkit-openjdk17

WORKDIR /app

COPY build/libs/dongne-be-0.0.1-SNAPSHOT.jar /app/dongne-be.jar

CMD ["java", "-jar" , "/app/dongne-be.jar"]