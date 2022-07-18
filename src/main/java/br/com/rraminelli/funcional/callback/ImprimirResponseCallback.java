package br.com.rraminelli.funcional.callback;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class ImprimirResponseCallback implements BiConsumer<String, String> {

        @Override
        public void accept(String response, String param) {
            System.out.println(response);
        }

}
