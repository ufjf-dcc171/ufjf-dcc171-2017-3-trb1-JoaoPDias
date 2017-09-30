package trabalholab3.janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import trabalholab3.modelos.Mesa;
import trabalholab3.modelos.Pedido;
import trabalholab3.modelos.Produto;
import trabalholab3.models.PedidoTableModel;

public class JanelaPedidos extends JFrame {

    private JTable tabela;
    private JButton adicionarPedido = new JButton("Adicionar Pedido");
    private JButton visualizarItem = new JButton("Visualizar Itens");
    private JButton fecharPedido = new JButton("Fechar Pedido");
    private JButton salvar = new JButton("Salvar");
    private List<Pedido> dados;
    private Mesa mesa;
    private List<Produto> produtos;
    private JanelaItemPedido itemPedido;

    public JanelaPedidos(List<Produto> p, Mesa m, List<Pedido> data) throws HeadlessException {
        super("Gerenciador de Pedidos");
        setMinimumSize(new Dimension(500, 500));
        this.dados = data;
        this.mesa = m;
        this.produtos = p;
        tabela = new JTable(new PedidoTableModel(dados));
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        adicionarPedido.setBackground(new Color(034, 139, 034));
        adicionarPedido.setForeground(Color.WHITE);
        visualizarItem.setBackground(new Color(000, 000, 205));
        visualizarItem.setForeground(Color.WHITE);
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 2));
        botoes.add(adicionarPedido);
        botoes.add(visualizarItem);
        add(botoes, BorderLayout.SOUTH);
        adicionarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoTableModel modelo = (PedidoTableModel) tabela.getModel();
                if (modelo.haAbertos() == false) {
                    Pedido p = mesa.criarPedido();
                    modelo.addRow(p);
                } else {
                    JOptionPane.showMessageDialog(null, "Há um pedido aberto. Utilize-o ou feche-o e tente novamente", "Alerta", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        visualizarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabela.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    if (itemPedido == null) {
                        PedidoTableModel modelo = (PedidoTableModel) tabela.getModel();
                        JanelaItemPedido item = new JanelaItemPedido(produtos, modelo.getRow(tabela.getSelectedRow()), getInstance());
                        itemPedido = item;
                        item.solicitaItemPedido();
                    } else {
                        itemPedido.solicitaItemPedido();
                    }
                }
            }
        });
    }

    public void solicitaPedido() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JanelaPedidos getInstance() {
        return this;
    }

    public void atualizaTabela() {
        PedidoTableModel modelo = (PedidoTableModel) tabela.getModel();
        modelo.fireTableDataChanged();
    }
}
