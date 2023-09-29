package br.com.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BuildJSON {
    static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
            .setPrettyPrinting()
            .create();

    public static ViaCEPRecord getJson(String body) {
        return gson.fromJson(body, ViaCEPRecord.class);
    }
}
