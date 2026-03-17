# 📚 Library Management API

API REST para la gestión de bibliotecas desarrollada con **Spring Boot**, enfocada en arquitectura escalable, seguridad y buenas prácticas de desarrollo backend.

---

## 🚀 Descripción

**Library Management API** es un sistema backend que permite gestionar libros, usuarios y préstamos dentro de una biblioteca.

La aplicación implementa una arquitectura basada en microservicios (o modular, según configuración), integrando autenticación segura, documentación automática y contenerización.

---

## 🧩 Características principales

- 📖 Gestión de libros (CRUD)
- 👤 Gestión de usuarios
- 🔄 Sistema de préstamos y devoluciones
- 🔐 Autenticación y autorización con Keycloak (JWT)
- 📄 Documentación interactiva con Swagger / OpenAPI
- 🐳 Contenerización con Docker
- ⚙️ Configuración centralizada (opcional)
- 🧪 Testing con Testcontainers
- 🛡️ Resiliencia con Resilience4j

---

## 🏗️ Arquitectura

El proyecto sigue principios de:

- Clean Architecture / Hexagonal Architecture
- Separación por capas:
  - `domain`
  - `application`
  - `infrastructure`
- Uso de DTOs y mapeadores
- APIs RESTful

---

## 🛠️ Tecnologías utilizadas

### Backend
- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security

### Seguridad
- Keycloak
- JWT

### DevOps
- Docker
- Docker Compose
- Kubernetes *(opcional)*

### Documentación
- Swagger / OpenAPI

### Testing
- JUnit
- Testcontainers

### Resiliencia
- Resilience4j

---

## ⚙️ Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/DUVAN0829/library-management-api.git
cd library-management-api
