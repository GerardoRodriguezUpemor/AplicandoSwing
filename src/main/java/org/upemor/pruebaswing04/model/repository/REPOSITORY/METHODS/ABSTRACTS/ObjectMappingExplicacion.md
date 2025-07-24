# ¿Qué hace el método abstracto `objectMapping(ResultSet rs)`?

Este método abstracto obliga a cada subclase de `Repository` a definir cómo convertir una fila de la base de datos (representada por un `ResultSet`) en un objeto Java de tipo T (la entidad correspondiente).

## ¿Por qué es abstracto?
- Cada entidad (por ejemplo, Usuario, Producto, etc.) tiene una estructura de datos diferente.
- El mapeo de los datos de la base a los atributos del objeto Java depende de la entidad concreta.
- Al ser abstracto, fuerza a que cada repositorio concreto implemente su propia lógica de conversión.

## ¿Cómo se implementa?
La subclase debe sobrescribir este método y extraer los datos del `ResultSet` para crear y devolver un objeto de la entidad correspondiente.

### Ejemplo de implementación para un Usuario
```java
@Override
protected Usuario objectMapping(ResultSet rs) throws Exception {
    Usuario usuario = new Usuario();
    usuario.setId(rs.getLong("id"));
    usuario.setNombre(rs.getString("nombre"));
    usuario.setEmail(rs.getString("email"));
    // ...otros campos...
    return usuario;
}
```

## ¿Por qué es importante?
- Permite convertir los datos crudos de la base en objetos Java listos para usar en la aplicación.
- Centraliza y personaliza el proceso de mapeo para cada entidad.
- Hace que la lógica CRUD sea genérica y reutilizable, pero flexible para cada tipo de dato.

---

**Resumen:**
El método abstracto `objectMapping(ResultSet rs)` define la obligación de transformar una fila de la base de datos en un objeto Java, adaptándose a la estructura de cada entidad concreta.
