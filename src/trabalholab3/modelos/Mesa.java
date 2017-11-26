package trabalholab3.modelos;
import Generator.GeneratorMesa;
import Generator.GeneratorPedido;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import trabalholab3.interfaces.Gravavel;

public class Mesa implements Gravavel {

    private int id;

    private String descricao;

    private List<Pedido> pedido;
    private int gencodigoPedido = 1;
    
    private static final File arq = new File("Dados\\Mesas", "Mesa.txt");
    
    private GeneratorPedido gerador;

    public int gerarCodigoPedido() throws IOException {
        gerador= new GeneratorPedido(this);
        int cod = gerador.getIDPedido();
        gerador.addIDPedido(cod);
        return cod;
    }
    
    

    public Mesa() throws IOException {
        this.id = gerarCodigo();
        this.descricao = "Mesa " + id;
        this.pedido = new ArrayList<>();
    }

    public Mesa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        this.pedido = new ArrayList<>();
    }
    
    
    public int getId() {
        return id;
    }
    public static File getArq(){
        return arq;
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

    public void gerarPedido() throws IOException {
        Pedido p = new Pedido(this,LocalTime.now());
        this.pedido.add(p);
    }
    
    public Pedido criarPedido() throws IOException {
        Pedido p = new Pedido(this,LocalTime.now());
        return p;
    }

    public static int gerarCodigo() throws IOException {
        int cod = GeneratorMesa.getIDMesa();
        GeneratorMesa.addIDMesa(cod);
        return cod;
    }
    
      @Override
    public String ToSerial() {
        return this.getId()+";"+this.getDescricao();
    }

    public static Mesa ToObject(String s) {
        String[] array = s.split(";");
        Integer id = Integer.parseInt(array[0]);
        String descricao = array[1];
        Mesa mesa = new Mesa(id,descricao);
        return mesa;

    }

}
