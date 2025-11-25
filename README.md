# Sistema de Gestión Comercial

Proyecto final desarrollado con Spring Boot y JPA para el grupo de trabajo ACM WebDev Java Spring. Sistema REST API completo para la gestión de operaciones comerciales incluyendo productos, almacenes, ventas y usuarios.

## Descripción del Proyecto

Sistema de gestión comercial que permite administrar el flujo completo de operaciones comerciales: desde el control de inventarios en múltiples almacenes, gestión de productos con categorías, hasta el registro y seguimiento de ventas con sus respectivos detalles. El sistema implementa una arquitectura REST con Spring Boot, persistencia con JPA/Hibernate y PostgreSQL como base de datos.

## Características Principales

- API REST completa con operaciones CRUD para todas las entidades
- 14 consultas avanzadas personalizadas con filtros y búsquedas complejas
- Arquitectura en capas (Controller, Service, Repository, Entity)
- Persistencia con JPA/Hibernate y PostgreSQL
- Documentación interactiva con Swagger/OpenAPI
- Relaciones entre entidades (ManyToOne, ManyToMany)
- Dataset de prueba precargado
- Queries personalizadas con JPQL

## Tecnologías Utilizadas

| Tecnología | Versión |
|------------|---------|
| Java | 24.0.1 |
| Spring Boot | 4.0.0 |
| Spring Data JPA | 4.0.0 |
| PostgreSQL | 13.23 |
| Hibernate | 7.1.8.Final |
| Maven | 3.9.11 |
| Lombok | 1.18.42 |
| Springdoc OpenAPI | 2.6.0 |

## Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java JDK 21 o superior**
- **Maven 3.6+**
- **Docker y Docker Compose** (para levantar PostgreSQL)
- **Git** (para clonar el repositorio)

## Instalación y Ejecución

### 1. Clonar el Repositorio

```bash
git clone https://github.com/Edd022/ComercialManagement.git
cd ComercialManagement/gestioncomercial
```

### 2. Levantar la Base de Datos

El proyecto incluye un archivo `docker-compose.yml` para levantar PostgreSQL fácilmente:

```bash
docker-compose up -d
```

Esto creará un contenedor con PostgreSQL en el puerto 5433 con las siguientes credenciales:
- Usuario: `myuser`
- Contraseña: `mypassword`
- Base de datos: `acmdb`

### 3. Ejecutar la Aplicación

```bash
./mvnw spring-boot:run
```

O si estás en Windows:

```bash
mvnw.cmd spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

### 4. Cargar el Dataset de Prueba

El proyecto incluye un archivo `dataset.sql` con datos de prueba. Para cargarlo:

```bash
psql -h localhost -p 5433 -U myuser -d acmdb -f dataset.sql
```

Alternativamente, la aplicación está configurada con `spring.jpa.hibernate.ddl-auto=update`, por lo que las tablas se crearán automáticamente al iniciar.

## Estructura del Proyecto

```
gestioncomercial/
├── src/
│   ├── main/
│   │   ├── java/com/acmspring/co/gestioncomercial/
│   │   │   ├── controller/          # Controladores REST
│   │   │   ├── entity/               # Entidades JPA
│   │   │   ├── repository/           # Repositorios JPA
│   │   │   ├── services/             # Capa de servicios
│   │   │   └── enums/                # Enumeraciones
│   │   └── resources/
│   │       └── application.properties # Configuración
│   └── test/                          # Tests de integración
├── dataset.sql                        # Datos de prueba
├── docker-compose.yml                 # Configuración de PostgreSQL
└── pom.xml                            # Dependencias Maven
```

## Modelo de Datos

### Entidades Principales

El sistema cuenta con 10 entidades JPA completamente mapeadas:

#### 1. **Usuario (UsuarioEntity)**
- Gestión de usuarios del sistema
- Relación con Rol y Ciudad
- Campos: nombre, apellido, email, teléfono, username, password

#### 2. **Rol Usuario (RolUsuarioEntity)**
- Roles del sistema (ADMIN, USER, MODERATOR)
- Relación OneToMany con Usuario

#### 3. **Departamento (DepartamentoEntity)**
- Organización geográfica de primer nivel
- Relación OneToMany con Ciudad

#### 4. **Ciudad (Ciudad)**
- Ciudades asociadas a departamentos
- Relación ManyToOne con Departamento

#### 5. **Producto (ProductoEntity)**
- Catálogo de productos
- Relación ManyToMany con Categoría
- Campos: nombre, precio, descripción, fechas de creación/modificación

#### 6. **Categoría (CategoriaEntity)**
- Categorización de productos
- Relación ManyToMany con Producto

#### 7. **Almacén (AlmacenEntity)**
- Almacenes físicos
- Relación con Ciudad
- Gestión de inventario por ubicación

#### 8. **Almacén Producto (AlmacenProductoEntity)**
- Tabla intermedia para stock por almacén
- Relación ManyToOne con Almacén y Producto
- Campo: stock (cantidad disponible)

#### 9. **Venta (VentaEntity)**
- Registro de ventas realizadas
- Relación ManyToOne con Usuario (cliente)
- Campo: fecha de venta

#### 10. **Venta Producto (VentaProductoEntity)**
- Detalle de productos vendidos
- Relación ManyToOne con Venta y Producto
- Campo: cantidad vendida

## API REST - Endpoints

### Operaciones CRUD Estándar

Todas las entidades cuentan con endpoints CRUD completos:

```
GET    /api/{entidad}           # Listar todos
GET    /api/{entidad}/{id}      # Obtener por ID
POST   /api/{entidad}           # Crear nuevo
PUT    /api/{entidad}/{id}      # Actualizar
DELETE /api/{entidad}/{id}      # Eliminar
```

Donde `{entidad}` puede ser:
- `usuario`
- `rol-usuario`
- `departamento`
- `ciudad`
- `producto`
- `categoria`
- `almacen`
- `almacen-producto`
- `venta`
- `venta-producto`

### Consultas Avanzadas

El sistema implementa 14 consultas personalizadas con filtros y búsquedas complejas:

#### Consultas de Usuario

```http
GET /api/usuario/apellido/{apellido}
# Busca usuarios por apellido exacto

GET /api/usuario/ciudad/{ciudadId}
# Lista usuarios de una ciudad específica

GET /api/usuario/departamento/{departamentoId}
# Lista usuarios por departamento (incluye todas las ciudades del departamento)

GET /api/usuario/buscar?texto={texto}
# Búsqueda por texto en el nombre (case-insensitive)
```

#### Consultas de Producto

```http
GET /api/producto/categoria/{categoriaId}
# Lista productos de una categoría específica

GET /api/producto/precio?precioMin={min}&precioMax={max}
# Busca productos en un rango de precio

GET /api/producto/ordenar-precio?orden={asc|desc}
# Ordena productos por precio (ascendente o descendente)

GET /api/producto/fecha-desde?fecha={fecha}
# Lista productos creados después de una fecha (formato ISO 8601)

GET /api/producto/mas-vendidos
# Lista productos ordenados por cantidad total vendida
```

#### Consultas de Almacén

```http
GET /api/almacen-producto/almacen/{almacenId}
# Lista todos los productos con stock de un almacén

GET /api/almacen/ciudad/{ciudadId}
# Lista almacenes ubicados en una ciudad específica
```

#### Consultas de Ventas

```http
GET /api/venta/cliente/{clienteId}
# Lista todas las ventas de un cliente

GET /api/venta/total-por-fecha?fecha={fecha}
# Calcula el total vendido en una fecha específica

GET /api/venta/monto-mayor?monto={valor}
# Lista ventas cuyo monto total supera el valor especificado
```

## Documentación con Swagger

El proyecto incluye documentación interactiva completa generada con Swagger/OpenAPI. **Esta documentación reemplaza el PDF solicitado originalmente**, ofreciendo una experiencia mucho más completa y práctica.

### Acceder a Swagger UI

Una vez la aplicación esté corriendo, accede a:

```
http://localhost:8080/swagger-ui.html
```

Swagger UI proporciona:

- **Documentación completa de todos los endpoints**
- **Interfaz interactiva para probar las APIs** directamente desde el navegador
- **Esquemas de request y response** para cada endpoint
- **Ejemplos de uso** con datos de prueba
- **Códigos de respuesta HTTP** documentados
- **Modelos de datos** con todas las propiedades de las entidades

### Ventajas de Swagger sobre PDF

- Documentación siempre actualizada automáticamente
- Posibilidad de ejecutar requests en tiempo real
- Validación de datos en vivo
- Exploración interactiva de la API
- Generación de código cliente en múltiples lenguajes

## Repositorios y Consultas Personalizadas

### Implementación de Queries

Todos los repositorios extienden `JpaRepository` y algunos incluyen queries personalizadas con JPQL:

**Ejemplo - UsuarioRepository:**

```java
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    
    // Query derivada automática
    List<UsuarioEntity> findByApellido(String apellido);
    
    // Query JPQL personalizada
    @Query("SELECT u FROM UsuarioEntity u WHERE u.ciudad.id = :ciudadId")
    List<UsuarioEntity> findByCiudadId(@Param("ciudadId") Long ciudadId);
    
    // Query con navegación de relaciones
    @Query("SELECT u FROM UsuarioEntity u WHERE u.ciudad.departamento.id = :departamentoId")
    List<UsuarioEntity> findByDepartamentoId(@Param("departamentoId") Long departamentoId);
}
```

**Ejemplo - ProductoRepository:**

```java
public interface ProductoRepository extends JpaRepository<ProductoEntity, Long> {
    
    // Query con JOIN
    @Query("SELECT p FROM CategoriaEntity c JOIN c.productos p WHERE c.Id = :categoriaId")
    List<ProductoEntity> findByCategoriaId(@Param("categoriaId") Integer categoriaId);
    
    // Query derivada con rango
    List<ProductoEntity> findByPrecioBetween(Double precioMin, Double precioMax);
    
    // Query derivada con ordenamiento
    List<ProductoEntity> findAllByOrderByPrecioDesc();
}
```

**Ejemplo - VentaRepository:**

```java
public interface VentaRepository extends JpaRepository<VentaEntity, Long> {
    
    // Query con agregación
    @Query("SELECT vp.producto, SUM(vp.cantidad) as total FROM VentaProductoEntity vp " +
           "GROUP BY vp.producto ORDER BY total DESC")
    List<Object[]> findProductosMasVendidos();
    
    // Query con función de fecha
    @Query("SELECT DATE(v.fechaVenta), SUM(vp.cantidad * vp.producto.precio) " +
           "FROM VentaEntity v JOIN VentaProductoEntity vp ON v.id = vp.venta.id " +
           "WHERE DATE(v.fechaVenta) = DATE(:fecha) GROUP BY DATE(v.fechaVenta)")
    List<Object[]> findTotalVendidoPorFecha(@Param("fecha") LocalDateTime fecha);
}
```

## Arquitectura del Proyecto

### Patrón de Capas

El proyecto sigue una arquitectura en capas bien definida:

```
┌─────────────────────────┐
│   Controller Layer      │  <- REST API Endpoints
├─────────────────────────┤
│   Service Layer         │  <- Lógica de negocio
├─────────────────────────┤
│   Repository Layer      │  <- Acceso a datos (JPA)
├─────────────────────────┤
│   Entity Layer          │  <- Modelos de datos
├─────────────────────────┤
│   Database (PostgreSQL) │  <- Persistencia
└─────────────────────────┘
```

### Separación de Responsabilidades

- **Controllers**: Manejan las peticiones HTTP y respuestas
- **Services**: Contienen la lógica de negocio y validaciones
- **Repositories**: Abstraen el acceso a datos con Spring Data JPA
- **Entities**: Representan el modelo de dominio y mapeo ORM

## Configuración de la Base de Datos

La configuración se encuentra en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5433/acmdb
spring.datasource.username=myuser
spring.datasource.password=mypassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

### Estrategia de Generación de Schema

El proyecto usa `ddl-auto=update` que:
- Crea las tablas automáticamente si no existen
- Actualiza el schema cuando se modifican las entidades
- Preserva los datos existentes
- Es ideal para desarrollo

## Dataset de Prueba

El archivo `dataset.sql` incluye datos de prueba para todas las entidades:

- 3 Departamentos (Antioquia, Cundinamarca, Valle del Cauca)
- 5 Ciudades distribuidas por departamento
- 4 Usuarios con diferentes roles
- 6 Productos en múltiples categorías
- 2 Almacenes con inventario
- 2 Ventas con productos asociados

## Ejemplos de Uso

### Crear un Producto

```bash
curl -X POST http://localhost:8080/api/producto \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Laptop HP",
    "precio": 899.99,
    "descripcion": "Laptop empresarial",
    "fechaCreacion": "2025-11-24T10:00:00"
  }'
```

### Buscar Productos por Precio

```bash
curl "http://localhost:8080/api/producto/precio?precioMin=100&precioMax=500"
```

### Listar Ventas de un Cliente

```bash
curl http://localhost:8080/api/venta/cliente/1
```

### Ver Productos Más Vendidos

```bash
curl http://localhost:8080/api/producto/mas-vendidos
```

## Testing

El proyecto incluye tests de integración que validan:

- Inicialización correcta del contexto de Spring
- Carga de todas las entidades y repositorios
- Funcionamiento de los endpoints REST

Para ejecutar los tests:

```bash
./mvnw test
```

## Notas Técnicas

### Manejo de Relaciones

- **ManyToOne**: Usuario-Ciudad, Usuario-Rol, Producto-Categoría (inversa)
- **OneToMany**: Departamento-Ciudad, Categoría-Producto
- **ManyToMany**: Producto-Categoría (con tabla intermedia `producto_categoria`)

### Generación de IDs

La mayoría de entidades usan `GenerationType.IDENTITY` para auto-incremento, excepto entidades intermedias que tienen IDs asignados manualmente.

### Lombok

El proyecto usa Lombok para reducir código boilerplate:
- `@Getter` / `@Setter`: Getters y setters automáticos
- `@Builder`: Patrón builder para construcción de objetos
- `@AllArgsConstructor` / `@NoArgsConstructor`: Constructores
- `@ToString`: Método toString() automático

## Problemas Comunes y Soluciones

### Puerto 8080 Ocupado

Si el puerto 8080 está en uso, puedes cambiarlo en `application.properties`:

```properties
server.port=8081
```

### Error de Conexión a PostgreSQL

Verifica que el contenedor Docker esté corriendo:

```bash
docker ps
```

Si no está activo, levántalo nuevamente:

```bash
docker-compose up -d
```

### Error de Autenticación en PostgreSQL

Asegúrate de usar las credenciales correctas definidas en `docker-compose.yml` y `application.properties`.

## Características Avanzadas Implementadas

- Queries JPQL personalizadas con JOINs
- Búsquedas con parámetros opcionales
- Filtros por rango de fechas y precios
- Agregaciones (SUM, COUNT, GROUP BY)
- Ordenamiento dinámico
- Búsquedas case-insensitive
- Navegación de relaciones en queries
- Validaciones con Jakarta Validation

## Autor

**Edward Julian Garcia Gaitan**

Proyecto desarrollado para el grupo de trabajo ACM WebDev Java Spring.

## Licencia

Este proyecto está licenciado bajo GPL-3.0 - ver [LICENSE](https://github.com/Edd022/ComercialManagement#GPL-3.0-1-ov-file) para más detalles.

## Recursos Adicionales

- [Documentación de Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Swagger/OpenAPI](https://swagger.io/specification/)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)

---

**Proyecto Final - Sistema de Gestión Comercial**  
ACM WebDev Java Spring  
2025

