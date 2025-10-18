package projectrest.Datos;

import projectrest.Entidades.Comprobante;
import java.util.ArrayList;
import java.util.List;

public class ComprobanteDAO {
    private List<Comprobante> comprobantes = new ArrayList<>();
    private int nextId = 1;
    
    public ComprobanteDAO() {
        comprobantes.add(new Comprobante(1, 1, "F001", 1, "Boleta"));
    }
    
    public List<Comprobante> listarTodos() {
        return new ArrayList<>(comprobantes);
    }
    
    public boolean insertar(Comprobante comprobante) {
        comprobante.setIdComprobante(nextId++);
        return comprobantes.add(comprobante);
    }
    
    public Comprobante buscarPorPedido(int idPedido) {
        for (Comprobante comp : comprobantes) {
            if (comp.getIdPedido() == idPedido) {
                return comp;
            }
        }
        return null;
    }
    
    public String generarNumeroComprobante(String tipo) {
        int maxCorrelativo = 0;
        for (Comprobante comp : comprobantes) {
            if (comp.getTipoComprobante().equals(tipo) && comp.getCorrelativo() > maxCorrelativo) {
                maxCorrelativo = comp.getCorrelativo();
            }
        }
        return "F001-" + (maxCorrelativo + 1);
    }
}