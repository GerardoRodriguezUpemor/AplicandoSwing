
/**
 * Representa un usuario del sistema con sus datos personales y de acceso.
 * Hereda de Entity para incluir el identificador único.
 *
 * Esta clase almacena la información relevante de cada usuario, como nombre, correo, contraseña, fecha de nacimiento y tipo de usuario.
 */
package org.upemor.pruebaswing04.model.entity;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


/**
 * Entidad que representa a un usuario del sistema.
 * @author cerimice
 */

/**
 * Las anotaciones @Setter y @Getter de Lombok generan automáticamente los métodos set y get
 * para todos los campos de la clase. Esto evita tener que escribir manualmente estos métodos,
 * haciendo el código más limpio y fácil de mantener.
 * <p>
 * Es importante colocarlas en cada clase que requiera estos métodos, ya que las anotaciones de Lombok
 * no se heredan de la superclase. Así, aunque la clase Entity ya tenga @Setter y @Getter para su campo 'id',
 * aquí se agregan para que Lombok genere los métodos set y get para los campos propios de Usuario.
 * <p>
 * Esto facilita el acceso y modificación de los atributos privados de la clase de forma segura y controlada.
 */
@Setter
@Getter
public class Usuario extends Entity {

    /** Nombre del usuario. */
    private String nombre;
    /** Apellidos del usuario. */
    private String apellidos;
    /** Correo electrónico del usuario. */
    private String email;
    /** Contraseña de acceso del usuario. */
    private String password;
    /** Fecha de nacimiento del usuario. */
    private LocalDate fechaDeNacimiento;
    /** Identificador del tipo de usuario (relación con TipoUsuario). */
    private long tipoUsuario;

    /**
     * Este método redefine el método toString() que todas las clases en Java heredan de la clase Object.
     * <p>
     * Por defecto, si no se sobrescribe este método, cuando intentas mostrar un objeto de Usuario
     * (por ejemplo, usando System.out.println(usuario) o agregándolo a un JComboBox), Java mostrará
     * algo como "org.upemor.pruebaswing04.model.entity.Usuario@1a2b3c", que no es útil para el usuario.
     * <p>
     * Al sobrescribir toString() para que devuelva el campo nombre, logramos que cada vez que el objeto
     * se convierta a texto, se muestre directamente el nombre del usuario (por ejemplo, "Juan").
     * Esto es especialmente útil en interfaces gráficas, listas, tablas, logs, o cualquier lugar donde quieras
     * que el usuario vea información clara y comprensible en vez de la referencia interna del objeto.
     * <p>
     * Ejemplo de uso:
     * <pre>
     *     Usuario usuario = new Usuario();
     *     usuario.setNombre("Juan");
     *     System.out.println(usuario); // Imprime: Juan
     * </pre>
     *
     * @return el nombre del usuario, para mostrarlo de forma clara y legible.
     */
    @Override
    public String toString() {
        return nombre;
    }

}
