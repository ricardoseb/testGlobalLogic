# global-logic-test

## Requerimientos

Para compilar y ejecutar la aplicación se necesita:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Ejecutando la aplicación de forma local

Una forma es ejecuntado desde su IDE, el metodo `main` de la de la aplicacion. Alternativamente puedes construir y ejecutar un artefacto del proyecto, haga algo como esto:
```
$ gradle build
$ java -jar build/libs/mymodule-0.0.1-SNAPSHOT.jar
```

## REST APIs Endpoints
### Login
```
POST /transbank/login
- User: admin
- Password: 12345
Devolvera un token
```

### Obtener informacion sobre la salud de la aplicaion
```
GET /actuator/health
```

### Para los soguientes Endpoints debemos pasarle el bearer token devuelto por login
### Crear venta
```
POST /sale/create
Accept: application/json
Content-Type: application/json
```

### Obtener venta por fecha
```
GET /sale/{fecha}
```




