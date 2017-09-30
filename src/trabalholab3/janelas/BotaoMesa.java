/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalholab3.janelas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import trabalholab3.modelos.Mesa;

/**
 *
 * @author jpdia
 */
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
