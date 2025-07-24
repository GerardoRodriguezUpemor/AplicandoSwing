# Clase MiConexion

| Componente         | Tipo completo / Ejemplo                                                    | Descripción breve                                                                 |
|--------------------|------------------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| SQLITE             | `public static int SQLITE = 1;`                                              | Indica el uso de la base de datos SQLite                                          |
|                   |                                                                                 | **¿Cómo se usa?**<br>Se utiliza como parámetro para seleccionar SQLite como motor de base de datos. |
| MARIADB            | `public static int MARIADB = 2;`                                             | Indica el uso de la base de datos MariaDB                                         |
|                   |                                                                                 | **¿Cómo se usa?**<br>Se utiliza como parámetro para seleccionar MariaDB como motor de base de datos. |
| url                | `private String url;`<br>Ejemplo: `jdbc:sqlite:prueba_swing.db`<br>`jdbc:mariadb://localhost:3306/prueba_swing` | Dirección completa de conexión JDBC                                               |
|                   |                                                                                 | **¿Cómo se construye la url?**<br>La url se arma dentro de la clase combinando los valores de los atributos:<br>- Para SQLite: `"jdbc:sqlite:" + baseDeDatos`<br>- Para MariaDB: `"jdbc:mariadb://" + servidor + ":" + puerto + "/" + baseDeDatos`<br>Por ejemplo:<br>- SQLite: `jdbc:sqlite:prueba_swing.db`<br>- MariaDB: `jdbc:mariadb://localhost:3306/prueba_swing` |
| driverClass        | `private String driverClass;`<br>Ejemplo: `org.sqlite.JDBC`<br>`org.mariadb.jdbc.Driver` | Nombre de la clase del driver JDBC necesario para la conexión                     |
|                   |                                                                                 | **¿Cómo se asigna?**<br><br>**SQLite:**<br>- Se usa `org.sqlite.JDBC`.<br>- Este nombre corresponde a la clase principal del driver JDBC de SQLite.<br>- Se obtiene de la documentación oficial y del contenido del archivo `.jar` (buscando `org/sqlite/JDBC.class`).<br>- Es necesario para que Java pueda cargar el driver con `Class.forName(driverClass)`.<br><br>**MariaDB:**<br>- Se usa `org.mariadb.jdbc.Driver`.<br>- Este nombre corresponde a la clase principal del driver JDBC de MariaDB.<br>- Se obtiene de la documentación oficial y del contenido del archivo `.jar` (buscando `org/mariadb/jdbc/Driver.class`).<br>- Es necesario para que Java pueda cargar el driver con `Class.forName(driverClass)`.<br><br>**MySQL:**<br>- Se usa `com.mysql.cj.jdbc.Driver`.<br>- Este nombre corresponde a la clase principal del driver JDBC de MySQL.<br>- Se encuentra en el archivo `.jar` (por ejemplo, `mysql-connector-j-8.0.33.jar`) en la ruta `com/mysql/cj/jdbc/Driver.class`.<br>- Si usas Maven, solo necesitas agregar la dependencia en tu `pom.xml` y Maven descargará el `.jar` automáticamente y lo pondrá en el classpath.<br>- Ejemplo de dependencia Maven:<br>```xml<br><dependency><br>  <groupId>mysql</groupId><br>  <artifactId>mysql-connector-java</artifactId><br>  <version>8.0.33</version><br></dependency><br>```<br>- Luego puedes usar en tu código:<br>```java<br>driverClass = "com.mysql.cj.jdbc.Driver";<br>Class.forName(driverClass);<br>``` |
| servidor           | `private String servidor;`<br>Ejemplo: `localhost`                          | Dirección (host) del servidor de la base de datos (solo MariaDB)                  |
|                   |                                                                                 | **¿Cómo se usa?**<br>Se asigna el nombre o IP del servidor donde está MariaDB.<br>Se utiliza para construir la url de conexión. |
| puerto             | `private long puerto;`<br>Ejemplo: `3306`                                   | Puerto de conexión al servidor (solo MariaDB)                                     |
|                   |                                                                                 | **¿Cómo se usa?**<br>Se asigna el puerto donde escucha MariaDB (por defecto 3306).<br>Se utiliza para construir la url de conexión. |
| baseDeDatos        | `private String baseDeDatos;`<br>Ejemplo: `prueba_swing.db` (SQLite)<br>`prueba_swing` (MariaDB) | Nombre del archivo (SQLite) o base de datos (MariaDB)                         |
|                   |                                                                                 | **¿Cómo se usa?**<br>Para SQLite, es el nombre del archivo de la base.<br>Para MariaDB, es el nombre de la base en el servidor.<br>Se utiliza para construir la url de conexión. |
| usuario            | `private String usuario;`<br>Ejemplo: `root`                                 | Usuario para autenticación (solo MariaDB)                                         |
|                   |                                                                                 | **¿Cómo se usa?**<br>Se asigna el nombre de usuario para acceder a MariaDB.<br>Se utiliza al crear la conexión JDBC. |
| contrasenia        | `private String contrasenia;`<br>Ejemplo: `1234`                             | Contraseña para autenticación (solo MariaDB)                                      |
|                   |                                                                                 | **¿Cómo se usa?**<br>Se asigna la contraseña del usuario de MariaDB.<br>Se utiliza al crear la conexión JDBC. |
| instancia          | `private static MiConexion instancia;`                                       | Instancia única de la clase (patrón Singleton)                                    |
|                   |                                                                                 | **¿Cómo se usa?**<br>Se utiliza para asegurar que solo haya una instancia de MiConexion en toda la aplicación.<br>Se accede mediante el método `getInstancia()`. |
| conexion           | `private Connection conexion;`                                               | Objeto JDBC que representa la conexión activa                                     |
|                   |                                                                                 | **¿Cómo se usa?**<br>Se utiliza para ejecutar sentencias SQL y comunicarse con la base de datos.<br>Se obtiene mediante el método `conectar()`. |

> Esta tabla resume los atributos y su función principal en la clase MiConexion. Más detalles y ejemplos se explican en las secciones siguientes.

> **Paquete:** `org.upemor.pruebaswing04.model.repository`

## Descripción General
La clase `MiConexion` es responsable de gestionar la conexión a la base de datos de la aplicación, permitiendo elegir entre SQLite y MariaDB. Implementa el patrón Singleton para asegurar que solo exista una instancia de conexión durante la ejecución del programa.

---

## Atributos

- **public static int SQLITE = 1;**
  - Constante para indicar el uso de la base de datos SQLite.
  - Si se usa este valor, la conexión se configura para trabajar con un archivo local SQLite.
  - No requiere XAMPP ni servidor externo.

- **public static int MARIADB = 2;**
  - Constante para indicar el uso de la base de datos MariaDB.
  - Si se usa este valor, la conexión se configura para trabajar con un servidor MariaDB.
  - Requiere que el servicio MariaDB/MySQL esté activo (por ejemplo, con XAMPP).

- **private String url;**
  - URL de conexión JDBC. Indica a Java cómo y a qué base de datos conectarse.
  - **¿Cuál es la diferencia entre `servidor` (host) y `url`?**
    - `servidor` (host) es solo una parte de la información: representa la dirección de la máquina donde está la base de datos (por ejemplo, `localhost` o una IP).
    - `url` es la dirección completa de conexión JDBC, que incluye el tipo de base de datos, el host, el puerto, el nombre de la base de datos y, a veces, parámetros extra.
    - Ejemplo:
      - `servidor` = "localhost"
      - `url` = "jdbc:mariadb://localhost:3306/prueba_swing"
    - En la clase, primero defines el `servidor` y otros datos, y luego construyes la `url` usando todos esos valores juntos.
  - Ejemplo para SQLite: `jdbc:sqlite:prueba_swing.db`
  - Ejemplo para MariaDB: `jdbc:mariadb://localhost:3306/prueba_swing`
  - **¿Cómo se relaciona con la conexión?**
    - Esta URL es utilizada por el objeto de conexión JDBC para saber a qué base de datos debe conectarse y cómo hacerlo.
    - Cuando se llama a `DriverManager.getConnection(url, usuario, contrasenia)`, la URL le indica a Java el tipo de base de datos, la ubicación (archivo o servidor), el puerto y el nombre de la base.
    - Si la URL está mal formada, la conexión fallará o se conectará a la base equivocada.


**private String driverClass;**
  - Nombre de la clase del driver JDBC necesario para la conexión.
  - Ejemplo para SQLite: `org.sqlite.JDBC`
  - Ejemplo para MariaDB: `org.mariadb.jdbc.Driver`
  - **Ejemplo para SQLite:**
    - driverClass = "org.sqlite.JDBC"
    - El archivo .jar del driver debe estar en el classpath (por ejemplo, `sqlite-jdbc-3.36.0.3.jar`).
      - **¿Qué es el classpath?**
        - El classpath es una lista de rutas (carpetas o archivos .jar) que Java utiliza para buscar las clases y librerías necesarias cuando ejecuta un programa.
        - Si un archivo .jar (por ejemplo, el driver JDBC) no está en el classpath, Java no podrá encontrarlo y lanzará un error como `ClassNotFoundException`.
        - Puedes agregar archivos al classpath usando la opción `-cp` o `-classpath` al ejecutar tu programa, o configurándolo en tu IDE o en el archivo de construcción (por ejemplo, en Maven o Gradle).
        - Ejemplo en consola:
          - `java -cp .;sqlite-jdbc-3.36.0.3.jar MiPrograma`
            - Este comando es solo necesario si trabajas con proyectos Java "puros" (sin Maven ni IDE), donde tienes que descargar el .jar del driver manualmente y agregarlo al classpath al ejecutar tu programa.
            - **Si usas Maven o un IDE moderno (como VS Code, Eclipse o IntelliJ), NO necesitas este comando ni descargar el .jar manualmente:** solo agrega la dependencia en tu pom.xml y Maven se encarga de descargar el driver y ponerlo en el classpath automáticamente.
        - En proyectos Maven, los .jar de dependencias se agregan automáticamente al classpath cuando compilas y ejecutas desde Maven o tu IDE.
    - Se usa para bases de datos locales basadas en archivos.
  - **Ejemplo para MariaDB:**
    - driverClass = "org.mariadb.jdbc.Driver"
    - El archivo .jar del driver debe estar en el classpath (por ejemplo, `mariadb-java-client-2.7.4.jar`).
    - Se usa para bases de datos en servidor.
  - **¿Por qué se le llama librería y driver?**
    - Un driver JDBC es una librería (archivo .jar) que contiene el código necesario para traducir las instrucciones estándar de Java (JDBC) a comandos específicos del motor de base de datos (por ejemplo, SQLite o MariaDB).
    - Se le llama "driver" porque actúa como un "conductor" o "puente" entre tu aplicación Java y la base de datos, permitiendo que ambos se entiendan aunque hablen "idiomas" diferentes.
    - Cada base de datos tiene su propio driver, ya que cada una implementa su protocolo de comunicación.
  - **¿Cómo permite la comunicación con la base de datos?**
    - Cuando cargas la clase del driver con `Class.forName(driverClass)`, Java registra ese driver en el sistema JDBC.
    - A partir de ese momento, cuando llamas a `DriverManager.getConnection(...)`, el sistema sabe cómo crear la conexión y cómo enviar/recibir datos usando el protocolo correcto para esa base de datos.
    - Sin el driver, Java no sabría cómo comunicarse con la base de datos, aunque tengas la URL correcta.
  - **¿Por qué es necesario cargarlo antes de abrir la conexión?**
    - Cargar la clase del driver es lo que le dice a Java: "aquí está el código que sabe hablar con esta base de datos".
    - Si no se carga, el sistema JDBC no puede encontrar el "traductor" adecuado y la conexión fallará.


- **private String servidor;**
  - Dirección (host) del servidor de la base de datos.
  - Solo se usa para MariaDB (ejemplo: `localhost`).
  - **¿Cómo se relaciona con la conexión?**
    - Especifica en qué máquina o red se encuentra la base de datos MariaDB.
    - **¿Cómo se identifica la base de datos?**
      - En JDBC, la base de datos se identifica principalmente a través de la URL de conexión.
      - **Para SQLite:**
        - La URL incluye el nombre del archivo de la base de datos, por ejemplo: `jdbc:sqlite:prueba_swing.db`.
        - El archivo `prueba_swing.db` es la base de datos; si el archivo no existe, SQLite lo crea automáticamente.
      - **Para MariaDB:**
        - La URL incluye el nombre de la base de datos en el servidor, por ejemplo: `jdbc:mariadb://localhost:3306/prueba_swing`.
        - Aquí, `prueba_swing` es el nombre de la base de datos dentro del servidor MariaDB (debe existir previamente o ser creada por el usuario).
      - Además, el atributo `baseDeDatos` en la clase almacena este nombre y se usa para construir la URL de conexión.
    - Se utiliza para construir la URL de conexión JDBC.
    - **Nota:** El atributo `servidor` es el dato base (la dirección de la máquina o red) que se combina con el puerto y el nombre de la base de datos para formar la `url` de conexión JDBC. Sin el `servidor`, no se podría construir la `url` completa.


- **private long puerto;**
  - Puerto de conexión al servidor de base de datos.
  - Solo se usa para MariaDB (por defecto: 3306).
  - **¿Cómo se relaciona con la conexión?**
    - Indica el canal de comunicación en el servidor donde escucha la base de datos.
    - Es parte de la URL de conexión JDBC.


- **private String baseDeDatos;**
  - Nombre de la base de datos a la que se conectará.
  - Para SQLite es el nombre del archivo (ejemplo: `prueba_swing.db`).
  - Para MariaDB es el nombre de la base en el servidor (ejemplo: `prueba_swing`).
  - **¿Cómo se relaciona con la conexión?**
    - Especifica exactamente a qué base de datos acceder dentro del servidor o archivo.
    - Es parte fundamental de la URL de conexión JDBC.

- **private String usuario;**
  - Usuario para autenticación en la base de datos.
  - Solo se usa para MariaDB.

- **private String contrasenia;**
  - Contraseña para autenticación en la base de datos.
  - Solo se usa para MariaDB.

- **private static MiConexion instancia;**
  - Instancia única de la clase (patrón Singleton).

- **private Connection conexion;**
  - JDBC connection object, reused while the application is running.
  - **¿Qué es una conexión JDBC?**
    - Es el objeto que representa la comunicación activa entre tu aplicación Java y la base de datos (ya sea SQLite o MariaDB).
    - JDBC (Java Database Connectivity) es la API estándar de Java para ejecutar consultas, insertar, actualizar y eliminar datos en bases de datos relacionales.
    - A través de este objeto, tu aplicación puede enviar comandos SQL y recibir resultados, funcionando como un puente entre el programa y la base de datos.

---

## Métodos

### cargarDatosSQLite()
Configura los parámetros de conexión para usar una base de datos SQLite.
- Asigna el driver JDBC, el nombre del archivo de la base de datos y construye la URL de conexión.
- Los campos de usuario y contraseña quedan vacíos, ya que SQLite no los requiere.
- Llamado automáticamente por el constructor si se selecciona SQLITE como manejador.

### cargarDatosMariaDB()
Configura los parámetros de conexión para usar una base de datos MariaDB.
- Asigna el driver JDBC, el host, el puerto, el nombre de la base de datos, el usuario y la contraseña.
- Construye la URL de conexión con todos estos datos.
- Llamado automáticamente por el constructor si se selecciona MARIADB como manejador.

### getInstancia()
Devuelve la instancia única de MiConexion.
- Si no existe, la crea usando SQLITE como manejador por defecto.
- Así se asegura que solo haya una conexión activa y centralizada en toda la aplicación.
- Lanza excepción si ocurre un error al crear la instancia.

### MiConexion(int manejador)
Constructor privado que inicializa la configuración de conexión según el manejador seleccionado.
- Solo puede ser llamado desde dentro de la clase (por el patrón Singleton).
- Recibe como parámetro el tipo de base de datos a usar (SQLITE o MARIADB).
- Lanza excepción si el manejador no está configurado.

### conectar()
Establece y devuelve la conexión a la base de datos.
- Si la conexión ya existe, la reutiliza.
- Si no, carga el driver JDBC y crea una nueva conexión usando los parámetros configurados (url, usuario, contraseña).
- Devuelve un objeto Connection listo para ejecutar sentencias SQL.
- Lanza excepción si ocurre un error al conectar.

### desconectar()
Cierra la conexión a la base de datos si está abierta.
- Es recomendable llamar a este método cuando la aplicación ya no necesite acceder a la base de datos, para liberar recursos del sistema.
- Devuelve true si la desconexión fue exitosa, false en caso contrario.
- Lanza excepción si ocurre un error al cerrar la conexión.

---

## Ejemplo de uso
```java
// Obtener la instancia única
MiConexion conexion = MiConexion.getInstancia();

// Conectar a la base de datos
Connection conn = conexion.conectar();

// ... ejecutar sentencias SQL ...

// Desconectar cuando ya no se necesite
conexion.desconectar();
```

---

## Notas adicionales
- El patrón Singleton asegura que toda la aplicación use la misma conexión, evitando problemas de múltiples conexiones abiertas.
- Cambiar entre SQLite y MariaDB es tan sencillo como modificar el manejador al crear la instancia.
- Los métodos están documentados en el código fuente para facilitar su comprensión y mantenimiento.

## Autor
- cerimice
