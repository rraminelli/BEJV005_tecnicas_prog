package br.com.rraminelli.funcional.exercicios;

import br.com.rraminelli.model.Cliente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ExercicioClienteFilter {

    public static void main(String[] args) {

        final List<Cliente> clientes = List.of(
                new Cliente("José", LocalDate.parse("1980-06-01"), Cliente.Sexo.M),
                new Cliente("Mariana", LocalDate.parse("1990-06-01"), Cliente.Sexo.F),
                new Cliente("Vitoria", LocalDate.parse("1987-10-01"), Cliente.Sexo.F),
                new Cliente("Ricardo", LocalDate.parse("2000-10-01"), Cliente.Sexo.M)
        );

        clientes.parallelStream().forEach(cliente -> {
            System.out.println("Print: " + cliente);
        });

        String nomes = "";
        for (Cliente cliente: clientes) {
            //nomes += cliente.getNome();
            nomes = "";
        }




        VerificadorCliente verificadorCliente = cliente -> cliente.getSexo().equals(Cliente.Sexo.F);

        VerificadorCliente verificadorCliente2 = new VerificadorCliente() {
            @Override
            public boolean verificar(Cliente cliente) {
                return cliente.getSexo().equals(Cliente.Sexo.F);
            }
        };

        final List<Cliente> listaFiltrada = filtrar(clientes, Cliente::isFeminino);
        listaFiltrada.forEach(System.out::println);

        final List<Cliente> listaFiltrada2 = filtrar(
                clientes,
                cliente -> {
                    return cliente.getSexo().equals(Cliente.Sexo.F);
                });
        listaFiltrada2.forEach(System.out::println);


        filtrar(clientes, cliente -> cliente.getSexo().equals(Cliente.Sexo.M)).forEach(System.out::println);

        System.out.println("Maior de 35 - M");
        filtrar(clientes, cliente -> {
            return ChronoUnit.YEARS.between(cliente.getDataNascimento(), LocalDate.now()) > 35 &&
                    cliente.getSexo().equals(Cliente.Sexo.M);
        }).forEach(System.out::println);


        filtrar(clientes, cliente -> cliente.getSexo().equals(Cliente.Sexo.M))
                .forEach(cliente -> {

                });

        Predicate<Cliente> predicate = new Predicate<Cliente>() {
            @Override
            public boolean test(Cliente cliente) {
                return cliente.getSexo().equals(Cliente.Sexo.M);
            }
        };

        clientes.stream().filter(predicate).findFirst();

        Consumer<Cliente> consumer = new Consumer<Cliente>() {
            @Override
            public void accept(Cliente s) {
                System.out.println(s);
            }
        };
        clientes.forEach(consumer);

        clientes.forEach(cliente -> System.out.println(cliente));

        clientes.forEach(System.out::println);

    }

    static List<Cliente> filtrar(List<Cliente> clientes, Predicate<Cliente> verificadorCliente) {

        return clientes
                .stream()
                .filter(verificadorCliente)
                .collect(Collectors.toList());

        /*final List<Cliente> listaFiltrada = new ArrayList<>();
        for (Cliente cliente : clientes) {
            if (verificadorCliente.test(cliente)) {
                listaFiltrada.add(cliente);
            }
        }
        return listaFiltrada;*/
    }

}
