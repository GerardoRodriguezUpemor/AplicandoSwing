# ¿Qué hace la clase `Controller<R extends Repository<E>, E extends Entity>`?

Esta clase abstracta define la estructura base para los controladores de la aplicación, permitiendo manejar operaciones genéricas sobre cualquier entidad (`E`) y su repositorio asociado (`R`). Utiliza generics para ser reutilizable y flexible.

## Desglose de la declaración
- **public abstract class Controller<R extends Repository<E>, E extends Entity>**: Es una clase abstracta y genérica. El tipo `R` debe ser un repositorio de la entidad `E`, y `E` debe extender de `Entity`.
- **protected R repository;**: Referencia al repositorio asociado, que se usará para las operaciones CRUD.

## Métodos clave

### Método abstracto: validate(E obj)
> Relacionado: [¿Qué es validate y por qué es abstracto?](METHODS/ABSTRACTS/ValidateMetodoExplicacion.md)
```java
protected abstract boolean validate(E obj) throws Exception;
```
- Obliga a cada subclase a definir cómo validar un objeto antes de guardarlo o actualizarlo.
- Permite que cada entidad tenga su propia lógica de validación.

### save(E obj)
- Valida el objeto usando `validate(obj)`.
- Si el id es 0, crea un nuevo registro; si no, actualiza el existente.
- Devuelve true si la operación fue exitosa.

### getAll()
- Recupera todos los registros de la entidad usando el repositorio.

## ¿Por qué es importante?
- Centraliza la lógica de control y validación para todas las entidades.
- Permite reutilizar código y mantener la aplicación organizada.
- Obliga a implementar la validación específica para cada entidad.

---

**Resumen:**
La clase `Controller` es la base para todos los controladores, permitiendo operaciones genéricas y validación personalizada para cada entidad.
