/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import projectrest.Datos.PedidoDAO;
import projectrest.Entidades.Pedido;
import projectrest.Entidades.DetallePedido;
import javax.swing.table.DefaultTableModel;

import projectrest.Entidades.Cliente;
import projectrest.Datos.ClienteDAO;

import projectrest.Entidades.Mesa;
import projectrest.Datos.MesaDAO;

import projectrest.Entidades.Empleado;
import projectrest.Datos.EmpleadoDAO;

import projectrest.Entidades.Plato;
import projectrest.Datos.PlatoDAO;

public class PedidoNegocio {

    private final PedidoDAO DATOS;
    private final ClienteDAO DATOSCLI;
    private final MesaDAO DATOSME;
    private final EmpleadoDAO DATOSEMP;
    private final PlatoDAO DATOSPLA;
    private DefaultTableModel dtm;
    private Pedido obj;

    public PedidoNegocio() {
        this.DATOS = new PedidoDAO();
        this.DATOSCLI = new ClienteDAO();
        this.DATOSME = new MesaDAO();
        this.DATOSEMP = new EmpleadoDAO();
        this.DATOSPLA = new PlatoDAO();
        this.obj = new Pedido();
    }

    public DefaultTableModel listar(String texto) {
        List<Pedido> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        String[] titulos = {"Id", "Mesa ID", "Nro Mesa", "Cliente ID", "Cliente", "Empleado ID", "Empleado", "Comprobante", "Serie", "Correlativo", "Fecha", "Total", "Estado"};
        this.dtm = new DefaultTableModel(null, titulos);

        String[] registro = new String[13];
        String comp;
        for (Pedido item : lista) {
            switch (item.getTipoComprobante()) {
                case "N":
                    comp = "Nota";
                    break;
                case "B":
                    comp = "Boleta";
                    break;
                case "F":
                    comp = "Factura";
                    break;
                default:
                    comp = "Nota";
            }
            registro[0] = Integer.toString(item.getIdPedido());
            registro[1] = Integer.toString(item.getIdMesa());
            registro[2] = item.getNroMesa();
            registro[3] = Integer.toString(item.getIdCliente());
            registro[4] = item.getCliente();
            registro[5] = Integer.toString(item.getIdEmpleado());
            registro[6] = item.getEmpleado();
            registro[7] = comp;
            registro[8] = item.getSerie();
            registro[9] = Integer.toString(item.getCorrelativo());
            registro[10] = item.getFechaPedido();
            registro[11] = Float.toString(item.getMontoTotal());
            registro[12] = item.isEstado() ? "Activa" : "Anulada";

            this.dtm.addRow(registro);
        }
        return this.dtm;
    }

    public String insertar(int idCliente, int idMesa, String tipoComprobante, String serie, int correlativo, int idEmpleado, float montoTotal, DefaultTableModel modeloDetalles) {
        if (DATOS.existePedido(serie, correlativo)) {
            return "El registro ya existe.";
        } else {
            obj.setIdEmpleado(idEmpleado);
            obj.setIdMesa(idMesa);
            obj.setIdCliente(idCliente);
            obj.setMontoTotal(montoTotal);
            obj.setEstado(true);

            obj.setTipoComprobante(tipoComprobante);
            obj.setSerie(serie);
            obj.setCorrelativo(correlativo);

            List<DetallePedido> detalles = new ArrayList();
            int idPlato;
            int cantidad;
            float descuento;

            for (int i = 0; i < modeloDetalles.getRowCount(); i++) {
                idPlato = Integer.parseInt(String.valueOf(modeloDetalles.getValueAt(i, 0)));
                cantidad = Integer.parseInt(String.valueOf(modeloDetalles.getValueAt(i, 4)));
                descuento = Float.parseFloat(String.valueOf(modeloDetalles.getValueAt(i, 6)));
                detalles.add(new DetallePedido(idPlato, cantidad, descuento));
            }

            obj.setDetalles(detalles);

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro de la venta.";
            }
        }
    }

    public String editarEstado(Pedido pedido) {
        return DATOS.editarEstado(pedido) ? "OK" : "Error en la actualización de estado";
    }

    public DefaultComboBoxModel seleccionarCliente() {
        DefaultComboBoxModel<Cliente> items = new DefaultComboBoxModel<>();

        List<Cliente> lista = new ArrayList();
        lista = DATOSCLI.seleccionar();

        for (Cliente item : lista) {
            items.addElement(new Cliente(item.getIdCliente(), item.getNombreCompleto()));
        }
        return items;
    }

    public DefaultComboBoxModel seleccionarMesa() {
        DefaultComboBoxModel<Mesa> items = new DefaultComboBoxModel<>();

        List<Mesa> lista = new ArrayList();
        lista = DATOSME.seleccionar();

        for (Mesa item : lista) {
            items.addElement(new Mesa(item.getIdMesa(), item.getNroMesa()));
        }
        return items;
    }

    public DefaultComboBoxModel seleccionarEmpleado() {
        DefaultComboBoxModel<Empleado> items = new DefaultComboBoxModel<>();

        List<Empleado> lista = new ArrayList();
        lista = DATOSEMP.seleccionar();

        for (Empleado item : lista) {
            items.addElement(new Empleado(item.getIdEmpleado(), item.getNombreCompleto()));
        }
        return items;
    }

    public DefaultComboBoxModel seleccionarPlato() {
        DefaultComboBoxModel<Plato> items = new DefaultComboBoxModel<>();

        List<Plato> lista = new ArrayList();
        lista = DATOSPLA.seleccionar();

        for (Plato item : lista) {
            items.addElement(new Plato(item.getIdPlato(), item.getNombre(), item.getPrecio(), item.getCategoriaPlato(), item.getCodigo()));
        }
        return items;
    }
}
