# ¿Qué hace el método `public List<T> readAll()`?

Este método permite recuperar todos los registros de una tabla de la base de datos y devolverlos como una lista de objetos de tipo T. Es la implementación de la operación **Read All** en el patrón CRUD.

## Explicación paso a paso

1. **Preparar la sentencia SQL**
   - Utiliza la conexión a la base de datos (`myConnection`) para preparar la sentencia SQL de lectura de todos los registros (`queryReadAll`).
   - `queryReadAll` es una cadena que contiene la instrucción SQL para seleccionar todos los registros de la tabla.

2. **Ejecutar la consulta**
   - Ejecuta la consulta con `statement.executeQuery()`, obteniendo un `ResultSet` con todos los resultados.

3. **Mapear los resultados**
   - Recorre cada fila del `ResultSet` usando un bucle `while(rs.next())`.
   - Para cada fila, utiliza el método abstracto `objectMapping(rs)` para convertirla en un objeto Java de tipo T.
   - Agrega cada objeto a una lista (`List<T> list`).

4. **Devolver la lista**
   - Devuelve la lista completa de objetos encontrados en la tabla.

5. **Manejo de excepciones**
   - Si ocurre un error, imprime un mensaje detallado en la consola y vuelve a lanzar la excepción para que pueda ser manejada por el código que llama a este método.

## ¿Por qué es importante?
- Permite obtener todos los registros de una tabla de forma sencilla y reutilizable.
- Usa sentencias preparadas para mayor seguridad y eficiencia.
- El mapeo a objetos Java facilita el trabajo con los datos en la aplicación.
- El manejo de excepciones ayuda a la depuración y control de errores.

## Ejemplo de uso
```java
UsuarioRepository repo = new UsuarioRepository();
List<Usuario> usuarios = repo.readAll();
for (Usuario usuario : usuarios) {
    System.out.println(usuario.getNombre());
}
```

---

**Resumen:**
El método `readAll()` recupera todos los registros de la tabla asociada, los convierte en objetos Java y los devuelve en una lista.
