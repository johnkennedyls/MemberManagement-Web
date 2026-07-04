# Member Management Web

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.5-6DB33F?style=for-the-badge&logo=springboot)
![Maven](https://img.shields.io/badge/Maven-3.9+-C71A36?style=for-the-badge&logo=apachemaven)
![Oracle](https://img.shields.io/badge/Oracle-Database-F80000?style=for-the-badge&logo=oracle)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.1-005F0F?style=for-the-badge&logo=thymeleaf)

## 📖 Descripción

**Member Management Web** es una aplicación web desarrollada con **Spring Boot** para la administración y gestión de miembros.

El sistema permite registrar, consultar, actualizar y administrar la información de los miembros mediante una interfaz web construida con **Thymeleaf**, utilizando **Oracle Database** como base de datos principal.

La arquitectura sigue el patrón **MVC (Model-View-Controller)**, proporcionando una solución escalable, mantenible y segura para la gestión de información.

---

## 🚀 Tecnologías

- Java 21
- Spring Boot 3.3.5
- Spring MVC
- Spring Data JPA
- Spring Security
- Thymeleaf
- Bean Validation
- Maven
- Oracle Database
- H2 Database (desarrollo)
- Lombok

---

## 🏗 Arquitectura

```
Cliente
   │
   ▼
Thymeleaf (Views)
   │
   ▼
Controllers
   │
   ▼
Services
   │
   ▼
Repositories (JPA)
   │
   ▼
Oracle Database
```

---

## ✨ Funcionalidades

- Registro de miembros
- Consulta de miembros
- Actualización de información
- Eliminación de registros
- Validación de formularios
- Autenticación con Spring Security
- Persistencia mediante JPA
- Interfaz web con Thymeleaf

---

## 📂 Estructura del proyecto

```
src
├── main
│   ├── java
│   │    ├── controller
│   │    ├── service
│   │    ├── repository
│   │    ├── model
│   │    ├── dto
│   │    ├── config
│   │    └── MemberManagementApplication.java
│   │
│   └── resources
│        ├── templates
│        ├── static
│        ├── application.properties
│        └── application.yml
│
└── test
```

---

## ⚙️ Requisitos

- Java 21
- Maven 3.9+
- Oracle Database
- Git

---

## 🔧 Instalación

Clonar el repositorio

```bash
git clone https://github.com/johnkennedyls/MemberManagement-Web.git
```

Entrar al proyecto

```bash
cd MemberManagement-Web
```

Compilar

```bash
mvn clean install
```

Ejecutar

```bash
mvn spring-boot:run
```

---

## ⚙️ Configuración

Configurar la conexión a Oracle en `application.properties`

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521/XEPDB1
spring.datasource.username=usuario
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🌐 Acceso

```
http://localhost:8080
```

---

## 🔒 Seguridad

El proyecto utiliza **Spring Security** para:

- Autenticación
- Protección de rutas
- Gestión de sesiones
- Control de acceso

---

## 🗄 Base de datos

Durante el desarrollo puede utilizarse:

- H2 Database

Para producción:

- Oracle Database

---

## 📌 Futuras mejoras

- Gestión de roles
- Dashboard administrativo
- Auditoría de cambios
- Reportes PDF y Excel
- API REST
- Documentación con OpenAPI (Swagger)
- Integración biométrica
- Integración con IA para validación y análisis

---

## 👨‍💻 Autor

**John Kennedy**

Ingeniero de Sistemas

---

## 📄 Licencia

Este proyecto es de uso académico y educativo.
