/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import projectrest.Datos.MesaDAO;
import projectrest.Entidades.Mesa;

public class MesaNegocio {

    private final MesaDAO DATOS;
    private DefaultTableModel dtm;

    public MesaNegocio() {
        this.DATOS = new MesaDAO();
    }

    public DefaultTableModel listar(String texto) {
        List<Mesa> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));
        String[] columnas = {"ID", "Nro Mesa", "Capacidad"};
        this.dtm = new DefaultTableModel(null, columnas);
        String[] reg = new String[3];
        for (Mesa m : lista) {
            reg[0] = Integer.toString(m.getIdMesa());
            reg[1] = m.getNroMesa();
            reg[2] = Integer.toString(m.getMaximoClientes());
            this.dtm.addRow(reg);
        }
        return this.dtm;
    }

    public String insertar(Mesa m) {
        return DATOS.insertar(m) ? "OK" : "Error en la inserción";
    }

    public String editar(Mesa m) {
        return DATOS.editar(m) ? "OK" : "Error en la actualización";
    }

    public String eliminar(Mesa m) {
        return DATOS.eliminar(m) ? "OK" : "Error en la eliminación";
    }
}
