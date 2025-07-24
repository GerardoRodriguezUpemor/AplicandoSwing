# Cómo usar JDBC con MariaDB en Java: dos formas

Este documento explica las dos formas principales de usar el driver JDBC para MariaDB en Java: descargando el archivo `.jar` manualmente o agregando la dependencia en tu proyecto (por ejemplo, con Maven).

---

## 1. Usando el driver descargado manualmente

### Pasos
1. Descarga el archivo `mysql-connector-j-8.0.33.jar` (o el driver oficial de MariaDB, por ejemplo, `mariadb-java-client-2.7.4.jar`).
2. Si trabajas en un proyecto Maven y necesitas agregar el .jar manualmente, la carpeta recomendada es `src/main/resources` o puedes crear una carpeta llamada `lib` en la raíz del proyecto. Sin embargo, **lo más recomendable en Maven es usar dependencias en el pom.xml y dejar que Maven gestione los .jar automáticamente**.
3. Al ejecutar tu programa, agrega el `.jar` al classpath:

```powershell
java -cp .;ruta\al\mariadb-java-client-2.7.4.jar MiPrograma
```

> En Windows, usa `;` para separar rutas. En Linux/Mac, usa `:`.

### Ejemplo de código

```java
Class.forName("org.mariadb.jdbc.Driver");
Connection conn = DriverManager.getConnection(
    "jdbc:mariadb://localhost:3306/prueba_swing",
    "usuario",
    "contrasenia"
);
```

### Ventajas y desventajas
- **Ventaja:** No necesitas usar Maven ni otro gestor de dependencias.
- **Desventaja:** Debes descargar y actualizar el `.jar` manualmente, y asegurarte de agregarlo al classpath cada vez que ejecutes tu programa.

---

## 2. Usando dependencias (Maven)

### Pasos
1. Agrega la dependencia en tu archivo `pom.xml`:

```xml
<dependency>
    <groupId>org.mariadb.jdbc</groupId>
    <artifactId>mariadb-java-client</artifactId>
    <version>2.7.4</version>
</dependency>
```

2. Maven descarga automáticamente el `.jar` y lo agrega al classpath cuando compilas y ejecutas tu proyecto.

### Ejemplo de código

```java
// El código es igual, Maven se encarga del driver:
Class.forName("org.mariadb.jdbc.Driver");
Connection conn = DriverManager.getConnection(
    "jdbc:mariadb://localhost:3306/prueba_swing",
    "usuario",
    "contrasenia"
);
```

### Ventajas y desventajas
- **Ventaja:** No tienes que preocuparte por descargar ni actualizar el driver manualmente; Maven lo gestiona por ti.
- **Ventaja:** El driver se agrega automáticamente al classpath.
- **Desventaja:** Necesitas usar Maven o algún gestor de dependencias.

---

## Resumen
- Ambas formas permiten conectar Java con MariaDB usando JDBC.
- Usar dependencias (Maven) es más cómodo y seguro para proyectos grandes o colaborativos.
- Usar el `.jar` descargado es útil para proyectos simples o cuando no se usa Maven.

---

## Autor
- cerimice
