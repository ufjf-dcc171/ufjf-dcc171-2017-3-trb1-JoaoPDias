/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalholab3.utilitarios;

import java.util.List;
import javax.swing.JComboBox;
import trabalholab3.modelos.Produto;

public class ProdutoComboBox extends JComboBox {

    private List<Produto> produtos;

    public ProdutoComboBox(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public void setSelectedIndex(int anIndex) {
        int size = dataModel.getSize();

        if (anIndex == -1) {
            setSelectedItem(null);
        } else if (anIndex < -1 || anIndex >= size) {
            throw new IllegalArgumentException("setSelectedIndex: " + anIndex + " out of bounds");
        } else {
            for (Produto p : produtos) {
                if(p.getId()==anIndex){
                setSelectedItem(dataModel.getElementAt(anIndex));
                return;
                }
            }
        }
    }

}
