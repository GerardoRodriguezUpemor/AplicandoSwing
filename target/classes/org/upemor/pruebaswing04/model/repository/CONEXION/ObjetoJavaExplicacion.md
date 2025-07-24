# ¿Qué es un objeto en Java?

## Definición básica
Un **objeto** en Java es una instancia de una clase. Es una entidad que tiene atributos (datos) y métodos (comportamientos) definidos por su clase. Los objetos permiten modelar elementos del mundo real dentro de un programa.

## Ejemplo sencillo
Si tienes una clase:
```java
public class Persona {
    String nombre;
    int edad;
}
```
Puedes crear un objeto así:
```java
Persona p = new Persona();
p.nombre = "Juan";
p.edad = 30;
```
Aquí, `p` es un objeto de tipo `Persona`.

## ¿Qué ocurre al declarar una variable tipo clase?
Cuando escribes:
```java
private MiConexion myConnection;
```
Solo creas una referencia (un "apuntador") a un objeto de tipo `MiConexion`, pero **no** has creado el objeto aún. El objeto se crea cuando usas `new` o llamas a un método que retorna una instancia:
```java
myConnection = MiConexion.getInstancia();
```
Ahora `myConnection` apunta al objeto real y puedes usar sus métodos.

## ¿Por qué se declara así?
- Permite tener una referencia para acceder a los métodos y atributos del objeto.
- Facilita el uso del patrón Singleton (como en `MiConexion`), donde solo debe existir una instancia en toda la aplicación.
- Es una práctica estándar en Java para trabajar con objetos y aprovechar la orientación a objetos.

## Resumen

- Una variable tipo clase es solo una referencia.
- El objeto se crea al instanciar la clase.
- Así puedes acceder a los métodos y atributos definidos en la clase.
- En el caso de `MiConexion`, esto permite gestionar la conexión a la base de datos de forma centralizada y segura.

---

## ¿Por qué se usa un objeto JDBC?

Un objeto JDBC (por ejemplo, `Connection`, `PreparedStatement`, `ResultSet`) es fundamental en Java para interactuar con bases de datos relacionales. JDBC (Java Database Connectivity) es la API estándar que permite:

- **Abrir y gestionar conexiones** a la base de datos (con `Connection`).
- **Ejecutar sentencias SQL** (con `Statement` o `PreparedStatement`).
- **Recuperar resultados** de consultas (con `ResultSet`).

Ventajas de usar objetos JDBC:
- Permiten separar la lógica de acceso a datos del resto de la aplicación.
- Facilitan el manejo seguro y eficiente de la base de datos.
- Proveen métodos para ejecutar consultas, actualizaciones y transacciones.

Ejemplo:
```java
Connection conn = myConnection.conectar();
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios");
ResultSet rs = stmt.executeQuery();
while (rs.next()) {
    // Procesar cada registro
}
```
Así, los objetos JDBC son la herramienta que Java usa para comunicarse con la base de datos, enviar comandos y recibir datos.

Ejemplo:
```java
Connection conn = myConnection.conectar();
PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usuarios");
ResultSet rs = stmt.executeQuery();
while (rs.next()) {
    // Procesar cada registro
}
```
Así, los objetos JDBC son la herramienta que Java usa para comunicarse con la base de datos, enviar comandos y recibir datos.

---

**Autor:** cerimice
