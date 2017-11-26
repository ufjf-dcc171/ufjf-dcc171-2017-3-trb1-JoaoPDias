package trabalholab3.Dao;

import java.util.ArrayList;
import java.util.List;
import trabalholab3.modelos.Pedido;

public class PedidoDAO {
    List<Pedido> pedidos;

    public PedidoDAO() {
        this.pedidos = new ArrayList<>(this.listarTodos());
       
    }
    
    public void inserir(Pedido pedido) {
    }

    public void alterar(Pedido pedido) {
    }

    public void excluir(Pedido pedido) {
    }

    private List<Pedido> listarTodos(){ 
        return null;
    }

    public Pedido listar(Integer id) {
        return null;
    }

}
