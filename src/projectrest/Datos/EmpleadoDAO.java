package projectrest.Datos;

import projectrest.Entidades.Empleado;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    private List<Empleado> empleados = new ArrayList<>();
    private int nextId = 1;
    
    public EmpleadoDAO() {
        empleados.add(new Empleado(1, "Ana García", "Mesero", "DNI", "12345678", "ana", "123", "Mañana", true, "2024-01-01", ""));
    }
    
    public List<Empleado> listarTodos() {
        return new ArrayList<>(empleados);
    }
    
    public List<Empleado> listarActivos() {
        List<Empleado> activos = new ArrayList<>();
        for (Empleado emp : empleados) {
            if (emp.isEstado()) {
                activos.add(emp);
            }
        }
        return activos;
    }
    
    public boolean insertar(Empleado empleado) {
        empleado.setIdEmpleado(nextId++);
        return empleados.add(empleado);
    }
    
    public boolean actualizar(Empleado empleado) {
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getIdEmpleado() == empleado.getIdEmpleado()) {
                empleados.set(i, empleado);
                return true;
            }
        }
        return false;
    }
    
    public Empleado buscarPorUsuario(String usuario) {
        for (Empleado emp : empleados) {
            if (emp.getUsuario().equals(usuario) && emp.isEstado()) {
                return emp;
            }
        }
        return null;
    }
    
    public boolean validarLogin(String usuario, String clave) {
        Empleado emp = buscarPorUsuario(usuario);
        return emp != null && emp.getClave().equals(clave);
    }
    
    public List<Empleado> buscarPorRol(String rol) {
        List<Empleado> resultado = new ArrayList<>();
        for (Empleado emp : empleados) {
            if (emp.getRol().equalsIgnoreCase(rol) && emp.isEstado()) {
                resultado.add(emp);
            }
        }
        return resultado;
    }
}