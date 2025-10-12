/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

/**
 *
 * @author Rafael
 */
public class DetallePedido {

    private int idDetalle;
    private int idPedido;
    private int idPlato;
    private int cantidad;
    private float descuento;

    public DetallePedido() {
    }

    public DetallePedido(int idDetalle, int idPedido, int idPlato, int cantidad, float descuento) {
        this.idDetalle = idDetalle;
        this.idPedido = idPedido;
        this.idPlato = idPlato;
        this.cantidad = cantidad;
        this.descuento = descuento;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdPlato() {
        return idPlato;
    }

    public void setIdPlato(int idPlato) {
        this.idPlato = idPlato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    @Override
    public String toString() {
        return "DetallePedido{" + "idDetalle=" + idDetalle + ", idPedido=" + idPedido + ", idPlato=" + idPlato + ", cantidad=" + cantidad + ", descuento=" + descuento + '}';
    }
}
