
package trabalholab3.models;

import java.io.IOException;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import trabalholab3.Dao.ItemPedidoDAO;
import trabalholab3.modelos.ItemPedido;
import trabalholab3.modelos.Pedido;
import trabalholab3.modelos.Produto;

public class ItemPedidoTableModel extends AbstractTableModel {
    private List<ItemPedido> itemPedidos;
    private ItemPedidoDAO itempedidoDao;

    public ItemPedidoTableModel(Pedido pedido) throws IOException {
        this.itempedidoDao = new ItemPedidoDAO(pedido);
        this.itemPedidos = itempedidoDao.getItemPedidos();
    }

    @Override
    public int getRowCount() {
        return itemPedidos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return itemPedidos.get(rowIndex).getProduto().getDescricao();
            case 1:
                return Integer.toString(itemPedidos.get(rowIndex).getQuantidade());
            case 2:
                return "R$ "+Double.toString(itemPedidos.get(rowIndex).getValorTotal());
            default:
                throw new IndexOutOfBoundsException();
        }
    }
     public void setValueAt(Object value,int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                itemPedidos.get(rowIndex).setProduto(((Produto)value).clonar());
                break;
            case 1:
                itemPedidos.get(rowIndex).setQuantidade((Integer.parseInt((String)value)));
                break;
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public String getColumnName(int column) {

        switch (column) {
            case 0:
                return "Descrição do Produto";
            case 1:
                return "Quantidade";
            case 2:
                return "Valor Total";
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    public void addRow(ItemPedido p) throws IOException{
        itempedidoDao.inserir(p);
        this.fireTableDataChanged();
    }
    public void removeRow(ItemPedido p) throws IOException{
        itempedidoDao.excluir(p);
        this.fireTableDataChanged();
    }
    
    public boolean alterarItemPedido(ItemPedido itempedido) throws IOException{
        boolean fechado = this.itempedidoDao.alterarItemPedido(itempedido.getId());
        this.itemPedidos = this.itempedidoDao.getItemPedidos();
        return fechado;
        
    }
    
       public ItemPedido getRow(int p){
        return itemPedidos.get(p);
        
    }
}
