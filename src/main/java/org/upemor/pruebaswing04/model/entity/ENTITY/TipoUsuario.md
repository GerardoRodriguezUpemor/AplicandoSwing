# Clase TipoUsuario

> **Paquete:** `org.upemor.pruebaswing04.model.entity`

## Descripción General

La clase `TipoUsuario` representa el tipo o rol de un usuario en el sistema (por ejemplo: Administrador, Invitado, etc.). Hereda de la clase base `Entity`, por lo que incluye el campo `id` como identificador único.

## Propósito y Ventajas
- **Categorizar usuarios:** Permite clasificar a los usuarios según su función o permisos dentro del sistema.
- **Herencia:** Aprovecha la estructura base de `Entity` para mantener consistencia en el modelo de datos.
- **Facilidad de uso:** El método `toString()` está sobrescrito para mostrar el nombre del tipo de usuario, lo que facilita su visualización en interfaces gráficas y logs.

## Detalles Técnicos
- **Campo principal:**
  - `nombre`: Nombre del tipo de usuario (por ejemplo, "Administrador").
- **Lombok (@Getter, @Setter):**
  - Genera automáticamente los métodos `getNombre()` y `setNombre()`.
  - Es necesario anotarlo en cada clase, ya que Lombok no hereda estas anotaciones de la superclase.
- **toString():**
  - Sobrescribe el método de `Object` para devolver el nombre del tipo de usuario, haciendo más legible la salida en listas, combos y logs.

## Código fuente
```java
@Getter
@Setter
public class TipoUsuario extends Entity {
    private String nombre;

    @Override
    public String toString() {
        return nombre;
    }
}
```


## Ejemplo de uso
```java
TipoUsuario tipo = new TipoUsuario();
tipo.setNombre("Administrador");
System.out.println(tipo); // Imprime: Administrador
```

### ¿Qué pasaría si no sobrescribimos el método toString()?
```java
// Si no implementas el método toString(), la salida sería algo como:
System.out.println(tipo); // Imprime: org.upemor.pruebaswing04.model.entity.TipoUsuario@6d06d69c
```
Esto muestra el nombre de la clase y un hashcode, lo cual no es útil ni legible para el usuario.

## Notas
- Todas las entidades que representen roles o tipos de usuario deben heredar de esta clase.
- El campo `nombre` debe ser único para evitar duplicidad de roles.

## Autor
- cerimice
