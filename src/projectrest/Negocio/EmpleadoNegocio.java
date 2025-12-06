/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import projectrest.Datos.EmpleadoDAO;
import projectrest.Entidades.Empleado;
import javax.swing.table.DefaultTableModel;

import projectrest.Entidades.Session.Session;

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
        String turn = "";
        for (Empleado e : lista) {
            switch (e.getTurno()) {
                case "M":
                    turn = "Mañana";
                    break;
                case "T":
                    turn = "Tarde";
                    break;
                default:
                    turn = "Noche";
            }

            reg[0] = Integer.toString(e.getIdEmpleado());
            reg[1] = e.getNombreCompleto();
            reg[2] = e.getRol().equals("A") ? "Administrador" : "Empleado";
            reg[3] = e.getTipoDocumento().equals("D") ? "DNI" : "CE";
            reg[4] = e.getDocumento();
            reg[5] = e.getUsuario();
            reg[6] = turn;
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

    public String editarCredenciales(Empleado empleado) {
        return DATOS.editarCredenciales(empleado) ? "OK" : "Error en la inserción";
    }

    public String eliminar(Empleado empleado) {
        return DATOS.eliminar(empleado) ? "OK" : "Error en la inserción";
    }

    public DefaultComboBoxModel seleccionarRol() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        items.addElement("Empleado");
        items.addElement("Administrador");
        return items;
    }

    public DefaultComboBoxModel seleccionarTDocumento() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        items.addElement("DNI");
        items.addElement("CE");
        return items;
    }

    public DefaultComboBoxModel seleccionarTurno() {
        DefaultComboBoxModel items = new DefaultComboBoxModel();
        items.addElement("Mañana");
        items.addElement("Tarde");
        items.addElement("Noche");
        return items;
    }

    public String iniciarSesion(String username, String password) {
        String msg = DATOS.iniciarSesion(username, password);
        if (msg.contains("ERROR LOGIN:")) {
            return msg;
        } else {
            Session session = Session.getInstance();
            String[] parts = msg.split("\\|");
            session.login(Integer.parseInt(parts[0]), parts[1], parts[2]);
            return "Inicio de sesión exitoso..!";
        }
    }
}
