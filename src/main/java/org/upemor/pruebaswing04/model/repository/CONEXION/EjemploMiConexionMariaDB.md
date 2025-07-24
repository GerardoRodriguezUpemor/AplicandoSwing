# Ejemplo de uso de MiConexion para MariaDB

Este ejemplo muestra cómo utilizar la clase `MiConexion` para conectarse a una base de datos MariaDB en Java, explicando cada paso y los valores necesarios.

---

## Requisitos previos

- Tener el servicio MariaDB/MySQL activo (por ejemplo, usando XAMPP).
- Agregar el driver JDBC de MariaDB como dependencia en tu proyecto (por ejemplo, en Maven):

```xml
<dependency>
    <groupId>org.mariadb.jdbc</groupId>
    <artifactId>mariadb-java-client</artifactId>
    <version>2.7.4</version>
</dependency>
```

**¿Qué significa esta dependencia?**
- Esta sección se coloca en el archivo `pom.xml` de tu proyecto Maven.
- Indica a Maven que debe descargar el driver JDBC oficial para MariaDB (`mariadb-java-client`) en la versión indicada.
- El driver es una librería que permite a Java comunicarse con el servidor MariaDB usando JDBC.
- Al agregar esta dependencia, Maven descarga automáticamente el archivo `.jar` necesario y lo agrega al classpath de tu proyecto, para que puedas abrir conexiones a MariaDB sin instalar nada manualmente.

---

## Ejemplo de código

```java
import org.upemor.pruebaswing04.model.repository.MiConexion;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class EjemploMariaDB {
    public static void main(String[] args) {
        try {
            // Obtener la instancia única de MiConexion para MariaDB
            MiConexion conexion = new MiConexion(MiConexion.MARIADB);

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
   - Se crea una nueva instancia pasando `MiConexion.MARIADB` como parámetro.
   - Esto configura todos los atributos necesarios para conectarse a MariaDB (driver, host, puerto, base de datos, usuario, contraseña, url).

2. **Conexión a la base de datos**
   - Se llama al método `conectar()`, que carga el driver JDBC y abre la conexión usando los datos configurados.

3. **Ejecutar consultas SQL**
   - Se crea un `Statement` y se ejecuta una consulta SQL (`SELECT * FROM usuarios`).
   - Se recorren los resultados usando el objeto `ResultSet`.

4. **Desconexión**
   - Se llama a `desconectar()` para cerrar la conexión y liberar recursos.

---

## Notas importantes
- Los valores de host, puerto, usuario, contraseña y base de datos se pueden modificar en el método `cargarDatosMariaDB()` de la clase MiConexion.
- Si la base de datos o la tabla no existen, deberás crearlas previamente en MariaDB.
- El patrón Singleton asegura que toda la aplicación use la misma conexión.

---

## Resumen de atributos usados
| Atributo      | Valor ejemplo           | Descripción                                  |
|---------------|------------------------|-----------------------------------------------|
| driverClass   | org.mariadb.jdbc.Driver| Clase del driver JDBC para MariaDB            |
| servidor      | localhost              | Host del servidor MariaDB                     |
| puerto        | 3306                   | Puerto por defecto de MariaDB                 |
| baseDeDatos   | prueba_swing           | Nombre de la base de datos en el servidor     |
| usuario       | prueba                 | Usuario para autenticación                    |
| contrasenia   | prueba123              | Contraseña para autenticación                 |
| url           | jdbc:mariadb://localhost:3306/prueba_swing | Dirección completa de conexión JDBC |

---

## Autor
- cerimice
