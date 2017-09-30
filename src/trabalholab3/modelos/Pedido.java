package trabalholab3.modelos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    
    private int id;
    
    private String descricao;
    
    private Mesa mesa;
    
    private LocalTime hora_abertura;
    
    private LocalTime hora_fechamento;
    
    private List<ItemPedido> itemPedido;
    
    private boolean fechado;
    
    public Pedido(Mesa mesa, LocalTime hora_abertura) {
        this.mesa = mesa;
        this.hora_abertura = hora_abertura;
        this.id = this.mesa.gerarCodigoPedido();
        this.descricao = "Pedido " + id;
        this.itemPedido = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public Mesa getMesa() {
        return mesa;
    }
    
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    public LocalTime getHora_abertura() {
        return hora_abertura;
    }
    
    public void setHora_abertura(LocalTime hora_abertura) {
        this.hora_abertura = hora_abertura;
    }
    
    public LocalTime getHora_fechamento() {
        return hora_fechamento;
    }
    
    public void setHora_fechamento(LocalTime hora_fechamento) {
        this.hora_fechamento = hora_fechamento;
    }
    
    public List<ItemPedido> getItemPedido() {
        return itemPedido;
    }
    
    public void setItemPedido(List<ItemPedido> itemPedido) {
        this.itemPedido = itemPedido;
    }
    
    public boolean isFechado() {
        return fechado;
    }
    
    public void setFechado(boolean fechado) {
        this.fechado = fechado;
    }
    
    public Double getValorTotal() {
        Double valorTotal = 0.0;
        for (ItemPedido item : itemPedido) {
            valorTotal += item.getValorTotal();
        }
        return valorTotal;
    }
    
    public void adicionarItem(ItemPedido i) {
        itemPedido.add(i);
    }
    
    public void removerItem(ItemPedido i) {
        itemPedido.remove(i);
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n Descrição:" + descricao + "\n Mesa: " + mesa + "\n Hora de Abertura: " + hora_abertura + ", Hora de fechamento: " + hora_fechamento + " fechado=" + fechado + '}';
    }
    
    
}
