/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import projectrest.Datos.CalificacionDAO;
import projectrest.Entidades.Calificacion;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class CalificacionNegocio {

    private final CalificacionDAO DATOS;
    private DefaultTableModel dtm;

    public CalificacionNegocio() {
        this.DATOS = new CalificacionDAO();
    }

    public String insertar(Calificacion calificacion) {
        return DATOS.insertar(calificacion) ? "OK" : "Error en la inserción";
    }

    public String editar(Calificacion calificacion) {
        return DATOS.editar(calificacion) ? "OK" : "Error en la inserción";
    }

    public String eliminar(Calificacion calificacion) {
        return DATOS.eliminar(calificacion) ? "OK" : "Error en la inserción";
    }
}
