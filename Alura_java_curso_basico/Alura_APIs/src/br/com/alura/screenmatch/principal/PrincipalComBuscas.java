package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecoes.FilmeComMaisDe4DigitosException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBuscas {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);

        var busca = " ";
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();


        while(!busca.equalsIgnoreCase("sair")) {
            try {
                System.out.println("Digite um nome de filme:");
                busca = leitura.nextLine();

                if(busca.equalsIgnoreCase("sair")) {
                    break;
                }

                busca = URLEncoder.encode(busca, StandardCharsets.UTF_8);
                String endereco = "https://www.omdbapi.com/?t=" + busca + "&apikey=6e70e29f";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);

                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                titulos.add(meuTitulo);


            } catch (NumberFormatException e) {
                System.out.println("\nAconteceu um erro de conversão para numero.\nCodigo do erro: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("\nAconteceu um erro, problema na URL.\nCodigo do erro: " + e.getMessage());
            } catch (FilmeComMaisDe4DigitosException e) {
                System.out.println("\nAconteceu um erro, problema ano do filme fora do formato.\nCodigo do erro: " + e.getMessage());
            }
        }
        FileWriter writer = new FileWriter("filmes.json");
        BufferedWriter bWriter = new BufferedWriter(writer);

        bWriter.write(gson.toJson(titulos));
        bWriter.close();
        writer.close();

    }
}
