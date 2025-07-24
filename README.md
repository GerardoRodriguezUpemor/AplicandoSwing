# AplicandoSwing: Proyecto Java Swing con Arquitectura Didáctica

Este repositorio contiene un proyecto Java de escritorio basado en Swing, diseñado para demostrar buenas prácticas de arquitectura, patrones de diseño y documentación didáctica. Incluye ejemplos completos de conexión a base de datos (JDBC con SQLite y MariaDB), uso de patrones Repository y Controller, y documentación exhaustiva en Markdown para cada clase y concepto clave.

## Estructura del Proyecto

- **src/main/java/org/upemor/pruebaswing04/model/entity/**: Entidades del modelo de datos (`Usuario`, `TipoUsuario`, etc.) y sus explicaciones.
- **src/main/java/org/upemor/pruebaswing04/model/repository/**: Implementaciones de repositorios, conexión JDBC, patrones Singleton y documentación de cada método y concepto SQL.
- **src/main/java/org/upemor/pruebaswing04/controller/**: Controladores genéricos y específicos, lógica de validación y documentación detallada.
- **src/main/java/org/upemor/pruebaswing04/view/**: Interfaces gráficas Swing.
- **docs/**: Diagramas y scripts SQL.
- **prueba_swing.db**: Base de datos de ejemplo.
- **pom.xml**: Configuración Maven.

## Características principales

- **Patrón Repository**: Acceso a datos desacoplado y reutilizable para cualquier entidad.
- **Patrón Controller**: Lógica de validación y control centralizada y extensible.
- **Documentación didáctica**: Cada clase, método y concepto importante tiene su propio archivo `.md` explicativo, enlazado desde la documentación principal.
- **Ejemplos de conexión JDBC**: Incluye ejemplos para MariaDB y SQLite, con explicación de cada paso.
- **Ejemplo de uso CRUD**: Código y explicación para crear, leer, actualizar y eliminar entidades.

## ¿Para quién es este proyecto?
- Estudiantes y docentes que buscan un ejemplo completo y comentado de arquitectura Java Swing.
- Desarrolladores que quieren entender y aplicar patrones Repository y Controller en aplicaciones de escritorio.
- Cualquier persona interesada en aprender buenas prácticas de documentación y organización de proyectos Java.

## ¿Cómo navegar la documentación?
- Cada carpeta contiene archivos `.md` que explican el propósito y funcionamiento de las clases y métodos.
- Los archivos principales enlazan a explicaciones detalladas de métodos, patrones y conceptos SQL.
- Puedes abrir los archivos Markdown en VS Code y navegar fácilmente entre conceptos.

## Créditos
- Autor: cerimice

---

**Este repositorio es un ejemplo didáctico y completo de cómo organizar, documentar y estructurar una aplicación Java Swing con acceso a base de datos y buenas prácticas de desarrollo.**
