package com.pedro.comparacaoPrecosDeCarrosFIPE.principal;

import com.pedro.comparacaoPrecosDeCarrosFIPE.model.Dados;
import com.pedro.comparacaoPrecosDeCarrosFIPE.model.DadosModelos;
import com.pedro.comparacaoPrecosDeCarrosFIPE.model.DadosVeiculo;
import com.pedro.comparacaoPrecosDeCarrosFIPE.service.ConsumoAPI;
import com.pedro.comparacaoPrecosDeCarrosFIPE.service.ConverteDados;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private String redireciona;
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    ConverteDados converteDados = new ConverteDados();
    private final String endereco = "https://parallelum.com.br/fipe/api/v1/";
    public void exibeMenu() {
        //preciso filtrar pelos tipo de veiculo
        System.out.println("Digite o nome do que deseja consultar. \n-Carros\n-Motos\n-Caminhoes\nNome:");
        var leitor = scanner.nextLine();
        redireciona = endereco + leitor.toLowerCase()+"/marcas";
        var json = consumoAPI.obterDados(redireciona);
        List<Dados> marcas =converteDados.obterLista(json, Dados.class);
        marcas.stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);


        System.out.println("Digite o codigo da marca que deseja consultar");
        leitor = scanner.nextLine();
        redireciona = redireciona + "/"+ leitor + "/modelos";
        json = consumoAPI.obterDados(redireciona.toLowerCase());
        DadosModelos modelosLista = (DadosModelos) converteDados.obterDados(json, DadosModelos.class);
        modelosLista.modelos().stream()
                .sorted(Comparator.comparing(Dados::codigo))
                .forEach(System.out::println);


        System.out.println("Digite um trecho do nome do carro para filtrar os modelos:");
        var nomeVeiculo = scanner.nextLine();
        List<Dados> dadosModelosFiltrados = modelosLista.modelos().stream()
                .filter(m -> m.marca().toLowerCase().contains(nomeVeiculo.toLowerCase()))
                .collect(Collectors.toList());
        dadosModelosFiltrados.forEach(System.out::println);


        System.out.println("Digite o codigo do modelo:");
        leitor = scanner.nextLine();
        redireciona = redireciona + "/" + leitor + "/anos";
        json = consumoAPI.obterDados(redireciona);
        List<Dados> modelosVeiculos = converteDados.obterLista(json, Dados.class);
        modelosVeiculos.forEach(System.out::println);
        System.out.println(modelosVeiculos.size());

        for (Dados veiculo: modelosVeiculos) {
            json = consumoAPI.obterDados(redireciona + "/" + veiculo.codigo());
            DadosVeiculo dadosVeiculo = (DadosVeiculo) converteDados.obterDados(json, DadosVeiculo.class);
            System.out.println(dadosVeiculo);
        }


//        System.out.println("Digite o codigo do ano da forma que foi desejado.");
//        leitor = scanner.nextLine();
//        redireciona = redireciona + "/" + leitor;
//        json = consumoAPI.obterDados(redireciona);
//        DadosVeiculo dadosVeiculo = (DadosVeiculo) converteDados.obterDados(json, DadosVeiculo.class);
//        System.out.println(dadosVeiculo);

    }
}
