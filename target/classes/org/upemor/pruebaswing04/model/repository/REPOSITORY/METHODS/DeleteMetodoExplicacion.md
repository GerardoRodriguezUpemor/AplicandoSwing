# ¿Qué hace el método `public boolean delete(long id)`?

Este método permite eliminar un registro específico de la base de datos, utilizando su identificador único (`id`). Es la implementación de la operación **Delete** en el patrón CRUD.

## Explicación paso a paso

1. **Preparar la sentencia SQL**
   - Utiliza la conexión a la base de datos (`myConnection`) para preparar la sentencia SQL de borrado (`queryDelete`).
   - `queryDelete` es una cadena que contiene la instrucción SQL para eliminar un registro por su id.

2. **Asignar el parámetro**
   - Asigna el valor del parámetro `id` al primer parámetro de la sentencia SQL usando `statement.setLong(1, id)`.

3. **Ejecutar la eliminación**
   - Ejecuta la sentencia SQL con `statement.executeUpdate()`, que realiza la eliminación en la base de datos.
   - El resultado es un número entero que indica cuántas filas fueron afectadas.

4. **Devolver el resultado**
   - Devuelve `true` si la operación fue exitosa (es decir, si se eliminó al menos una fila).

5. **Manejo de excepciones**
   - Si ocurre un error, imprime un mensaje detallado en la consola y vuelve a lanzar la excepción para que pueda ser manejada por el código que llama a este método.

## ¿Por qué es importante?
- Permite eliminar registros de la base de datos de manera segura y controlada.
- Usa sentencias preparadas para evitar inyecciones SQL.
- El manejo de excepciones ayuda a la depuración y control de errores.
- Al ser genérico, puede ser utilizado por cualquier entidad que extienda de `Entity`.

## Ejemplo de uso
```java
UsuarioRepository repo = new UsuarioRepository();
boolean exito = repo.delete(1); // Elimina el usuario con id 1
if (exito) {
    System.out.println("Usuario eliminado correctamente");
} else {
    System.out.println("No se pudo eliminar el usuario");
}
```

---

**Resumen:**
El método `delete(long id)` elimina un registro de la base de datos según su id, gestionando la conexión, la sentencia SQL y el manejo de errores de forma centralizada y reutilizable.
