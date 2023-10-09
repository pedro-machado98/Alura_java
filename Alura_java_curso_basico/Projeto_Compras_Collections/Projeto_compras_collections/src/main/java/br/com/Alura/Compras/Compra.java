package br.com.Alura.Compras;

public class Compra implements Comparable<Compra>{
    private String descricao;
    private double valor;
    public Compra(String descricao, double valor) {
        this.descricao = descricao;
        this.valor = valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public double getValor() {
        return valor;
    }
    @Override
    public String toString() {
        return "Compra: item = " + descricao + " valor = " + valor;
    }
    @Override
    //Solução alura
    public int compareTo(Compra outraCompra) {
        return Double.compare(this.valor, outraCompra.valor);
    }
}