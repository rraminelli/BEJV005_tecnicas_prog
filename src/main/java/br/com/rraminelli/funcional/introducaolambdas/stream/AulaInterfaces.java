package br.com.rraminelli.funcional.introducaolambdas.stream;

/*
    FINAL: https://www.baeldung.com/java-final-performance
 */

import br.com.rraminelli.funcional.introducaolambdas.model.Animal;
import br.com.rraminelli.funcional.introducaolambdas.model.Cliente;

import java.time.LocalDate;
import java.util.function.*;

public class AulaInterfaces {


    public static void main(String[] args) {

        // Supplier: é usado para gerar ou fornecer valores sem uso de parâmetros.
        Supplier<String> supplier = () -> "Curso Lets Code";
        String s = supplier.get();

        //Consumer e BiConsumer: é usado para processar ou consumir um parâmetro sem retornar nenhum valor (void).
        Consumer<String> consumer1 = new Consumer<String>() {
            @Override
            public void accept(String string) {
                System.out.println(string);
            }
        };
        Consumer<String> print = (string) -> System.out.println(string);
        print.accept(supplier.get());

        BiConsumer<LocalDate, String> printData = (data, nome) -> System.out.println("Ola, " + nome + ". Hoje é " + data);

        printData.accept(LocalDate.now(), "Rodrigo");
        printData.accept(LocalDate.now(), "Teste 2");
        printData.accept(LocalDate.now(), "Teste 3");

        //Predicate e BiPredicate: é usado frequentemente para filtrar ou identificar padrões de dados.
        /*Predicate<String> predicate1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };*/
        Predicate<String> predicate1 = (str) -> !str.isEmpty();
        Predicate<String> predicate2 = (str) -> str.startsWith("X");
        Predicate<String> predicate3 = predicate1.negate();

        System.out.println("é vazio? " + predicate3.test("XX") );

        System.out.println("Teste predicate1 e 2: " +
                predicate1.and(predicate2).test("YYYXXXX"));

        BiPredicate<String, LocalDate> biPredicate = (nome, data) -> {
            return nome.isEmpty() || data == null;
        };
        System.out.println("Teste bi predicate: " + biPredicate.test("valor", null));



        //Function e BiFunction: é responsável por receber um parâmetro de entrada e transformá-lo em um potencial tipo de dado diferente e retorná-lo como saída.
        Function<LocalDate, String> function1 = new Function<LocalDate, String>() {
            @Override
            public String apply(LocalDate data) {
                return "Hoje é " + data.getDayOfWeek();
            }
        };
        Function<LocalDate, String> function = (data) -> {
            return "Hoje é " + data.getDayOfWeek();
        };
        System.out.println( function.apply(LocalDate.now()) );


        BiFunction<Cliente, Animal, String> biFunction = (cliente, animal) -> {
            return "Cliente: " + cliente + "\n" + "Animal: " + animal;
        };
        print.andThen(print).andThen(print).accept(biFunction.apply(
                new Cliente("Cliente1", LocalDate.now(), Cliente.Sexo.F),
                new Animal("Gato", false)
        ));





        //Metodos adicionais
    }



}
