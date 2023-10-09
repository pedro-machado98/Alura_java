package br.com.view;

import br.com.model.CEP;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteJsonFile {

    static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
            .setPrettyPrinting()
            .create();

    public static void escreveArquivo(String nome, List<CEP> conteudo){
        try {
            FileWriter writer = new FileWriter(nome);
            BufferedWriter bwriter = new BufferedWriter(writer);


            bwriter.write(gson.toJson(conteudo));

            bwriter.close();
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
