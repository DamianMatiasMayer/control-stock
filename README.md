# Control de Stock

Aplicación web para controlar el stock de un comercio con uno o más depósitos.
Segundo parcial de Programación Avanzada (2.º cuatrimestre 2026, Lautaro Chiappero).

**Autor:** Damian Mayer

> En desarrollo. Este README se completa a medida que avanza el proyecto.

## Problema

Un comercio controla su stock a mano: se vende más de lo que hay, no se sabe
cuánto hay en cada depósito, nadie se entera a tiempo de un faltante y no queda
historial de los movimientos.

## Requisitos

- JDK 17 o superior
- Maven: no hace falta instalarlo. El proyecto incluye el Maven Wrapper
  (`mvnw` / `mvnw.cmd`), que descarga automáticamente la versión correcta
  la primera vez que se ejecuta.

## Versiones utilizadas

| Herramienta | Versión |
|---|---|
| Java | compilado para 17 (target), probado con JDK 25.0.2 |
| Spring Boot | 4.0.9 |
| Base de datos | H2 2.4.240 (en memoria) |
| Persistencia | Spring Data JPA / Hibernate |
| Vistas | Thymeleaf, HTML y CSS |

## Cómo ejecutar

Desde la raíz del proyecto:

```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

La aplicación queda disponible en http://localhost:8080

## Base de datos

Se usa H2 en memoria: la base se crea al arrancar y se borra al cerrar.
No requiere instalación ni configuración.

Para ver las tablas: http://localhost:8080/h2-console

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:stockdb` |
| User Name | `sa` |
| Password | *(vacío)* |

## Pruebas

*Pendiente.*

## Datos de demostración

*Pendiente.*

## Documentación

*Pendiente:* casos de uso, diagrama de clases, diagrama entidad-relación,
esquema de capas y diagrama del patrón.