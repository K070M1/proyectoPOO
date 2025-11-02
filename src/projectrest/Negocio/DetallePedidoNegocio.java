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

    public DefaultTableModel listar(String texto) {
        List<DetallePedido> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));
        String[] columnas = {"ID", "Pedido", "Plato", "Cantidad", "Descuento"};
        this.dtm = new DefaultTableModel(null, columnas);
        String[] reg = new String[5];
        for (DetallePedido d : lista) {
            reg[0] = Integer.toString(d.getIdDetalle());
            reg[1] = Integer.toString(d.getIdPedido());
            reg[2] = Integer.toString(d.getIdPlato());
            reg[3] = Integer.toString(d.getCantidad());
            reg[4] = Float.toString(d.getDescuento());
            this.dtm.addRow(reg);
        }
        return this.dtm;
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
