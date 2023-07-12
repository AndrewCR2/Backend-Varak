FROM openjdk:18
EXPOSE 8899
ADD ./target/ms-vehiculo.jar  ms-vehiculo.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/ms-vehiculo.jar"]