package projectrest.Datos;

import projectrest.Entidades.Calificacion;
import java.util.ArrayList;
import java.util.List;

public class CalificacionDAO {
    private List<Calificacion> calificaciones = new ArrayList<>();
    private int nextId = 1;
    
    public CalificacionDAO() {
        // Datos de ejemplo
        calificaciones.add(new Calificacion(1, 1, 5, "Excelente servicio", "2024-01-15"));
    }
    
    public List<Calificacion> listarTodos() {
        return new ArrayList<>(calificaciones);
    }
    
    public boolean insertar(Calificacion calificacion) {
        calificacion.setIdCalificacion(nextId++);
        return calificaciones.add(calificacion);
    }
    
    public List<Calificacion> buscarPorPedido(int idPedido) {
        List<Calificacion> resultado = new ArrayList<>();
        for (Calificacion cal : calificaciones) {
            if (cal.getIdPedido() == idPedido) {
                resultado.add(cal);
            }
        }
        return resultado;
    }
    
    public double obtenerPromedioCalificaciones() {
        if (calificaciones.isEmpty()) return 0;
        double suma = 0;
        for (Calificacion cal : calificaciones) {
            suma += cal.getCalificacion();
        }
        return suma / calificaciones.size();
    }
}