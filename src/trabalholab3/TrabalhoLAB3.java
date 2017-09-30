package trabalholab3;

import javax.swing.JFrame;
import trabalholab3.janelas.JanelaInicial;

public class TrabalhoLAB3 {

    public static void main(String[] args) {
        JanelaInicial janela = new JanelaInicial();
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
    }

}
