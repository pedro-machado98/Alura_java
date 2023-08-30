package br.com.pedro.mysongs.models;

public class Audio {
	private String titulo;
	private int duracao;
	private int totalReproducoes;
	private int totalCurtidas;
	private int classificacao;
	
	public void curte() {
		this.totalCurtidas++;
	}
	
	public void reproduz() {
		this.totalReproducoes++;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public int getTotalReproducoes() {
		return totalReproducoes;
	}

	public void setTotalReproducoes(int totalReproducoes) {
		this.totalReproducoes = totalReproducoes;
	}

	public int getTotalCurtidas() {
		return totalCurtidas;
	}

	public void setTotalCurtidas(int totalCurtidas) {
		this.totalCurtidas = totalCurtidas;
	}

	public int getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}


}
