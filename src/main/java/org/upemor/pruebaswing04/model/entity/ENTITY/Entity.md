# Clase Entity

> **Paquete:** `org.upemor.pruebaswing04.model.entity`

## Descripción General

La clase `Entity` es la base para todas las entidades del modelo de datos en la aplicación. Su principal función es proporcionar un campo `id` común a todas las entidades, que representa el identificador único (clave primaria) en la base de datos. Además, utiliza las anotaciones de Lombok para generar automáticamente los métodos getter y setter, facilitando el acceso y modificación del campo `id`.

## Propósito y Ventajas
- **Reutilización:** Permite que todas las entidades hereden el campo `id`, evitando duplicación de código.
- **Herencia:** Facilita la gestión de herencia en el modelo de datos, ya que todas las entidades comparten una estructura base.
- **Mantenimiento:** Si se requiere cambiar la lógica del identificador, solo se modifica en un lugar.
- **Compatibilidad con frameworks:** Es una práctica común en frameworks ORM (como Hibernate o JPA) tener una clase base con el identificador.

## Código fuente
```java
@Getter
@Setter
public class Entity {
    /**
     * Identificador único de la entidad. Corresponde a la clave primaria en la base de datos.
     */
    protected long id;
}
```

## Detalles Técnicos
- **Lombok (@Getter, @Setter):** Estas anotaciones generan automáticamente los métodos `getId()` y `setId()`, eliminando la necesidad de escribirlos manualmente.
- **Visibilidad:** El campo `id` es `protected`, lo que permite que las subclases accedan directamente a él si es necesario.
- **Uso:** Todas las entidades del sistema deben heredar de esta clase para asegurar que tengan un identificador único.

## Ejemplo de uso
```java
public class Usuario extends Entity {
    private String nombre;
    // ...otros campos y métodos...
}

Usuario usuario = new Usuario();
usuario.setId(1);
usuario.setNombre("Juan");
System.out.println(usuario.getId()); // Imprime: 1
```

## Autor
- cerimice
