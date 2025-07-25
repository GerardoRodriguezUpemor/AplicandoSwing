package org.upemor.pruebaswing04.view.Tools;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.ui.FlatListCellBorder.Default;

public abstract class BaseDLG extends JPanel {
    
    protected JLabel lbBuscar;
    protected JTextField tfBuscar;
    protected JButton btnBuscar;

    protected JButton btnEliminar;
    protected JButton btnEditar;
    protected JButton btnAgregar;

    protected DefaultTableModel modeloTabla;
    protected JTable tbResultado;

    public BaseDLG() {
        initComponents();
    }

    private void initComponents() {
        // Initialize components
        lbBuscar = new JLabel("Proporciona el nombre a buscar:");
            lbBuscar.setFont(this.getFont().deriveFont(Font.BOLD));
        tfBuscar = new JTextField();
        btnBuscar = new JButton("Buscar");
            btnBuscar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnBuscar.setForeground(Color.decode("#C5E7CD"));
            btnBuscar.setBackground(Color.decode("#4CD964"));

        btnEliminar = new JButton("Eliminar");
            btnEliminar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnEliminar.setForeground(Color.decode("#C5E7CD"));
            btnEliminar.setBackground(Color.decode("#FF2D55"));
        btnEditar = new JButton("Editar");
            btnEditar.setForeground(Color.decode("#C5E7CD"));
            btnEditar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnEditar.setBackground(Color.decode("#898989"));
        btnAgregar = new JButton("Agregar");
            btnAgregar.setForeground(Color.decode("#C5E7CD"));
            btnAgregar.setFont(this.getFont().deriveFont(Font.BOLD));
            btnAgregar.setBackground(Color.decode("#007AFF"));

        modeloTabla = new DefaultTableModel();
        tbResultado = new JTable();
            tbResultado.setModel(modeloTabla);
            tbResultado.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            tbResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Add components to the panel
        this.setLayout(new GridBagLayout());
        this.add(lbBuscar, crearRestricciones(0,0,12,1,true));
        this.add(tfBuscar, crearRestricciones(0,1,10,1,true));
        this.add(btnBuscar, crearRestricciones(10,1,2,1,false));

        JPanel panelOps = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 15));
            panelOps.add(btnEliminar);
            panelOps.add(btnEditar);
            panelOps.add(btnAgregar);
        this.add(panelOps, crearRestricciones(10,2,2,1,false));
    
        this.add(tbResultado, crearRestricciones(0,3,12,8,true));

        JScrollPane scrollPane = new JScrollPane(tbResultado);
            this.add(scrollPane, crearRestricciones(0,3,12,8,true));
    }

    protected GridBagConstraints crearRestricciones(
        int posicionX,int posicionY,int tamanioX,int tamanioY,boolean responsivo){
        GridBagConstraints constraints = new GridBagConstraints();
            constraints.gridx = posicionX; // Posicion en columna (X)
            constraints.gridy = posicionY; // Posicion en fila (Y)
            constraints.gridwidth= tamanioX; // Tamaño en columnas (X)
            constraints.gridheight= tamanioY; // tamaño en filas (Y)
            constraints.ipadx = 10; // Padding X
            constraints.ipady = 10; // Padding Y
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.weightx = responsivo?1.0:0.0;
        return constraints;    
    }

    protected abstract void eventoBotonEliminar();
    protected abstract void eventoBotonEditar();
    protected abstract void eventoBotonAgregar();
    
}
