# Explicación del Constructor Repository(String tabla, long parameters)

## ¿Qué es un constructor en Java?
Un **constructor** es un método especial de una clase que se ejecuta al crear un nuevo objeto. Su función principal es inicializar los atributos y preparar el objeto para su uso.

## ¿Qué hace el constructor de Repository?
El constructor:
```java
Repository(String tabla, long parameters)
```
- Recibe el nombre de la tabla y la cantidad de parámetros que tendrá cada operación SQL.
- Inicializa la conexión a la base de datos usando la instancia Singleton de `MiConexion`.
- Llama al método `initQueries()` para construir dinámicamente las sentencias SQL (INSERT, SELECT, UPDATE, DELETE) según la tabla y el número de parámetros.
- Maneja excepciones, mostrando mensajes de error detallados si ocurre algún problema al inicializar la conexión o las sentencias.

## ¿Por qué es importante?
- Permite que cada repositorio se adapte a la entidad y tabla correspondiente.
- Automatiza la construcción de las sentencias SQL, evitando errores y facilitando el mantenimiento.
- Centraliza la lógica de inicialización, haciendo el código más limpio y reutilizable.

## Ejemplo de uso
Supón que tienes una tabla `usuarios` con 3 parámetros:
```java
UsuarioRepository repo = new UsuarioRepository("usuarios", 3);
```
Esto inicializa el repositorio para la tabla `usuarios` y prepara las sentencias SQL necesarias para operar sobre ella.

## Manejo de excepciones
El constructor incluye manejo de excepciones para:
- Fallos en la conexión a la base de datos.
- Errores al construir las sentencias SQL.
- Cualquier otro problema durante la inicialización.
Esto ayuda a detectar y reportar problemas rápidamente, facilitando la depuración.

## Resumen
El constructor de `Repository` es clave para inicializar correctamente cada repositorio, adaptándolo a la tabla y parámetros necesarios, y asegurando que la conexión y las sentencias SQL estén listas para operar.

---

**Autor:** cerimice
