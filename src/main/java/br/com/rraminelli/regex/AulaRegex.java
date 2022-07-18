package br.com.rraminelli.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AulaRegex {

    public static void main(String[] args) {

        /*
            [abc] - qualquer um dos caracteres do grupo
            [^abc] - qualquer caracter DIFERENTE do grupo
            [0-9] - qualquer caracter entre o intervalo
            | - uma das palavras presentes no grupo one|two|three
            . - apenas uma vez o valor: .X
            ^ - uma string que começa com: ^Hello
            $ - uma string que termina com: World$
            \d - um digito, igual a [0-9]
            \s - um espaço
            \b - ancora - um elemento dentro da string. Inicio: \bWORD, ou no final: WORD\b
            \\uxxxx - caractere unicode

            Qualificadores:
            ? - zero ou uma ocorrência;
            * - zero ou mais ocorrências;
            + - uma ou mais ocorrências;
            {n, m} - de n até m ocorrências. [0-9]{1,3}

         */

        //final Pattern pattern = Pattern.compile("\\bLets Code([\\sa-zA-Z]+)2022$");
        //final Pattern pattern = Pattern.compile("\\s[a-zA-Z]+");
        //final Matcher matcher = pattern.matcher("Curso Lets Code e Banco do Brasil 2022");

        final Matcher matcher = Pattern
                .compile("(^\\+\\d{2}\\([0-9]{2}\\)\\s)(9?[0-9]{4})-([0-9]{4})")
                        .matcher("+55(11) 92345-1234");

        //System.out.println("Resultado: " + matcher.find());

        //Retorna os valores encontrados
        if (matcher.find()) {
            for (int i = 0; i <= matcher.groupCount(); i++) {
                System.out.println("Group: " + matcher.group(i));
            }
        }


        /*while (matcher.find()) {
            System.out.println("Group: " + matcher.group(i++));
        }*/



    }

}
