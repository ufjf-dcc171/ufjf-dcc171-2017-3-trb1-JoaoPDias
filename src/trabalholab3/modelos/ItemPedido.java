package trabalholab3.modelos;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import trabalholab3.Dao.PedidoDAO;
import trabalholab3.interfaces.Gravavel;

public class ItemPedido implements Gravavel {

	private Pedido pedido;

	private Produto produto;

	private int quantidade;
        
        private PedidoDAO pedidodao;

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

    @Override
    public String toString() {
        return "Item: "+getProduto().toString() + " X " + getQuantidade() + " = " +getValorTotal();
    }

    @Override
    public String ToSerial() {
        return this.getPedido().getId()+";"+this.getProduto().ToSerial()+";"+this.getQuantidade()+";"+this.getValorTotal();
    }
    
    /*public static Gravavel ToObject(String s) {
        String[] array = s.split(";");
        Integer id_pedido = Integer.parseInt(array[0]);
        Produto p = (Produto) Produto.ToObject(array[1]+";"+array[2]+";"+array[3]);
        Integer quantidade = Integer.parseInt(array[4]);
        ItemPedido IP = new ItemPedido(id,p,quantidade);
    }

    */
        
        

}
