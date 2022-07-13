package br.com.rraminelli.javatime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;
import java.util.Date;
import java.util.Locale;

public class ResumoJavaTime {

    public static void main(String[] args) {

        /*
            Representação do tempo para HUMANOS:
                LocalDate (data), LocalTime (hora), LocalDateTime (data e hora)
         */
        final LocalDateTime localDateTimeNOW = LocalDateTime.now();
        final LocalDateTime localDateTimeOF = LocalDateTime.of(2022,7,8,14,5);
        final LocalDateTime localDateTimePARSE = LocalDateTime.parse("2022-07-08T15:45:43"); //yyyy-MM-ddTHH:mm:ss - formato ISO


        /*
            Formataçao: Analisam e imprimem datas e horas com padrões e em uma variedade de estilos.
                DateTimeFormatter
         */
        //formato diferente da ISO
        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        //de string para LocalDateTIme
        final LocalDateTime localDateTimeFORMATTER = LocalDateTime.parse("07/07/2022 11:15", dateTimeFormatter);
        //de LocalDateTime para string - saida: 07/07/22 11:15:00
        final String dataFormatada =  localDateTimeFORMATTER.format(DateTimeFormatter.ofPattern("dd-MM-yy HH:mm:ss"));


        /*
            Operações com data/hora
         */
        final LocalDate dataNascimento = LocalDate.of(1980, 6, 19);
        dataNascimento.getDayOfWeek(); //dia da semana
        dataNascimento.minusDays(3); // 3 dias antes
        dataNascimento.plusDays(3); // 3 dias depois


        /*
            Fuso Horario: Representa data e hora com informação de fuso horário.
                OffsetDateTime
         */
        //ZonedDateTime - representa uma data e hora contendo um fuso horário (ZoneId), base GMT
        final LocalDateTime dateTimeNOW = LocalDateTime.now();
        final ZonedDateTime zonedDateTimeSP = ZonedDateTime.of(dateTimeNOW, ZoneId.of("America/Sao_Paulo"));
        final ZonedDateTime zonedDateTimeAC = ZonedDateTime.of(dateTimeNOW, ZoneId.of("Brazil/Acre"));
        final ZonedDateTime zonedDateTimePT = ZonedDateTime.of(dateTimeNOW, ZoneId.of("Europe/Lisbon"));


        /*
            Fuso horario
         */
        final LocalDateTime dataFusoHorario = LocalDateTime.now(ZoneId.of("Europe/Lisbon"));


        /*
            Horario de verao
         */
        final ZoneId zoneIdRome = ZoneId.of("Europe/Rome");
        final ZonedDateTime zonedDateTimeRoma = ZonedDateTime.of(LocalDateTime.now(), zoneIdRome);
        boolean horarioVerao = zoneIdRome.getRules().isDaylightSavings(zonedDateTimeRoma.toInstant());


        /*
            Internacionalizando o formato de datas e horas
                Locale
         */
        final String formatoBrasil =
                ZonedDateTime.now().format(
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                                .withLocale(new Locale("pt", "BR")));
        final String formatoEUA =
                ZonedDateTime.now().format(
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                                .withLocale(new Locale("US")));
        final String formatoFranca =
                ZonedDateTime.now().format(
                        DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)
                                .withLocale(Locale.FRENCH));

        /*
            Ajustes de datas e horas
                TemporalAdjusters
         */
        final LocalDateTime localDateTimeAdjusters = LocalDateTime.now();
        final LocalDateTime ultimoDiaDoMes = localDateTimeAdjusters.with(TemporalAdjusters.lastDayOfMonth());
        final LocalDateTime proximoSabado = localDateTimeAdjusters.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        final LocalDateTime ultimaSextaFeiraDoMes = localDateTimeAdjusters.with(TemporalAdjusters.lastInMonth(DayOfWeek.FRIDAY));

        /*
            Calculos de datas e horas
                ChronoUnit
         */
        final LocalDateTime localDateTimeCalcINICIO = LocalDateTime.now();
        final LocalDateTime localDateTimeCalcFIM = localDateTimeCalcINICIO.plusDays(250);
        long anos = ChronoUnit.YEARS.between(localDateTimeCalcINICIO, localDateTimeCalcFIM);
        long meses = ChronoUnit.MONTHS.between(localDateTimeCalcINICIO, localDateTimeCalcFIM);



        /*
            Representação do tempo para MAQUINAS: Representa uma quantidade de tempo
                Period, Duration e Instant
         */
        //Period: Quantidade de tempo em dias, meses e anos
        //** Utiliza apenas data - LocalDate
        final LocalDate localDateINICIO = LocalDate.now();
        final LocalDate localDateFIM = localDateINICIO.plusDays(300);
        final Period periodTempo = Period.between(localDateINICIO, localDateFIM);
        final String periodoResultado = "A atividade foi executada em " +
                periodTempo.getYears() + " anos " +
                periodTempo.getMonths() + " meses e " +
                periodTempo.getDays() + " dias.";


        //Duration: Quantidade de tempo em minutos ou segundos
        //** Utiliza apenas time - LocalTime
        final LocalTime localTimeINICIO = LocalTime.now();
        final LocalTime localTimeFIM = localTimeINICIO.plusHours(2);
        final Duration duration = Duration.between(localTimeINICIO, localTimeFIM);
        final String duracaoResultadoMinutos = "A atividade foi executada em " + duration.toMinutes() + " minutos ";
        final String duracaoResultadoSegundos = "A atividade foi executada em " + duration.toSeconds() + " segundos ";


        //Instant: representando o número de segundos e nanossegundos desde 01/01/1970. Formato numero (long)
        final LocalDateTime localDateTimeINSTANT = LocalDateTime.now();
        final Instant instant = localDateTimeINSTANT.toInstant(ZoneOffset.UTC);
        final long timestamp = instant.getEpochSecond(); //segundos a partir de 01/01/1970



        /*
            Conversoes
         */
        //java.util.Date => java.time.LocalDateTime
        final Date dataJavaUtil = new Date();
        final LocalDateTime dataJavaTime =
                LocalDateTime.ofInstant(dataJavaUtil.toInstant(), ZoneId.systemDefault());


        //java.time.LocalDateTime => java.util.Date
        final LocalDateTime dataJavaTime2 = LocalDateTime.now();
        final Date dataJavaUtil2 =
                Date.from(dataJavaTime2.atZone(ZoneId.systemDefault()).toInstant());


    }

}
