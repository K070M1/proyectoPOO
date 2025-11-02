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

    public DefaultTableModel listar(String texto) {
        List<Pedido> lista = new ArrayList<>();
        int idCliente = 0;

        try {
            idCliente = Integer.parseInt(texto);
        } catch (NumberFormatException e) {

            String[] columnas = {"ID", "Empleado", "Cliente", "Mesa", "Monto", "Fecha"};
            this.dtm = new DefaultTableModel(null, columnas);
            return this.dtm;
        }

        lista.addAll(DATOS.listar(idCliente));

        String[] columnas = {"ID", "Empleado", "Cliente", "Mesa", "Monto", "Fecha"};
        this.dtm = new DefaultTableModel(null, columnas);
        String[] reg = new String[6];

        for (Pedido p : lista) {
            reg[0] = Integer.toString(p.getIdPedido());
            reg[1] = Integer.toString(p.getIdEmpleado());
            reg[2] = Integer.toString(p.getIdCliente());
            reg[3] = Integer.toString(p.getIdMesa());
            reg[4] = Float.toString(p.getMontoTotal());
            reg[5] = p.getFechaPedido();
            this.dtm.addRow(reg);
        }

        return this.dtm;
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
