/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import projectrest.Datos.ClienteDAO;
import projectrest.Entidades.Cliente;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael
 */
public class ClienteNegocio {

    private final ClienteDAO DATOS;
    private DefaultTableModel dtm;

    public ClienteNegocio() {
        this.DATOS = new ClienteDAO();
    }

    public DefaultTableModel listar(String texto) {
        List<Cliente> lista = new ArrayList();
        lista.addAll(DATOS.listar(texto));

        String[] columnas = {"ID", "Nombres", "Categoria", "Correo", "Telefono"};
        this.dtm = new DefaultTableModel(null, columnas);
        String[] registro = new String[5];
        String estado;
        for (Cliente item : lista) {
            registro[0] = Integer.toString(item.getIdCliente());
            registro[1] = item.getNombreCompleto();
            registro[2] = item.getCategoriaCliente();
            registro[3] = item.getCorreo();
            registro[4] = item.getTelefono();

            this.dtm.addRow(registro);
        }

        return this.dtm;
    }

    public String insertar(Cliente cliente) {
        return DATOS.insertar(cliente) ? "OK" : "Error en la inserción";
    }

    public String editar(Cliente cliente) {
        return DATOS.editar(cliente) ? "OK" : "Error en la inserción";
    }

    public String eliminar(int idCliente) {
        return DATOS.eliminar(idCliente) ? "OK" : "Error en la inserción";
    }
}
