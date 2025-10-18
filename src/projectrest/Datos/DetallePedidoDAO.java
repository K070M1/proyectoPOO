package projectrest.Datos;

import projectrest.Entidades.DetallePedido;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO {
    private List<DetallePedido> detalles = new ArrayList<>();
    private int nextId = 1;
    
    public DetallePedidoDAO() {
        detalles.add(new DetallePedido(1, 1, 1, 2, 0));
    }
    
    public List<DetallePedido> listarTodos() {
        return new ArrayList<>(detalles);
    }
    
    public boolean insertar(DetallePedido detalle) {
        detalle.setIdDetalle(nextId++);
        return detalles.add(detalle);
    }
    
    public List<DetallePedido> buscarPorPedido(int idPedido) {
        List<DetallePedido> resultado = new ArrayList<>();
        for (DetallePedido detalle : detalles) {
            if (detalle.getIdPedido() == idPedido) {
                resultado.add(detalle);
            }
        }
        return resultado;
    }
    
    public boolean eliminarPorPedido(int idPedido) {
        return detalles.removeIf(detalle -> detalle.getIdPedido() == idPedido);
    }
    
    public double calcularSubtotalPedido(int idPedido) {
        double subtotal = 0;
        PlatoDAO platoDAO = new PlatoDAO();
        
        for (DetallePedido detalle : detalles) {
            if (detalle.getIdPedido() == idPedido) {
                var plato = platoDAO.buscarPorId(detalle.getIdPlato());
                if (plato != null) {
                    double precio = plato.getPrecio() * detalle.getCantidad();
                    double descuento = precio * (detalle.getDescuento() / 100);
                    subtotal += precio - descuento;
                }
            }
        }
        return subtotal;
    }
}