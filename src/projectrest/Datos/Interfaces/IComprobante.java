/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projectrest.Datos.Interfaces;

import projectrest.Entidades.Comprobante;
import java.util.List;

/**
 *
 * @author Rafael
 */
public interface IComprobante {

    public List<Comprobante> listar(String texto);

    public boolean insertar(Comprobante comprobante);

    public boolean eliminar(Comprobante comprobante);

    public boolean editar(Comprobante comprobante);
}
