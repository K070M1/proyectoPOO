/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectrest.Datos.Interfaces;

import projectrest.Entidades.Cliente;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface ICliente {

    public List<Cliente> listar(String texto);

    public boolean insertar(Cliente cliente);

    public boolean eliminar(int idCliente);

    public boolean editar(Cliente cliente);
}
