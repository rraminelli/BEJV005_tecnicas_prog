package br.com.rraminelli.javatime.exercicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/*
    Um sistema ecommerce precisa criar uma nova funcionalidade de parcelamento das compras em até 12x sem juros.

    A partir da data atual e a quantidade de parcelas escolhidas, imprimir o valor e a data de vencimento da parcela.

    A data de vencimento não pode ser no fim de semana. Se cair no fim de semana ajustar a data para a proxima segunda-feira.

    ** utilizar a API java.time

    Saida: Numero da parcela - Data de vencimento - Valor parcela
 */
public class DataVencimentoParcela {

    public static void main(String[] args) {

        imprimirParcelas(new BigDecimal(1000), 3);

    }

    static void imprimirParcelas(final BigDecimal valorTotal, final int parcelas) {

        if (parcelas < 1 || parcelas > 12) {
            throw new RuntimeException("Numero de parcelas invalidas (1 - 12)");
        }

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        final String printParcela = "%d - R$ %.2f - %s";

        final BigDecimal valorParcela = valorTotal.divide(new BigDecimal(parcelas), 2, RoundingMode.HALF_UP);

        for (int i = 1; i <= parcelas; i++) {
            final LocalDate vencimentoParcela = getDataVencimento(i);

            final BigDecimal valorParcelaResto = calculoRestoParcela(valorTotal, parcelas, i);

            System.out.println(String.format(printParcela, i, valorParcela.add(valorParcelaResto), vencimentoParcela.format(dateTimeFormatter)));

        }

    }

    static BigDecimal calculoRestoParcela(final BigDecimal valorTotal, int parcelas, int parcelaAtual) {
        if (parcelaAtual != 1) {
            return BigDecimal.ZERO;
        }

        final BigDecimal valorParcela = valorTotal.divide(new BigDecimal(parcelas), 2, RoundingMode.HALF_UP);

        final BigDecimal resto = valorTotal.subtract(valorParcela.multiply(new BigDecimal(parcelas)));

        return resto;

    }

    static LocalDate getDataVencimento(final int parcela) {
        final LocalDate hoje = LocalDate.now();
        final LocalDate vencimento = hoje.plusMonths(parcela);
        if (isFinalSemana(vencimento)) {
            return vencimento.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        } else {
            return vencimento;
        }
    }

    static boolean isFinalSemana(final LocalDate data) {
        return data.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

}
