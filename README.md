# proyectoFinalTiendaRopa  -  API REST con Spring Boot

Este proyecto es una PI REST full desarrollada en Spring Boot para la gestión de un sistema de inventario. Permite manejar productos, sus categorías y sus atributos.

## Características

- Crea productos, categorías y atributos.
- Actualiza entidades por su ID.
- Obtene un producto, categoría o atributo por ID.
- Lista todos los productos, categorías o atributos.
- Elimina productos, categorías o atributos por ID.
- Validaciones básicas en los endpoints.


## Entidades principales
### Productos
- `id`: Identificador único.
- `nombre`: Nombre del producto.
- `stock`: Cantidad disponible.
- `precio`: Precio del producto.
- `estado`: Activo o inactivo.
- `categoriaId`: Relación con la categoría.
- Lista de atributos relacionados.

### Categorías
- `id`: Identificador único.
- `nombre`: Nombre de la categoría.

### Atributos del Producto
- `id`: Identificador único.
- `atributo`: Nombre del atributo (ej: Talla, Color).
- `caracteristicasAtributo`: Valor del atributo (ej: M, Rojo).
- `productoId`: Relación con el producto.


## Endpoints disponibles

### Productos

|  Método  |        Endpoint       |            Descripción          |
|----------|-----------------------|---------------------------------|
| `GET`    | `/api/productos`      | Lista todos los productos.      |
| `GET`    | `/api/productos/{id}` | Obtiene un producto por su ID.  |
| `POST`   | `/api/productos`      | Crea un nuevo producto.         |
| `PUT`    | `/api/productos/{id}` | Actualiza un producto existente.|
| `DELETE` | `/api/productos/{id}` | Elimina un producto por ID.     |

### Categorías

| Método |       Endpoint         |             Descripción            |
|--------|------------------------|------------------------------------|
| `GET`  | `/api/categorias` | Lista todas las categorías.             |
| `GET`  | `/api/categorias/{id}` | Obtiene una categoría por ID.      |
| `POST` | `/api/categorias` | Crea una nueva categoría.               |
| `PUT`  | `/api/categorias/{id}` | Actualiza una categoría existente. |
| `DELETE` | `/api/categorias/{id}` | Elimina una categoría por ID.    |

### Atributos del Producto

|   Método  |           Endpoint             |              Descripción                |
|-----------|--------------------------------|-----------------------------------------|
| `GET`     | `/api/atributos_producto`      | Lista todos los atributos de productos. |
| `GET`     | `/api/atributos_producto/{id}` | Obtiene un atributo por ID.             |
| `POST`    | `/api/atributos_producto`      | Crea un nuevo atributo de producto.     |
| `PUT`     | `/api/atributos_producto/{id}` | Actualiza un atributo existente.        |
| `DELETE`  | `/api/atributos_producto/{id}` | Elimina un atributo por ID.             |


### Prerrequisitos
Antes de ejecutar este proyecto, es necesario asegurse de tener instalado lo siguiente:
- Java 17 o una versión superior
- Maven
- Postman (opcional; puedes utilizar curl o HTTPie si lo prefieres)

Verificar el entorno:
Para ello es necesario abrir una terminal (cmd) y ejecutar los siguientes comandos para verificar la instalación:

java -version
mvn -version

### Ejecutar la aplicación
Desde la raíz del proyecto, donde se encuentra el archivo pom.xml, ejecutar el siguiente comando para iniciar la aplicación:

mvn spring-boot:run

Una vez iniciada, la API estará disponible en la siguiente URL:

http://localhost:8080

Utilizar Postman para realizar peticiones a los endpoints de la API utilizando esta URL como base.