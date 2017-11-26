package trabalholab3.janelas;

import trabalholab3.utilitarios.BotaoMesa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import trabalholab3.Dao.MesaDAO;
import trabalholab3.modelos.Mesa;
import trabalholab3.modelos.Produto;
import trabalholab3.util.Mensagem;

public class JanelaMesas extends JFrame {

    private MesaDAO mesaDAO;
    private List<Mesa> mesas;
    private List<BotaoMesa> Botaomesas = new ArrayList<>();
    private JPanel LayoutMesas = new JPanel();
    private JScrollPane espacoMesas = new JScrollPane(LayoutMesas);
    private final JButton adicionarMesa = new JButton("Adicionar Mesa");
    private JanelaProdutos janelaProdutos;
    private List<Produto> produtos;

    public JanelaMesas() {
        super("Gerenciador de Mesas");
        try {
            mesaDAO = new MesaDAO();
        } catch (IOException ex) {
            Mensagem.erroAcesso(this, Mesa.getArq().getAbsolutePath());
        }
        this.mesas = mesaDAO.getMesas();
        for (Mesa m : mesas) {
            GerarMesa(m);
        }
        setMinimumSize(new Dimension(500, 500));
        adicionarMesa.setBackground(new Color(034, 139, 034));
        adicionarMesa.setForeground(Color.WHITE);
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 1));
        botoes.add(adicionarMesa);
        add(botoes, BorderLayout.SOUTH);
        adicionarMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Mesa m = new Mesa();
                    mesaDAO.inserir(m);
                    GerarMesa(m);
                } catch (IOException ex) {
                    Mensagem.erroAcesso(getInstance(), "Erro ao adicionar a mesa.");
                }

            }
        });
    }

    public JanelaProdutos getJanelaProdutos() {
        return janelaProdutos;
    }

    public void setJanelaProdutos(JanelaProdutos janelaProdutos) {
        this.janelaProdutos = janelaProdutos;
        this.produtos = this.janelaProdutos.getDados();

    }

    private void GerarMesa(Mesa m) {
        BotaoMesa p = new BotaoMesa(m);
        p.setIcon(new ImageIcon(getClass().getResource("icones\\table.png")));
        p.setVerticalTextPosition(SwingConstants.BOTTOM);
        p.setHorizontalTextPosition(SwingConstants.CENTER);
        p.setBackground(Color.WHITE);
        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p.getJanelaPedidos() == null) {
                    JanelaPedidos pedido;
                    try {
                        pedido = new JanelaPedidos(produtos, p.getMesa());
                        p.setJanelaPedidos(pedido);
                        p.getJanelaPedidos().solicitaPedido();
                    } catch (Exception ex) {
                        Mensagem.erroAcesso(getInstance(), "Erro ao adicionar a Pedido.");
                    }

                } else {
                    p.getJanelaPedidos().solicitaPedido();
                }
            }
        });
        Double d = Math.ceil(mesas.size() / 3.0);
        int row = d.intValue();
        LayoutMesas.setLayout(new GridLayout(row, 3));
        LayoutMesas.add(p);
        add(espacoMesas, BorderLayout.CENTER);
        espacoMesas.updateUI();
        LayoutMesas.updateUI();
        Botaomesas.add(p);

    }

    public void solicitaMesas() {
        setLocationRelativeTo(null);
        setVisible(true);

    }
/*
    public void gerarPedidos() {
        for (int i = 0; i < 1; i++) {
            mesas.forEach((m) -> {
                m.gerarPedido();
            });

        }

    }
*/
    private JanelaMesas getInstance() {
        return this;
    }

}
