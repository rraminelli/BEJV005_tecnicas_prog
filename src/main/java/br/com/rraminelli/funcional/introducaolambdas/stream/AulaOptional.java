package br.com.rraminelli.funcional.introducaolambdas.stream;

import java.util.Optional;

/*
    A criação de um Optional é feita através de uma fábrica (factory) e possui uma sintaxe simples.
    Optional.empty()
    Optional.of(<value>)

 */
public class AulaOptional {

    public static void main(String[] args) {



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


}
