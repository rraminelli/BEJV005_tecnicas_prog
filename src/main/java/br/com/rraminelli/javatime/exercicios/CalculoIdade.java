package br.com.rraminelli.javatime.exercicios;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Locale;

/*
    A partir de uma lista de pessoas (nome e data de nascimento no formato yy-mm-dd),
    calcular a idade de cada pessoa e também quem é o mais novo e o mais velho.

Imprimir a lista de pessoas no formato:

Nome - data de nascimento (dd/mm/yyyy) - dia da semana do nascimento - idade.

** Utilizar a API java.time

Exemplo:

Pessoas: [Roberto:21-07-08,Ricardo:20-07-08]
Saida:
Roberto - 08/07/2021 - Quinta Feira - 1 ano
Ricardo - 08/07/2020 - Quarta Feira - 2 anos
 */

public class CalculoIdade {

    public static void main(String[] args) {
        new CalculoIdade().exec();
    }

    void exec() {

        final List<Pessoa> pessoaList = List.of(
                new Pessoa("Rodrigo", "84-06-19"),
                new Pessoa("Pedro", "13-12-17"),
                new Pessoa("Giovanna", "16-12-12")
        );

        final int anoAtual = Year.now().getValue();

        final Locale localeBrasil = new Locale("pt", "BR");


        //yy-MM-dd
        final DateTimeFormatter dataNascimentoFormatterIn = new DateTimeFormatterBuilder()
                .appendValueReduced(ChronoField.YEAR, 2, 2, anoAtual - 99) // 1923 a 2022, maximo 99 anos
                .appendPattern("-MM-dd")
                .toFormatter(localeBrasil);


        final DateTimeFormatter dataNascimentoFormatterOut =
                DateTimeFormatter.ofPattern("dd/MM/yyyy - EEEE", localeBrasil);


        for (Pessoa pessoa : pessoaList) {
            final LocalDate dataNascimentoParse = LocalDate.parse(pessoa.dataNascimento, dataNascimentoFormatterIn);
            final long idade = this.getIdade(dataNascimentoParse);
            final String dataNascimentoFormatada = dataNascimentoFormatterOut.format(dataNascimentoParse);

            System.out.println(pessoa.nome + " - " + dataNascimentoFormatada + " - " + idade);
        }

    }

    long getIdade(final LocalDate dataNascimento) {
        final LocalDate hoje = LocalDate.now();

        final long idade = ChronoUnit.YEARS.between(dataNascimento, hoje);

        return idade;
    }

    class Pessoa {
        String nome;
        String dataNascimento;
        Pessoa (String nome, String dataNascimento) {
            this.nome = nome;
            this.dataNascimento = dataNascimento;
        }
    }
}
