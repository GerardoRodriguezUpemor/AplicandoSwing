# Ejemplo de uso de MiConexion para SQLite

Este ejemplo muestra cómo utilizar la clase `MiConexion` para conectarse a una base de datos SQLite en Java, explicando cada paso y los valores necesarios.

---

## Requisitos previos
- No necesitas instalar ni activar ningún servidor externo (como XAMPP).
- Solo necesitas el archivo de la base de datos SQLite (por ejemplo, `prueba_swing.db`).
- Agrega el driver JDBC de SQLite a tu proyecto. Si usas Maven, agrega la dependencia:

```xml
<dependency>
    <groupId>org.xerial</groupId>
    <artifactId>sqlite-jdbc</artifactId>
    <version>3.36.0.3</version>
</dependency>
```

**¿Qué significa esta dependencia?**
- Se coloca en el archivo `pom.xml` de tu proyecto Maven.
- Indica a Maven que debe descargar el driver JDBC oficial para SQLite (`sqlite-jdbc`).
- El driver es una librería que permite a Java comunicarse con archivos de base de datos SQLite usando JDBC.
- Maven descarga automáticamente el archivo `.jar` y lo agrega al classpath de tu proyecto.

---

## Ejemplo de código

```java
import org.upemor.pruebaswing04.model.repository.MiConexion;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class EjemploSQLite {
    public static void main(String[] args) {
        try {
            // Obtener la instancia única de MiConexion para SQLite
            MiConexion conexion = new MiConexion(MiConexion.SQLITE);

            // Conectar a la base de datos
            Connection conn = conexion.conectar();

            // Ejecutar una consulta de ejemplo
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios");

            // Procesar los resultados
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
            }

            // Desconectar cuando ya no se necesite
            conexion.desconectar();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```

---

## Explicación paso a paso

1. **Instancia de MiConexion**
   - Se crea una nueva instancia pasando `MiConexion.SQLITE` como parámetro.
   - Esto configura todos los atributos necesarios para conectarse a SQLite (driver, archivo de base de datos, url).

2. **Conexión a la base de datos**
   - Se llama al método `conectar()`, que carga el driver JDBC y abre la conexión usando los datos configurados.

3. **Ejecutar consultas SQL**
   - Se crea un `Statement` y se ejecuta una consulta SQL (`SELECT * FROM usuarios`).
   - Se recorren los resultados usando el objeto `ResultSet`.

4. **Desconexión**
   - Se llama a `desconectar()` para cerrar la conexión y liberar recursos.

---

## Notas importantes

- El archivo de la base de datos SQLite se crea automáticamente si no existe.
- Por defecto, se crea en la misma carpeta donde se ejecuta tu programa. Si quieres que se guarde en otra ubicación, puedes especificar una ruta en el nombre del archivo (por ejemplo, `data/prueba_swing.db`).
- No es obligatorio crear una carpeta especial, pero si especificas una ruta, asegúrate de que la carpeta exista antes de ejecutar el programa, para evitar errores.
- Este archivo almacena toda la información de la base de datos (tablas, datos, etc.) y es el "contenedor" de tu base de datos SQLite.
- No se requiere usuario ni contraseña para SQLite por defecto.
- El patrón Singleton asegura que toda la aplicación use la misma conexión.

---

## Resumen de atributos usados
| Atributo      | Valor ejemplo           | Descripción                                  |
|---------------|------------------------|-----------------------------------------------|
| driverClass   | org.sqlite.JDBC        | Clase del driver JDBC para SQLite             |
| baseDeDatos   | prueba_swing.db        | Archivo de la base de datos SQLite            |
| url           | jdbc:sqlite:prueba_swing.db | Dirección completa de conexión JDBC      |

---

## Autor
- cerimice
