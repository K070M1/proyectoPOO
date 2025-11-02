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

    public DefaultTableModel listar(String texto) {
        List<Empleado> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));
        String[] columnas = {"ID", "Nombre Completo", "Rol", "Tipo Doc", "Documento", "Usuario", "Turno", "Estado", "Ingreso", "Salida"};
        this.dtm = new DefaultTableModel(null, columnas);
        String[] reg = new String[10];
        for (Empleado e : lista) {
            reg[0] = Integer.toString(e.getIdEmpleado());
            reg[1] = e.getNombreCompleto();
            reg[2] = e.getRol();
            reg[3] = e.getTipoDocumento();
            reg[4] = e.getDocumento();
            reg[5] = e.getUsuario();
            reg[6] = e.getTurno();
            reg[7] = e.isEstado() ? "Activo" : "Inactivo";
            reg[8] = e.getFechaIngreso();
            reg[9] = e.getFechaSalida();
            this.dtm.addRow(reg);
        }
        return this.dtm;
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
