# 🐱 Cat API Backend - Spring Boot

Este es un proyecto backend construido con **Spring Boot**, que se conecta a [TheCatAPI](https://thecatapi.com/) para consultar información sobre razas de gatos. Además, cuenta con un sistema de **autenticación mediante JWT** y una base de datos **H2** en memoria.

---

## 🚀 Características

- ✅ Autenticación con usuario y contraseña usando **JWT**  
- 🔐 Protección de endpoints mediante tokens  
- 🌐 Conexión y consumo de **TheCatAPI**  
- 💾 Base de datos en memoria con **H2 Console** habilitada  
- 🔎 Búsqueda de razas por ID o nombre  
- 📦 Arquitectura modular con buenas prácticas (SOLID, Clean Code)


## 📌 Endpoints principales

  * POST /auth/register -> retorna (HttpStatus.ACCEPTED)
  * POST /auth/login -> retorna el token
  * GET /cats -> retorna la lista de razas la misma que se consulta en la apcaPública
  * GET /cats/{id} -> retorna solo un objeto de la lista
  * GET /search?name={name} -> retrona solo un elemento de la búsqueda.
  * GET /image?id={id}

## 🔐 Seguridad
La autenticación se hace con el token que coresponde a un BearerToken para realizar la consulta.

## 🛠️ Tecnologías
  - Java 17
  - Spring Boot
  - Spring Security
  - H2 Database
  - Java JWT (Auth0)
  - REST Template
  - Lombok


