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

    public DefaultTableModel listar(int id) {
        List<DetallePedido> lista = new ArrayList();
        lista.addAll(DATOS.listar(id));

        String[] titulos = {"Id", "CODIGO", "CATEGORIA", "PLATO", "CANTIDAD", "PRECIO", "DESCUENTO", "SUBTOTAL"};
        this.dtm = new DefaultTableModel(null, titulos);

        String[] registro = new String[8];
        String categoriaPlato;

        for (DetallePedido item : lista) {
            switch (item.getCategoriaPlato()) {
                case "P":
                    categoriaPlato = "Principal";
                    break;
                case "S":
                    categoriaPlato = "Sopas";
                    break;
                case "B":
                    categoriaPlato = "Bebidas";
                    break;
                default:
                    categoriaPlato = "Principal";

            }
            registro[0] = Integer.toString(item.getIdPlato());
            registro[1] = item.getCodigoPlato();
            registro[2] = categoriaPlato;
            registro[3] = item.getNombrePlato();
            registro[4] = Integer.toString(item.getCantidad());
            registro[5] = Float.toString(item.getPrecioPlato());
            registro[6] = Double.toString(item.getDescuento());
            registro[7] = Double.toString(item.getSubtotal());
            this.dtm.addRow(registro);
        }
        return this.dtm;
    }
}
