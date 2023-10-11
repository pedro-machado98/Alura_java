package com.pedro.comparacaoPrecosDeCarrosFIPE.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConverteDados<classe> implements iConverteDados{
    private final ObjectMapper mapper = new ObjectMapper();
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        try{
            CollectionType lista = mapper.getTypeFactory()
                    .constructCollectionType(List.class, classe);
            return mapper.readValue(json, lista);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
