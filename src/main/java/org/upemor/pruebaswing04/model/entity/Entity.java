
/**
 * Clase base para todas las entidades del modelo de datos.
 * Proporciona un campo 'id' común y métodos getter/setter generados por Lombok.
 *
 * Esta clase sirve como superclase para otras entidades que representan tablas en la base de datos.
 * Permite la reutilización del campo identificador y facilita la gestión de herencia en el modelo.
 */
package org.upemor.pruebaswing04.model.entity;


import lombok.Getter;
import lombok.Setter;


/**
 * Entidad base con identificador único.
 * Todas las entidades del sistema deben heredar de esta clase.
 * @author cerimice
 */

@Setter
@Getter
public class Entity {
    /**
     * Identificador único de la entidad. Corresponde a la clave primaria en la base de datos.
     */
    protected long id;
}
