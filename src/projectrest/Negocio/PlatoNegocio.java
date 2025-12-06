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

        String[] columnas = {"ID", "Código", "Nombre", "Descripción", "Categoría", "Precio", "Estado"};
        this.dtm = new DefaultTableModel(null, columnas);
        String[] reg = new String[7];
        String categoriaPlato;

        for (Plato p : lista) {
            switch (p.getCategoriaPlato()) {
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
            reg[0] = Integer.toString(p.getIdPlato());
            reg[1] = p.getCodigo();
            reg[2] = p.getNombre();
            reg[3] = p.getDescripcion();
            reg[4] = categoriaPlato;
            reg[5] = Float.toString(p.getPrecio());
            reg[6] = p.isEstadoPlato() ? "Disponible" : "No disponible";
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

    public String generarCodigo() {
        return DATOS.obtenerSiguienteCodigo();
    }
}
