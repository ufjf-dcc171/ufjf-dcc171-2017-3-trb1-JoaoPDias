package trabalholab3.modelos;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import trabalholab3.interfaces.Gravavel;

public class Pedido implements Gravavel {

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

    public void adicionarItem(ItemPedido i) {
        itemPedido.add(i);
    }

    public void removerItem(ItemPedido i) {
        itemPedido.remove(i);
    }

    @Override
    public String toString() {
        return "\nID: " + id + "\n Descrição:" + descricao + "\n Mesa: " + mesa + "\n Hora de Abertura: " + hora_abertura.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "\n Hora de fechamento: " + hora_fechamento.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "\n Valor Total: R$ " + getValorTotal();
    }

     @Override
    public String ToSerial() {
        
        return this.getId()+";"+this.getDescricao()+";"+this.getMesa().getId()+";"+this.getHora_abertura()+";"+this.getHora_fechamento()+";"+this.isFechado();
    }

   /* public static Pedido ToObject(String s) {
        String[] array = s.split(";");
        Integer id = Integer.parseInt(array[0]);
        String descricao = array[1];
        Double valor = Double.parseDouble(array[2]);
        

    }
*/
}
