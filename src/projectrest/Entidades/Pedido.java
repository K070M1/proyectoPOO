/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

import java.util.List;

/**
 *
 * @author Rafael
 */
public class Pedido {

    private int idPedido;
    private int idEmpleado;
    private String empleado;
    private int idCliente;
    private String cliente;
    private int idMesa;
    private String nroMesa;
    private float montoTotal;
    private String fechaPedido;
    private boolean estado;
    private String tipoComprobante;
    private String serie;
    private int correlativo;
    private List<DetallePedido> detalles;

    public Pedido() {
    }

    public Pedido(int idPedido, int idEmpleado, String empleado, int idCliente, String cliente, int idMesa, String nroMesa, float montoTotal, String fechaPedido, boolean estado, String tipoComprobante, String serie, int correlativo) {
        this.idPedido = idPedido;
        this.idEmpleado = idEmpleado;
        this.empleado = empleado;
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.idMesa = idMesa;
        this.nroMesa = nroMesa;
        this.montoTotal = montoTotal;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.correlativo = correlativo;
    }

    public Pedido(int idPedido, int idEmpleado, String empleado, int idCliente, String cliente, int idMesa, String nroMesa, float montoTotal, String fechaPedido, boolean estado, String tipoComprobante, String serie, int correlativo, List<DetallePedido> detalles) {
        this.idPedido = idPedido;
        this.idEmpleado = idEmpleado;
        this.empleado = empleado;
        this.idCliente = idCliente;
        this.cliente = cliente;
        this.idMesa = idMesa;
        this.nroMesa = nroMesa;
        this.montoTotal = montoTotal;
        this.fechaPedido = fechaPedido;
        this.estado = estado;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.correlativo = correlativo;
        this.detalles = detalles;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }

}
