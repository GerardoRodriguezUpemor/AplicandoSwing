package org.upemor.pruebaswing04.view.Administracion.tipoUsuario;

import org.upemor.pruebaswing04.view.Tools.BaseDLG;

public class TipoUsuarioDLG extends BaseDLG {
    public TipoUsuarioDLG(){
        super();// Call the constructor of BaseDLG to initialize components
        init();
    }

    private void init() {
        // Initialize components specific to TipoUsuarioDLG
        this.setVisible(true);
    }

    @Override
    protected void eventoBotonEliminar() {
        // Implementar evento para botón Eliminar
    }

    @Override
    protected void eventoBotonEditar() {
        // Implementar evento para botón Editar
    }

    @Override
    protected void eventoBotonAgregar() {
        // Implementar evento para botón Agregar
    }
}