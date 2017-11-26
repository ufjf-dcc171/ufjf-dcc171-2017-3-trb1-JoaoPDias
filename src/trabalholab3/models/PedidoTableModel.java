package trabalholab3.models;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import trabalholab3.Dao.PedidoDAO;
import trabalholab3.modelos.Mesa;
import trabalholab3.modelos.Pedido;

public class PedidoTableModel extends AbstractTableModel {

    private List<Pedido> pedidos;
    private PedidoDAO pedidoDao;

    public PedidoTableModel(Mesa mesa) throws IOException {
        this.pedidoDao = new PedidoDAO(mesa);
        this.pedidos = pedidoDao.getPedidos();
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.toString(pedidos.get(rowIndex).getId());
            case 1:
                return pedidos.get(rowIndex).getDescricao();
            case 2:
                return "R$ " + Double.toString(pedidos.get(rowIndex).getValorTotal());
            case 3:
                return pedidos.get(rowIndex).getHora_abertura().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
            case 4:
                if (pedidos.get(rowIndex).getHora_fechamento() == null) {
                    return "Pedido Aberto";
                } else {
                    return pedidos.get(rowIndex).getHora_fechamento().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT));
                }
            case 5:
                if (pedidos.get(rowIndex).isFechado() == true) {
                    return "Sim";
                } else {
                    return "Não";
                }
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Descrição";
            case 2:
                return "Valor Total";
            case 3:
                return "Hora de Abertura";
            case 4:
                return "Hora de Fechamento";
            case 5:
                return "Fechado?";
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void addRow(Pedido p) throws IOException {
        pedidoDao.inserir(p);
        this.fireTableDataChanged();
    }

    public void removeRow(Pedido p) throws IOException {
        pedidoDao.excluir(p);
        this.fireTableDataChanged();
    }

    public Pedido getRow(int p) {
        return pedidos.get(p);

    }
    
    public boolean fecharPedido(Pedido pedido) throws IOException{
        boolean fechado = this.pedidoDao.fecharPedido(pedido.getId());
        this.pedidos = pedidoDao.getPedidos();
        return fechado;
        
    }

    public boolean haAbertos() {
        for (Pedido p : pedidos) {
            if (p.isFechado() == false) {
                return true;
            }
        }
        return false;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
    
    
}
