package trabalholab3.janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import trabalholab3.Dao.PedidoDAO;
import trabalholab3.modelos.Mesa;
import trabalholab3.modelos.Pedido;
import trabalholab3.modelos.Produto;
import trabalholab3.models.PedidoTableModel;
import trabalholab3.util.Mensagem;

public class JanelaPedidos extends JFrame {

    private JTable tabela;
    private JButton adicionarPedido = new JButton("Adicionar Pedido");
    private JButton gerenciarItem = new JButton("Gerenciar Itens");
    private JButton fecharPedido = new JButton("Fechar Pedido");
    private JButton salvar = new JButton("Salvar");
    private Mesa mesa;
    private List<Produto> produtos;
    private PedidoDAO pedidodao;

    public JanelaPedidos(List<Produto> p, Mesa m) throws HeadlessException, IOException {
        super("Gerenciador de Pedidos");
        setMinimumSize(new Dimension(500, 500));
        this.mesa = m;
        this.produtos = p;
        pedidodao = new PedidoDAO(this.mesa);
        tabela = new JTable(new PedidoTableModel(mesa));
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        adicionarPedido.setBackground(new Color(034, 139, 034));
        adicionarPedido.setForeground(Color.WHITE);
        gerenciarItem.setBackground(new Color(000, 000, 205));
        gerenciarItem.setForeground(Color.WHITE);
        fecharPedido.setBackground(Color.red);
        fecharPedido.setForeground(Color.white);
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 3));
        botoes.add(adicionarPedido);
        botoes.add(gerenciarItem);
        botoes.add(fecharPedido);
        add(botoes, BorderLayout.SOUTH);
        adicionarPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PedidoTableModel modelo = (PedidoTableModel) tabela.getModel();
                if (modelo.haAbertos() == false) {
                    try {
                        Pedido p = mesa.criarPedido();
                        modelo.addRow(p);
                    } catch (IOException ex) {
                        Mensagem.erroAcesso(getInstance(), "Erro ao adicionar a pedido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Há um pedido aberto. Utilize-o ou feche-o e tente novamente", "Alerta", JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        gerenciarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabela.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    PedidoTableModel modelo = (PedidoTableModel) tabela.getModel();
                    JanelaItemPedido item;
                    try {
                        item = new JanelaItemPedido(produtos, modelo.getRow(tabela.getSelectedRow()), getInstance());
                        item.solicitaItemPedido();
                    } catch (IOException ex) {
                        Mensagem.erroAcesso(getInstance(), "Erro ao adicionar o item.");
                    }
                    /*if (dados.get(0).getItemPedido().size() <= 0 && dados.get(0).isFechado() == false) {
                        gerarItensPedido();
                    }*/
                    

                }
            }
        });
        fecharPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean fechado = false;
                Pedido pedido;
                if (tabela.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    PedidoTableModel modelo = (PedidoTableModel) tabela.getModel();
                    try {
                        pedido = modelo.getRow(tabela.getSelectedRow());
                        fechado = modelo.fecharPedido(pedido);
                        if (fechado == true) {
                            JOptionPane.showMessageDialog(null, "Pedido Fechado", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            pedido = modelo.getRow(tabela.getSelectedRow());
                            JanelaFinalizacao janela = new JanelaFinalizacao(pedido);
                            janela.solicitaFinalizacao();
                            atualizaTabela();
                        } else {
                            JOptionPane.showMessageDialog(null, "Pedido já Fechado", "Alerta", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException ex) {
                        Mensagem.erroAcesso(getInstance(), "Erro ao fechar o pedido.");
                    }

                }
            }
        });

    }

    public void solicitaPedido() {
        setLocationRelativeTo(null);
        setVisible(true);
        tabela.clearSelection();
    }

    public JanelaPedidos getInstance() {
        return this;
    }

    public void atualizaTabela() {
        PedidoTableModel modelo = (PedidoTableModel) tabela.getModel();
        modelo.fireTableDataChanged();
    }

    /* public void gerarItensPedido() {
        for (int i = 0; i < 30; i++) {
                Random r = new Random();
                Produto p = produtos.get(r.nextInt(10));
                ItemPedido item = new ItemPedido(dados.get(0), p, 5);
                dados.get(0).getItemPedido().add(item);
        }

    }*/
}
