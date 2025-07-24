# Clase Usuario

> **Paquete:** `org.upemor.pruebaswing04.model.entity`

## Descripción General

La clase `Usuario` representa a un usuario del sistema, almacenando sus datos personales y de acceso. Hereda de la clase base `Entity`, por lo que incluye el campo `id` como identificador único.

## Propósito y Ventajas
- **Modelo de usuario:** Permite gestionar la información relevante de cada usuario, como nombre, apellidos, correo, contraseña, fecha de nacimiento y tipo de usuario.
- **Herencia:** Aprovecha la estructura base de `Entity` para mantener consistencia en el modelo de datos.
- **Facilidad de uso:** El método `toString()` está sobrescrito para mostrar el nombre del usuario, lo que facilita su visualización en interfaces gráficas y logs.

## Detalles Técnicos
- **Campos principales:**
  - `nombre`: Nombre del usuario.
  - `apellidos`: Apellidos del usuario.
  - `email`: Correo electrónico.
  - `password`: Contraseña de acceso.
  - `fechaDeNacimiento`: Fecha de nacimiento (`LocalDate`).
  - `tipoUsuario`: Identificador del tipo de usuario (relación con `TipoUsuario`).
- **Lombok (@Getter, @Setter):**
  - Genera automáticamente los métodos get/set para todos los campos.
  - Es necesario anotarlo en cada clase, ya que Lombok no hereda estas anotaciones de la superclase.
- **toString():**
  - Sobrescribe el método de `Object` para devolver el nombre del usuario, haciendo más legible la salida en listas, combos y logs.

## Código fuente
```java
@Getter
@Setter
public class Usuario extends Entity {
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private LocalDate fechaDeNacimiento;
    private long tipoUsuario;

    @Override
    public String toString() {
        return nombre;
    }
}
```

## Ejemplo de uso
```java
Usuario usuario = new Usuario();
usuario.setNombre("Juan");
usuario.setApellidos("Pérez");
usuario.setEmail("juan.perez@mail.com");
usuario.setPassword("secreto");
usuario.setFechaDeNacimiento(LocalDate.of(1990, 5, 20));
usuario.setTipoUsuario(1);
System.out.println(usuario); // Imprime: Juan
```

## Notas
- Todas las entidades que representen usuarios deben heredar de esta clase.
- El campo `tipoUsuario` debe corresponder a un registro válido en la tabla de tipos de usuario.

## Autor
- cerimice
