# ¿Qué hace el método abstracto `setStatementParameters(PreparedStatement statement, T obj, boolean newObj)`?

Este método abstracto obliga a cada subclase de `Repository` a definir cómo asignar los valores de un objeto de tipo T a los parámetros de una sentencia SQL (`PreparedStatement`).

## ¿Por qué es abstracto?
- Cada entidad (por ejemplo, Usuario, Producto, etc.) tiene diferentes atributos y, por lo tanto, diferentes parámetros que deben asignarse en las sentencias SQL.
- El proceso de asignación depende de la estructura de la entidad y del tipo de operación (inserción o actualización).
- Al ser abstracto, fuerza a que cada repositorio concreto implemente su propia lógica de asignación de parámetros.

## ¿Cómo se implementa?
La subclase debe sobrescribir este método y usar los métodos de `PreparedStatement` (como `setString`, `setLong`, etc.) para asignar los valores del objeto a los parámetros de la sentencia SQL.

### Ejemplo de implementación para un Usuario
```java
@Override
protected void setStatementParameters(PreparedStatement statement, Usuario usuario, boolean newObj) throws Exception {
    int index = 1;
    if (!newObj) {
        statement.setLong(index++, usuario.getId());
    }
    statement.setString(index++, usuario.getNombre());
    statement.setString(index++, usuario.getEmail());
    // ...otros campos...
}
```
- El parámetro `newObj` indica si se trata de una inserción (`true`) o una actualización (`false`). Esto puede afectar el orden o la cantidad de parámetros asignados.

## ¿Por qué es importante?
- Permite que cada entidad controle cómo se insertan o actualizan sus datos en la base de datos.
- Centraliza y personaliza el proceso de asignación de parámetros para cada entidad.
- Hace que la lógica CRUD sea genérica y reutilizable, pero flexible para cada tipo de dato.

---

**Resumen:**
El método abstracto `setStatementParameters(PreparedStatement statement, T obj, boolean newObj)` define la obligación de asignar los valores de un objeto a los parámetros de una sentencia SQL, adaptándose a la estructura y lógica de cada entidad concreta.
