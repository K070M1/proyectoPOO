/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import projectrest.Datos.ComprobanteDAO;
import projectrest.Entidades.Comprobante;

public class ComprobanteNegocio {

    private final ComprobanteDAO DATOS;
    private DefaultTableModel dtm;

    public ComprobanteNegocio() {
        this.DATOS = new ComprobanteDAO();
    }

    public DefaultTableModel listar(String texto) {
        List<Comprobante> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));
        String[] columnas = {"ID", "Pedido", "Serie", "Correlativo", "Tipo"};
        this.dtm = new DefaultTableModel(null, columnas);
        String[] reg = new String[5];
        for (Comprobante c : lista) {
            reg[0] = Integer.toString(c.getIdComprobante());
            reg[1] = Integer.toString(c.getIdPedido());
            reg[2] = c.getSerie();
            reg[3] = Integer.toString(c.getCorrelativo());
            reg[4] = c.getTipoComprobante();
            this.dtm.addRow(reg);
        }
        return this.dtm;
    }

    public String insertar(Comprobante c) {
        return DATOS.insertar(c) ? "OK" : "Error en la inserción";
    }

    public String editar(Comprobante c) {
        return DATOS.editar(c) ? "OK" : "Error en la actualización";
    }

    public String eliminar(Comprobante c) {
        return DATOS.eliminar(c) ? "OK" : "Error en la eliminación";
    }
}
