# Explicación del método initQueries(String tabla, long parameters)

## ¿Qué hace este método?
El método `initQueries` se encarga de construir dinámicamente las sentencias SQL necesarias para realizar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre una tabla específica de la base de datos.

## Parámetros
- **tabla**: El nombre de la tabla sobre la que se van a ejecutar las operaciones.
- **parameters**: La cantidad de parámetros (columnas) que tiene la tabla, para ajustar las sentencias SQL.

## ¿Cómo funciona?
- Construye la sentencia de **inserción** (`INSERT INTO ...`) agregando los signos de interrogación (`?`) necesarios para cada columna.
- Construye la sentencia de **lectura** (`SELECT ...`) para obtener registros por id y para obtener todos los registros.
- Construye la sentencia de **actualización** (`REPLACE INTO ...`) ajustando los parámetros.
- Construye la sentencia de **eliminación** (`DELETE ...`) para borrar registros por id.

## Ejemplo de sentencias generadas
Supón que la tabla tiene 3 columnas:
- `INSERT INTO tabla VALUES(NULL, ?, ?, ?)`
- `SELECT * FROM tabla WHERE id IN (?)`
- `REPLACE INTO tabla VALUES(?, ?, ?)`
- `DELETE FROM tabla WHERE id IN (?)`

## ¿Por qué es útil?
- Permite adaptar las sentencias SQL a cualquier tabla y cantidad de columnas sin escribirlas manualmente.
- Facilita la reutilización y el mantenimiento del código.
- Evita errores al construir las sentencias SQL.

## Resumen
El método `initQueries` automatiza la creación de las sentencias SQL para cada operación CRUD, haciendo que el repositorio sea flexible y adaptable a cualquier entidad y tabla de la base de datos.

---

**Autor:** cerimice
