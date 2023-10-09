package br.com.alura.screenmatch.excecoes;

public class FilmeComMaisDe4DigitosException extends RuntimeException {
    private final String e;
    public FilmeComMaisDe4DigitosException(String e) {
        this.e = e;
    }

    @Override
    public String getMessage() {
        return this.e;
    }
}
