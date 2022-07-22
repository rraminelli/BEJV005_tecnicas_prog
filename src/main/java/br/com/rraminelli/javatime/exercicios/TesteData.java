package br.com.rraminelli.javatime.exercicios;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TesteData {

    public static void main(String[] args) {

        ZoneId zoneIdSP = ZoneId.of("America/Sao_Paulo");
        ZoneId zoneIdParis = ZoneId.of("Europe/Paris");

        final ZonedDateTime zonedDateTimeSP = ZonedDateTime.of(LocalDateTime.parse("2022-07-19T10:00"), zoneIdSP);

        final ZonedDateTime zonedDateTimeParis = zonedDateTimeSP.withZoneSameInstant(zoneIdParis);

        System.out.println(zonedDateTimeParis);

    }

}
