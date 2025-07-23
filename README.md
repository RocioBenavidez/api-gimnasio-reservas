# 🏋️‍♀️ API de Gestión de Reservas en un Gimnasio

Este proyecto es una API REST desarrollada en Java con Spring Boot que permite gestionar reservas de clases en un gimnasio. Los usuarios pueden registrarse, consultar clases disponibles, reservar y cancelar reservas, todo con verificación de disponibilidad en tiempo real.

---

## 🚀 Tecnologías utilizadas

- Java + Spring Boot
- Spring Data JPA (para mapeo de entidades)
- MySQL (base de datos)
- Postman (para pruebas de endpoints)

---

## 🧱 Arquitectura del proyecto

El proyecto está estructurado en capas para facilitar su mantenimiento y escalabilidad:

- `controller/` – Manejo de peticiones HTTP
- `dto/` – Objetos de transferencia de datos
- `mapper/` – Conversión entre entidades y DTOs
- `repository/` – Interfaces de acceso a datos con JPA

---

## 📚 Funcionalidades principales

### 👤 Usuario
- `POST /api/usuarios` → Crear usuario
- `GET /api/usuarios/{id}` → Obtener un usuario
- `GET /api/usuarios` → Listar todos los usuarios
- `PUT /api/usuarios/{id}` → Actualizar usuario
- `DELETE /api/usuarios/{id}` → Eliminar usuario

### 🏋️ Clase
- `POST /api/clases` → Crear clase
- `GET /api/clases/{id}` → Obtener clase
- `GET /api/clases` → Listar clases
- `PUT /api/clases/{id}` → Actualizar clase
- `DELETE /api/clases/{id}` → Eliminar clase
- `GET /api/clases/disponibilidad/{id}` → Verificar cupos disponibles

### 📆 Reserva
- `POST /api/reserva` → Crear reserva
- `GET /api/reserva/{id}` → Obtener reserva
- `GET /api/reserva` → Listar reservas
- `PUT /api/reserva/{id}` → Actualizar reserva
- `DELETE /api/reserva/{id}` → Cancelar reserva

---

## 🎯 Objetivos del proyecto

- Facilitar la gestión de clases y usuarios para gimnasios.
- Permitir reservas con validación en tiempo real de disponibilidad.
- Mejorar la experiencia de usuario y reducir tareas administrativas.

---

## 📥 Presentación del proyecto

👉 [Descargar presentación .pptx](docs/presentacion-api-gimnasio.pptx)

---

## ✨ Posibles mejoras futuras

- Implementar autenticación y autorización con JWT.
- Enviar notificaciones automáticas por email a los usuarios.
- Integrar un sistema de pagos en línea para reservar clases.
- Dashboard visual para administración (con gráficos y estadísticas).

---

## 🧑‍💻 Autora

**Rocío Benavidez**  
Estudiante de Tecnicatura en Análisis, Desarrollo y Programación de Aplicaciones.
