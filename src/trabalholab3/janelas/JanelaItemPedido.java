package trabalholab3.janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import trabalholab3.modelos.ItemPedido;
import trabalholab3.modelos.Pedido;
import trabalholab3.modelos.Produto;
import trabalholab3.models.ItemPedidoTableModel;
import trabalholab3.models.ProdutoComboBox;

public class JanelaItemPedido extends JFrame {

    private ProdutoComboBox combo;
    private List<Produto> dadosProdutos;
    private Pedido pedido;
    private JLabel lblProduto = new JLabel("Produto: ");
    private JLabel lblQuantidade = new JLabel("Quantidade: ");
    private JTextField txtQuantidade = new JTextField(10);
    private JButton salvarItem = new JButton("Salvar Alterações");
    private JButton adicionarItem = new JButton("Adicionar Item");
    private JButton removerItem = new JButton("Remover Item");
    private JTable tabela;
    private JanelaPedidos janelaPedido;

    public JanelaItemPedido(List<Produto> dadosProdutos, Pedido p, JanelaPedidos j) {
        super("Itens do Pedido");
        setMinimumSize(new Dimension(500, 500));
        this.dadosProdutos = dadosProdutos;
        this.pedido = p;
        this.janelaPedido = j;
        combo = new ProdutoComboBox(dadosProdutos);
        JPanel formulario = new JPanel();
        JPanel campoItem = new JPanel();
        JPanel campoQuantidade = new JPanel();
        JPanel campoBotao = new JPanel();
        JPanel campoJuncao = new JPanel();
        campoItem.setLayout(new GridLayout(2, 1));
        campoQuantidade.setLayout(new GridLayout(2, 1));
        campoBotao.setLayout(new GridLayout(2, 1));
        campoItem.add(lblProduto);
        campoItem.add(combo);
        campoQuantidade.add(lblQuantidade);
        campoQuantidade.add(txtQuantidade);
        campoBotao.add(salvarItem);
        campoBotao.add(adicionarItem);
        campoJuncao.setLayout(new GridLayout(1, 2));
        campoJuncao.add(campoQuantidade);
        campoJuncao.add(campoBotao);
        formulario.setLayout(new GridLayout(2, 1));
        formulario.add(campoItem);
        formulario.add(campoJuncao);
        salvarItem.setBackground(new Color(000, 000, 205));
        salvarItem.setForeground(Color.WHITE);
        adicionarItem.setBackground(new Color(034, 139, 034));
        adicionarItem.setForeground(Color.WHITE);
        removerItem.setBackground(Color.red);
        removerItem.setForeground(Color.WHITE);
        add(formulario, BorderLayout.NORTH);
        tabela = new JTable(new ItemPedidoTableModel(pedido.getItemPedido()));
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 1));
        botoes.add(removerItem);
        add(botoes, BorderLayout.SOUTH);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        adicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ItemPedidoTableModel modelo = (ItemPedidoTableModel) tabela.getModel();
                    Produto prod = (Produto) combo.getSelectedItem();
                    Integer quantidade = Integer.parseInt(txtQuantidade.getText());
                    ItemPedido item = new ItemPedido(pedido, prod.clonar(), quantidade);
                    modelo.addRow(item);
                    combo.setSelectedIndex(0);
                    txtQuantidade.setText("");
                    janelaPedido.atualizaTabela();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantidade Inválida", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
        removerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabela.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ItemPedidoTableModel modelo = (ItemPedidoTableModel) tabela.getModel();
                    modelo.removeRow(tabela.getSelectedRow());
                    combo.setSelectedIndex(0);
                    txtQuantidade.setText("");
                    janelaPedido.atualizaTabela();
                }
            }
        });

        salvarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabela.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um Item", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        ItemPedidoTableModel modelo = (ItemPedidoTableModel) tabela.getModel();
                        Produto p = (Produto) combo.getSelectedItem();
                        int linha = tabela.getSelectedRow();
                        modelo.setValueAt(p, linha, 0);
                        modelo.setValueAt(txtQuantidade.getText(), linha, 1);
                        modelo.fireTableDataChanged();
                        combo.setSelectedIndex(0);
                        txtQuantidade.setText("");
                        janelaPedido.atualizaTabela();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Quantidade Inválida", "Alerta", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (pedido.isFechado() == false) {
                    if (tabela.getSelectedRowCount() == 0) {
                        removerItem.setEnabled(false);
                        salvarItem.setEnabled(false);
                        adicionarItem.setEnabled(true);
                    } else {
                        salvarItem.setEnabled(true);
                        removerItem.setEnabled(true);
                        adicionarItem.setEnabled(false);
                        ItemPedidoTableModel modelo = (ItemPedidoTableModel) tabela.getModel();
                        ItemPedido p = modelo.getRow(tabela.getSelectedRow());
                        combo.setSelectedIndex(p.getProduto().getId());
                        int linha = tabela.getSelectedRow();
                        txtQuantidade.setText((String) modelo.getValueAt(linha, 1));
                    }
                } else {
                    removerItem.setEnabled(false);
                    salvarItem.setEnabled(false);
                    adicionarItem.setEnabled(false);
                    txtQuantidade.setEnabled(false);
                    combo.setEnabled(false);
                }
            }
        });

    }

    public void solicitaItemPedido() {
        setLocationRelativeTo(null);
        setVisible(true);
        dadosProdutos.forEach((p) -> {
            combo.addItem(p);
        });
        removerItem.setEnabled(false);
        salvarItem.setEnabled(false);
        if (pedido.isFechado() == false) {
            adicionarItem.setEnabled(true);
        } else {
            adicionarItem.setEnabled(false);
            txtQuantidade.setEnabled(false);
            combo.setEnabled(false);
        }
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
import trabalholab3.modelos.ItemPedido;
import trabalholab3.modelos.Pedido;
import trabalholab3.modelos.Produto;
import trabalholab3.models.ItemPedidoTableModel;
import trabalholab3.utilitarios.ProdutoComboBox;

public class JanelaItemPedido extends JFrame {

    private ProdutoComboBox combo;
    private List<Produto> dadosProdutos;
    private Pedido pedido;
    private JLabel lblProduto = new JLabel("Produto: ");
    private JLabel lblQuantidade = new JLabel("Quantidade: ");
    private JTextField txtQuantidade = new JTextField(10);
    private JButton salvarItem = new JButton("Salvar Alterações");
    private JButton adicionarItem = new JButton("Adicionar Item");
    private JButton removerItem = new JButton("Remover Item");
    private JTable tabela;
    private JanelaPedidos janelaPedido;

    public JanelaItemPedido(List<Produto> dadosProdutos, Pedido p, JanelaPedidos j) {
        super("Itens do Pedido");
        setMinimumSize(new Dimension(500, 500));
        this.dadosProdutos = dadosProdutos;
        this.pedido = p;
        this.janelaPedido = j;
        combo = new ProdutoComboBox(dadosProdutos);
        JPanel formulario = new JPanel();
        JPanel campoItem = new JPanel();
        JPanel campoQuantidade = new JPanel();
        JPanel campoBotao = new JPanel();
        JPanel campoJuncao = new JPanel();
        campoItem.setLayout(new GridLayout(2, 1));
        campoQuantidade.setLayout(new GridLayout(2, 1));
        campoBotao.setLayout(new GridLayout(2, 1));
        campoItem.add(lblProduto);
        campoItem.add(combo);
        campoQuantidade.add(lblQuantidade);
        campoQuantidade.add(txtQuantidade);
        campoBotao.add(salvarItem);
        campoBotao.add(adicionarItem);
        campoJuncao.setLayout(new GridLayout(1, 2));
        campoJuncao.add(campoQuantidade);
        campoJuncao.add(campoBotao);
        formulario.setLayout(new GridLayout(2, 1));
        formulario.add(campoItem);
        formulario.add(campoJuncao);
        salvarItem.setBackground(new Color(000, 000, 205));
        salvarItem.setForeground(Color.WHITE);
        adicionarItem.setBackground(new Color(034, 139, 034));
        adicionarItem.setForeground(Color.WHITE);
        removerItem.setBackground(Color.red);
        removerItem.setForeground(Color.WHITE);
        add(formulario, BorderLayout.NORTH);
        tabela = new JTable(new ItemPedidoTableModel(pedido.getItemPedido()));
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 1));
        botoes.add(removerItem);
        add(botoes, BorderLayout.SOUTH);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        adicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ItemPedidoTableModel modelo = (ItemPedidoTableModel) tabela.getModel();
                    Produto prod = (Produto) combo.getSelectedItem();
                    Integer quantidade = Integer.parseInt(txtQuantidade.getText());
                    ItemPedido item = new ItemPedido(pedido, prod.clonar(), quantidade);
                    modelo.addRow(item);
                    combo.setSelectedIndex(0);
                    txtQuantidade.setText("");
                    janelaPedido.atualizaTabela();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Quantidade Inválida", "Alerta", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        );
        removerItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabela.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um Pedido", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    ItemPedidoTableModel modelo = (ItemPedidoTableModel) tabela.getModel();
                    modelo.removeRow(tabela.getSelectedRow());
                    combo.setSelectedIndex(0);
                    txtQuantidade.setText("");
                    janelaPedido.atualizaTabela();
                }
            }
        });

        salvarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tabela.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um Item", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    try {
                        ItemPedidoTableModel modelo = (ItemPedidoTableModel) tabela.getModel();
                        Produto p = (Produto) combo.getSelectedItem();
                        int linha = tabela.getSelectedRow();
                        modelo.setValueAt(p, linha, 0);
                        modelo.setValueAt(txtQuantidade.getText(), linha, 1);
                        modelo.fireTableDataChanged();
                        combo.setSelectedIndex(0);
                        txtQuantidade.setText("");
                        janelaPedido.atualizaTabela();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Quantidade Inválida", "Alerta", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (pedido.isFechado() == false) {
                    if (tabela.getSelectedRowCount() == 0) {
                        removerItem.setEnabled(false);
                        salvarItem.setEnabled(false);
                        adicionarItem.setEnabled(true);
                    } else {
                        salvarItem.setEnabled(true);
                        removerItem.setEnabled(true);
                        adicionarItem.setEnabled(false);
                        ItemPedidoTableModel modelo = (ItemPedidoTableModel) tabela.getModel();
                        ItemPedido p = modelo.getRow(tabela.getSelectedRow());
                        combo.setSelectedIndex(p.getProduto().getId());
                        int linha = tabela.getSelectedRow();
                        txtQuantidade.setText((String) modelo.getValueAt(linha, 1));
                    }
                } else {
                    removerItem.setEnabled(false);
                    salvarItem.setEnabled(false);
                    adicionarItem.setEnabled(false);
                    txtQuantidade.setEnabled(false);
                    combo.setEnabled(false);
                }
            }
        });

    }

    public void solicitaItemPedido() {
        setLocationRelativeTo(null);
        setVisible(true);
        dadosProdutos.forEach((p) -> {
            combo.addItem(p);
        });
        removerItem.setEnabled(false);
        salvarItem.setEnabled(false);
        if (pedido.isFechado() == false) {
            adicionarItem.setEnabled(true);
        } else {
            adicionarItem.setEnabled(false);
            txtQuantidade.setEnabled(false);
            combo.setEnabled(false);
        }
        tabela.clearSelection();
        janelaPedido.atualizaTabela();
    }

}
