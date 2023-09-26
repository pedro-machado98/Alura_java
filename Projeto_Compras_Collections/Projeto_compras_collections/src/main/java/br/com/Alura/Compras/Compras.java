package br.com.Alura.Compras;

import java.util.HashMap;
import java.util.Map;

public class Compras {
    private int limiteCartao;
    private String descricaoCompra;
    private int valorCompra;
    Map<String, Integer> produtos = new HashMap<String, Integer>();
    public Compras(){}
    public void setLimiteCartao(int limiteCartao) {
        this.limiteCartao = limiteCartao;
    }

    public boolean setProdutos(String descricaoCompra, int valorCompra) {
        if (valorCompra > limiteCartao){
            return false;
        } else{
            this.limiteCartao-=valorCompra;
            this.produtos.put(descricaoCompra, valorCompra);
        }
      return true;
    }
}
