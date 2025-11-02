/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import projectrest.Datos.PedidoDAO;
import projectrest.Entidades.Pedido;
import javax.swing.table.DefaultTableModel;

public class PedidoNegocio {

    private final PedidoDAO DATOS;
    private DefaultTableModel dtm;

    public PedidoNegocio() {
        this.DATOS = new PedidoDAO();
    }

    public String insertar(Pedido pedido) {
        return DATOS.insertar(pedido) ? "OK" : "Error en la inserción";
    }

    public String editar(Pedido pedido) {
        return DATOS.editar(pedido) ? "OK" : "Error en la inserción";
    }

    public String eliminar(int idPedido) {
        return DATOS.eliminar(idPedido) ? "OK" : "Error en la inserción";
    }
}
