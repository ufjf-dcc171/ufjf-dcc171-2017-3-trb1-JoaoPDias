package trabalholab3.Dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import trabalholab3.modelos.Mesa;
import trabalholab3.modelos.Pedido;

public class PedidoDAO {

    List<Pedido> pedidos;
    Mesa mesa;

    public PedidoDAO(Mesa mesa) throws IOException {
        this.mesa = mesa;
        this.pedidos = this.listarTodos();

    }

    public void inserir(Pedido pedido) throws IOException {
        this.pedidos.add(pedido);
        this.gravar(pedidos);
        
    }

    public boolean fecharPedido(Integer id) throws IOException {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return this.gravarAlteracoes(p);

            }
        }
        return false;
    }

    private List<Pedido> listarTodos() throws FileNotFoundException, IOException {
        File arquivo = Pedido.gerarArquivo(mesa);
        if (!(arquivo).exists()) {
            arquivo.createNewFile();
        }
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        String s;
        ArrayList<Pedido> lista = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            Pedido p = Pedido.ToObject(s);
            if (p.getMesa().getId() == mesa.getId()) {
                lista.add(p);
            }
        }

        br.close();
        fr.close();
        return lista;
    }

    public Pedido listar(Integer id) {
        for (Pedido p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private void gravar(List<Pedido> lista) throws IOException {
        File arquivo = Pedido.gerarArquivo(mesa);
        if (!(arquivo).exists()) {
            arquivo.createNewFile();
        }
        FileWriter fw = new FileWriter(arquivo);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Pedido p : lista) {
            bw.write(p.ToSerial());
            bw.newLine();

        }
        bw.close();
        fw.close();
        this.pedidos = this.listarTodos();

    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void excluir(Pedido pedido) throws IOException {
        this.pedidos.remove(pedido);
        this.gravar(pedidos);
    }

    private boolean gravarAlteracoes(Pedido pedido) throws FileNotFoundException, IOException {
         File arquivo = Pedido.gerarArquivo(mesa);
        if (!(arquivo).exists()) {
            arquivo.createNewFile();
        }
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        String s;
        ArrayList<Pedido> lista = new ArrayList<>();
        boolean alterado = false;
        while ((s = br.readLine()) != null) {
            Pedido p = Pedido.ToObject(s);
            if (p.getMesa().getId() == pedido.getMesa().getId() && p.getId() == pedido.getId() && p.isFechado() == false) {
                p.setFechado(true);
                p.setHora_fechamento(LocalTime.now());
                alterado = true;
            }
            lista.add(p);
        }
        this.gravar(lista);
        br.close();
        fr.close();
        return alterado;
    }

}
