# Image Processing Service

## Introducción

Esta aplicación es un servicio REST avanzado desarrollado con Spring Boot, diseñado para facilitar la manipulación de imágenes de forma eficiente y segura. Ofrece funcionalidades para subir, actualizar, eliminar y aplicar diversas transformaciones a imágenes almacenadas en AWS S3, mientras que la información de sus metadatos se almacena localmente en una base de datos MySQL.

Entre las transformaciones soportadas se incluyen cambios de tamaño, recortes, rotaciones, marcas de agua, espejado,  compresión, conversión de formatos (JPEG, PNG, etc.) y la aplicación de filtros como escala de grises o sepia.

Para garantizar la seguridad, el servicio utiliza autenticación basada en JWT (JSON Web Tokens). Además, incorpora  OpenAPI para una documentación clara y detallada de sus endpoints, facilitando la integración con otros sistemas.

## Características

- **Cargar imágenes**: Subir imágenes a un bucket de **AWS S3**.
- **Actualizar imágenes**: Modificar las imágenes existentes en la nube.
- **Eliminar imágenes**: Borrar imágenes del almacenamiento en la nube.
- **Transformaciones**:
  - Redimensionar (Resize).
  - Recortar (Crop).
  - Rotar (Rotate).
  - Aplicar marca de agua (Watermark).
  - Voltear
  - Espejo
  - Cambiar formato (JPEG, PNG, etc.).
  - Aplicar filtros (Escala de grises, sepia, entre otros).
- **Autenticación**: Uso de **JWT** para acceso seguro.
- **Caché**: Utiliza un sistema de caché para almacenar temporalmente las peticiones a AWS S3.
- **Especificación de API**: Documentación con **OpenAPI** para facilitar la integración.

## Tecnologías

- **Spring Boot**: Framework principal para el desarrollo del backend.
- **MySQL**: Base de datos relacional para almacenar información.
- **JWT**: Autenticación basada en tokens.
- **AWS S3**: Almacenamiento de imágenes en la nube.
- **Docker**: Creación y uso de contenedores.
- **OpenAPI**: Documentación de la API.
- **Scalr**: Manipulación de imágenes.
- **Lombok**: Reducción del código.

## Requisitos

- **Java**: Versión 18 o superior.
- **Maven**: Para la gestión de dependencias.
- **AWS**: Acceso a un bucket de S3 configurado.
- **Docker y Docker Compose**: Para la ejecución de contenedores.

## Uso

###  Configurar Docker Compose
Modifica el archivo `docker-compose.yml` para configurar el servicio de MySQL.
   ```yaml  
   MYSQL_DATABASE: database  
   MYSQL_USER: user 
   MYSQL_PASSWORD: password  
 ```

### Ejecutar Docker Compose:
Construye y levanta los contenedores con el siguiente comando:
```bash
docker-compose up
```
### Modificar el archivo application.properties
Configura las propiedades necesarias en el archivo `src/main/resources/application.properties`
```properties
spring.datasource.url=url  
spring.datasource.username=user  
spring.datasource.password=password

aws.accessKey=accessKey  
aws.secretKey=secretKey  
aws.s3.region=region  
aws.s3.bucket=bucket  
  
application.security.jwt.secret-key=secret-key  
application.security.jwt.expiration=expiration
```

### Compilar y ejecutar con Maven
Compila y ejecuta el proyecto de manera local con los siguientes comandos
```bash
mvn clean install
mvn spring-boot:run
```