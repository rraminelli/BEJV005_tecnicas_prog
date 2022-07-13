package br.com.rraminelli.funcional.introducaolambdas.stream;

import br.com.rraminelli.funcional.introducaolambdas.model.Cliente;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
    https://www.oracle.com/br/technical-resources/articles/java/processing-streams-java-se-8.html
 */
public class AulaStream {

    public static void main(String[] args) {

        // [FONTE] -> {Operaçoes Intermediarias} -> [Operaçoes terminais]

        final List<Cliente> clientes = List.of(
                new Cliente("José", LocalDate.parse("1980-06-01"), Cliente.Sexo.M),
                new Cliente("Mariana", LocalDate.parse("1990-06-01"), Cliente.Sexo.F),
                new Cliente("Vitoria", LocalDate.parse("1987-10-01"), Cliente.Sexo.F),
                new Cliente("Ricardo", LocalDate.parse("2000-10-01"), Cliente.Sexo.M)
        );

        Predicate<Cliente> predicate1990 = (cliente) -> cliente.getDataNascimento().getYear() == 1990;
        Predicate<Cliente> predicate2000 = (cliente) -> cliente.getDataNascimento().getYear() == 2000;
        Predicate<Cliente> predicates = predicate1990.or(predicate2000);

        Predicate<Cliente> predicate1990or2000 = (cliente) -> {
            int anoNasc = cliente.getDataNascimento().getYear();
            return anoNasc == 1990 || anoNasc == 2000;
        };

        List<LocalDate> nomes = clientes.parallelStream()
                .filter(Cliente::isFeminino)
                .filter(predicate1990or2000)
                .sorted(Comparator.comparing(Cliente::getNome))
                .limit(2)
                .map(cliente -> cliente.getDataNascimento())
                .collect(Collectors.toList());


        //Fonte de dados - Stream
        final Stream<Integer> usandoVarrags = Stream.of(1, 2, 3);


        //Fonte de dados - Collections


    }

}
