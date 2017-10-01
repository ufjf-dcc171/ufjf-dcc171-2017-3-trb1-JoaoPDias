package trabalholab3.janelas;

import trabalholab3.utilitarios.BotaoMesa;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import trabalholab3.modelos.Mesa;
import trabalholab3.modelos.Produto;

public class JanelaMesas extends JFrame {

    private List<Mesa> mesas;
    private List<BotaoMesa> Botaomesas = new ArrayList<>();
    private JPanel LayoutMesas = new JPanel();
    private JScrollPane espacoMesas = new JScrollPane(LayoutMesas);
    private final JButton adicionarMesa = new JButton("Adicionar Mesa");
    private JanelaProdutos janelaProdutos;
    private List<Produto> produtos;

    public JanelaMesas(List<Mesa> mesas) {
        super("Gerenciador de Mesas");
        setMinimumSize(new Dimension(500, 500));
        this.mesas = mesas;
        mesas.forEach((m) -> {
            GerarMesa(m);
        });
        adicionarMesa.setBackground(new Color(034, 139, 034));
        adicionarMesa.setForeground(Color.WHITE);
        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1, 1));
        botoes.add(adicionarMesa);
        add(botoes, BorderLayout.SOUTH);
        adicionarMesa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Mesa m = new Mesa();
                mesas.add(m);
                GerarMesa(m);

            }
        });
        gerarPedidos();
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
        p.setIcon(new ImageIcon("C:\\Users\\jpdia\\Documents\\TrabDCC171\\src\\trabalholab3\\icones\\table.png"));
        p.setVerticalTextPosition(SwingConstants.BOTTOM);
        p.setHorizontalTextPosition(SwingConstants.CENTER);
        p.setBackground(Color.WHITE);
        p.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (p.getJanelaPedidos() == null) {
                    JanelaPedidos pedido = new JanelaPedidos(produtos,p.getMesa(),p.getMesa().getPedido());
                    p.setJanelaPedidos(pedido);
                    p.getJanelaPedidos().solicitaPedido();
                } else {
                    p.getJanelaPedidos().solicitaPedido();
                }
            }
        });
        Double d = Math.ceil(mesas.size() / 3.0);
        int row = d.intValue();
        LayoutMesas.setLayout(new GridLayout(row, 3));
        LayoutMesas.add(p);
        LayoutMesas.updateUI();
        add(espacoMesas, BorderLayout.CENTER);

        Botaomesas.add(p);

    }

    public void solicitaMesas() {
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void gerarPedidos() {
        for (int i = 0; i < 1; i++) {
            mesas.forEach((m) -> {
                m.gerarPedido();
            });

        }

    }
}
