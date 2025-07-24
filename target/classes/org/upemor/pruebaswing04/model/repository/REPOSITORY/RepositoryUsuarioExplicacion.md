# ¿Qué hace la clase `RepositoryUsuario`?

Esta clase es una implementación concreta del repositorio para la entidad `Usuario`. Hereda de la clase genérica `Repository<Usuario>` y define cómo mapear los datos de la base de datos a objetos `Usuario`, así como cómo asignar los parámetros en las sentencias SQL.

## Explicación de los métodos clave

### Constructor
```java
public RepositoryUsuario() throws Exception{
    super("usuario",7);
}
```
- Llama al constructor de la clase base `Repository` indicando el nombre de la tabla (`usuario`) y la cantidad de parámetros (7: id, nombre, apellidos, email, password, fecha_de_nacimiento, tipo_usuario).

### objectMapping(ResultSet rs)
> Relacionado: [¿Qué es objectMapping y por qué es abstracto?](METHODS/ABSTRACTS/ObjectMappingExplicacion.md)
```java
@Override
protected Usuario objectMapping(ResultSet rs) throws Exception{
    Usuario obj = new Usuario();
    obj.setId(rs.getLong("id"));
    obj.setNombre(rs.getString("nombre"));
    obj.setApellidos(rs.getString("apellidos"));
    obj.setEmail(rs.getString("email"));
    obj.setPassword(rs.getString("password"));
    obj.setFechaDeNacimiento(dateToLocaDate(rs.getDate("fecha_de_nacimiento")));
    obj.setTipoUsuario(rs.getLong("tipo_usuario"));
    return obj;
}
```
- Convierte una fila del resultado de la base de datos en un objeto `Usuario`.
- Extrae todos los campos relevantes del `ResultSet` y los asigna al objeto.
- Utiliza un método auxiliar para convertir la fecha SQL a `LocalDate`.

### setStatementParameters(PreparedStatement statement, Usuario obj, boolean newObj)
> Relacionado: [¿Qué es setStatementParameters y por qué es abstracto?](METHODS/ABSTRACTS/SetStatementParametersExplicacion.md)
```java
@Override
protected void setStatementParameters(PreparedStatement statement, Usuario obj, boolean newObj) throws Exception{
    int i=1;
    if(!newObj) statement.setLong(i++,obj.getId());
    statement.setString(i++,obj.getNombre());
    statement.setString(i++,obj.getApellidos());
    statement.setString(i++,obj.getEmail());
    statement.setString(i++,obj.getPassword());
    statement.setString(i++,obj.getFechaDeNacimiento().format(DateTimeFormatter.ISO_DATE));
    statement.setLong(i++,obj.getTipoUsuario());
}
```
- Asigna los valores del objeto `Usuario` a los parámetros de la sentencia SQL.
- Si no es un objeto nuevo (`newObj == false`), asigna primero el `id` (para actualizaciones).
- Luego asigna el resto de los campos en el orden correspondiente.

## ¿Por qué es importante?
- Permite reutilizar la lógica CRUD genérica de `Repository` pero adaptada a la estructura de la entidad `Usuario`.
- Centraliza el acceso a datos y el mapeo entre la base y los objetos Java.
- Facilita el mantenimiento y la extensión del sistema.

---

**Resumen:**
`RepositoryUsuario` es el repositorio específico para la entidad `Usuario`, implementando el mapeo y la asignación de parámetros necesarios para operar con la base de datos.
