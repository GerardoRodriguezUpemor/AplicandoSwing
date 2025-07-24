# ¿Qué hace la clase `RepositoryTipoUsuario`?

Esta clase es una implementación concreta del repositorio para la entidad `TipoUsuario`. Hereda de la clase genérica `Repository<TipoUsuario>` y define cómo mapear los datos de la base de datos a objetos `TipoUsuario`, así como cómo asignar los parámetros en las sentencias SQL.

## Explicación de los métodos clave

### Constructor
```java
public RepositoryTipoUsuario() throws Exception{
    super("tipo_usuario",2);
}
```
- Llama al constructor de la clase base `Repository` indicando el nombre de la tabla (`tipo_usuario`) y la cantidad de parámetros (2: id y nombre).

### objectMapping(ResultSet rs)
> Relacionado: [¿Qué es objectMapping y por qué es abstracto?](METHODS/ABSTRACTS/ObjectMappingExplicacion.md)
```java
@Override
protected TipoUsuario objectMapping(ResultSet rs) throws Exception{
    TipoUsuario obj = new TipoUsuario();
    obj.setId(rs.getLong("id"));
    obj.setNombre(rs.getString("nombre"));
    return obj;
}
```
- Convierte una fila del resultado de la base de datos en un objeto `TipoUsuario`.
- Extrae el `id` y el `nombre` del `ResultSet` y los asigna al objeto.

### setStatementParameters(PreparedStatement statement, TipoUsuario obj, boolean newObj)
> Relacionado: [¿Qué es setStatementParameters y por qué es abstracto?](METHODS/ABSTRACTS/SetStatementParametersExplicacion.md)
```java
@Override
protected void setStatementParameters(PreparedStatement statement, TipoUsuario obj, boolean newObj) throws Exception{
    int i=1;
    if(!newObj) statement.setLong(i++,obj.getId());
    statement.setString(i++,obj.getNombre());
}
```
- Asigna los valores del objeto `TipoUsuario` a los parámetros de la sentencia SQL.
- Si no es un objeto nuevo (`newObj == false`), asigna primero el `id` (para actualizaciones).
- Luego asigna el `nombre`.

## ¿Por qué es importante?
- Permite reutilizar la lógica CRUD genérica de `Repository` pero adaptada a la estructura de la entidad `TipoUsuario`.
- Centraliza el acceso a datos y el mapeo entre la base y los objetos Java.
- Facilita el mantenimiento y la extensión del sistema.

---

**Resumen:**
`RepositoryTipoUsuario` es el repositorio específico para la entidad `TipoUsuario`, implementando el mapeo y la asignación de parámetros necesarios para operar con la base de datos.
