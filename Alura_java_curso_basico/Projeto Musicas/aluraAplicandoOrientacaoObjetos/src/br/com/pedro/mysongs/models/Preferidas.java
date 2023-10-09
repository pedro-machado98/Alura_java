package br.com.pedro.mysongs.models;

public class Preferidas {
	
	public void inclui(Audio audio) {
		if(audio.getClassificacao() >= 9) {
			System.out.println(audio.getTitulo() +" é um sucesso em todas as plataformas");
		} else {
			System.out.println(audio.getTitulo() + " é otimo");
		}
	}
}
