package br.com.pedro.mysongs.models;

import br.com.pedro.mysongs.calc.Relevancia;

public class Podcast extends Audio implements Relevancia{
	private String host;
	private String descricao;
	
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public int getClassificacao() {
		if (this.getTotalReproducoes() > 500) {
			return 10;
		} else {
			return 8;
		}
	}

	@Override
	public int getRelevancia() {
		if (this.getTotalCurtidas()>5 && this.getTotalReproducoes()>1) {
			return 5;
		} else {
			return 2;
		}
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return this.getTitulo();
	}
}
