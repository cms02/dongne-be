FROM rsunix/yourkit-openjdk17

WORKDIR /app

COPY build/libs/dongne-be.jar /app/dongne-be.jar

CMD ["java", "-jar" , "/app/dongne-be.jar"]