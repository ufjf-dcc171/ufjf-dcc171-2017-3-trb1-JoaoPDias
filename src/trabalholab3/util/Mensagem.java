package trabalholab3.util;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Mensagem {

    public static void erroAcesso(Component referencia,String mensagem) {
        JOptionPane.showMessageDialog(referencia,
                mensagem,
                "Erro de Acesso",
                JOptionPane.ERROR_MESSAGE);
    }
}
