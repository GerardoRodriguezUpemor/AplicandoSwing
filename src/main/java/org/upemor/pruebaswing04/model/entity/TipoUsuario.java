
/**
 * Representa el tipo o rol de un usuario en el sistema.
 * Hereda de Entity para incluir el identificador único.
 *
 * Esta clase se utiliza para categorizar a los usuarios según su función o permisos.
 */
package org.upemor.pruebaswing04.model.entity;


import lombok.Getter;
import lombok.Setter;


/**
 * Entidad que representa un tipo de usuario.
 * @author cerimice
 */

/**
 * Las anotaciones @Setter y @Getter de Lombok generan automáticamente los métodos set y get
 * para todos los campos de la clase. Esto evita tener que escribir manualmente estos métodos,
 * haciendo el código más limpio y fácil de mantener.
 * <p>
 * Es importante colocarlas en cada clase que requiera estos métodos, ya que las anotaciones de Lombok
 * no se heredan de la superclase. Así, aunque la clase Entity ya tenga @Setter y @Getter para su campo 'id',
 * aquí se agregan para que Lombok genere los métodos setNombre y getNombre para el campo 'nombre'.
 * <p>
 * Esto facilita el acceso y modificación de los atributos privados de la clase de forma segura y controlada.
 */
@Setter
@Getter
public class TipoUsuario extends Entity {

    /**
     * Nombre del tipo de usuario (por ejemplo: Administrador, Invitado, etc.).
     */
    private String nombre;

    /**
     * Este método redefine el método toString() que todas las clases en Java heredan de la Superclase Object.
     * <p>
     * Por defecto, si no se sobrescribe este método, cuando intentas mostrar un objeto de TipoUsuario
     * (por ejemplo, usando System.out.println(tipoUsuario) o agregándolo a un JComboBox), Java mostrará
     * algo como "org.upemor.pruebaswing04.model.entity.TipoUsuario@1a2b3c", que no es útil para el usuario.
     * <p>
     * Al sobrescribir toString() para que devuelva el campo nombre, logramos que cada vez que el objeto
     * se convierta a texto, se muestre directamente el nombre del tipo de usuario (por ejemplo, "Administrador" o "Invitado").
     * Esto es especialmente útil en interfaces gráficas, listas, tablas, logs, o cualquier lugar donde quieras
     * que el usuario vea información clara y comprensible en vez de la referencia interna del objeto.
     * <p>
     * Ejemplo de uso:
     * <pre>
     *     TipoUsuario tipo = new TipoUsuario();
     *     tipo.setNombre("Administrador");
     *     System.out.println(tipo); // Imprime: Administrador
     * </pre>
     *
     * @return el nombre del tipo de usuario, para mostrarlo de forma clara y legible.
     */
    @Override
    public String toString() {
        return nombre;
    }

}
