/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

/**
 *
 * @author Rafael
 */
public class Pedido {

    private int idPedido;
    private int idEmpleado;
    private int idCliente;
    private int idMesa;
    private float montoTotal;
    private String fechaPedido;

    public Pedido() {
    }

    public Pedido(int idPedido, int idEmpleado, int idCliente, int idMesa, float montoTotal, String fechaPedido) {
        this.idPedido = idPedido;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.idMesa = idMesa;
        this.montoTotal = montoTotal;
        this.fechaPedido = fechaPedido;
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
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

    @Override
    public String toString() {
        return "Pedido{" + "idPedido=" + idPedido + ", idEmpleado=" + idEmpleado + ", idCliente=" + idCliente + ", idMesa=" + idMesa + ", montoTotal=" + montoTotal + ", fechaPedido=" + fechaPedido + '}';
    }
}
