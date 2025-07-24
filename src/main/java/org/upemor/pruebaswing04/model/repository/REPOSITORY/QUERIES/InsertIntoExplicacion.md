# Diferencia entre `INSERT INTO tabla VALUES(NULL, ...)` y el método genérico

Existen dos formas comunes de escribir una sentencia SQL para insertar registros:

1. **Tu método (usado en el repositorio):**
   ```sql
   INSERT INTO tabla VALUES(NULL, ?, ?, ?)
   ```
   - El primer valor `NULL` corresponde a la columna `id` (clave primaria autoincremental).
   - Así, la base de datos genera automáticamente el valor del `id` al insertar el registro.
   - Es útil cuando el orden de los valores coincide exactamente con el orden de las columnas en la tabla.

2. **Método genérico (más flexible):**
   ```sql
   INSERT INTO tabla (col1, col2, col3) VALUES (?, ?, ?)
   ```
   - Aquí se especifican los nombres de las columnas y no se incluye el `id` porque la base de datos lo autogenera.
   - Es más seguro y flexible, especialmente si el orden de las columnas puede cambiar o si no quieres insertar en todas las columnas.

**¿Por qué se usa la versión con `NULL` en tu código?**
- Porque la tabla tiene una columna autoincremental (`id`) y quieres que la base de datos asigne ese valor automáticamente.
- Es más simple si siempre insertas en todas las columnas y el orden es fijo.

**¿Cuándo usar el método genérico?**
- Cuando quieres insertar solo en algunas columnas, o el orden de las columnas puede variar.
- Es la forma recomendada en proyectos grandes o cuando la estructura de la tabla puede cambiar.

---

# Explicación de la sentencia SQL: INSERT INTO tabla VALUES(NULL, ?, ?, ?)

## ¿Qué hace esta sentencia?
Esta instrucción SQL se utiliza para **insertar un nuevo registro** en una tabla de la base de datos. El primer valor (`NULL`) suele corresponder a la columna `id` o clave primaria, que normalmente es autoincremental. Los signos de interrogación (`?`) son **parámetros** que se reemplazan por los valores reales al ejecutar la consulta desde Java usando JDBC.

## Estructura
- `INSERT INTO tabla` indica que se va a insertar un registro en la tabla especificada.
- `VALUES(NULL, ?, ?, ?)` define los valores a insertar:
  - `NULL`: El valor para la columna `id` (autoincremental).
  - `?`: Cada signo de interrogación representa un valor que se asigna mediante un método como `setString`, `setInt`, etc., en un `PreparedStatement`.

## ¿Por qué usar parámetros (`?`)?
- Permiten construir consultas seguras y evitar inyecciones SQL.
- Facilitan la reutilización de la sentencia para diferentes valores.
- Mejoran el rendimiento al permitir que la base de datos optimice la ejecución.

## Ejemplo en Java
```java
String query = "INSERT INTO usuarios VALUES(NULL, ?, ?, ?)";
PreparedStatement stmt = conn.prepareStatement(query);
stmt.setString(1, "Juan");
stmt.setString(2, "juan@email.com");
stmt.setInt(3, 25);
stmt.executeUpdate();
```

## Resumen
Esta sentencia es fundamental para agregar nuevos datos a la base de datos de forma segura y eficiente, aprovechando las ventajas de los parámetros en JDBC.

---

**Autor:** cerimice
