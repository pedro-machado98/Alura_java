package br.com.model;

import br.com.exceptions.CepNaoTem8DigitosException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class BuildURI {
    private final String uri;

    public BuildURI(String cep) {
        if(cep.length() == 8) {
            this.uri = "https://viacep.com.br/ws/"+ URLEncoder.encode(cep, StandardCharsets.UTF_8) +"/json/";
        } else {
            throw new CepNaoTem8DigitosException("O CEP possui mais ou menos do que 8 digitos, verificar.");
        }
    }

    public String getUri (){
        return this.uri;
    }

    @Override
    public String toString() {
        return "URI: "+this.uri;
    }
}
