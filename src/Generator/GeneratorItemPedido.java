package Generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import trabalholab3.modelos.Pedido;

public class GeneratorItemPedido {

    private File arq;
    
    private Pedido pedido;

    public GeneratorItemPedido(Pedido pedido) {
        this.pedido = pedido;
        arq =new File("Dados\\Generator", "GeneratorItemPedido "+this.pedido.getDescricao()+this.pedido.getMesa().getDescricao()+" .txt");
    }
    

    public File getArq() {
        
        return arq;
    }

    public Integer getIDItemPedido() throws FileNotFoundException, IOException {
        if (!this.getArq().exists()) {
            this.getArq().createNewFile();
        }
        FileReader fr = new FileReader(this.getArq());
        BufferedReader br = new BufferedReader(fr);
        String s = br.readLine();
        Integer id;
        try {
            id = Integer.parseInt(s);
            br.close();
            fr.close();
            this.addIDItemPedido(id);
        } catch (NumberFormatException ex) {
            br.close();
            fr.close();
            this.addIDItemPedido(1);
            id = 1;
        }
        return id;
    }

    public void addIDItemPedido(Integer id) throws IOException {
        id++;
        if (!this.getArq().exists()) {
            this.getArq().createNewFile();
        }
        FileWriter fw = new FileWriter(this.getArq());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(id.toString());
        bw.close();
        fw.close();

    }
}
