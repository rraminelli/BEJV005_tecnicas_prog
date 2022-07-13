package br.com.rraminelli.funcional.introducaolambdas.callback;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

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
