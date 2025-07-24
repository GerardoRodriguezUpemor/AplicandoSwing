# ¿Qué es `public abstract class Repository<T extends Entity>`?

Esta línea declara una clase abstracta y genérica en Java llamada `Repository`, que sirve como base para todos los repositorios de acceso a datos en la aplicación. A continuación se explica cada parte:

## Desglose de la declaración

- **public**: La clase es pública, es decir, puede ser utilizada desde cualquier otro paquete.
- **abstract**: La clase es abstracta, lo que significa que no se puede instanciar directamente. Debe ser extendida por otras clases que implementen sus métodos abstractos.
- **class Repository**: Define el nombre de la clase.
- **<T extends Entity>**: Es un parámetro de tipo genérico. Indica que la clase trabajará con un tipo `T`, el cual debe ser una subclase de `Entity`. Esto permite reutilizar la lógica del repositorio para cualquier entidad del sistema (por ejemplo, Usuario, Producto, etc.).


## ¿Por qué se usan métodos abstractos en esta clase?

Al ser una clase genérica y abstracta, `Repository<T extends Entity>` no conoce la estructura interna de cada entidad concreta (por ejemplo, Usuario, Producto, etc.). Por eso, define métodos abstractos que deben ser implementados por cada subclase:

- **objectMapping(ResultSet rs):** Este método obliga a cada subclase a definir cómo convertir una fila del resultado de la base de datos en un objeto Java de tipo T. Cada entidad puede tener diferentes atributos y lógica de mapeo.  
  [Explicación detallada de este método](ABSTRACTS/ObjectMappingExplicacion.md)
- **setStatementParameters(PreparedStatement statement, T obj, boolean newObj):** Este método obliga a cada subclase a definir cómo asignar los valores del objeto a los parámetros de la sentencia SQL. Así, cada entidad puede controlar cómo se insertan o actualizan sus datos en la base.  
  [Explicación detallada de este método](ABSTRACTS/SetStatementParametersExplicacion.md)

Esto permite que la lógica general de acceso a datos (CRUD) sea reutilizable y centralizada, pero deja flexibilidad para que cada entidad defina su propio mapeo y asignación de parámetros, garantizando así seguridad de tipos, adaptabilidad y evitando duplicación de código.

**Ventajas:**
- Permite que cada entidad tenga su propio comportamiento específico sin perder la estructura común.
- Obliga a implementar los métodos clave para el funcionamiento correcto del repositorio.
- Facilita la extensión y el mantenimiento del sistema.

## Ventajas de usar una clase abstracta y genérica
- **Reutilización de código**: Evita duplicar la lógica CRUD para cada entidad.
- **Centralización**: Toda la lógica de acceso a datos está en un solo lugar.
- **Flexibilidad**: Permite personalizar el comportamiento para cada entidad mediante la herencia.
- **Seguridad de tipos**: El uso de genéricos garantiza que solo se trabajará con entidades válidas.

## Ejemplo de uso
```java
public class UsuarioRepository extends Repository<Usuario> {
    // Implementa los métodos abstractos para Usuario
}
```

En este ejemplo, `UsuarioRepository` hereda toda la funcionalidad genérica de `Repository` y solo necesita implementar los métodos específicos para la entidad `Usuario`.

---

**Resumen:**
La línea `public abstract class Repository<T extends Entity>` define una plantilla flexible y reutilizable para crear repositorios de datos en Java, facilitando la organización y el mantenimiento del código en aplicaciones que usan bases de datos.
