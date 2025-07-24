# ¿Qué es una sentencia SQL y cómo se usa en Java?

## Definición
Una **sentencia SQL** es una instrucción que se envía a la base de datos para realizar una operación, como crear, leer, actualizar o eliminar datos (CRUD). Ejemplos de sentencias SQL son:
- `INSERT INTO ...`
- `SELECT ...`
- `UPDATE ...`
- `DELETE ...`

## Sentencias SQL en Java
En Java, las sentencias SQL se almacenan como cadenas de texto (`String`) y se usan junto con objetos JDBC (como `PreparedStatement`) para interactuar con la base de datos.

Por ejemplo:
```java
String query = "SELECT * FROM usuarios WHERE id = ?";
PreparedStatement stmt = conn.prepareStatement(query);
stmt.setLong(1, 5);
ResultSet rs = stmt.executeQuery();
```

## ¿Por qué se almacenan en variables?
- Permite reutilizar la misma sentencia para diferentes operaciones.
- Facilita la construcción dinámica de consultas según la tabla y los parámetros.
- Mejora la organización y el mantenimiento del código.

## En el contexto de Repository
En la clase `Repository`, las sentencias SQL para cada operación CRUD se almacenan en variables como `queryCreate`, `queryRead`, `queryReadAll`, `queryUpdate`, `queryDelete`. Esto permite que cada repositorio tenga sus propias consultas adaptadas a la entidad y tabla correspondiente.

## Ventajas
- **Flexibilidad:** Puedes modificar o construir las sentencias según la necesidad de cada entidad.
- **Seguridad:** Usar `PreparedStatement` junto con sentencias parametrizadas ayuda a prevenir inyecciones SQL.
- **Mantenimiento:** Centralizar las sentencias facilita cambios futuros y depuración.

## Resumen
Las sentencias SQL son el puente entre tu aplicación Java y la base de datos. Al almacenarlas en variables y usarlas con JDBC, puedes realizar operaciones CRUD de forma segura, flexible y eficiente.

---

**Autor:** cerimice
