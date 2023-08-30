package br.com.pedro.mysongs.main;
import br.com.pedro.mysongs.calc.FiltroRelevancia;
import br.com.pedro.mysongs.models.*;

public class Principal {

	public static void main(String[] args) {
		
		Musicas musica = new Musicas();
		
		musica.setTitulo("Carpet Burn");
		musica.setArtista("DaBaby");
		
		for (int i = 0 ; i < 1000 ; i++) {
			musica.reproduz();
		}
		
		for (int i = 0 ; i < 100 ; i++) {
			musica.curte();
		}
		
		//System.out.println(musica.getTotalCurtidas());
		
		Podcast podcast = new Podcast();
		
		podcast.setTitulo("Kitsune da semana");
		podcast.setHost("Leonardo Kitsune");
		
		for (int i = 0 ; i < 10000 ; i++) {
			podcast.reproduz();
		}
		
		for (int i = 0 ; i < 10 ; i++) {
			podcast.curte();
		}
		
		//System.out.println(podcast.getTotalReproducoes());
		
		
		Preferidas preferida = new Preferidas();
		preferida.inclui(podcast);
		preferida.inclui(musica);
		
		FiltroRelevancia relevancia = new FiltroRelevancia();
		
		relevancia.filtra(podcast);
		relevancia.filtra(musica);
		
		


	}

}
