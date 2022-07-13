package br.com.rraminelli.javatime.exercicios;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/*
    A partir de uma lista de datas de feriados, calcular e imprimir qual o dia da semana o feriado
    acontece e se for quinta-feira ou terça-feira informar que é "feriado prolongado".

    Imprimir as informações para os anos: anterior, atual e próximo.

    Datas: 07/09, 12/10, 02/11, 15/11, 25/12, 01/01.

    Saida:

    #Ano 2021:
    #07/09 - Terça feira * feriado prolongado
    #12/10 - Dia da semana ... #...

    #Ano 2022:
    #07/09 - Dia da semana ... #12/10 - Dia da semana ...

    #Ano 2023:
    #07/09 - Dia da semana ... #12/10 - Dia da semana ...

    ** Utilizar API java.time

 */
public class Feriados {

    final static DateTimeFormatter dateTimeFormatterIn = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    final static DateTimeFormatter dateTimeFormatterOut =
            DateTimeFormatter.ofPattern("dd/MM/yyyy - EEEE", new Locale("pt", "BR"));

    public static void main(String[] args) {

        final List<String> feriados = List.of(
                "01/01", "07/09", "12/10", "02/11", "25/12"
        );

        final int anoAtual = Year.now().getValue();

        for (int i = anoAtual-1; i <= anoAtual+1; i++) {
            System.out.println("Feriados ano: ".concat(String.valueOf(i)));
            for (final String feriado : feriados) {
                imprimirInfoFeriado(feriado, i);
            }
        }

    }

    static void imprimirInfoFeriado(final String feriado, final int ano) {

        final String feriadoAno = feriado.concat("/").concat(String.valueOf(ano));

        final LocalDate feriadoLocalDate = LocalDate.parse(feriadoAno, dateTimeFormatterIn);

        final String infoFeriado = feriadoLocalDate.format(dateTimeFormatterOut)
                .concat(isFeriadoProlongado(feriadoLocalDate) ? " * feriado prolongado, partiu \u2600 \u26F1 " : "");

        System.out.println(infoFeriado);

    }

    static boolean isFeriadoProlongado(final LocalDate feriado) {
        return feriado.getDayOfWeek().equals(DayOfWeek.TUESDAY)
                || feriado.getDayOfWeek().equals(DayOfWeek.THURSDAY);
    }


}
