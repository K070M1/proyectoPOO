package projectrest.Datos;

import projectrest.Entidades.Plato;
import java.util.ArrayList;
import java.util.List;

public class PlatoDAO {
    private List<Plato> platos = new ArrayList<>();
    private int nextId = 1;
    
    public PlatoDAO() {
        platos.add(new Plato(1, "P001", "Lomo Saltado", "Plato tradicional peruano", "Plato Principal", 35.0f, true, "lomo.jpg"));
        platos.add(new Plato(2, "P002", "Ceviche", "Ceviche de pescado", "Entrada", 25.0f, true, "ceviche.jpg"));
        platos.add(new Plato(3, "P003", "Suspiro Limeño", "Postre tradicional", "Postre", 15.0f, true, "suspiro.jpg"));
    }
    
    public List<Plato> listarTodos() {
        return new ArrayList<>(platos);
    }
    
    public List<Plato> listarActivos() {
        List<Plato> activos = new ArrayList<>();
        for (Plato plato : platos) {
            if (plato.isEstadoPlato()) {
                activos.add(plato);
            }
        }
        return activos;
    }
    
    public boolean insertar(Plato plato) {
        plato.setIdPlato(nextId++);
        return platos.add(plato);
    }
    
    public boolean actualizar(Plato plato) {
        for (int i = 0; i < platos.size(); i++) {
            if (platos.get(i).getIdPlato() == plato.getIdPlato()) {
                platos.set(i, plato);
                return true;
            }
        }
        return false;
    }
    
    public Plato buscarPorId(int idPlato) {
        for (Plato plato : platos) {
            if (plato.getIdPlato() == idPlato) {
                return plato;
            }
        }
        return null;
    }
    
    public Plato buscarPorCodigo(String codigo) {
        for (Plato plato : platos) {
            if (plato.getCodigo().equals(codigo)) {
                return plato;
            }
        }
        return null;
    }
    
    public List<Plato> buscarPorCategoria(String categoria) {
        List<Plato> resultado = new ArrayList<>();
        for (Plato plato : platos) {
            if (plato.getCategoriaPlato().equalsIgnoreCase(categoria) && plato.isEstadoPlato()) {
                resultado.add(plato);
            }
        }
        return resultado;
    }
    
    public List<Plato> buscarPorNombre(String nombre) {
        List<Plato> resultado = new ArrayList<>();
        for (Plato plato : platos) {
            if (plato.getNombre().toLowerCase().contains(nombre.toLowerCase()) && plato.isEstadoPlato()) {
                resultado.add(plato);
            }
        }
        return resultado;
    }
}