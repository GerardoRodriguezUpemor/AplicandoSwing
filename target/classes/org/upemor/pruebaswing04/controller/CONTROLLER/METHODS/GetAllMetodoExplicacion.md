# ¿Qué hace el método `public List<E> getAll()`?

Este método permite recuperar todos los registros de la entidad `E` desde la base de datos, devolviéndolos como una lista de objetos. Es una operación común en los controladores para mostrar o procesar todos los datos de una tabla.

## Explicación paso a paso

1. **Llamar al repositorio**
   - Utiliza el método `readAll()` del repositorio asociado para obtener todos los registros de la entidad.
   - Devuelve la lista de objetos obtenida.

2. **Manejo de excepciones**
   - Si ocurre un error durante la consulta, imprime un mensaje detallado y vuelve a lanzar la excepción para que pueda ser manejada por el código que llama a este método.

## ¿Por qué es importante?
- Centraliza la lógica para obtener todos los registros de una entidad.
- Facilita la reutilización y el mantenimiento del código.
- Permite mostrar o procesar todos los datos de una tabla de manera sencilla.

## Ejemplo de uso
```java
UsuarioController controller = new UsuarioController();
List<Usuario> usuarios = controller.getAll();
for (Usuario usuario : usuarios) {
    System.out.println(usuario.getNombre());
}
```

---

**Resumen:**
El método `getAll()` recupera todos los registros de la entidad desde la base de datos y los devuelve como una lista, facilitando el acceso y manejo de los datos en la aplicación.
