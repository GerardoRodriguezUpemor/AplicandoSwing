    // Ejemplo de declaración de variable tipo MiConexion:
    // private MiConexion myConnection;
    //
    // Esto solo crea una referencia (apuntador) a un objeto de tipo MiConexion, pero no instancia la clase ni crea el objeto.
    // La instancia real se crea cuando se asigna, por ejemplo:
    // myConnection = MiConexion.getInstancia();
    // En ese momento, myConnection apunta al objeto único (Singleton) que gestiona la conexión a la base de datos.
    //
    // ¿Por qué se declara así?
    // No es porque MiConexion sea una "clase madre" (no es una clase base ni abstracta),
    // sino porque queremos tener una referencia para acceder a la instancia centralizada de la conexión.
    // Así podemos usar los métodos conectar() y desconectar() desde cualquier parte del código que tenga acceso a la variable.
    //
    // Declarar la variable como tipo MiConexion permite aprovechar el patrón Singleton y evitar múltiples conexiones abiertas.
package org.upemor.pruebaswing04.model.repository;

import java.sql.Connection;
import java.sql.DriverManager;

/** @author cerimice **/

public class MiConexion{
    
    /**
     * Constante para indicar el uso de la base de datos SQLite.
     * Si se pasa este valor al constructor, se configura la conexión para SQLite.
     *
     * NOTA: Si usas SQLITE, NO necesitas tener XAMPP encendido, ya que SQLite funciona como un archivo local.
     */
    public static int SQLITE = 1;

    /**
     * Constante para indicar el uso de la base de datos MariaDB.
     * Si se pasa este valor al constructor, se configura la conexión para MariaDB.
     *
     * NOTA: Si usas MARIADB, DEBES tener XAMPP encendido y el servicio de MariaDB/MySQL activo,
     * ya que la aplicación necesita conectarse al servidor de base de datos.
     */
    public static int MARIADB = 2;

    /**
     * URL de conexión JDBC que se usará para conectarse a la base de datos.
     * <p>
     * La URL es una cadena especial que indica a Java cómo y a qué base de datos debe conectarse.
     * Su formato depende del tipo de base de datos:
     * <ul>
     *   <li>Para SQLite: <b>jdbc:sqlite:prueba_swing.db</b> (acceso a un archivo local)</li>
     *   <li>Para MariaDB: <b>jdbc:mariadb://localhost:3306/prueba_swing</b> (acceso a un servidor en red o local)</li>
     * </ul>
     * Esta URL se construye automáticamente en los métodos cargarDatosSQLite() o cargarDatosMariaDB(),
     * según el tipo de base de datos seleccionado al crear la instancia de MiConexion.
     *
     * Es fundamental que la URL esté bien formada, ya que de ello depende que la conexión sea exitosa.
     */
    private String url;

    /**
     * Nombre de la clase del driver JDBC necesario para la conexión.
     * <p>
     * El driver JDBC es una librería que permite a Java comunicarse con la base de datos.
     * Debe estar disponible en el classpath del proyecto.
     * Ejemplo de valor para SQLite: "org.sqlite.JDBC"
     * Ejemplo de valor para MariaDB: "org.mariadb.jdbc.Driver"
     * Este valor se asigna automáticamente según el tipo de base de datos seleccionado.
     */
    private String driverClass;

    /**
     * Dirección (host) del servidor de la base de datos.
     * <p>
     * Para MariaDB, normalmente es "localhost" si el servidor está en la misma máquina,
     * o la IP/nombre de red si está en otro equipo.
     * Para SQLite no se utiliza, ya que la base es un archivo local.
     * Ejemplo: "localhost"
     */
    private String servidor;

    /**
     * Puerto de conexión al servidor de base de datos.
     * <p>
     * Solo se usa para MariaDB (por defecto 3306).
     * Para SQLite no se utiliza.
     * Ejemplo: 3306
     */
    private long puerto;

    /**
     * Nombre de la base de datos a la que se conectará.
     * <p>
     * Para SQLite es el nombre del archivo de la base de datos (ejemplo: "prueba_swing.db").
     * Para MariaDB es el nombre de la base de datos en el servidor (ejemplo: "prueba_swing").
     */
    private String baseDeDatos;

    /**
     * Usuario para la autenticación en la base de datos.
     * <p>
     * Solo se usa para MariaDB. Para SQLite normalmente no se requiere usuario.
     * Ejemplo: "prueba"
     */
    private String usuario;

    /**
     * Contraseña para la autenticación en la base de datos.
     * <p>
     * Solo se usa para MariaDB. Para SQLite normalmente no se requiere contraseña.
     * Ejemplo: "prueba123"
     */
    private String contrasenia;
    
    /**
     * Configura los parámetros de conexión para usar una base de datos SQLite.
     * <p>
     * Asigna el driver JDBC correspondiente, el nombre del archivo de la base de datos,
     * y construye la URL de conexión. Los campos de usuario y contraseña quedan vacíos,
     * ya que SQLite no los requiere.
     *
     * Este método es llamado automáticamente por el constructor cuando se selecciona SQLITE como manejador.
     */
    private void cargarDatosSQLite(){
        // El nombre "org.sqlite.JDBC" corresponde a la clase principal del driver JDBC de SQLite.
        // Este nombre se obtiene de la documentación oficial y del contenido del .jar (buscando org/sqlite/JDBC.class).
        // Es necesario para que Java pueda cargar el driver con Class.forName(driverClass).
        driverClass = "org.sqlite.JDBC";
        // En SQLite, el archivo .db es la base de datos real: contiene toda la información y los datos.
        // El archivo .sql solo contiene instrucciones para crear o poblar la base, pero no almacena los datos.
        // Por eso aquí se usa "prueba_swing.db" como baseDeDatos, que es el archivo que SQLite abre y modifica.
        servidor = "";
        puerto = 0;
        baseDeDatos = "prueba_swing.db";
        usuario = "";
        contrasenia = "";
        url = "jdbc:sqlite:"+baseDeDatos;
    }

    /**
     * Configura los parámetros de conexión para usar una base de datos MariaDB.
     * <p>
     * Asigna el driver JDBC correspondiente, el host, el puerto, el nombre de la base de datos,
     * el usuario y la contraseña. Construye la URL de conexión con todos estos datos.
     *
     * Este método es llamado automáticamente por el constructor cuando se selecciona MARIADB como manejador.
     */
    private void cargarDatosMariaDB(){
        driverClass = "org.mariadb.jdbc.Driver";
        servidor = "localhost";
        puerto = 3306;
        baseDeDatos = "prueba_swing";
        usuario = "prueba";
        contrasenia = "prueba123";
        url = "jdbc:mariadb://"+servidor+":"+puerto+"/"+baseDeDatos;
    }

    /**
     * Instancia única de la clase (patrón Singleton).
     * Permite que toda la aplicación comparta la misma conexión a la base de datos.
     */
    private static MiConexion instancia;

    /**
     * Devuelve la instancia única de MiConexion.
     * <p>
     * Si no existe, la crea usando SQLITE como manejador por defecto.
     * Así se asegura que solo haya una conexión activa y centralizada en toda la aplicación.
     *
     * @return instancia única de MiConexion
     * @throws Exception si ocurre un error al crear la instancia
     */
    public static MiConexion getInstancia() throws Exception{
        if(instancia == null){
            instancia = new MiConexion(SQLITE);
        }
        return instancia;
    }

    /**
     * Constructor privado que inicializa la configuración de conexión según el manejador seleccionado.
     * <p>
     * Solo puede ser llamado desde dentro de la clase (por el patrón Singleton).
     *
     * @param manejador Tipo de base de datos a usar (SQLITE o MARIADB)
     * @throws Exception si el manejador no está configurado
     */
    private MiConexion(int manejador) throws Exception{
        switch(manejador){
            case 1: cargarDatosSQLite(); break;
            case 2: cargarDatosMariaDB(); break;
            default:
                throw new Exception("El manejador solicitado no esta configurado");
        }
    }

    /**
     * Objeto de conexión JDBC. Se reutiliza mientras la aplicación esté en ejecución.
     */
    private Connection conexion;

    /**
     * Establece y devuelve la conexión a la base de datos.
     * <p>
     * Si la conexión ya existe, la reutiliza. Si no, carga el driver JDBC y crea una nueva conexión
     * usando los parámetros configurados (url, usuario, contraseña).
     *
     * @return Objeto Connection listo para ejecutar sentencias SQL
     * @throws Exception si ocurre un error al conectar
     */
    public Connection conectar() throws Exception{
        try{
            if(conexion == null){
                Class.forName(driverClass); // Carga el driver JDBC
                conexion = DriverManager.getConnection(url,usuario,contrasenia); // Abre la conexión
            }
            return conexion;
        }catch(Exception ex){
            System.out.println("Error: "+this.getClass().getName()+" => "+ ex.getMessage());
            throw ex;
        }
    }

    /**
     * Cierra la conexión a la base de datos si está abierta.
     * <p>
     * Es recomendable llamar a este método cuando la aplicación ya no necesite acceder a la base de datos,
     * para liberar recursos del sistema.
     *
     * @return true si la desconexión fue exitosa, false en caso contrario
     * @throws Exception si ocurre un error al cerrar la conexión
     */
    public boolean desconectar() throws Exception{
        try{
            if(conexion != null)
                conexion.close();
            return true;
        }catch(Exception ex){
            System.out.println("Error: "+this.getClass().getName()+" => "+ ex.getMessage());
            throw ex;
        }
    }
}
