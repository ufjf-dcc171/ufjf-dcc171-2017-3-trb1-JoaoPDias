package trabalholab3.janelas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import trabalholab3.modelos.Mesa;
import trabalholab3.modelos.Produto;

public class JanelaInicial extends JFrame {

    private final JPanel area = new JPanel();
    private final JButton produtos = new JButton("Gerenciar Produtos", new ImageIcon(getClass().getResource("icones\\produtos.png")));
    private final JButton mesas = new JButton("Gerenciar Mesas", new ImageIcon(getClass().getResource("icones\\mesas.png")));
    private final JanelaProdutos janelaProdutos = new JanelaProdutos(getSampleData());
    private final JanelaMesas janelaMesas = new JanelaMesas(getSampleDataMesa());

    public JanelaInicial() throws HeadlessException {
        super("Gerenciador de Mesas");
        setMinimumSize(new Dimension(500, 500));
        produtos.setVerticalTextPosition(SwingConstants.TOP);
        produtos.setHorizontalTextPosition(SwingConstants.CENTER);

        mesas.setVerticalTextPosition(SwingConstants.TOP);
        mesas.setHorizontalTextPosition(SwingConstants.CENTER);
        area.setLayout(new GridLayout(2, 1));
        area.add(produtos);
        area.add(mesas);
        add(area, BorderLayout.CENTER);
        produtos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaProdutos.solicitaProduto();
            }
        });
        
        mesas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janelaMesas.solicitaMesas();
            }
        });
        janelaMesas.setJanelaProdutos(janelaProdutos);
    }

    List<Produto> getSampleData() {
        List<Produto> ListProdutos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Produto p = new Produto("Produto" + i, (45.8 * i+1) / 3);
            ListProdutos.add(p);
        }
        return ListProdutos;

    }

    List<Mesa> getSampleDataMesa() {
        List<Mesa> ListMesa = new ArrayList<>();
        for (int i = 0; i <= 60; i++) {
            Mesa m = new Mesa();
            ListMesa.add(m);
        }
        return ListMesa;
    }

}
