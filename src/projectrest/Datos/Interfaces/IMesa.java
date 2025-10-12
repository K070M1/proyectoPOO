/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectrest.Datos.Interfaces;

import projectrest.Entidades.Mesa;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface IMesa {

    public List<Mesa> listar(String texto);

    public boolean insertar(Mesa mesa);

    public boolean eliminar(Mesa mesa);

    public boolean editar(Mesa mesa);
}
