package br.com.pedro.mysongs.calc;

public class FiltroRelevancia {
	
	public void filtra(Relevancia relevancia) {
		if (relevancia.getRelevancia() >= 4) {
			System.out.println(relevancia.getNome() +" está entre as mais relevantes do momento");
		} else {
			System.out.println(relevancia.getNome()  + " é muito boa");
		}
	}
}
