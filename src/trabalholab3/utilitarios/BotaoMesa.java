
package trabalholab3.utilitarios;
import javax.swing.JButton;
import trabalholab3.janelas.JanelaPedidos;
import trabalholab3.modelos.Mesa;

public class BotaoMesa extends JButton {
    private Mesa mesa;
    private JanelaPedidos janelaPedidos;

    public JanelaPedidos getJanelaPedidos() {
        return janelaPedidos;
    }

    public void setJanelaPedidos(JanelaPedidos janelaPedidos) {
        this.janelaPedidos = janelaPedidos;
    }
    
    
    
    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public BotaoMesa(Mesa mesa) {
        this.mesa = mesa;
        setText();
    }
    
    public void setText() {
        super.setText(mesa.toString());
    }
    
    
}
