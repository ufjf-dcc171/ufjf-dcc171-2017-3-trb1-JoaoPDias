package Generator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import trabalholab3.modelos.Mesa;

public class GeneratorMesa {

    private static final File arq = new File("Dados\\Generator", "GeneratorMesa.txt");

    public static File getArq() {
        return arq;
    }

    public static Integer getIDMesa() throws FileNotFoundException, IOException {
        if (!GeneratorMesa.getArq().exists()) {
            GeneratorMesa.getArq().createNewFile();
        }
        FileReader fr = new FileReader(GeneratorMesa.getArq());
        BufferedReader br = new BufferedReader(fr);
        String s = br.readLine();
        Integer id;
        try {
            id = Integer.parseInt(s);
            br.close();
            fr.close();
            GeneratorMesa.addIDMesa(id);
        } catch (NumberFormatException ex) {
            br.close();
            fr.close();
            GeneratorMesa.addIDMesa(1);
            id = 1;
        }
        return id;
    }

    public static void addIDMesa(Integer id) throws IOException {
        id++;
        if (!GeneratorMesa.getArq().exists()) {
            GeneratorMesa.getArq().createNewFile();
        }
        FileWriter fw = new FileWriter(GeneratorMesa.getArq());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(id.toString());
        bw.close();
        fw.close();

    }
}
