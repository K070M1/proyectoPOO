package projectrest.Datos;

import projectrest.Entidades.Pedido;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    private List<Pedido> pedidos = new ArrayList<>();
    private int nextId = 1;
    
    public PedidoDAO() {
        pedidos.add(new Pedido(1, 1, 1, 1, 85.0f, "2024-01-15"));
    }
    
    public List<Pedido> listarTodos() {
        return new ArrayList<>(pedidos);
    }
    
    public boolean insertar(Pedido pedido) {
        pedido.setIdPedido(nextId++);
        return pedidos.add(pedido);
    }
    
    public Pedido buscarPorId(int idPedido) {
        for (Pedido pedido : pedidos) {
            if (pedido.getIdPedido() == idPedido) {
                return pedido;
            }
        }
        return null;
    }
    
    public List<Pedido> buscarPorMesa(int idMesa) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getIdMesa() == idMesa) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }
    
    public List<Pedido> buscarPorFecha(String fecha) {
        List<Pedido> resultado = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            if (pedido.getFechaPedido().equals(fecha)) {
                resultado.add(pedido);
            }
        }
        return resultado;
    }
    
    public double calcularTotalVentasDelDia(String fecha) {
        double total = 0;
        for (Pedido pedido : pedidos) {
            if (pedido.getFechaPedido().equals(fecha)) {
                total += pedido.getMontoTotal();
            }
        }
        return total;
    }
}