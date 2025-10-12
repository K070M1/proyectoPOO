/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

/**
 *
 * @author Rafael
 */
public class Mesa {

    private int idMesa;
    private String nroMesa;
    private int maximoClientes;

    public Mesa() {
    }

    public Mesa(int idMesa, String nroMesa, int maximoClientes) {
        this.idMesa = idMesa;
        this.nroMesa = nroMesa;
        this.maximoClientes = maximoClientes;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getNroMesa() {
        return nroMesa;
    }

    public void setNroMesa(String nroMesa) {
        this.nroMesa = nroMesa;
    }

    public int getMaximoClientes() {
        return maximoClientes;
    }

    public void setMaximoClientes(int maximoClientes) {
        this.maximoClientes = maximoClientes;
    }

    @Override
    public String toString() {
        return "Mesa{" + "idMesa=" + idMesa + ", nroMesa=" + nroMesa + ", maximoClientes=" + maximoClientes + '}';
    }
}
