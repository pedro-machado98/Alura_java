//DEPRECATED

package br.com.Alura.Dinheiro;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

public class Dinheiro {
    private static final Currency BRL = Currency.getInstance("BRL");
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;
    private final BigDecimal quantia;
    private final Currency moeda;
    public static Dinheiro real(double quantia){
        return new Dinheiro(BigDecimal.valueOf(quantia), BRL);
    }
    Dinheiro(BigDecimal quantia, Currency moeda){
        this(quantia, moeda, DEFAULT_ROUNDING);
    }
    Dinheiro(BigDecimal quantia, Currency moeda, RoundingMode arredondamento){
        this.moeda = moeda;
        this.quantia = quantia.setScale(moeda.getDefaultFractionDigits(),arredondamento);
    }
    public BigDecimal getQuantia(){
        return quantia;
    }
    public Currency getMoeda() {
        return moeda;
    }
    @Override
    public String toString() {
        return getMoeda().getSymbol() + " " + getQuantia();
    }
    public String toString(Locale locale){
        return getMoeda().getSymbol(locale) + " " + getQuantia();
    }
}
