package br.com.rraminelli.funcional.callback;

import java.util.function.BiConsumer;

public class FunctionalLambdas {

    public static void main(String[] args) {

        salvar("Rodrigo", new ImprimirResponseCallback());

        salvar("Rodrigo", (response, param) -> {
            System.out.println(response);
        });

        salvar("Rodrigo", FunctionalLambdas::print);

        salvar("Antonio", FunctionalLambdas::print);



    }

    static void print(String str, String param) {
        System.out.println(str);
    }

    static void salvar (final String cliente, final BiConsumer<String, String> callback) {

        final String response = "salvo com sucesso";

        callback.accept(cliente, response);

    }

}
