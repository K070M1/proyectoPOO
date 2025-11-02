/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import projectrest.Datos.DetallePedidoDAO;
import projectrest.Entidades.DetallePedido;
import javax.swing.table.DefaultTableModel;

public class DetallePedidoNegocio {

    private final DetallePedidoDAO DATOS;
    private DefaultTableModel dtm;

    public DetallePedidoNegocio() {
        this.DATOS = new DetallePedidoDAO();
    }

    public String insertar(DetallePedido detallePedido) {
        return DATOS.insertar(detallePedido) ? "OK" : "Error en la inserción";
    }

    public String editar(DetallePedido detallePedido) {
        return DATOS.editar(detallePedido) ? "OK" : "Error en la inserción";
    }

    public String eliminar(DetallePedido detallePedido) {
        return DATOS.eliminar(detallePedido) ? "OK" : "Error en la inserción";
    }
}
