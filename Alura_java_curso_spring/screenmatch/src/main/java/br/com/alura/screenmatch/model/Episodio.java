package br.com.alura.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio {
    private Integer temporada;
    private String titulo;
    private Integer numeroEpisodio;
    private Double avaliacao;
    private LocalDate dataLancamento;

    public Episodio(Integer numeroTemporada, DadosEpisodio dadosEpisodios) {
        this.temporada = numeroTemporada;
        this.titulo = dadosEpisodios.titulo();
        this.numeroEpisodio = dadosEpisodios.numero();
        try{
            this.avaliacao = Double.valueOf(dadosEpisodios.avaliacao());
            this.dataLancamento = LocalDate.parse(dadosEpisodios.dataLancamento());

        } catch (NumberFormatException e) {
            this.avaliacao = 0.0;
        } catch(DateTimeException e) {
            this.dataLancamento = null;
        }
    }

    @Override
    public String toString() {
        return
                "temporada= " + temporada +
                ", titulo= " + titulo +
                ", numeroEpisodio= " + numeroEpisodio +
                ", avaliacao= " + avaliacao +
                ", dataLancamento= " + dataLancamento;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Integer getTemporada() {
        return temporada;
    }
}
