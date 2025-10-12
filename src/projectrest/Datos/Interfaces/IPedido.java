/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectrest.Datos.Interfaces;

import projectrest.Entidades.Pedido;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface IPedido {

    public List<Pedido> listar(String texto);

    public boolean insertar(Pedido pedido);

    public boolean eliminar(Pedido pedido);

    public boolean editar(Pedido pedido);
}
