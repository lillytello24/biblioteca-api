# biblioteca-api
Biblioteca 
# Biblioteca API

API REST para gestión de libros usando Spring Boot y PostgreSQL, dockerizada con Docker Compose.

---

## Requisitos

- Docker y Docker Compose instalados
- Java 17+ (solo si ejecutas sin Docker)
- Maven (solo si ejecutas sin Docker)

---

## Levantar el proyecto con Docker Compose

1. Clona el repositorio

```bash
git clone https://github.com/tu-usuario/biblioteca-api.git
cd biblioteca-api
    Ejecuta Docker Compose para levantar la base de datos y la app

docker-compose up --build

    Accede a la API en:

http://localhost:8080/api/libros

Endpoints principales

    GET /api/libros - Obtener todos los libros

    GET /api/libros/{id} - Obtener libro por id

    POST /api/libros - Crear libro

    PUT /api/libros/{id} - Actualizar libro completo

    PATCH /api/libros/{id} - Actualizar libro parcial

    DELETE /api/libros/{id} - Eliminar libro

Ejecutar sin Docker (opcional)

    Configura tu base de datos PostgreSQL y actualiza application.properties con las credenciales.

    Ejecuta con Maven

./mvnw spring-boot:run

Notas

    Asegúrate que el puerto 5432 (PostgreSQL) y 8080 (API) estén libres

    El contenedor de base de datos usa volumen para persistencia (./data/postgres)

Autor

Lilly Tello
