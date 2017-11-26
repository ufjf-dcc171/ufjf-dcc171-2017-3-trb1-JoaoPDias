
package trabalholab3.models;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import trabalholab3.modelos.Produto;

public class ProdutoTableModel extends AbstractTableModel {

    private List<Produto> produtos;

    public ProdutoTableModel(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.toString(produtos.get(rowIndex).getId());
            case 1:
                return produtos.get(rowIndex).getDescricao();
            case 2:
                return Double.toString(produtos.get(rowIndex).getValor());
            default:
                throw new IndexOutOfBoundsException();
        }
    }

    public void setValueAt(String value,int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                produtos.get(rowIndex).setId((Integer.parseInt(value)));
                break;
            case 1:
                produtos.get(rowIndex).setDescricao(value);
                break;
            case 2:
                produtos.get(rowIndex).setValor(Double.parseDouble(value));
                break;
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
                return "Valor";
            default:
                throw new IndexOutOfBoundsException();
        }
    }
    
    public void addRow(Produto p){
        produtos.add(p);
        this.fireTableDataChanged();
    }
    public void removeRow(int p){
        produtos.remove(p);
        this.fireTableDataChanged();
    }

}
