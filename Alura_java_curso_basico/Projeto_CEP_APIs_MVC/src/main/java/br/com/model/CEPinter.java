package br.com.model;

import java.util.List;

import static br.com.model.BuildJSON.gson;

public interface CEPinter {
    public String buildStringJsonCEP(List<String> list);
}
