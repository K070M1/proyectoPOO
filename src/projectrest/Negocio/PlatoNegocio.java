/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Negocio;

import java.util.List;
import java.util.ArrayList;
import projectrest.Datos.PlatoDAO;
import projectrest.Entidades.Plato;
import javax.swing.table.DefaultTableModel;

public class PlatoNegocio {

    private final PlatoDAO DATOS;
    private DefaultTableModel dtm;

    public PlatoNegocio() {
        this.DATOS = new PlatoDAO();
    }

    public DefaultTableModel listar(String texto) {
        List<Plato> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));
        String[] columnas = {"ID", "Código", "Nombre", "Descripción", "Categoría", "Precio", "Estado", "Imagen"};
        this.dtm = new DefaultTableModel(null, columnas);
        String[] reg = new String[8];
        for (Plato p : lista) {
            reg[0] = Integer.toString(p.getIdPlato());
            reg[1] = p.getCodigo();
            reg[2] = p.getNombre();
            reg[3] = p.getDescripcion();
            reg[4] = p.getCategoriaPlato();
            reg[5] = Float.toString(p.getPrecio());
            reg[6] = p.isEstadoPlato() ? "Disponible" : "No disponible";
            reg[7] = p.getImagenReferencia();
            this.dtm.addRow(reg);
        }
        return this.dtm;
    }

    public String insertar(Plato plato) {
        return DATOS.insertar(plato) ? "OK" : "Error en la inserción";
    }

    public String editar(Plato plato) {
        return DATOS.editar(plato) ? "OK" : "Error en la inserción";
    }

    public String eliminar(Plato plato) {
        return DATOS.eliminar(plato) ? "OK" : "Error en la inserción";
    }
}
