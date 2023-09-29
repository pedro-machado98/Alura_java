package br.com.model;

import br.com.exceptions.CepNaoTem8DigitosException;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static br.com.model.BuildJSON.gson;

public class CEP implements CEPinter {
    private String cep;
    private String rua;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String id;
    public CEP() {}
    public CEP(ViaCEPRecord cepRecord) {
        this.cep = cepRecord.cep();
        this.rua = cepRecord.logradouro();
        this.bairro = cepRecord.bairro();
        this.cidade = cepRecord.localidade();
        this.uf = cepRecord.uf();
    }

    public CEP(String cep) {
        this.cep = cep;
    }

    private  String buildURI(){
        final String address;
        if(this.cep.length() == 8) {
            return address = "https://viacep.com.br/ws/"+ URLEncoder.encode(this.cep, StandardCharsets.UTF_8) +"/json/";
        } else {
            throw new CepNaoTem8DigitosException("O CEP possui mais ou menos do que 8 digitos, verificar.");
        }
    }

    private  HttpResponse<String> makeRequest(){
        String address = buildURI();
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(address))
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public  CEP buildObjCEP(){
        HttpResponse<String> response = this.makeRequest();
        ViaCEPRecord cepRecord = BuildJSON.getJson(response.body());
        return new CEP(cepRecord);
    }

    public String buildStringJsonCEP(){
        return gson.toJson(this.buildObjCEP());
    }

    @Override
    public String buildStringJsonCEP(List<String> list) {

        return gson.toJson(list);
    }



    @Override
    public String toString() {
        return "cep: " + cep +"\n"+
                "Rua " + rua +"\n"+
                "Bairro: " + bairro + "\n"+
                "Cidade: " + cidade + "\n"+
                "Estado: " + uf;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

    public String getId() {
        return id;
    }
}
