package trabalholab3.modelos;

import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import trabalholab3.Dao.PedidoDAO;
import trabalholab3.interfaces.Gravavel;

public class ItemPedido implements Gravavel {
    private Integer id;
    private Pedido pedido;

    private Produto produto;

    private int quantidade;

    private PedidoDAO pedidodao;
    private File arq;

    public double getValorTotal() {
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.CEILING);
        Number n = 0;
        try {
            n = df.parse(df.format(produto.getValor() * quantidade));
        } catch (ParseException ex) {
            System.out.println("Erro na conversão");
        }
        return n.doubleValue();
    }

    public Integer getId() {
        return id;
    }
    

    public ItemPedido(Integer id_pedido, Produto produto, int quantidade) throws IOException {
       // pedidodao = new PedidoDao();
        Pedido ped = pedidodao.listar(id_pedido);
        this.pedido = ped;
        this.id = this.pedido.gerarCodigoItemPedido();
        this.produto = produto;
        this.quantidade = quantidade;
        this.arq = gerarArquivo(pedido);
        
    }

    public ItemPedido(Pedido pedido, Produto produto, int quantidade) throws IOException {
        this.pedido = pedido;
        this.id = this.pedido.gerarCodigoItemPedido();
        this.produto = produto;
        this.quantidade = quantidade;
        this.arq = gerarArquivo(pedido);
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
        return "Item: " + getProduto().toString() + " X " + getQuantidade() + " = " + getValorTotal();
    }

    @Override
    public String ToSerial() {
        return this.getPedido().getId() + ";" + this.getProduto().ToSerial() + ";" + this.getQuantidade() + ";";
    }

    public static ItemPedido ToObject(String s,Pedido pedido) throws IOException {
        String[] array = s.split(";");
        if(pedido.getId()==Integer.parseInt(array[0])){
        Produto p = (Produto) Produto.ToObject(array[1] + ";" + array[2] + ";" + array[3]);
        Integer quantidade = Integer.parseInt(array[4]);
        ItemPedido IP = new ItemPedido(pedido, p, quantidade);
        return IP;
        }else{
            throw new IOException();
        }
        
    }

    public static File gerarArquivo(Pedido pedido) {
        return new File("Dados\\ItensPedidos", "ItemPedido " + pedido.getDescricao() + pedido.getMesa().getDescricao() + ".txt");
    }

}
