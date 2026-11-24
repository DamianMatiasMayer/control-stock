# Control de Stock: definición del proyecto

## Problema

Un comercio con uno o más depósitos controla su stock a mano. Eso genera cuatro problemas:

- **Se vende más de lo que hay**, porque nadie verifica la cantidad disponible antes de registrar una salida.
- **No se sabe cuánto hay en cada depósito**: puede sobrar mercadería en uno y faltar en otro.
- **Nadie se entera a tiempo de un faltante**: cuando alguien se da cuenta, el producto ya se agotó.
- **No queda historial** de qué salió, de qué depósito, cuándo ni por qué.

## Usuarios

| Rol y Qué hace |

| **Administrador** | Gestiona categorías, productos y depósitos, define los stocks mínimos y atiende las alertas |
| **Operador** | Registra ingresos y egresos, y consulta el stock y los movimientos |

En el parcial todavía no hay login: los dos roles quedan definidos para el final.

## Alcance

### Implementado en el segundo parcial

- Consulta del stock de cada producto en cada depósito, con filtros y paginación.
- Registro de egresos, con validación en el servidor, reglas de negocio y transacción.
- Alertas automáticas de stock bajo, generadas con el patrón Observer.
- Consulta del movimiento registrado y de su alerta.
- Páginas de error propias.
- 22 existencias de demostración, con datos ficticios.
- Pruebas automatizadas con JUnit 5.

### Previsto para el examen final

- Login con roles (administrador y operador), contraseñas con BCrypt y administración de usuarios.
- CRUD de productos con baja lógica, y asignación de productos a depósitos con su stock mínimo.
- CRUD de categorías y de depósitos.
- Registro de ingresos de mercadería.
- Transferencias entre depósitos.
- Atención de alertas.
- Historial de movimientos con filtros.
- Datos persistentes al reiniciar (perfil H2 en archivo).
- Paquete ejecutable para Windows.

### Fuera de alcance

- Precios, facturación y cobros: el sistema controla cantidades, no dinero.
- Compras y proveedores.
- Varias empresas con datos separados.
- Lotes, vencimientos o seguimiento por unidad: los productos son intercambiables entre sí.

## Casos de uso

| CU | Nombre | Actor | Estado |

| CU9 | Consultar stock por depósito | Operador / Administrador | Implementado |
| CU1 | Registrar egreso de stock | Operador | Implementado |
| CU2 | Consultar movimiento registrado | Operador / Administrador | Implementado |
| CU3 | Iniciar sesión | Todos | Final |
| CU4 | CRUD de categorías | Administrador | Final |
| CU5 | CRUD de productos, con baja lógica | Administrador | Final |
| CU6 | Registrar ingreso de stock | Operador | Final |
| CU7 | Ver y atender alertas | Administrador | Final |
| CU8 | Historial de movimientos con filtros | Operador / Administrador | Final |
| CU10 | CRUD de depósitos | Administrador | Final |
| CU11 | Transferir stock entre depósitos | Operador | Final |
| CU12 | Administrar usuarios | Administrador | Final |

### CU1: Registrar egreso (flujo principal)

1. El operador abre el listado de stock (CU9) y elige una fila: un producto en un depósito.
2. Completa la cantidad y elige el motivo.
3. El sistema valida los datos (cantidad mayor a 0, motivo de la lista).
4. El sistema verifica que haya stock suficiente en ese depósito (RN1).
5. El sistema descuenta la cantidad, registra el movimiento y, si el stock quedó en el mínimo o por debajo, genera una alerta (RN2). Todo en una sola transacción.
6. El sistema muestra el movimiento registrado, leído de la base, con su alerta si la hubo (CU2).

**Flujos alternativos:**
- **3a.** Datos inválidos: se vuelve al formulario con un mensaje en cada campo.
- **4a.** Stock insuficiente: se vuelve al formulario con el mensaje *"Stock insuficiente en {depósito}: disponible {n}, solicitado {m}"*. No se guarda nada.

## Reglas de negocio

| Regla | Descripción | Estado |

| **RN1** | Un egreso no puede superar el stock disponible **en ese depósito** | ✅ |
| **RN2** | Si después del movimiento la cantidad queda en el stock mínimo o por debajo, se genera una alerta. El mínimo es un umbral de aviso: no bloquea operaciones | ✅ |
| **RN3** | La cantidad de todo movimiento debe ser mayor a 0 | ✅ |
| **RN4** | No se puede operar con productos dados de baja | Final |
| **RN5** | Un producto tiene como máximo una existencia por depósito | ✅ (restricción en la base) |
| **RN6** | El stock solo cambia a través de movimientos, para que quede en el historial | ✅ |

## Decisiones de diseño

| Decisión y Por qué |

| `Existencia` como entidad intermedia | La relación entre Producto y Depósito tiene datos propios: la cantidad y el stock mínimo. Un mismo producto puede estar en varios depósitos |
| Sin precios | El sistema controla cantidades. Lo indicó el docente |
| Sin herencia por tipo de producto | La variedad se resuelve con Categoría: un rubro nuevo no requiere programar |
| Restricciones también en la base | `unique`, `not null` y `check` frenan datos inválidos aunque falle el código |
| Movimientos y alertas sin setters | Son registros históricos: no se modifican |
| Spring Boot 4.0.9 | Spring Initializr ya no ofrece la línea 3.x. Cambio acordado con el docente |
| Una sola DTO (`MovimientoForm`) | El formulario recibe un id y un motivo de una lista, no entidades, y valida sin ensuciar el modelo |

## Sugerencias del docente incorporadas

Después de la demostración del avance:

1. **Stock bajo primero:** el listado ordena primero las existencias en el mínimo o por debajo.
2. **Filtros automáticos:** al cambiar el depósito o la casilla "Solo stock bajo", la tabla se filtra sin tocar el botón (JavaScript en `static/js/stock.js`).
3. **Motivo de una lista:** el motivo del egreso se elige de un desplegable, validado en el servidor con el enum `MotivoEgreso`.

