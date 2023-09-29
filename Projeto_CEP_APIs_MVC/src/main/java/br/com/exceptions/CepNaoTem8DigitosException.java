package br.com.exceptions;

public class CepNaoTem8DigitosException extends RuntimeException {
    private final String message;
    public CepNaoTem8DigitosException(String s) {
        this.message = s;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
