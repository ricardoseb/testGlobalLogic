# global-logic-test

## Requerimientos

Para compilar y ejecutar la aplicación de forma local se necesita:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Installing Gradle](https://docs.gradle.org/current/userguide/installation.html)

Para compilar y ejecutar la aplicación a través de un contenedor Docker se necesita:

* [Windows](https://docs.docker.com/windows/started)
* [OS X](https://docs.docker.com/mac/started/)
* [Linux](https://docs.docker.com/linux/started/)

## Ejecutando la aplicación de forma local

Una forma es ejecuntado desde su IDE, el metodo `main` de la aplicacion. 
Alternativamente puedes construir y ejecutar el proyecto, descargando el proyecto, luego
situandose en la raiz del proyecto y ejecutando desde la linea de comando algo como esto:

```
$ gradle build
$ java -jar build/libs/global-logic-test-0.0.1-SNAPSHOT.jar
```

## Ejecutando la aplicación a través de Docker

Otra forma es situarce en la raiz del proyecto y ejecutando desde la linea de comando algo
como esto:

```
$ docker build --tag nombreImagen:nombreTag .
$ docker run -d --name nombreIdentificador -p 8088:8088 nombreImagen:nombreTag
```

## Puede ver la documentación de la API en swagger-ui apuntando a:

- [http://localhost:8088](http://localhost:8088)


### Adicionalmente puede obtener información sobre la salud de la aplicaión:
```
GET /actuator/health
```

## Autor

* **Ricardo Quiroga** - *Initial work* - [RicardoSeb](https://github.com/ricardoseb)




