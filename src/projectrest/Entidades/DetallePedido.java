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
    private String codigoPlato;
    private String nombrePlato;
    private float precioPlato;
    private String categoriaPlato;
    private int cantidad;
    private float descuento;
    private float subtotal;

    public DetallePedido() {
    }

    public DetallePedido(int idPlato, String codigoPlato, String nombrePlato, float precioPlato, String categoriaPlato, int cantidad, float descuento, float subtotal) {
        this.idPlato = idPlato;
        this.codigoPlato = codigoPlato;
        this.nombrePlato = nombrePlato;
        this.precioPlato = precioPlato;
        this.categoriaPlato = categoriaPlato;
        this.cantidad = cantidad;
        this.descuento = descuento;
        this.subtotal = subtotal;
    }
    

    public DetallePedido(int idDetalle, int idPedido, int idPlato, int cantidad, float descuento) {
        this.idDetalle = idDetalle;
        this.idPedido = idPedido;
        this.idPlato = idPlato;
        this.cantidad = cantidad;
        this.descuento = descuento;
    }

    public DetallePedido(int idPlato, int cantidad, float descuento) {
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

    public String getCodigoPlato() {
        return codigoPlato;
    }

    public void setCodigoPlato(String codigoPlato) {
        this.codigoPlato = codigoPlato;
    }

    public String getNombrePlato() {
        return nombrePlato;
    }

    public void setNombrePlato(String nombrePlato) {
        this.nombrePlato = nombrePlato;
    }

    public float getPrecioPlato() {
        return precioPlato;
    }

    public void setPrecioPlato(float precioPlato) {
        this.precioPlato = precioPlato;
    }

    public String getCategoriaPlato() {
        return categoriaPlato;
    }

    public void setCategoriaPlato(String categoriaPlato) {
        this.categoriaPlato = categoriaPlato;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }
    
    

    @Override
    public String toString() {
        return "DetallePedido{" + "idDetalle=" + idDetalle + ", idPedido=" + idPedido + ", idPlato=" + idPlato + ", cantidad=" + cantidad + ", descuento=" + descuento + '}';
    }
}
