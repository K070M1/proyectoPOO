/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import projectrest.Datos.PlatoDAO;
import projectrest.Entidades.Plato;
import javax.swing.table.DefaultTableModel;

public class PlatoNegocio {

    private final PlatoDAO DATOS;
    private DefaultTableModel dtm;

    public PlatoNegocio() {
        this.DATOS = new PlatoDAO();
    }

    public String insertar(Plato plato) {
        return DATOS.insertar(plato) ? "OK" : "Error en la inserción";
    }

    public String editar(Plato plato) {
        return DATOS.editar(plato) ? "OK" : "Error en la inserción";
    }

    public String eliminar(Plato plato) {
        return DATOS.eliminar(plato) ? "OK" : "Error en la inserción";
    }
}
