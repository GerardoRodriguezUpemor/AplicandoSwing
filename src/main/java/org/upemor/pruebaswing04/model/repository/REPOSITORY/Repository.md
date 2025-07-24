# Clase Repository<T extends Entity>

[¿Qué significa esta declaración? Explicación aquí.](AbstractRepositoryExplicacion.md)

> **Paquete:** `org.upemor.pruebaswing04.model.repository`

## Descripción General
La clase abstracta `Repository` es la base para todos los repositorios de acceso a datos en la aplicación. Implementa operaciones CRUD (crear, leer, actualizar, eliminar) genéricas para cualquier entidad que extienda de `Entity`. Utiliza JDBC para interactuar con la base de datos y define métodos abstractos para el mapeo de objetos y la configuración de sentencias SQL.

---

## Atributos

- **private MiConexion myConnection;**
  - Instancia de la clase `MiConexion` para gestionar la conexión a la base de datos.
  - Esta instancia se obtiene usando el patrón Singleton, lo que garantiza que toda la aplicación use una única conexión centralizada.
  - Para más información sobre el patrón Singleton y su uso en la gestión de conexiones JDBC, consulta:
    [Explicación del patrón Singleton](SingletonExplicacion.md)

- **protected PreparedStatement statement;**
  - Objeto JDBC para ejecutar sentencias SQL parametrizadas.
  - Los objetos JDBC (como PreparedStatement) permiten enviar comandos SQL y recibir resultados desde la base de datos de forma segura y eficiente.
  - Para más información sobre qué es un objeto JDBC y cómo se usa en Java, consulta:
    [Explicación de objetos en Java y JDBC](QUERIES/ObjetoJavaExplicacion.md)

- **protected String queryCreate, queryRead, queryReadAll, queryUpdate, queryDelete;**
  - Cadenas que almacenan las sentencias SQL para cada operación CRUD.
  - Estas sentencias son instrucciones que se envían a la base de datos para crear, leer, actualizar o eliminar datos.
  - Se inicializan en el constructor mediante el método `initQueries()`.
  - Para más información sobre qué es una sentencia SQL y cómo se usa en Java, consulta:
    [Explicación de sentencias SQL en Java](QUERIES/SentenciaSQLExplicacion.md)

---

## Constructor

### Repository(String tabla, long parameters)
- Inicializa la conexión y las sentencias SQL para la tabla y cantidad de parámetros especificados.
- Llama a `initQueries()` para construir las sentencias SQL dinámicamente.
- Maneja excepciones y muestra mensajes de error detallados.
- Para una explicación detallada sobre el funcionamiento y propósito de este constructor, consulta:
  [Explicación del constructor Repository](ConstructorRepositoryExplicacion.md)

---

## Métodos

### protected void initQueries(String tabla, long parameters)
Construye dinámicamente las sentencias SQL para las operaciones CRUD según la tabla y el número de parámetros.
Para una explicación detallada sobre el funcionamiento y propósito de este método, consulta:
[Explicación del método initQueries](QUERIES/InitQueriesExplicacion.md)
Ejemplo de sentencias generadas:
  - `INSERT INTO tabla VALUES(NULL, ?, ?, ?)`
    > Para una explicación detallada sobre esta sentencia y su uso en Java, consulta:
    > [Explicación de la sentencia INSERT INTO](QUERIES/InsertIntoExplicacion.md)
  - `SELECT * FROM tabla WHERE id IN (?)`
    > Para una explicación detallada sobre esta sentencia y su uso en Java, consulta:
    > [Explicación de la sentencia SELECT WHERE](QUERIES/SelectWhereExplicacion.md)
  - `REPLACE INTO tabla VALUES(?, ?, ?)`
    > Para una explicación detallada sobre esta sentencia y su uso en Java, consulta:
    > [Explicación de la sentencia REPLACE INTO](QUERIES/ReplaceIntoExplicacion.md)
  - `DELETE FROM tabla WHERE id IN (?)`
    > Para una explicación detallada sobre esta sentencia y su uso en Java, consulta:
    > [Explicación de la sentencia DELETE FROM](QUERIES/DeleteFromExplicacion.md)


### Métodos abstractos: ¿Por qué se usan aquí?

En una clase genérica y abstracta como `Repository`, no se conoce de antemano la estructura exacta de cada entidad (por ejemplo, Usuario, Producto, etc.). Por eso, se definen métodos abstractos que obligan a las subclases a implementar:

- **objectMapping(ResultSet rs):** cómo convertir una fila de la base de datos en un objeto Java de tipo T.  
  [Explicación detallada de este método](METHODS/AbstractRepositoryExplicacion.md)
- **setStatementParameters(PreparedStatement statement, T obj, boolean newObj):** cómo asignar los valores del objeto a los parámetros de la sentencia SQL.  
  [Explicación detallada de este método](METHODS/AbstractRepositoryExplicacion.md)

Esto permite que la lógica CRUD sea reutilizable y centralizada, pero deja flexibilidad para que cada entidad defina su propio mapeo y asignación de parámetros, garantizando así seguridad de tipos y adaptabilidad.

---

### public boolean create(T obj) throws Exception
- Inserta un nuevo registro en la base de datos usando la sentencia `queryCreate`.
- Llama a `setStatementParameters()` para asignar los valores.
- Devuelve true si la operación fue exitosa.
- Maneja y reporta excepciones.
- [Explicación detallada de este método](METHODS/CreateMetodoExplicacion.md)

### public T read(long id) throws Exception
- Recupera un registro por su id usando la sentencia `queryRead`.
- Llama a `objectMapping()` para convertir el resultado en un objeto T.
- Devuelve el objeto encontrado o null si no existe.
- Maneja y reporta excepciones.
- [Explicación detallada de este método](METHODS/ReadMetodoExplicacion.md)

### public List<T> readAll() throws Exception
- Recupera todos los registros de la tabla usando la sentencia `queryReadAll`.
- Llama a `objectMapping()` para cada registro y los agrega a una lista.
- Devuelve la lista de objetos.
- Maneja y reporta excepciones.
- [Explicación detallada de este método](METHODS/ReadAllMetodoExplicacion.md)

### public boolean update(T obj) throws Exception
- Actualiza un registro existente usando la sentencia `queryUpdate`.
- Llama a `setStatementParameters()` para asignar los valores.
- Verifica que el id del objeto sea válido (>0).
- Devuelve true si la operación fue exitosa.
- Maneja y reporta excepciones.
- [Explicación detallada de este método](METHODS/UpdateMetodoExplicacion.md)

### public boolean delete(long id) throws Exception
- Elimina un registro por su id usando la sentencia `queryDelete`.
- Devuelve true si la operación fue exitosa.
- Maneja y reporta excepciones.
- [Explicación detallada de este método](METHODS/DeleteMetodoExplicacion.md)

---

## Ejemplo de uso
```java
// Supongamos que tienes una clase Usuario que extiende Entity y un repositorio UsuarioRepository que extiende Repository<Usuario>
UsuarioRepository repo = new UsuarioRepository();
Usuario usuario = new Usuario();
usuario.setNombre("Juan");
repo.create(usuario); // Inserta un nuevo usuario
Usuario u = repo.read(1); // Lee el usuario con id 1
List<Usuario> usuarios = repo.readAll(); // Lee todos los usuarios
usuario.setNombre("Pedro");
repo.update(usuario); // Actualiza el usuario
repo.delete(usuario.getId()); // Elimina el usuario
```

---

## Notas adicionales
- Esta clase es abstracta y debe ser extendida por repositorios concretos para cada entidad.
- El uso de métodos abstractos permite personalizar el mapeo y la asignación de parámetros según la entidad.
- El manejo de excepciones incluye mensajes detallados para facilitar la depuración.
- El diseño genérico permite reutilizar la lógica CRUD para cualquier entidad del sistema.

## Autor
- cerimice
