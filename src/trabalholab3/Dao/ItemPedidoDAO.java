package trabalholab3.Dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import trabalholab3.modelos.ItemPedido;
import trabalholab3.modelos.Pedido;

public class ItemPedidoDAO {

    List<ItemPedido> itempedidos;
    Pedido pedido;

    public ItemPedidoDAO(Pedido pedido) throws IOException {
        this.pedido = pedido;
        this.itempedidos = this.listarTodos(pedido);

    }

    public void inserir(ItemPedido itempedido) throws IOException {
        this.itempedidos.add(itempedido);
        this.gravar(itempedidos);
        pedido.setItemPedido(itempedidos);
    }

    private List<ItemPedido> listarTodos(Pedido pedido) throws FileNotFoundException, IOException {
        File arquivo = ItemPedido.gerarArquivo(pedido);
        if (!(arquivo).exists()) {
            arquivo.createNewFile();
        }
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        String s;
        ArrayList<ItemPedido> lista = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            ItemPedido ip = ItemPedido.ToObject(s,pedido);
            lista.add(ip);
        }

        br.close();
        fr.close();
        return lista;
    }

    public boolean alterarItemPedido(Integer id) throws IOException {
        for (ItemPedido p : itempedidos) {
            if (Objects.equals(p.getId(), id)) {
                return this.gravarAlteracoes(p);

            }
        }
        return false;
    }

    private void gravar(List<ItemPedido> lista) throws IOException {
        File arquivoItemPedido = ItemPedido.gerarArquivo(pedido);
        if (!(arquivoItemPedido).exists()) {
            arquivoItemPedido.createNewFile();
        }
        FileWriter fw = new FileWriter(arquivoItemPedido);
        BufferedWriter bw = new BufferedWriter(fw);
        for (ItemPedido p : lista) {
            bw.write(p.ToSerial());
            bw.newLine();

        }
        bw.close();
        fw.close();

    }

    public List<ItemPedido> getItemPedidos() {
        return itempedidos;
    }

    public void excluir(ItemPedido pedido) throws IOException {
        this.itempedidos.remove(pedido);
        this.gravar(itempedidos);
    }

    private boolean gravarAlteracoes(ItemPedido itempedido) throws FileNotFoundException, IOException {
        File arquivoItemPedido = ItemPedido.gerarArquivo(pedido);
        if (!(arquivoItemPedido).exists()) {
            arquivoItemPedido.createNewFile();
        }
        FileReader fr = new FileReader(arquivoItemPedido);
        BufferedReader br = new BufferedReader(fr);
        String s;
        ArrayList<ItemPedido> lista = new ArrayList<>();
        boolean alterado = false;
        while ((s = br.readLine()) != null) {
            ItemPedido ip = ItemPedido.ToObject(s, pedido);
            ip.setProduto(itempedido.getProduto());
            ip.setQuantidade(itempedido.getQuantidade());
            lista.add(ip);
        }
        this.gravar(lista);
        br.close();
        fr.close();
        return alterado;
    }

}
