package trabalholab3.modelos;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private int id;

    private String descricao;

    private List<Pedido> pedido;

    private static int gencodigo = 0;
    private int gencodigoPedido = 0;

    public int gerarCodigoPedido() {
        int cod = gencodigoPedido;
        gencodigoPedido++;
        return cod;
    }

    public Mesa() {
        this.id = gerarCodigo();
        this.descricao = "Mesa " + id;
        this.pedido = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Pedido> getPedido() {
        return pedido;
    }

    public void gerarPedido() {
        Pedido p = new Pedido(this,LocalTime.now());
        this.pedido.add(p);
    }
    
    public Pedido criarPedido() {
        Pedido p = new Pedido(this,LocalTime.now());
        return p;
    }

    public static int gerarCodigo() {
        int cod = gencodigo;
        gencodigo++;
        return cod;
    }

}
