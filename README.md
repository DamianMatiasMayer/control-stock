# Control de Stock

Aplicación web para controlar el stock de un comercio con uno o más depósitos: registra los egresos de mercadería, impide sacar más de lo que hay y genera alertas automáticas cuando un producto queda en su stock mínimo.

Segundo parcial de **Programación Avanzada** · 2.º cuatrimestre 2026 · Docente: Lautaro Chiappero
**Autor:** Damian Mayer

## Problema

Un comercio controla su stock a mano. Se vende más de lo que hay, no se sabe cuánto hay en cada depósito, nadie se entera a tiempo de un faltante y no queda historial de lo que entra y sale.

## Alcance

### Implementado en el segundo parcial

| Caso de uso | Descripción |
|---|---|
| CU9 | Consultar el stock por depósito, con filtros (depósito, producto, solo stock bajo) y paginación |
| CU1 | Registrar un egreso, con validación en el servidor y reglas de negocio |
| CU2 | Consultar el movimiento registrado y su alerta, si se generó |

**Reglas de negocio**

- **RN1:** un egreso no puede superar el stock disponible en ese depósito.
- **RN2:** si después del movimiento la cantidad queda en el stock mínimo o por debajo, se genera una alerta automática (patrón Observer).
- **RN3:** la cantidad de todo movimiento debe ser mayor a 0.
- **RN6:** el stock solo cambia a través de movimientos, para que quede registrado en el historial.

### Previsto para el examen final

Login con roles (administrador y operador) y administración de usuarios; CRUD de productos con baja lógica; CRUD de categorías y depósitos; registro de ingresos; transferencias entre depósitos; atención de alertas; historial de movimientos con filtros; datos persistentes en H2 en archivo; paquete ejecutable para Windows.

### Fuera de alcance

Precios y facturación, compras a proveedores, varias empresas, y lotes o vencimientos por unidad.

## Requisitos

- **JDK 17 o superior.**
- **No hace falta instalar Maven:** el proyecto incluye el Maven Wrapper (`mvnw` / `mvnw.cmd`), que descarga la versión correcta de Maven la primera vez que se ejecuta.
- Un navegador web.

## Versiones utilizadas

| Herramienta | Versión |
|---|---|
| Java | compilado para Java 17 (target), probado con JDK 25.0.2 |
| Spring Boot | 4.0.9 (*) |
| Hibernate ORM | 7.2.25 |
| Base de datos | H2 2.4.240 |
| Vistas | Thymeleaf, HTML y CSS (con un script JavaScript para los filtros) |
| Validación | Jakarta Bean Validation |
| Pruebas | JUnit 5 |
| Construcción | Maven, mediante el Maven Wrapper |



## Cómo construir

Desde la raíz del proyecto:

```bash
# Windows (PowerShell)
.\mvnw.cmd clean package

# Linux / macOS
./mvnw clean package
```

Compila, ejecuta las pruebas y genera el archivo `target/control-stock-0.0.1-SNAPSHOT.jar`.

## Cómo ejecutar

**Opción 1: con el Maven Wrapper**

```bash
# Windows (PowerShell)
.\mvnw.cmd spring-boot:run

# Linux / macOS
./mvnw spring-boot:run
```

**Opción 2: con el `.jar` generado**

```bash
java -jar target/control-stock-0.0.1-SNAPSHOT.jar
```

Cuando en la consola aparece `Started ControlStockApplication`, la aplicación está lista en:

**http://localhost:8080**

Para **detenerla**, en la consola: `Ctrl + C`.

## Base de datos

Se usa **H2 en memoria**: la base se crea al arrancar la aplicación y se borra al detenerla. No requiere instalación ni configuración.

- Hibernate crea las tablas a partir de las entidades (`spring.jpa.hibernate.ddl-auto=create-drop`).
- La configuración está en `src/main/resources/application.properties`.

**Consola web de H2**, para ver las tablas mientras la aplicación está levantada: http://localhost:8080/h2-console

| Campo | Valor |
|---|---|
| JDBC URL | `jdbc:h2:mem:stockdb` |
| User Name | `sa` |
| Password | *(vacío)* |

## Datos de demostración

El archivo `src/main/resources/data.sql` carga **datos ficticios** automáticamente cada vez que arranca la aplicación:

| Datos | Cantidad |
|---|---|
| Categorías | 4 (Almacén, Limpieza, Bebidas, Perfumería) |
| Depósitos | 3 (Central, Sucursal Norte, Sucursal Sur) |
| Productos | 10 |
| Existencias (stock de un producto en un depósito) | 22, de las cuales 4 están en el stock mínimo o por debajo |

**Para volver a los datos iniciales**, alcanza con detener la aplicación y volver a ejecutarla.

## Pruebas

```bash
# Windows (PowerShell)
.\mvnw.cmd test

# Linux / macOS
./mvnw test
```

Resultado esperado: **6 tests, 0 fallas, `BUILD SUCCESS`**.

| Test | Tipo | Qué verifica |
|---|---|---|
| `egresoMenorAlStock_descuentaYGuardaElMovimiento` | Válido | Se descuenta la cantidad, se guarda el movimiento y no se genera alerta |
| `egresoMayorAlStock_lanzaExcepcionYNoGuardaNada` | Inválido (RN1) | Se rechaza con `StockInsuficienteException` y no se guarda nada |
| `egresoQueDejaElStockJustoEnElMinimo_generaUnaAlerta` | Límite (RN2) | Se genera la alerta, vinculada al movimiento |
| `hayStockEnOtroDepositoPeroNoEnElElegido_seRechaza` | Inválido (RN1) | La regla controla el depósito elegido, no el total |
| `egresoConCantidadCero_seRechaza` | Inválido (RN3) | Se rechaza una cantidad igual a 0 |
| `contextLoads` | Arranque | La aplicación levanta y `data.sql` no tiene errores |

Las pruebas del servicio crean sus propios datos, no cargan `data.sql` y deshacen sus cambios al terminar, así no dependen entre sí ni de los datos de demostración.

### Prueba manual sugerida

Con la aplicación levantada:

1. Entrar a http://localhost:8080: se ven 22 resultados en 3 páginas, con el stock bajo primero.
2. Filtrar por producto `yerba`: aparece en los tres depósitos.
3. **Yerba 1 kg / Sucursal Norte** (6, mínimo 3) → *Registrar egreso*:
   - cantidad **7** → *"Stock insuficiente en Sucursal Norte: disponible 6, solicitado 7"* (no se guarda nada);
   - cantidad **3**, motivo *Venta* → se registra el movimiento y aparece la **alerta de stock bajo**.
4. Entrar a http://localhost:8080/movimientos/999 → página de error propia, sin trazas.

## Estructura del proyecto

```
src/main/java/ar/edu/davinci/controlstock/
├── controller/   Controllers web y manejo de errores
├── dto/          MovimientoForm, el formulario de egreso
├── entity/       Entidades JPA y enums del dominio
├── repository/   Repositorios de Spring Data JPA
└── service/      Reglas de negocio y patrón Observer
src/main/resources/
├── templates/    Vistas Thymeleaf (y páginas de error)
├── static/       CSS y JavaScript
├── application.properties
├── data.sql      Datos de demostración
└── messages.properties
src/test/java/    Pruebas JUnit 5
docs/             Definición del proyecto y diagramas
```

**Arquitectura en capas:** Controller → Service → Repository → Entity. Las reglas de negocio están en `InventarioService`. El patrón **Observer** está implementado en código propio: `InventarioService` (Subject) notifica a la interfaz `ObservadorStock` (Observer), y `AlertaStockBajoObserver` (ConcreteObserver) crea la alerta.

## Problemas habituales

| Problema | Solución |
|---|---|
| `mvnw.cmd` no se reconoce en PowerShell | Escribirlo con `.\` adelante: `.\mvnw.cmd spring-boot:run` |
| *"Port 8080 was already in use"* | Otro programa usa el puerto 8080, por ejemplo Tomcat de XAMPP. Detenerlo, o cerrar otra instancia de la aplicación que haya quedado abierta |
| *"JAVA_HOME is not defined"* o *"java no se reconoce"* | Instalar un JDK 17 o superior y verificar con `java -version` |
| Un cambio en CSS o JavaScript no se ve | Recargar sin caché con `Ctrl + F5` |
| La primera ejecución tarda | Es normal: el Maven Wrapper descarga Maven y las dependencias una sola vez |
