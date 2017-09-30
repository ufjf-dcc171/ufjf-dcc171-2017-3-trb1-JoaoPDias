package trabalholab3.modelos;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;

public class Produto {

    private Integer id;

    private String descricao;

    private Double valor;

    private static int gencodigo = 0;

    public Produto() {
    }
    
    

    public Produto(String descricao, Double valor) {
        this.id = gerarCodigo();
        this.descricao = descricao;
        setValor(valor);
    }

    public Produto(Integer id, String descricao, Double valor) {
        this.id = id;
        this.descricao = descricao;
        setValor(valor);
    }

    public static int gerarCodigo() {
        int cod = gencodigo;
        gencodigo++;
        return cod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        Number n = 0;
        try{
        n = df.parse(df.format(valor));
        }catch (ParseException ex){
            System.out.println("Erro na conversão");
        }
        this.valor = n.doubleValue();
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    public Produto clonar(){
        Produto p = new Produto();
        p.setId(this.id);
        p.setDescricao(this.descricao);
        p.setValor(this.valor);
        return p;
    }
}
