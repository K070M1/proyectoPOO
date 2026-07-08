/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectrest.Datos.Interfaces;

import projectrest.Entidades.DetallePedido;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface IDetallePedido {

    public List<DetallePedido> listar(int id);
}
