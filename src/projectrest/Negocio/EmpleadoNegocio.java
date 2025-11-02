/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import projectrest.Datos.EmpleadoDAO;
import projectrest.Entidades.Empleado;
import javax.swing.table.DefaultTableModel;

public class EmpleadoNegocio {

    private final EmpleadoDAO DATOS;
    private DefaultTableModel dtm;

    public EmpleadoNegocio() {
        this.DATOS = new EmpleadoDAO();
    }
    
    public String insertar(Empleado empleado) {
        return DATOS.insertar(empleado) ? "OK" : "Error en la inserción";
    }

    public String editar(Empleado empleado) {
        return DATOS.editar(empleado) ? "OK" : "Error en la inserción";
    }

    public String eliminar(Empleado empleado) {
        return DATOS.eliminar(empleado) ? "OK" : "Error en la inserción";
    }
}
