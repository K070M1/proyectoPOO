/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectrest.Datos.Interfaces;

import projectrest.Entidades.Calificacion;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface ICalificacion {

    public List<Calificacion> listar(String texto);

    public boolean insertar(Calificacion calificacion);

    public boolean eliminar(int id);

    public boolean editar(Calificacion calificacion);
}
