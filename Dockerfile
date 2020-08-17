FROM openjdk:8
# Default to UTF-8 file.encoding
ENV LANG C.UTF-8
ADD ./build/libs/global-logic-test-0.0.1-SNAPSHOT.jar global-logic-test-0.0.1-SNAPSHOT.jar
EXPOSE 8088
#El indicador adicional java.security.edg=file:/dev/./urandomse se utiliza para acelerar el inicio de la aplicación y evitar posibles bloqueos
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","global-logic-test-0.0.1-SNAPSHOT.jar"]