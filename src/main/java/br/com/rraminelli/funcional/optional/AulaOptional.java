package br.com.rraminelli.funcional.optional;

import java.util.Optional;

/*
    A criação de um Optional é feita através de uma fábrica (factory) e possui uma sintaxe simples.
    Optional.empty()
    Optional.of(<value>)
    Optional.ofNullable(<value>) - aceita objeto null
 */
public class AulaOptional {

    public static void main(String[] args) {

        Double media = mediaDouble();

        Optional<Double> mediaOpt = media();

    }

    static Optional<Double> media(int... valores) {
        if (valores.length == 0) {
            return Optional.empty();
        }

        int soma = 0;
        for (int valor : valores) {
            soma += valor;
        }

        return Optional.of((double) soma / valores.length);
        //return Optional.ofNullable((double) soma / valores.length);
    }

    static Double mediaDouble(int... valores) {
        if (valores.length == 0) {
            return null;
        }

        int soma = 0;
        for (int valor : valores) {
            soma += valor;
        }

        return (double) soma / valores.length;
    }


}
