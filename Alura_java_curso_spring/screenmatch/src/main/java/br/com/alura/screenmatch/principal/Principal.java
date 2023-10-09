package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.model.Episodio;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    ConverteDados converteDados = new ConverteDados();
    private final String endereco = "https://www.omdbapi.com/?t=";
    private final String apikey = "&apikey=6585022c";
    public void exibeMenu() {

        System.out.println("Digite o nome da serie: ");
        var nomeSerie = scanner.nextLine();

        var json = consumoAPI.obterDados(endereco + nomeSerie.replace(" ", "+") + apikey);
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);
        //System.out.println(dados);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1 ; i <= dados.totalTemporadas() ; i++) {
            json = consumoAPI.obterDados(endereco + nomeSerie.replace(" ", "+") + "&season="+ i + apikey);
            DadosTemporada dadosTemporada = converteDados.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(DadosTemporada::numero);

/*
        temporadas.forEach(temporada -> temporada.episodios().forEach(episodio -> System.out.println(episodio.titulo())));

        temporadas.forEach(System.out::println);

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());


        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(6)
                .forEach(System.out::println);
*/


        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numero(), d))
                ).collect(Collectors.toList());

//        episodios.forEach(System.out::println);
//
//        System.out.println("Digite o trecho do titulo do episodio:");
//        String trechoTitulo = scanner.nextLine();
//        Optional<Episodio> episodioBuscado = episodios.stream()
//                .filter(e -> e.getTitulo().toLowerCase().contains(trechoTitulo.toLowerCase()))
//                .findFirst();
//
//        if(episodioBuscado.isPresent()) {
//            System.out.println("Episodio encontrado!");
//            System.out.println(episodioBuscado);
//        } else {
//            System.out.println("Episodio não encontrado.");
//        }

        Map<Integer, Double> avaliacoesPorTemporada = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.groupingBy(Episodio::getTemporada,
                        Collectors.averagingDouble(Episodio::getAvaliacao)));

        System.out.println(avaliacoesPorTemporada);

        DoubleSummaryStatistics est = episodios.stream()
                .filter(e -> e.getAvaliacao() > 0.0)
                .collect(Collectors.summarizingDouble(Episodio::getAvaliacao));
        System.out.println("Média: " + est.getAverage());
        System.out.println("Melhor episódio: " + est.getMax());
        System.out.println("Pior episódio: " + est.getMin());
        System.out.println("Quantidade: " + est.getCount());

    }
}
