package trabalholab3.janelas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import trabalholab3.modelos.ItemPedido;
import trabalholab3.modelos.Pedido;

public class JanelaFinalizacao extends JFrame {

    private JLabel titulo = new JLabel();
    private JLabel mesa = new JLabel();
    private JLabel abertura = new JLabel();
    private JLabel fechamento = new JLabel();
    private JLabel valorTotal = new JLabel();

    public JanelaFinalizacao(Pedido pedido) {
        super("Finalização do Pedido");
        this.setMinimumSize(new Dimension(500, 300));
        this.setMaximumSize(new Dimension(500, 300));
        titulo.setText("Resumo do " + pedido.getDescricao());
        mesa.setText("Mesa: " + pedido.getMesa().getDescricao());
        abertura.setText("Hora de Abertura: " + pedido.getHora_abertura().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        fechamento.setText("Hora de Fechamento: " + pedido.getHora_fechamento().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
        valorTotal.setText("Valor Total do Pedido: R$ " + pedido.getValorTotal());
        titulo.setFont(new Font("Arial", Font.BOLD, 30));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setVerticalAlignment(SwingConstants.TOP);
        JPanel resumo = new JPanel();
        JPanel dados = new JPanel();
        resumo.setLayout(new GridLayout(3, 1));
        resumo.add(titulo, BorderLayout.NORTH);
        dados.setFont(new Font("Arial", Font.PLAIN, 15));
        dados.setLayout(new GridLayout(2, 2));
        dados.setSize(new Dimension(200, 120));
        dados.add(mesa);
        dados.add(valorTotal);
        dados.add(abertura);
        dados.add(fechamento);
        resumo.add(dados, BorderLayout.CENTER);
        JPanel produtos = new JPanel();
        produtos.setFont(new Font("Arial", Font.BOLD, 18));
        produtos.setLayout(new GridLayout(pedido.getItemPedido().size(), 1));
        for (ItemPedido p : pedido.getItemPedido()) {
            JLabel lbl = new JLabel(p.toString());
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            produtos.add(lbl);
        }
        resumo.add(new JScrollPane(produtos),BorderLayout.SOUTH);
        add(resumo);
        setBackground(Color.WHITE);
    }

    public void solicitaFinalizacao() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
