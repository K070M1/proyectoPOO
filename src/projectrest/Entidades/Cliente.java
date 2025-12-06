/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

/**
 *
 * @author Rafael
 */
public class Cliente {

    private int idCliente;
    private String nombreCompleto;
    private String categoriaCliente;
    private String correo;
    private String telefono;

    public Cliente() {
    }

    public Cliente(int idCliente, String nombreCompleto) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
    }

    public Cliente(int idCliente, String nombreCompleto, String categoriaCliente, String correo, String telefono) {
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.categoriaCliente = categoriaCliente;
        this.correo = correo;
        this.telefono = telefono;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCategoriaCliente() {
        return categoriaCliente;
    }

    public void setCategoriaCliente(String categoriaCliente) {
        this.categoriaCliente = categoriaCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return nombreCompleto;
    }
}
