package com.pedro.comparacaoPrecosDeCarrosFIPE.service;

import java.util.List;

public interface iConverteDados {
    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
