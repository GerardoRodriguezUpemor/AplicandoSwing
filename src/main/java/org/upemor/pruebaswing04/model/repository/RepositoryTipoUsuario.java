package org.upemor.pruebaswing04.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.upemor.pruebaswing04.model.entity.TipoUsuario;

/** @author cerimice **/

public class RepositoryTipoUsuario extends Repository<TipoUsuario>{
    
    public RepositoryTipoUsuario() throws Exception{
        super("tipo_usuario",2);
    }

    @Override
    protected TipoUsuario objectMapping(ResultSet rs) throws Exception{
        TipoUsuario obj = new TipoUsuario();
            obj.setId(rs.getLong("id"));
            obj.setNombre(rs.getString("nombre"));
        return obj;
    }

    @Override
    protected void setStatementParameters(PreparedStatement statement, TipoUsuario obj, boolean newObj) throws Exception{
        int i=1;
        if(!newObj) statement.setLong(i++,obj.getId());
        statement.setString(i++,obj.getNombre());
    }
    
}
