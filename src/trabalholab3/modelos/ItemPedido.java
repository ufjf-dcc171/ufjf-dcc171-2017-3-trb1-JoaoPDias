package trabalholab3.modelos;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

public class ItemPedido {

	private Pedido pedido;

	private Produto produto;

	private int quantidade;


    public double getValorTotal() {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.CEILING);
        Number n = 0;
        try{
        n = df.parse(df.format(produto.getValor()*quantidade));
        }catch (ParseException ex){
            System.out.println("Erro na conversão");
        }
        return n.doubleValue();
    }

    public ItemPedido(Pedido pedido, Produto produto, int quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    
        
        

}
