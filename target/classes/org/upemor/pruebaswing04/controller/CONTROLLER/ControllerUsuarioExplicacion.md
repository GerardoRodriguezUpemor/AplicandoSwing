# ¿Qué hace la clase `ControllerUsuario`?

Esta clase es una implementación concreta del controlador para la entidad `Usuario`. Hereda de la clase genérica `Controller<RepositoryUsuario, Usuario>` y define la lógica de validación específica para los objetos `Usuario`.

## Explicación de los métodos clave

### Constructor
```java
public ControllerUsuario() throws Exception {
    repository = new RepositoryUsuario();
}
```
- Inicializa el repositorio específico para `Usuario`.

### validate(Usuario obj)
> Relacionado: [¿Qué es validate y por qué es abstracto?](METHODS/ABSTRACTS/ValidateMetodoExplicacion.md)
```java
@Override
protected boolean validate(Usuario obj) throws Exception {
    if (obj.getNombre().isEmpty())
        throw new Exception("El nombre no ha sido proporcionado");
    if (obj.getApellidos().isEmpty())
        throw new Exception("El apellido no ha sido proporcionado");
    if (obj.getEmail().isEmpty())
        throw new Exception("El email no ha sido proporcionado");
    if (obj.getPassword().isEmpty())
        throw new Exception("El password no ha sido proporcionado");
    if (obj.getTipoUsuario() <= 0)
        throw new Exception("El tipo de usuario no ha sido proporcionado");
    return true;
}
```
- Valida que todos los campos obligatorios estén presentes y no vacíos.
- Si falta algún campo, lanza una excepción con un mensaje descriptivo.
- Si la validación es exitosa, retorna `true`.

## ¿Por qué es importante?
- Permite reutilizar la lógica genérica de controladores, pero adaptada a las reglas de negocio de la entidad `Usuario`.
- Centraliza la validación y el acceso a datos para esta entidad.
- Facilita el mantenimiento y la extensión del sistema.

---

**Resumen:**
`ControllerUsuario` es el controlador específico para la entidad `Usuario`, implementando la validación necesaria antes de guardar o actualizar registros en la base de datos.
