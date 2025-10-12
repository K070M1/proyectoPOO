/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectrest.Datos.Interfaces;

import projectrest.Entidades.Plato;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface IPlato {

    public List<Plato> listar(String texto);

    public boolean insertar(Plato plato);

    public boolean eliminar(Plato plato);

    public boolean editar(Plato plato);
}
