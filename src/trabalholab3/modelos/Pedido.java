package trabalholab3.modelos;

import Generator.GeneratorItemPedido;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import trabalholab3.Dao.ItemPedidoDAO;
import trabalholab3.Dao.MesaDAO;
import trabalholab3.interfaces.Gravavel;

public class Pedido implements Gravavel {

    private int id;

    private String descricao;

    private Mesa mesa;

    private LocalTime hora_abertura;

    private LocalTime hora_fechamento;

    private List<ItemPedido> itemPedido;

    private boolean fechado;

    private static File arq;

    private MesaDAO mesaDao;
    private GeneratorItemPedido gerador;
    ItemPedidoDAO itempedidodao;

    public Pedido(Mesa mesa, LocalTime hora_abertura) throws IOException {
        this.mesa = mesa;
        this.hora_abertura = hora_abertura;
        this.id = this.mesa.gerarCodigoPedido();
        this.descricao = "Pedido " + id;
        arq  = gerarArquivo(mesa);
        this.itempedidodao = new ItemPedidoDAO(this);
        this.itemPedido = itempedidodao.getItemPedidos();
    }

    public Pedido(int id, String descricao, Integer idmesa, LocalTime hora_abertura, boolean fechado) throws IOException {
        this.id = id;
        this.descricao = descricao;
        mesaDao = new MesaDAO();
        Mesa mesa = mesaDao.listar(idmesa);
        this.mesa = mesa;
        this.hora_abertura = hora_abertura;
        this.fechado = fechado;
        arq = gerarArquivo(mesa);
        this.itempedidodao = new ItemPedidoDAO(this);
        this.itemPedido = itempedidodao.getItemPedidos();
    }

    public Pedido(int id, String descricao, Integer idmesa, LocalTime hora_abertura, LocalTime hora_fechamento, boolean fechado) throws IOException {
        this.id = id;
        this.descricao = descricao;
        mesaDao = new MesaDAO();
        Mesa mesa = mesaDao.listar(idmesa);
        this.mesa = mesa;
        this.hora_abertura = hora_abertura;
        this.hora_fechamento = hora_fechamento;
        this.fechado = fechado;
        arq = gerarArquivo(mesa);
        this.itempedidodao = new ItemPedidoDAO(this);
        this.itemPedido = itempedidodao.getItemPedidos();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public File getArq() {
        return arq;
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
        DecimalFormat df = new DecimalFormat("#0.00");
        df.setRoundingMode(RoundingMode.CEILING);
        Number n = 0;
        try {
            n = df.parse(df.format(valorTotal));
        } catch (ParseException ex) {
            System.out.println("Erro na conversão");
        }
        return n.doubleValue();
    }

    @Override
    public String toString() {
        return "\nID: " + id + "\n Descrição:" + descricao + "\n Mesa: " + mesa + "\n Hora de Abertura: " + hora_abertura.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "\n Hora de fechamento: " + hora_fechamento.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "\n Valor Total: R$ " + getValorTotal();
    }

    @Override
    public String ToSerial() {
        String fechamento = this.getHora_fechamento() != null ? this.getHora_fechamento().toString() : "NULO";
        return this.getId() + ";" + this.getMesa().getId() + ";" + this.getDescricao() + ";" + this.getHora_abertura() + ";" + fechamento;
    }

    public static Pedido ToObject(String s) throws IOException {
        String[] array = s.split(";");
        Pedido p;
        Integer id = Integer.parseInt(array[0]);
        Integer id_mesa = Integer.parseInt(array[1]);
        String descricao = array[2];
        LocalTime horaAbertura = LocalTime.parse(array[3]);
        if ("NULO".equals(array[4])) {
            p = new Pedido(id, descricao, id_mesa, horaAbertura, false);
            return p;
        }
        LocalTime horaFechamento = LocalTime.parse(array[4]);
        p = new Pedido(id, descricao, id_mesa, horaAbertura, horaFechamento, true);
        return p;

    }
    
    public static File gerarArquivo(Mesa mesa){
        return new File("Dados\\Pedidos", "Pedido " + mesa.getDescricao()+".txt");
    }
    
     public int gerarCodigoItemPedido() throws IOException {
        gerador= new GeneratorItemPedido(this);
        int cod = gerador.getIDItemPedido();
        gerador.addIDItemPedido(cod);
        return cod;
    }
}
