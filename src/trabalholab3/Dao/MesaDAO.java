package trabalholab3.Dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import trabalholab3.modelos.Mesa;

public class MesaDAO {

    List<Mesa> mesas;

    public MesaDAO() throws IOException {
        this.mesas = listarTodos();
    }

    public void inserir(Mesa mesa) throws IOException {
        this.mesas.add(mesa);
        this.gravar(mesas);
    }

    public void excluir(Mesa mesa) throws IOException {
        this.mesas.remove(mesa);
        this.gravar(mesas);
    }

    private List<Mesa> listarTodos() throws FileNotFoundException, IOException {
        if (!Mesa.getArq().exists()) {
            Mesa.getArq().createNewFile();
        }
        FileReader fr = new FileReader(Mesa.getArq());
        BufferedReader br = new BufferedReader(fr);
        String s;
        ArrayList<Mesa> lista = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            Mesa m = Mesa.ToObject(s);
            lista.add(m);
        }

        br.close();
        fr.close();
        return lista;
    }

    public Mesa listar(Integer id) {
        for (Mesa m : mesas) {
            if (m.getId() == id) {
                return m;
            }
        }
        return null;
    }

    private void gravar(List<Mesa> lista) throws IOException {
        if (!Mesa.getArq().exists()) {
            Mesa.getArq().createNewFile();
        }
        FileWriter fw = new FileWriter(Mesa.getArq());
        BufferedWriter bw = new BufferedWriter(fw);
        for (Mesa m : lista) {
            bw.write(m.ToSerial());
            bw.newLine();

        }
        bw.close();
        fw.close();

    }

    public List<Mesa> getMesas() {
        return mesas;
    }

}
