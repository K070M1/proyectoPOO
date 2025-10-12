/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectrest.Entidades;

/**
 *
 * @author Rafael
 */
public class Comprobante {

    private int idComprobante;
    private int idPedido;
    private String serie;
    private int correlativo;
    private String tipoComprobante;

    public Comprobante() {
    }

    public Comprobante(int idComprobante, int idPedido, String serie, int correlativo, String tipoComprobante) {
        this.idComprobante = idComprobante;
        this.idPedido = idPedido;
        this.serie = serie;
        this.correlativo = correlativo;
        this.tipoComprobante = tipoComprobante;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
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

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    @Override
    public String toString() {
        return "Comprobante{" + "idComprobante=" + idComprobante + ", idPedido=" + idPedido + ", serie=" + serie + ", correlativo=" + correlativo + ", tipoComprobante=" + tipoComprobante + '}';
    }
}
