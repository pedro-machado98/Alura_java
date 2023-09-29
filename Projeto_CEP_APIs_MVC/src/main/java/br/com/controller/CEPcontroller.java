package br.com.controller;

import br.com.model.CEP;
import br.com.view.WriteJsonFile;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CEPcontroller {
    public static void start(){

        String cep = " ";
        String nome;
        List<CEP> ceps = new ArrayList<>();
        List<String> cepsString = new ArrayList<>();
        Scanner reader = new Scanner(System.in);

        System.out.println("Digite o nome do arquivo, digite 'sair' para encerrar o programa.");
        nome = reader.nextLine();
        nome += ".json";
        System.out.println(nome);


        System.out.println("Digite um CEP, digite 'sair' para encerrar o programa.");
        cep = reader.nextLine();


        while(!cep.equalsIgnoreCase("sair")) {

            CEP cepObj = new CEP(cep);
            System.out.println(cepObj.buildObjCEP());
            System.out.println(cepObj.buildStringJsonCEP());
            ceps.add(cepObj.buildObjCEP());


            System.out.println("Digite um CEP, digite 'sair' para encerrar o programa.");
            cep = reader.nextLine();
        }


        WriteJsonFile.escreveArquivo(nome, ceps);
    }
}
