# 📚 Library Management API

API REST para la gestión de bibliotecas desarrollada con **Spring Boot**, basada en arquitectura de microservicios, seguridad con **Keycloak (OAuth2 + JWT)** y herramientas modernas de backend.

---

## 🚀 Descripción

**Library Management API** es un sistema distribuido que permite gestionar:

* 📚 Libros
* 📦 Ejemplares (copies)
* 🔄 Préstamos
* 👤 Usuarios

La aplicación implementa autenticación centralizada, documentación interactiva y despliegue mediante contenedores.

---

## 🧩 Arquitectura del sistema

| Servicio       | Puerto | Descripción            |
| -------------- | ------ | ---------------------- |
| Books Service  | 8081   | Gestión de libros      |
| Copies Service | 8082   | Gestión de ejemplares  |
| Loans Service  | 8083   | Gestión de préstamos   |
| Users Service  | 8084   | Gestión de usuarios    |
| API Gateway    | 8090   | Punto de entrada único |

---

## 🐳 Ejecución con Docker

### 🔹 Requisitos

* Docker
* Docker Compose

---

### 🔹 Levantar todo el entorno

```bash
docker-compose up --build
```

Esto iniciará automáticamente:

* Keycloak (servidor de autenticación)
* Base de datos
* Microservicios
* API Gateway
* Grafana

---

### 🔹 Detener servicios

```bash
docker-compose down
```

---

## 📄 Swagger (Documentación interactiva por microservicio)

Cada microservicio expone su propia documentación:

* 📚 **Books Service (Libros)**
  http://localhost:8081/swagger-ui/index.html

* 📦 **Copies Service (Ejemplares)**
  http://localhost:8082/swagger-ui/index.html

* 🔄 **Loans Service (Préstamos)**
  http://localhost:8083/swagger-ui/index.html

* 👤 **Users Service (Usuarios)**
  http://localhost:8084/swagger-ui/index.html

---

## 🔍 Filtros y parámetros de búsqueda

Algunos endpoints permiten filtrar información mediante **query params**.

📌 Ejemplos comunes:

```http
GET /books?title=CleanCode
GET /loans?userId=1
GET /copies?status=AVAILABLE
```

Estos filtros:

* Permiten búsquedas más específicas
* Mejoran el rendimiento al evitar traer toda la data
* Están documentados directamente en Swagger

👉 Puedes explorar todos los parámetros disponibles desde cada Swagger UI.

---

## 🔐 Autenticación en Swagger (Keycloak)

Los endpoints están protegidos mediante **OAuth2 + JWT**, por lo que es necesario autenticarse antes de consumirlos.

---

### 🧠 ¿Cómo funciona?

Swagger está integrado con Keycloak usando el flujo **Authorization Code**:

1. Swagger redirige a Keycloak
2. El usuario inicia sesión
3. Keycloak genera un **JWT (access token)**
4. Swagger incluye automáticamente ese token en cada request

---

### ✅ Pasos para autenticarse

1. Abre cualquier Swagger UI
2. Haz clic en **Authorize 🔒**
3. Ingresa el client-secret:

```text
0j3TiwtuMi23ouyn5dVCOZI0nafu7AFH`
```

4. Se abrirá la pantalla de login de Keycloak
5. Inicia sesión con:

```text
username: duvan
password: 12345
```

6. Acepta la autorización

---

### 🎯 Resultado

* Swagger enviará automáticamente el token JWT en cada request
* Podrás ejecutar endpoints protegidos (POST, PUT, DELETE)
* El acceso dependerá del rol (`ADMIN`, `LIBRARIAN`, `MEMBER`)

---

## 🔐 Configuración de Keycloak

### 📍 Acceso

* URL: http://localhost:8080
* Usuario: `admin`
* Contraseña: `admin`

---

### ⚙️ Realm

```text
library-management-api
```

---

### 👥 Roles

* ADMIN
* LIBRARIAN
* MEMBER

---

### 👤 Usuario de prueba

```json
{
  "username": "duvan",
  "password": "12345",
  "role": "ADMIN"
}
```

---

### 🔑 Clientes configurados

#### Cliente principal

* clientId: `library-app`
* client-secret: `0j3TiwtuMi23ouyn5dVCOZI0nafu7AFH`

#### Cliente interno

* clientId: `user-service`

#### Cliente Swagger

* clientId: `swagger_test`
* Tipo: public
* Flujo: Authorization Code

---

## 🌐 Uso con API Gateway (Postman)

Base URLs:

```text
http://localhost:8090/books/api/v1
http://localhost:8090/copies/api/v1
http://localhost:8090/loans/api/v1
http://localhost:8090/users/api/v1
```

---

### 📌 Operaciones disponibles

| Método | Endpoint | Descripción    |
| ------ | -------- | -------------- |
| GET    | `/`      | Listar todos   |
| GET    | `/{id}`  | Obtener por ID |
| POST   | `/`      | Crear          |
| PUT    | `/{id}`  | Actualizar     |
| DELETE | `/{id}`  | Eliminar       |

---

## 🧪 Testing

El proyecto incluye pruebas unitarias y de integración.

---

### 🔹 Tests unitarios

```bash
mvn test
```

✔️ Rápidos
✔️ Usan mocks
✔️ No usan base de datos real

---

### 🔹 Tests de integración (Testcontainers)

```bash
mvn verify
```

✔️ Levantan contenedores automáticamente
✔️ Simulan entorno real
✔️ Validan integración entre capas

---

### 🧠 Diferencia clave

| Tipo        | Usa Spring | DB real | Velocidad    |
| ----------- | ---------- | ------- | ------------ |
| Unitarios   | ❌          | ❌       | ⚡ Rápido     |
| Integración | ✅          | ✅       | 🐢 Más lento |

---

## 📊 Observabilidad

### Grafana

* URL: http://localhost:3000

---

## 🛠️ Tecnologías utilizadas

### Backend

* Java 17+
* Spring Boot
* Spring Security
* Spring Cloud
* Kafka
* Spring Data JPA
* Resilience4j

### Seguridad

* Keycloak
* OAuth2 / JWT

### DevOps

* Docker
* Docker Compose
* Github Actions
* DockerHub

### Documentación

* Swagger / OpenAPI

### Testing

* JUnit
* Testcontainers

---

## 📈 Mejoras futuras

* 🔍 Búsqueda avanzada
* 📊 Métricas con Prometheus
* 📩 Notificaciones
* 🧱 Arquitectura basada en eventos (Kafka)

---

## 👨‍💻 Autor

**Duván González**

* GitHub: https://github.com/DUVAN0829

---

## 📄 Licencia

MIT License
