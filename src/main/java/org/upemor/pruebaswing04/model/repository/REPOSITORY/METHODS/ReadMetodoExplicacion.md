# ¿Qué hace el método `public T read(long id)`?

Este método permite recuperar un registro específico de la base de datos, utilizando su identificador único (`id`). Es parte fundamental de la operación **Read** en el patrón CRUD.

## Explicación paso a paso

1. **Preparar la sentencia SQL**
   - Utiliza la conexión a la base de datos (`myConnection`) para preparar la sentencia SQL de lectura (`queryRead`).
   - `queryRead` es una cadena que contiene la instrucción SQL para buscar un registro por su id.

2. **Asignar el parámetro**
   - Asigna el valor del parámetro `id` al primer parámetro de la sentencia SQL usando `statement.setLong(1, id)`.

3. **Ejecutar la consulta**
   - Ejecuta la consulta con `statement.executeQuery()`, obteniendo un `ResultSet` con los resultados.

4. **Mapear el resultado**
   - Si hay resultados, utiliza el método abstracto `objectMapping(rs)` para convertir la fila del resultado en un objeto Java de tipo T.
   - Si no hay resultados, devuelve `null`.

5. **Manejo de excepciones**
   - Si ocurre un error, imprime un mensaje detallado en la consola y vuelve a lanzar la excepción para que pueda ser manejada por el código que llama a este método.

## ¿Por qué es importante?
- Permite buscar y recuperar un registro específico de la base de datos de manera segura y reutilizable.
- Usa sentencias preparadas para evitar inyecciones SQL.
- El mapeo a objetos Java facilita el trabajo con los datos en el resto de la aplicación.
- El manejo de excepciones ayuda a la depuración y control de errores.

## Ejemplo de uso
```java
UsuarioRepository repo = new UsuarioRepository();
Usuario usuario = repo.read(1); // Recupera el usuario con id 1
if (usuario != null) {
    System.out.println("Usuario encontrado: " + usuario.getNombre());
} else {
    System.out.println("No se encontró el usuario");
}
```

---

**Resumen:**
El método `read(long id)` busca un registro por su id en la base de datos, lo convierte en un objeto Java y lo devuelve, o devuelve `null` si no existe.
