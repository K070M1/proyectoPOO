/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectrest.Datos.Interfaces;

import projectrest.Entidades.Empleado;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface IEmpleado {

    public List<Empleado> listar(String texto);

    public boolean insertar(Empleado empleado);

    public boolean eliminar(Empleado empleado);

    public boolean editar(Empleado empleado);
}
