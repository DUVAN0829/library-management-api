# 📚 Library Management API

API REST para la gestión de bibliotecas desarrollada con **Spring Boot**, basada en arquitectura de microservicios, seguridad con Keycloak y herramientas modernas de backend.

---

## 🚀 Descripción

**Library Management API** es un sistema distribuido que permite gestionar:

- 📚 Libros
- 📦 Ejemplares (copies)
- 🔄 Préstamos
- 👤 Usuarios

Incluye autenticación y autorización mediante **Keycloak (OAuth2 + JWT)**, documentación con **Swagger**, monitoreo y despliegue con **Docker**.

---

## 🧩 Arquitectura del sistema

Microservicios independientes:

| Servicio | Puerto | Descripción |
|----------|--------|------------|
| Books Service | 8081 | Gestión de libros |
| Copies Service | 8082 | Gestión de ejemplares |
| Loans Service | 8083 | Gestión de préstamos |
| Users Service | 8084 | Gestión de usuarios |
| API Gateway | 8090 | Punto de entrada único |

---

## 📄 Acceso a Swagger (Documentación API)

Cada microservicio tiene su propia documentación:

- 📚 Books → http://localhost:8081/swagger-ui/index.html  
- 📦 Copies → http://localhost:8082/swagger-ui/index.html  
- 🔄 Loans → http://localhost:8083/swagger-ui/index.html  
- 👤 Users → http://localhost:8084/swagger-ui/index.html  

---

## 🔐 Autenticación con Keycloak

### 📍 Acceso a Keycloak

- URL: http://localhost:8080  
- Usuario: `admin`  
- Contraseña: `admin`  

---

### ⚙️ Configuración del Realm

- **Realm ID:** `library-management-api`

### 👥 Roles disponibles

- `ADMIN`
- `LIBRARIAN`
- `MEMBER`

---

### 🔑 Clientes configurados

#### 1. Cliente principal (API)

- **clientId:** `library-app`
- **client-secret:** `0j3TiwtuMi23ouyn5dVCOZI0nafu7AFH`
- Tipo: confidential
- Direct Access Grants: enabled

---

#### 2. Cliente interno

- **clientId:** `user-service`
- Uso interno entre microservicios

---

#### 3. Cliente para Swagger

- **clientId:** `swagger_test`
- Tipo: public
- Flujo: Authorization Code

---

### 👤 Usuario administrador

```json
{
  "username": "duvan",
  "password": "12345",
  "role": "ADMIN"
}
