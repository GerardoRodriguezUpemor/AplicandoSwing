package org.upemor.pruebaswing04.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import org.upemor.pruebaswing04.model.entity.Entity;

/**
 * Clase abstracta y genérica que define la estructura base para los repositorios de acceso a datos.
 * <p>
 * Utiliza el parámetro de tipo genérico T, que debe extender de Entity, para permitir que cualquier entidad
 * del sistema pueda tener su propio repositorio reutilizando la lógica CRUD (crear, leer, actualizar, eliminar).
 * <p>
 * Esta estructura permite:
 * <ul>
 *   <li>Reutilizar código para todas las entidades, evitando duplicación.</li>
 *   <li>Centralizar la lógica de acceso a datos y manejo de conexiones.</li>
 *   <li>Personalizar el mapeo de objetos y la asignación de parámetros SQL mediante métodos abstractos.</li>
 *   <li>Facilitar el mantenimiento y la escalabilidad del sistema.</li>
 * </ul>
 *
 * Para cada entidad concreta (por ejemplo, Usuario, TipoUsuario), se debe crear una subclase que implemente
 * los métodos abstractos para el mapeo y la configuración de sentencias.
 *
 * @param <T> Tipo de entidad que extiende de Entity
 * @author cerimice
 */
public abstract class Repository<T extends Entity>{
    
    private MiConexion myConnection;
    protected PreparedStatement statement;
    
    /* query definition */
    protected String queryCreate;   // INSERT INTO tabla VALUES(?,?,?)
    protected String queryRead;     // SELECT * FROM tabla WHERE id IN (?);
    protected String queryReadAll;  // SELECT * FROM tabla;
    protected String queryUpdate;   // REPLACE INTO tabla VALUES(?)
    protected String queryDelete;   // DELETE FROM tabla WHERE id IN (?);
    
    
    public Repository(String tabla,long parameters) throws Exception{
        try{
            myConnection = MiConexion.getInstancia();
            initQueries(tabla,parameters);
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: constructor()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    protected void initQueries(String tabla,long parameters){
        // Sentencia para insertar un registro. El primer valor NULL corresponde al id autoincremental.
        // Los signos de interrogación (?) son parámetros que se asignan con PreparedStatement.
        // Ejemplo: INSERT INTO usuarios VALUES(NULL, ?, ?, ?)
        queryCreate = "INSERT INTO "+tabla+" VALUES(NULL";
        for(int x=1;x<=(parameters-1);x++) queryCreate += ",?";
        queryCreate += ")";

        // Sentencia para leer un registro por id. El parámetro (?) se asigna con setLong.
        // Ejemplo: SELECT * FROM usuarios WHERE id IN (?)
        queryRead = "SELECT * FROM "+tabla+" WHERE id IN (?)";

        // Sentencia para leer todos los registros de la tabla.
        // Ejemplo: SELECT * FROM usuarios
        queryReadAll = "SELECT * FROM "+tabla+"";

        // Sentencia para actualizar (reemplazar) un registro. Los parámetros (?) corresponden a los valores de las columnas.
        // Ejemplo: REPLACE INTO usuarios VALUES(?, ?, ?)
        queryUpdate = "REPLACE INTO "+tabla+" VALUES(";
        for(int x=1;x<=parameters;x++) queryUpdate += ",?";
        queryUpdate += ")";
        queryUpdate = queryUpdate.replace("(,?","(?");

        // Sentencia para eliminar un registro por id. El parámetro (?) se asigna con setLong.
        // Ejemplo: DELETE FROM usuarios WHERE id IN (?)
        queryDelete = "DELETE FROM "+tabla+" WHERE id IN (?)";
        
        //System.out.println(queryCreate);
        //System.out.println(queryRead);
        //System.out.println(queryUpdate);
        //System.out.println(queryDelete);
        
    }
    /**
     * Métodos abstractos para personalizar el comportamiento según la entidad concreta.
     * <p>
     * Se definen como abstractos porque cada entidad (por ejemplo, Usuario, Producto, etc.)
     * puede tener una estructura de datos diferente y requerir un mapeo específico entre
     * los datos de la base y los objetos Java, así como una asignación particular de los
     * parámetros en las sentencias SQL.
     * <ul>
     *   <li><b>objectMapping</b>: Permite convertir una fila del ResultSet en un objeto de tipo T.</li>
     *   <li><b>setStatementParameters</b>: Permite asignar los valores del objeto a los parámetros de la sentencia SQL.</li>
     * </ul>
     * De esta forma, la clase Repository es genérica y reutilizable, pero obliga a las subclases
     * a implementar los detalles específicos de cada entidad, garantizando flexibilidad y seguridad de tipos.
     */
    protected abstract T objectMapping(ResultSet rs) throws Exception;
    protected abstract void setStatementParameters(PreparedStatement statement,T obj,boolean newObj) throws Exception;
    
    public boolean create(T obj) throws Exception{
        try{
            statement = myConnection.conectar().prepareStatement(queryCreate);
            setStatementParameters(statement,obj,true);
            int result = statement.executeUpdate();
            return result>=0;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: create()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    public T read(long id) throws Exception{
        try{
            statement = myConnection.conectar().prepareStatement(queryRead);
                statement.setLong(1,id);
            ResultSet rs  = statement.executeQuery();
            T obj = null;
            while(rs.next()){obj = objectMapping(rs);}
            return obj;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: read()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    /**
     * Recupera todos los registros de la tabla asociada y los devuelve como una lista de objetos.
     * <p>
     * Ejecuta la consulta definida en queryReadAll, mapea cada fila del resultado a un objeto T usando objectMapping,
     * y los agrega a una lista. Devuelve la lista completa.
     *
     * @return Lista de todos los objetos encontrados en la tabla
     * @throws Exception si ocurre un error en la consulta o el mapeo
     */
    public List<T> readAll() throws Exception{
        try{
            statement = myConnection.conectar().prepareStatement(queryReadAll);
            ResultSet rs  = statement.executeQuery();
            T obj = null;
            List<T> list = new LinkedList<>();
            while(rs.next()){
                obj = objectMapping(rs); // Convierte cada fila en un objeto T
                list.add(obj);
            }
            return list;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: readAll()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    /**
     * Actualiza un registro existente en la base de datos con los datos del objeto recibido.
     * <p>
     * Verifica que el id del objeto sea válido (>0), prepara la sentencia SQL de actualización,
     * asigna los parámetros usando setStatementParameters y ejecuta la actualización.
     *
     * @param obj Objeto con los datos a actualizar (debe tener un id válido)
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws Exception si el id es inválido o ocurre un error en la actualización
     */
    public boolean update(T obj) throws Exception{
        try{
            if(obj.getId() <= 0) throw new Exception("El id no puede ser cero en la actualizacion");
            statement = myConnection.conectar().prepareStatement(queryUpdate);
            setStatementParameters(statement,obj,false);
            int result = statement.executeUpdate();
            return result>=0;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: update()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
    
    /**
     * Elimina un registro de la base de datos según su id.
     * <p>
     * Prepara la sentencia SQL de borrado, asigna el id como parámetro y ejecuta la operación.
     *
     * @param id Identificador del registro a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     * @throws Exception si ocurre un error en la eliminación
     */
    public boolean delete(long id) throws Exception{
        try{
            statement = myConnection.conectar().prepareStatement(queryDelete);
                statement.setLong(1,id);
            int result = statement.executeUpdate();
            return result>=0;
        }catch(Exception ex){
            System.out.println(
                "Error: "+ex.getMessage()
                +" in method: delete()"
                +" in class: "+this.getClass().getName()
            );
            throw ex;
        }
    }
}
